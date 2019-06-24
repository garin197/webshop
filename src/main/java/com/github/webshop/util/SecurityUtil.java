/*
 * Copyright © github@garin197 五邑大学
 */

package com.github.webshop.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.util.Base64;
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

    public static String vertify_getDecimalString() {
        Random random = new Random();
        int in = random.nextInt();
        if (in < 0) {
            in = (-1) * in;
        }
        String s;
        try {
            s = Integer.toString(in).substring(0, 6);
        } catch (Exception e) {
            return "888888";
        }

        return s;
    }


    public static String base64_changetoBase64String(String normalStr) {

        byte[] b64StrArray = Base64.getEncoder().encode(normalStr.getBytes());
        String b64String = new String(b64StrArray);
        return b64String;
    }

    public static String base64_changetoNormalString(String b64Str) {

        byte[] normalStrArray = Base64Utils.decode(b64Str.getBytes());
        String normalString = new String(normalStrArray);
        return normalString;
    }
}
