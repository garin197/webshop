package com.github.webshop.util;

import com.github.webshop.pojo.User;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
    /**
     * 获取日期字符串Id
     *
     * @return
     */
    public static String getDateId() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获得用户id
     */
    public static Integer getUserId(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        return user.getUserId();
    }

}
