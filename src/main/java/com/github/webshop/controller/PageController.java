package com.github.webshop.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/page")
public class PageController {

    private Logger logger = Logger.getLogger(PageController.class);

    @RequestMapping("")
    public String t(HttpSession session) {
        logger.info("访问后台管理-页面");
        session.setAttribute("currentAdmin","jkfjd");
        return "/backstage/bs-index";
    }

    @ResponseBody
    @RequestMapping("/data")
    public Map d() {
        JSONObject map=new JSONObject();
        map.put("id","10003");
        map.put("username","王勃");
        map.put("email", "xianxin@layui.com");
        map.put("sex", "男");
        map.put("city", "浙江杭州");
        map.put("sign", "人生恰似一场修行");
        map.put("experience", "65");
        map.put("ip", "192.168.0.8");
        map.put("logins", "106");
        map.put("joinTime", "2016-10-14");
        Map result=new HashMap<String,Object>();
        result.put("code",0);
        result.put("message","");
        result.put("count",1000);
        result.put("data",map);
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(map);
        result.put("data",jsonArray);
        return result;
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
        return "index";
    }

    @RequestMapping("/err")
    public String error() {
        return "404";
    }
}
