package com.github.webshop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/page")
public class PageController {

    private Logger logger = Logger.getLogger(PageController.class);

    /**
     * 我的订单
     * @return
     */
    @GetMapping("/myorders")
    public String myorders(){

        return "myorders";
    }

    @GetMapping("/shopingcart")
    public String shopingcart(HttpServletRequest request){

        // TODO: 2019/4/3 登录检查 
        return "shoppingCart";
    }

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

    @GetMapping("/bglogin")
    public String bg_login() {
        return "backstage/bs-login";
    }

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

    @GetMapping("/index")
    public String index() {
        logger.info("访问主页-页面");
        return "index";
    }

    @GetMapping("/err")
    public String error() {
        return "404";
    }
}
