package com.github.webshop;

import com.github.webshop.util.MyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebshopApplicationTests {

    @Test
    public void contextLoads() {
        String s= MyUtil.getFormatDate();
        System.out.println(s);
    }

}
