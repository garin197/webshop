package com.github.webshop.controller;

import com.github.webshop.pojo.CartVo;
import com.github.webshop.pojo.Product;
import com.github.webshop.service.ProductService;
import com.github.webshop.util.CookieUtil;
import com.github.webshop.util.HashMapUtil;
import com.github.webshop.util.MyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品控制器
 */
@Controller
@PropertySource("classpath:upload-config.properties")
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = Logger.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    //配置的实际上传路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * post请求我的订单信息
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/myorders")
    public Map<String ,Object> myorders(HttpServletRequest request,HttpSession session){
        Map result=HashMapUtil.getFormatMap();
        result.put("data",productService.getOrderItemList(session,request));
        return result;
    }


    /**
     * 立即购买
     *
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/buy")
    public String buy(HttpServletRequest request, HttpSession session) throws Exception {
        int n=productService.buy_one(request,session);
        if (n>0){
            return "success";
        }
        return "falied";
    }

    /**
     * 获取购物车列表
     *
     * @param request
     * @param response
     * @return 购物车列表
     * @throws UnsupportedEncodingException 抛出异常
     */
    @ResponseBody
    @PostMapping("/getCart")
    public List<CartVo> getCart(HttpServletRequest request, HttpServletResponse response) throws
            UnsupportedEncodingException {
        return CookieUtil.getCartInCookie(response, request);
    }

    /**
     * 清空购物车
     *
     * @param response
     * @param request
     * @return 成功与否
     */
    @ResponseBody
    @PostMapping("/deleteAllCookie")
    public String deleteCookie(HttpServletResponse response, HttpServletRequest request) {
        // 获取名为"cart"的cookie
        Cookie cookie = CookieUtil.getCookie(request, "cart");
        // 设置寿命为0秒
        cookie.setMaxAge(0);
        // 设置路径
        cookie.setPath("/");
        // 设置cookie的value为null
        cookie.setValue(null);
        // 更新cookie
        response.addCookie(cookie);
        return "success";
    }


    /**
     * 加入购物车
     *
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/add_cart/{pid}")
    public String add_cart(HttpServletRequest request, @PathVariable("pid") Integer productId, HttpServletResponse response) throws UnsupportedEncodingException {

        // 从cookie中获取购物车列表
        List<CartVo> cartVos = (List<CartVo>) CookieUtil.getCartInCookie(response, request);///////
        Cookie cookie_2st;
        // 如果购物车列表为空
        if (cartVos.size() <= 0) {
            // 根据商品ID获取商品信息
            CartVo cartVo = requset_function_for_add_cart(request, productId);

//            CartVo cartVo = new CartVo(); // 测试用，实际应当根据id获取

            // 将当前传来的商品添加到购物车列表
            cartVos.add(cartVo);
            if (CookieUtil.getCookie(request, "cart") == null) {
                // 制作购物车cookie数据
                cookie_2st = new Cookie("cart", URLEncoder.encode(CookieUtil.makeCookieValue(cartVos), "utf-8"));
                cookie_2st.setPath("/");//设置在该项目下都可以访问该cookie
                cookie_2st.setMaxAge(60 * 30);//设置cookie有效时间为30分钟
                response.addCookie(cookie_2st);//添加cookie
            } else {
                cookie_2st = CookieUtil.getCookie(request, "cart");
                cookie_2st.setPath("/");//设置在该项目下都可以访问该cookie
                cookie_2st.setMaxAge(60 * 30);//设置cookie有效时间为30分钟
                cookie_2st.setValue(URLEncoder.encode(CookieUtil.makeCookieValue(cartVos)));
                response.addCookie(cookie_2st);//添加cookie
            }
        }
        // 当获取的购物车列表不为空时
        else {
            int bj = 0;
            for (CartVo cart : cartVos) {
                // 如果购物车中存在该商品则数量+1
                if (cart.getProductId().equals(productId)) {
                    cart.setNum(cart.getNum() + 1);
                    bj = 1;
                    break;
                }
                // TODO: 2019/4/3 数据库信息同步 -- 库存同步

            }
            if (bj == 0) {
                // 根据商品ID获取商品信息
                CartVo cartVo = requset_function_for_add_cart(request, productId);

                // 将当前传来的商品添加到购物车列表
                cartVos.add(cartVo);
            }
            // 获取名为"cart"的cookie
            cookie_2st = CookieUtil.getCookie(request, "cart");
            cookie_2st.setPath("/");//设置在该项目下都可以访问该cookie
            cookie_2st.setMaxAge(60 * 30);//设置cookie有效时间为30分钟
            cookie_2st.setValue(URLEncoder.encode(CookieUtil.makeCookieValue(cartVos))); // 设置value
            response.addCookie(cookie_2st);//添加cookie
            return "success";
        }
        return "failed";
    }

    private CartVo requset_function_for_add_cart(HttpServletRequest request, Integer productId) {
        CartVo cartVo = new CartVo(); // 测试用，实际应当根据id获取
        //String productId=request.getParameter("pid");
        String productName = request.getParameter("pname");
        String money = request.getParameter("money");
        String num = request.getParameter("num");
        String imgUrl = request.getParameter("imgUrl");
        String orderCartCode = MyUtil.getDateId();
        String addDate = MyUtil.getFormatDate();
        String uri = request.getParameter("uri");
        cartVo.setImgUrl(imgUrl);
        cartVo.setMoney(new Float(money));
        cartVo.setProductId(productId);
        cartVo.setProductName(productName);
        cartVo.setNum(new Integer(num));
        cartVo.setAddDate(addDate);
        cartVo.setOrderCartCode(orderCartCode);
        cartVo.setProductUri(uri);
        return cartVo;
    }

    /**
     * 无用
     *
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping("/forecheckLogin")
    public String islogin(HttpSession session) {

        return "false";
    }

    /**
     * 更新数据库的库存
     *
     * @param pid
     * @param number
     */
    public synchronized void update_stock(Integer pid, Integer number) {
        productService.update_stock(pid, number);
    }

    /**
     * 立即购买，前提：已登录用户
     *
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/forebuyone")
    public Map<String, Object> forebuyone(HttpServletRequest request) {

        return null;
    }

    /**
     * 显示商品详情__前台
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/index_product_detail")
    public Map<String, Object> index_product_detail(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
//        List list = productService.get_product_detail(request);
        Map result = HashMapUtil.getFormatMap(productService.getRowCount());
//        result.put("data", list);
        return result;
    }

    /**
     * 显示商品详情页面__前台
     *
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @GetMapping("/index_product_detail")
    public String get_index_product(HttpServletRequest request, Map map) throws Exception {
        request.setCharacterEncoding("utf-8");
        Product product = productService.get_product_detail(request);
        map.put("product", product);
        return "product-detail";
    }

    /**
     * 主页商品预展示__前台
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/index_product_list")
    public Map<String, Object> index_product_list(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        List list = productService.get_index_product(request);
        Map result = HashMapUtil.getFormatMap(productService.getRowCount());
        result.put("data", list);
        return result;
    }

    @ResponseBody
    @PostMapping("/bs-index-product-property-manage-getpName")
    public Map<String, Object> bs_index_product_property_manage_getpName(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Map result = new HashMap();
        result.put("productName", productService.getProductNameById(request));
        return result;
    }

    /**
     * 库存排序__后台模块
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("stocksort")
    public Map<String, Object> stocksort(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        List list = productService.sort_stock(request);
        Map result = HashMapUtil.getFormatMap(list.size());
        result.put("data", list);
        return result;
    }

    /**
     * 搜索功能_后台板块
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/search")
    public Map<String, Object> search(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        List list = productService.getProductListByStatement(request);
        Map result = HashMapUtil.getFormatMap(list.size());
        result.put("data", list);
        return result;
    }

    /**
     * 修改功能_后台板块
     *
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/edit")
    public Integer edit(HttpServletRequest request) {
        int result = 0;
        try {
            request.setCharacterEncoding("utf-8");
            result = productService.updateProduct(request);
        } catch (Exception e) {
            logger.info("修改数据库失败*数据格式错误");
        }
        return result;
    }

    /**
     * 查看所有图片信息___后台+前台
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/imgList")
    public Map<String, Object> imgList(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Map result = HashMapUtil.getFormatMap();
        result.put("data", productService.getImgList(request));
        return result;
    }

    /**
     * 删除功能__后台板块
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/del")
    public Map<String, Object> del(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Map result = MyUtil.successOrFailed(productService.delProduct(request));
        return result;
    }

    /**
     * 获取封面（type=1）以及商品和属性等
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        List list = productService.get_img_product_category_list(request);
        Map result = HashMapUtil.getFormatMap(productService.getRowCount());
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
     * 添加图片_图片上传至服务器（或指定路径）_后台板块
     *
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
        String fileName = null;       //传过来原文件名
        File newFile = null;          //实际保存的文件路径
        String realFileName = null;   //实际保存的文件名，做uuid处理
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
            logger.info("上传文件信息：" + uploadFolder + realFileName);

            //检查是不是封面图片，并且同步到数据库
            if (MyUtil.isIndexImg(fileName, regex)) {
                imgType = 1;
            }

        }
        Map<String, Object> result = MyUtil.successOrFailed(productService.addImage(id, imgType, realFileName));//只存加uuid的文件名.具体路径配置文件中file.uploadFolder配置了
        return result;//返回productIdsy
    }
}
