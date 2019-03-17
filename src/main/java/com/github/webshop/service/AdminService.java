package com.github.webshop.service;

import com.github.webshop.pojo.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminService {

    Admin FindAdmin(Admin admin);

    int AddAdmin(Admin admin);
}
