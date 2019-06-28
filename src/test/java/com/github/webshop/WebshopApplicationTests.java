package com.github.webshop;

import com.github.webshop.util.MyUtil;
import com.github.webshop.util.SecurityUtil;
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

        long l = SecurityUtil.salt_getDecimal();
        System.out.println(l);
        System.out.println(SecurityUtil.base64_changetoBase64String(Long.toString(l)));
        System.out.println(Long.toHexString(l));
        String s = SecurityUtil.md5_mixedSaltEncry("212", Long.toHexString(l));
        System.out.println(s);
    }

    @Test
    public void dateSetTest() {
        String dateset = MyUtil.getDateId();
        System.out.println(dateset);
    }

    @Test
    public void jsoup() {
        Connection connection = Jsoup.connect("http://localhost:8888");
//        connection.
    }

    @Test
    public void ff() {
        System.out.println(63 % 5);
    }

}
