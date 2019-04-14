/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.service;

import com.github.webshop.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    User check_exsist_username(HttpServletRequest request);
    User check_exsist_email(HttpServletRequest request);
    boolean check_vaild(HttpSession session,HttpServletRequest request);
    int addUser(HttpServletRequest request);
    User find_user(HttpServletRequest request);
    List get_all_user_list_pagination( Integer page, Integer limit);
    Integer get_rows_count();
}
