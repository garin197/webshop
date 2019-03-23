package com.github.webshop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/page")
public class PageController {

    private Logger logger = Logger.getLogger(PageController.class);



    @RequestMapping("")
    public String index_(HttpSession session) {
        logger.info("访问主页-页面");
        session.setAttribute("currentAdmin","jkfjd");
        return "index";
    }


    @RequestMapping("/imgDetail")
    public String imgDetail(Map map,@RequestParam(value = "productId",required = false) Integer productId){
        map.put("productId",productId);
        return "backstage/bs-index-imgDetail";
    }

    @RequestMapping("/login")
    public String passport() {
        return "user-login";
    }

    @RequestMapping("/iframe-table1-add")
    public String iframe_table1_add() {
        return "backstage/bs-index-iframe-table1-add";
    }

//    @ResponseBody
    @RequestMapping("/iframe-table2-property-manage")
    public String iframe_table2_category_manage(@RequestParam(value = "id") Integer category,Map map) {
        map.put("category",category);
        return "backstage/bs-index-iframe-table2-property-manage";
    }

    @RequestMapping("/index")
    public String index() {
        logger.info("访问主页-页面");
        return "index";
    }

    @RequestMapping("/err")
    public String error() {
        return "404";
    }
}
