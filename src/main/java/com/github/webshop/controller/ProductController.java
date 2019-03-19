package com.github.webshop.controller;

import com.github.webshop.service.ProductService;
import com.github.webshop.util.HashMapUtil;
import com.github.webshop.util.MyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    private static Logger logger=Logger.getLogger(ProductController.class);

    @ResponseBody
    @RequestMapping("/list")
    public Map<String,Object> list(HttpServletRequest request)throws Exception{
        request.setCharacterEncoding("utf-8");
        List list=productService.get_img_product_category_list(request);
        Map result=HashMapUtil.getFormatMap();
        result.put("data",list);
        return result;
    }

    /**
     * 添加商品记录
     * @param request
     * @return 操作状态代码和插入记录的主键id值
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/add")
    public Map<String,Object> add(HttpServletRequest request)throws Exception{
        request.setCharacterEncoding("utf-8");
        Map<String,Object> result= HashMapUtil.getFormatMap();
        result.put("productId",productService.addProduct(request));
        return result;//返回productId
    }

    /**
     * @param file 传过来的文件信息
     * @param id productId
     * @param regex 封面的标志
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addImg")
    public Map<String,Object> addImg(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id, @RequestParam("type") String regex,HttpServletRequest request)throws Exception{
        request.setCharacterEncoding("utf-8");
        int flag=0;
        Integer imgType=0;
        if (!file.isEmpty()){
            //tomcat中的‘/upload’文件夹的目录
            String realUploadPath = request.getSession().getServletContext().getRealPath("/upload");//获取servlet容器里/upload文件夹的路径
            //获取文件名
            String fileName=file.getOriginalFilename();
            //创建完整路径的文件，判断完整目录下的其父目录是否存在
            File newFile=new File(realUploadPath+"/",fileName);
            //判断路径是否存在,不存在则创建一个目录 mkdirs建立多级目录
            if (!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            //将上传的文件保存在目录中
            String realFile=realUploadPath+File.separator+MyUtil.getUUID()+'_'+fileName;
            file.transferTo(new File(realFile));
            logger.info("上传文件的路径："+realFile);
            //同步到数据库
            if (MyUtil.isIndexImg(fileName,regex)){
                imgType=1;
            }
            flag=productService.addImage(id,imgType,realFile);
        }
        Map<String,Object> result=null;
        if (flag>0){
            result=HashMapUtil.getFormatMap("success");
        }else{
            result=HashMapUtil.getFormatMap("failed");
        }
        return result;//返回productId
    }
}
