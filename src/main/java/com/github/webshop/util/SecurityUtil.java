/*
 * Copyright © github@garin197 五邑大学
 */

package com.github.webshop.util;

import org.springframework.util.DigestUtils;

import java.util.Random;

public class SecurityUtil {

    public static String md5_getString(String protetype) {
        byte[] b = protetype.getBytes();
        return DigestUtils.md5DigestAsHex(b);
    }

    public static String md5_mixedSaltEncry(String md5string, String salt) {
        String s = md5string + "/%#*&/" + salt + "/@$^!/";
        return md5_getString(s);
    }

    public static Long salt_getDecimal() {
        Random random = new Random();
        Long lo = random.nextLong();
        if (lo < 0) {
            lo = lo * (-1);
        }
        return lo;
    }

}
