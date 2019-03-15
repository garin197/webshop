package com.github.webshop.controller;

import com.github.webshop.dao.AdminMapper;
import com.github.webshop.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    @ResponseBody
    @RequestMapping("/login")
    public String test(){
        Admin admin=new Admin();
        admin.setId(1);
        admin.setAdminName("lin2");
        admin.setPassword("21");
        //插入
//        int a=adminMapper.add(admin);
//        adminMapper.delete(2);
        adminMapper.update(admin);
        return "";
    }



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
