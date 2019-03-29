package com.github.webshop.service;

import com.github.webshop.pojo.Admin;

import javax.servlet.http.HttpServletRequest;


public interface AdminService {

    Admin FindAdmin(HttpServletRequest request);

    int AddAdmin(Admin admin);
}
