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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/send")
    public String mailvaild(HttpServletRequest request){
        String num=""+(Math.random()*1000000000)%10000;
        mailService.sendValidMessage(sender,"953626691@qq.com","一点购物平台 验证码",num);
        return null;
    }
}
