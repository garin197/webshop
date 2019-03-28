package com.github.webshop.service;

import com.github.webshop.pojo.Admin;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

@Repository
public interface AdminService {

    Admin FindAdmin(HttpServletRequest request);

    int AddAdmin(Admin admin);
}
