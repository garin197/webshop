/*
 * Copyright © github@garin197 五邑大学
 */

package com.github.webshop.conf;

/**
 * uri
 * 拦截 & 放行
 * 类
 */

public class ReleaseUriConfig {

    //保持登陆标识
    public final static String FLAG_KEEPING_LOGIN = "loginkeeping";

    //请求方法标志
    public final static String FLAG_METHOD_POST = "POST";
    public final static String FLAG_METHOD_GET = "GET";

    //登陆前保留页面标志
    public final static String FLAG_BEFORE_LOGIN = "beforeLogin";


    ///////////////////////////////////
    /**
     * 放行 uri
     */
    public final static String RELEASE_URI_ON_KEEPING_LOGIN
            = "/page//login" +
            ",";

    public final static String RELEASE_URI_ON_PAGE
            ="/product/index_product_detail" +
            "," +
            "/page/s_by_prdct/" +
            "," +
            "/page/s_by_cat/";



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

    ////////////////////////////////////////////////
}
