package com.github.webshop;

import com.github.webshop.pojo.Admin;
import com.github.webshop.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebshopApplicationTests {

    @Autowired
    AdminService adminService;
    @Test
    public void contextLoads() {
        Admin admin=new Admin();
        admin.setAdminName("lin");
        admin.setPassword("1");
        adminService.AddAdmin(admin);
    }

}
