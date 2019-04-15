package com.github.webshop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 控制页面跳转、页面中转控制器
 */
@Controller
@RequestMapping("/page")
public class PageController {

    private Logger logger = Logger.getLogger(PageController.class);

//    //买家评论页中转
//    @GetMapping("/review/{pid}")
//    public String rev(Map map,
//                      @PathVariable("pid") Integer pid, @RequestParam("uid") Integer uid,//商品id、用户id
//                      @RequestParam("ordercreatedate") String ordercreatedate,@RequestParam("productName") String productName) {//订单创建日期、商品名称
//        map.put("pid",pid);
//        map.put("uid",uid);
//        map.put("productName",productName);
//        map.put("ordercreatedate",ordercreatedate);
//        return "product-review.html";
//    }

    //搜索结果页面中转
    @GetMapping("/s_by_prdct/{s}")
    public String s_prdct(Map map, @PathVariable("s") String s) {
        map.put("s", s);
        return "OnSearchResultPage";
    }

    //分类结果页面中转
    @GetMapping("/s_by_cat/{c}")
    public String s_by_cat(Map map, @PathVariable("c") String regex) {
        map.put("c", regex);
        return "OnSearchCategoryResultPage";
    }

    //我的订单页面中转
    @GetMapping("/myorders")
    public String myorders() {

        return "myorders";
    }

    //购物车页面中转
    @GetMapping("/shopingcart")
    public String shopingcart(HttpServletRequest request) {
        return "shoppingCart";
    }

    // 主页中转
    @GetMapping("")
    public String index_(HttpSession session) {
        logger.info("访问主页-页面");
        session.setAttribute("currentAdmin", "jkfjd");
        return "index";
    }

    @GetMapping("/imgDetail")
    public String imgDetail(Map map, @RequestParam(value = "productId", required = false) Integer productId) {
        map.put("productId", productId);
        return "backstage/bs-index-imgDetail";
    }

    //后台登录中转
    @GetMapping("/bglogin")
    public String bg_login() {
        return "backstage/bs-login";
    }

    //前台登录跳转
    @GetMapping("/login")
    public String passport() {
        return "user-login";
    }

    @GetMapping("/iframe-table1-add")
    public String iframe_table1_add() {
        return "backstage/bs-index-iframe-table1-add";
    }

    @GetMapping("/index-iframe-table1-property-manage")
    public String index_iframe_table1_property_manage(HttpServletRequest request, Map map) throws Exception {
        request.setCharacterEncoding("utf-8");
        map.put("pid", request.getParameter("pid"));
        map.put("cid", request.getParameter("cid"));
        return "index-iframe-table1-property-manage";
    }

    @RequestMapping("/iframe-table2-property-manage")
    public String iframe_table2_category_manage(@RequestParam(value = "id") Integer category, Map map) {
        map.put("category", category);
        return "backstage/bs-index-iframe-table2-property-manage";
    }

    //前台主页中转
    @GetMapping("/index")
    public String index() {
        logger.info("访问主页-页面");
        return "index";
    }

    //错误页中转
    @GetMapping("/err")
    public String error() {
        return "404";
    }
}
