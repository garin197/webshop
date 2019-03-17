package com.github.webshop.service.impl;

import com.github.webshop.dao.AdminMapper;
import com.github.webshop.pojo.Admin;
import com.github.webshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Admin FindAdmin(Admin admin) {

//        adminMapper.InsertNewAdmin(admin);

        return null;
    }

}
