/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.controller;

import com.github.webshop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@PropertySource("classpath:springboot-main-config.properties")
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @Value("${spring.mail.username}")
    private String sender;

    @ResponseBody
    @PostMapping("/send")
    public boolean mailvaild(HttpServletRequest request) {
        String num = "" + (Math.random() * 10000) % 10000;
        num = num.substring(0, 4).replace(".", "8");
        String email = request.getParameter("email");
        try {
            mailService.sendValidMessage(sender, email, "一点购物平台 验证码", "尊敬的用户：你正在操作-一点购物平台-注册账号，操作验证码为：" + num);
        } catch (Exception e) {
            return false;
        }
        request.getSession().setAttribute("vaild", num);
        return true;
    }

    //发送发货通知--后台
    @ResponseBody
    @PostMapping("/deliver/send")
    public String t(@RequestParam("email")String email,@RequestParam("productName")String productName){
        try {
            mailService.sendValidMessage(sender, email,
                    "一点购物平台 商品状态变更 发货通知",
                    "尊敬的用户：您订购的  '"+productName+"  '的商品已经发货，由xx快递进行运送，快递单号为：'1234567890123456789',请认真验货查收！");
        } catch (Exception e) {
            return "failed";
        }
        return "success";
    }
}
