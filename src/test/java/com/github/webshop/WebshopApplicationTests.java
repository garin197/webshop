package com.github.webshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebshopApplicationTests {

    @Test
    public void contextLoads() {
        String s= "1213";
        System.out.println(s.replace(".","8"));
    }

}
