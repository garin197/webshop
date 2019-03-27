package com.github.webshop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = Logger.getLogger(AdminController.class);


    @RequestMapping("")
    public String t(HttpSession session) {
        logger.info("访问后台管理-页面");
//        session.setAttribute("currentAdmin","jkfjd");
        return "/backstage/bs-index";
//        return "product-detail";
    }

    //响应没登录，先模拟登录-后面加登录
    @GetMapping("/err")
    public String adminlogin(HttpSession session){
//        session.setAttribute("currentAdmin","haha");
        //加cookie
        return "redirect:/admin";
    }

    @ResponseBody
    @PostMapping("/islogin")
    public boolean islogin(HttpSession session){
        String currentAdmin= (String) session.getAttribute("currentAdmin");
        if (currentAdmin==null) {
            return false;
        }
        return true;
    }

    /**
     * 检查登录
     * @param response
     * @param session
     */
    @ModelAttribute
    public void isLogin(HttpServletResponse response, HttpSession session) throws Exception {
//        if (session.getAttribute("currentAdmin")==null){
////            throw new AdminInVaildLoginException("ADMIN / 请您先登录！(Please login first!)");
//            response.sendRedirect("/admin/err");
//        }
    }
}
