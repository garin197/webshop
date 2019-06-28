package com.github.webshop.service.impl;

import com.github.webshop.dao.AdminMapper;
import com.github.webshop.pojo.Admin;
import com.github.webshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int AddAdmin(Admin admin) {

//        插入
        return adminMapper.add(admin);
    }

    @Override
    public Admin FindAdmin(HttpServletRequest request) {
        String userName = request.getParameter("admin-input-username");
//        String password = request.getParameter("admin-input-password");



        Admin admin = new Admin();
        admin.setAdminName(userName);
//        admin.setPassword(password);


        Admin currentAdmin = null;
        try {
            currentAdmin = adminMapper.findByName(userName);
        }catch (Exception e){

        }
        return currentAdmin;
    }

}
