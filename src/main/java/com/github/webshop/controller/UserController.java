package com.github.webshop.controller;

import com.github.webshop.exception.UserInVaildLoginException;
import com.github.webshop.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String GetLogin(Model model){
        return "user-login";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpServletResponse response,HttpSession session, User user){
        ModelAndView modelAndView=new ModelAndView("redirect:/page/index");
        modelAndView.addObject("msg","hello");

        session.setAttribute("currentUser",new User());
        return modelAndView;
    }

    @GetMapping("/register")
    public String toregister() throws Exception {

        return "user-login";
    }

    /**
     * 处理注册的控制类
     *
     * @param request
     * @param user
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpSession session, User user, Map map) throws Exception {
        if (false) {//失败
            map.put("userName", user.getUserName());
            map.put("password", user.getPassword());
            return "user-login";

        } else {
            //
        }
        //插入数据库


        //注册成功直接登录
        Map currentUserMap = new HashMap<String, String>();
        currentUserMap.put("currentUserName", user.getUserName());
        currentUserMap.put("currentUserEmail", user.getEmail());

        session.setAttribute("currentUserMap", currentUserMap);
        return "index";
    }

    /**
     * 在方法执行前检查是否登录
     *
     * @param session
     * @throws UserInVaildLoginException
     */
//    @ModelAttribute
//    public void isLogin(HttpServletResponse response, HttpSession session) throws Exception {
//        if (session.getAttribute("currentUser") == null) {
////            throw new UserInVaildLoginException("USER / 请您先登录！(Please login first!)");
//            response.sendRedirect("../passport");
//        }
//    }
}
