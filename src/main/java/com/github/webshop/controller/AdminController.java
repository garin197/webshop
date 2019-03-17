package com.github.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {




    /**
     * 检查登录
     * @param response
     * @param session
     */
    @ModelAttribute
    public void isLogin(HttpServletResponse response, HttpSession session) throws Exception {
        if (session.getAttribute("currentAdmin")==null){
//            throw new AdminInVaildLoginException("ADMIN / 请您先登录！(Please login first!)");
            response.sendRedirect("/err");
        }
    }
}
