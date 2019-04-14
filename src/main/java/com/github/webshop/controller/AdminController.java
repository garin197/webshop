package com.github.webshop.controller;

import com.github.webshop.pojo.Admin;
import com.github.webshop.service.AdminService;
import com.github.webshop.service.UserService;
import com.github.webshop.util.HashMapUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    private Logger logger = Logger.getLogger(AdminController.class);


    //获取用户列表--后台模块
    @ResponseBody
    @RequestMapping("/list/all")
    public Map<String ,Object> userlist(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "limit", required = false) Integer limit){
        Map result= HashMapUtil.getFormatMap(userService.get_rows_count());
        result.put("data",userService.get_all_user_list_pagination( page, limit));
        return result;
    }

    @ResponseBody
    @PostMapping("/adlogin")
    public Map<String,Object> adlogin(HttpSession session, HttpServletRequest request){
        Map result=new HashMap();
        Admin currentAdmin=adminService.FindAdmin(request);
        if (currentAdmin == null) {
            result.put("msg","账号或密码错误");
            result.put("code","0");
        }else {
            result.put("code","1");
            session.setAttribute("currentAdmin",currentAdmin.getAdminName());
        }
        return result;
    }

    /**
     * 管理员退出
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("currentAdmin");
        return "success";
    }

    @RequestMapping("")
    public String t(HttpSession session) {
        logger.info("访问后台管理-页面");
        return "backstage/bs-index";
    }

    //响应没登录，先模拟登录-后面加登录
    @GetMapping("/err")
    public String adminlogin(HttpSession session){
//        session.setAttribute("currentAdmin","haha");
        //加cookie
        return "redirect:/admin";
    }

    /**
     * 判断是否登录
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/islogin")
    public String islogin(HttpSession session){
        String currentAdmin= (String) session.getAttribute("currentAdmin");
        if (currentAdmin==null) {
            return "false";
        }
        return "true";
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
//            response.sendRedirect("/admin");
//        }
    }
}
