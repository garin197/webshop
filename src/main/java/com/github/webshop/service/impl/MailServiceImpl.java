/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.service.impl;

import com.github.webshop.service.MailService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 发送邮件验证
 */
@Service("sendemailservice")
@PropertySource("classpath:springboot-main-config.properties")
public class MailServiceImpl implements MailService {


    @Resource
    private JavaMailSender javaMailSender;


    /**
     * 发送邮件验证
     * @param sender
     * @param receiver
     * @param title
     * @param text
     * @return
     */
    @Override
    public String sendValidMessage(String sender, String receiver, String title, String text) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setTo(receiver);
        javaMailSender.send(simpleMailMessage);
        return null;
    }
}
