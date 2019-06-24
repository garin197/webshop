/*
 * Copyright © github@garin197 五邑大学
 */

package com.github.webshop.conf;

public class ReleaseUriConfig {

    public final static String FLAG_KEEPING_LOGIN = "loginkeeping";


    public final static String RELEASE_URI_ON_KEEPING_LOGIN
            ="/page//login" +
            ","
            ;

    public final static String RELEASE_URI_ON_USER
            = "/page/myorders" +
            "," +
            "/page/shopingcart" +
            "," +
            "/product/delCartItem" +
            "," +
            "/product/review/add" +
            "," +
            "/product/delivered/" +
            "," +
            "/product/onPay" +
            "," +
            "/product/delOrderItem" +
            "," +
            "/product/buy" +
            "," +
            "/product/getCart" +
            "," +
            "/product/deleteAllCookie" +
            "," +
            "/product/add_cart/" +
            "," +
            "";
}
