/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.service.impl;

import com.github.webshop.dao.UserMapper;
import com.github.webshop.pojo.User;
import com.github.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(HttpServletRequest request) {
        String userName=request.getParameter("userName");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        User user=new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        return userMapper.add(user);
    }

    /**
     * 按名字查找用户
     *
     * @param request
     * @return
     */
    @Override
    public User check_exsist_username(HttpServletRequest request) {
        String type = request.getParameter("type");
        String username = request.getParameter("userName");
//        if (type.equals("username")){
        return userMapper.findByName(username);
//        }else {
//
//        }
//        return null;
    }

    @Override
    public User check_exsist_email(HttpServletRequest request) {
        String email = request.getParameter("email");
        return userMapper.findByEmail(email);
    }

    @Override
    public boolean check_vaild(HttpSession session, HttpServletRequest request) {
        String vaild = request.getParameter("vaild");
        String sessionvaild = (String) session.getAttribute("vaild");
        if (vaild.equals(sessionvaild)) {
            return true;
        }
        return false;
    }
}
