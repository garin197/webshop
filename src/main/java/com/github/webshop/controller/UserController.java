package com.github.webshop.controller;

import com.github.webshop.exception.UserInVaildLoginException;
import com.github.webshop.pojo.User;
import com.github.webshop.service.MailService;
import com.github.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("/forecheckLogin")
    public String forecheckLogin(HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser != null) {
            return "true";
        }
        return "false";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpServletResponse response, HttpSession session, User user) {
        ModelAndView modelAndView = new ModelAndView("redirect:/page/index");
        modelAndView.addObject("msg", "hello");

        session.setAttribute("currentUser", new User());
        return modelAndView;
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
    public String register(HttpServletRequest request, HttpSession session, User user, Map map) throws Exception {

        String msg = "";
        boolean flag = false;
        if (userService.check_exsist_username(request) != null) {
            msg = "用户名已存在";
            flag = false;
        } else if (userService.check_exsist_email(request) != null) {
            msg = "邮箱已经被注册";
            flag = false;
        } else if (!userService.check_vaild(session, request)) {
            msg = "验证码错误";
            session.removeAttribute("vaild");
            flag = false;
        }

        if (flag) {//失败
            map.put("userName", user.getUserName());
            map.put("password", user.getPassword());
            map.put("msg", "layer.msg(" + msg + ")");

            return "user-login?#toregister";
        } else {

            //插入数据库
            int succ = userService.addUser(request);

            if (succ>=0){

                //注册成功直接登录
                Map currentUserMap = new HashMap<String, String>();
                currentUserMap.put("currentUserName", user.getUserName());
                currentUserMap.put("currentUserEmail", user.getEmail());
            }else {
//                注册
            }

//        currentUserMap.put("valid", valid);

//            session.setAttribute("currentUserMap", currentUserMap);

        }

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
