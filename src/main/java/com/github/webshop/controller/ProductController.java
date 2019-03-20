package com.github.webshop.controller;

import com.github.webshop.service.ProductService;
import com.github.webshop.util.HashMapUtil;
import com.github.webshop.util.MyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:upload-config.properties")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    private static Logger logger = Logger.getLogger(ProductController.class);
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @ResponseBody
    @RequestMapping("/del")
    public Map<String, Object> del(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Map result = MyUtil.successOrfailed(productService.delProduct(request));
        return result;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        List list = productService.get_img_product_category_list(request);
        Map result = HashMapUtil.getFormatMap();
        result.put("data", list);
        return result;
    }

    /**
     * 添加商品记录
     *
     * @param request
     * @return 操作状态代码和插入记录的主键id值
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/add")
    public Map<String, Object> add(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Map<String, Object> result = HashMapUtil.getFormatMap();
        result.put("productId", productService.addProduct(request));
        return result;//返回productId
    }

    /**
     * @param file    传过来的文件信息
     * @param id      productId
     * @param regex   封面的标志
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/addImg")
    public Map<String, Object> addImg(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id, @RequestParam("type") String regex, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Integer imgType = 0;        //图片是否为封面的标记
        String fileName=null;       //传过来原文件名
        File newFile=null;          //实际保存的文件路径
        String realFileName=null;   //实际保存的文件名，做uuid处理
        if (!file.isEmpty()) {
            //tomcat中的‘/upload’文件夹的目录  //有坑 启用
            //String realUploadPath = request.getSession().getServletContext().getRealPath("/upload");//获取servlet容器里/upload文件夹的路径

            //获取原文件名
            fileName = file.getOriginalFilename();

            //创建完整路径的文件，判断完整目录下的其父目录是否存在
            //判断路径是否存在,不存在则创建一个目录 mkdirs建立多级目录
            newFile = new File(uploadFolder + "/", fileName);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }

            //将上传的文件保存在目录中
            realFileName = MyUtil.getUUID() + '_' + fileName;
            file.transferTo(new File(uploadFolder + realFileName));
            logger.info("上传文件信息：" + uploadFolder+realFileName);

            //同步到数据库
            if (MyUtil.isIndexImg(fileName, regex)) {
                imgType = 1;
            }

        }
        Map<String, Object> result = MyUtil.successOrfailed(productService.addImage(id, imgType, realFileName));//只存加uuid的文件名.具体路径配置文件中file.uploadFolder配置了
        return result;//返回productIdsy
    }
}
