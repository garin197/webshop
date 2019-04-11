package com.github.webshop;

import com.github.webshop.util.MyUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
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

    @Test
    public void dateSetTest(){
        String dateset= MyUtil.getDateId();
        System.out.println(dateset);
    }

    @Test
    public void jsoup(){
        Connection connection=Jsoup.connect("http://localhost:8888");
//        connection.
    }

}
