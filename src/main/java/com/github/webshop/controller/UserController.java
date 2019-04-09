package com.github.webshop.controller;

import com.github.webshop.exception.UserInVaildLoginException;
import com.github.webshop.pojo.User;
import com.github.webshop.service.MailService;
import com.github.webshop.service.UserService;
import com.github.webshop.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MailService mailService;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private UserService userService;

    /**
     * 访问登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String GetLogin() {
        return "user-login";
    }

    @ResponseBody
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUserId");
        session.removeAttribute("currentUserName");
        session.removeAttribute("currentUserEmail");
        return "";
    }

    @ResponseBody
    @RequestMapping("/forecheckLogin")
    public String forecheckLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("uri") String requestURI) {
        Cookie cookie = CookieUtil.getCookie(request, "uri_cookie");
        if (cookie != null) {
            cookie.setValue(requestURI);
        } else {
            cookie = new Cookie("uri_cookie", requestURI);
            response.addCookie(cookie);
        }
        cookie.setMaxAge(60);
        String currentUserName = (String) session.getAttribute("currentUserName");
        if (currentUserName != null) {
            return "success";
        }
        return "failed";
    }

//    @ResponseBody
//    @GetMapping("/foreaddCart")
//    public String foreaddCart(HttpServletRequest request) {
//        return "failed";
//    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

        Cookie cookie = CookieUtil.getCookie(request, "uri_cookie");
        String requestURI;
        if (cookie != null)
            requestURI = cookie.getValue();
        else
            requestURI="/";

        User user = userService.find_user(request);
        if (user != null) {
//            Map currentUserMap = new HashMap<String, String>();
            session.setAttribute("currentUserId", user.getUserId());
            session.setAttribute("currentUserName", user.getUserName());
            session.setAttribute("currentUserEmail", user.getEmail());
            return "redirect:" + requestURI;

        }
        return "/";
    }

    @GetMapping("/register")
    public String toregister() throws Exception {
        return "user-login";
    }


    @ResponseBody
    @PostMapping("/isexist")
    public boolean isexist(HttpServletRequest request) {
        User user = userService.check_exsist_username(request);
        if (userService.check_exsist_username(request) != null) {
            return true;
        }
        return false;
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
    public String register(HttpServletRequest request, HttpSession session, User user, Map map, RedirectAttributes redirectAttributes) throws Exception {

        String msg = "";
        boolean flag = true;
        if (userService.check_exsist_username(request) != null) {
            msg = "用户名已存在";
            flag = false;
        } else if (userService.check_exsist_email(request) != null) {
            msg = "邮箱已经被注册";
//            flag = false;
        } else if (!userService.check_vaild(session, request)) {
            msg = "验证码错误";
            session.removeAttribute("vaild");
            flag = false;
        }

        if (!flag) {//失败
            map.put("username", user.getUserName());
            map.put("email", user.getEmail());
            map.put("msg", "layer.msg(" + msg + ")");
            return "user-login";
        } else {

            //插入数据库
            int succ = userService.addUser(request);

            if (succ >= 0) {

                //注册成功直接登录
                Map currentUserMap = new HashMap<String, String>();
                currentUserMap.put("currentUserId", user.getUserId());
                currentUserMap.put("currentUserName", user.getUserName());
                currentUserMap.put("currentUserEmail", user.getEmail());
                session.setAttribute("currentUserMap", currentUserMap);
                return "index";
            } else {
                //注册失败
                msg = "与母星失去联系";
                map.put("username", user.getUserName());
                map.put("password", user.getPassword());
                map.put("msg", "layer.msg(" + msg + ")");
                return "user-login";
            }
        }
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
