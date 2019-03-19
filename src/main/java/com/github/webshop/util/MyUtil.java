package com.github.webshop.util;

import com.github.webshop.pojo.User;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MyUtil {
    /**
     * 获取时间戳
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

    /**
     * 获取格式化的时间字符串
     *
     * @return
     */
    public static String getFormatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 子串查询
     *
     * @param fileName
     * @param regex
     * @return
     */
    public static boolean isIndexImg(String fileName, String regex) {
        if (fileName.contains(regex)) {
            return true;
        }
        return false;
    }

    /**
     * 获取UUID唯一识别码
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
