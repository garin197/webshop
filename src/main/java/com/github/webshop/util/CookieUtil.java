/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.github.webshop.util;

import com.github.webshop.pojo.CartVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class CookieUtil {

    final public static String splitRegex = "=_=";

    /**
     * 返回名为regex的cookie
     *
     * @param request
     * @param regex
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String regex) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (regex.equals(c.getName())) {
                cookie = c;
            }
        }
        return cookie;
    }

    /**
     * 获取cookie中的购物车列表
     *
     * @param response
     * @param request
     * @return 购物车列表
     * @throws UnsupportedEncodingException 抛出异常
     */
    public static List<CartVo> getCartInCookie(HttpServletResponse response, HttpServletRequest request) throws
            UnsupportedEncodingException {
        // 定义空的购物车列表
        List<CartVo> items = new ArrayList<CartVo>();
        String value_1st = "";
        // 购物cookie
        Cookie cart_cookie = getCookie(request, "cart");
        // 判断cookie是否为空
        if (cart_cookie != null) {
            // 获取cookie中String类型的value
            value_1st = URLDecoder.decode(cart_cookie.getValue(), "utf-8");//从cookie获取购物车
            // 判断value是否为空或者""字符串
            if (value_1st != null && !"".equals(value_1st)) {
                // 解析字符串中的数据为对象并封装至list中返回给上一级
                String[] arr_1st = value_1st.split("=_===");//分开商品
                for (String value_2st : arr_1st) {
                    String[] arr_2st = value_2st.split("=_=");//分开属性字段
                    CartVo item = new CartVo();
                    item.setProductId(Integer.parseInt(arr_2st[0])); //商品id
                    item.setProductName(arr_2st[1]); //商品名
                    item.setMoney(Float.parseFloat(arr_2st[2]));
                    item.setImgUrl(arr_2st[3]);
                    item.setNum(Integer.parseInt(arr_2st[4]));//加入购物车数量
                    item.setOrderCartCode(arr_2st[5]);
                    item.setAddDate(arr_2st[6]);
                    item.setProductUri(arr_2st[7]);
                    items.add(item);
                }
            }
        }
        return items;

    }


    /**
     * 制作cookie所需value
     *
     * @param cartVos 购物车列表
     * @return 解析为字符串的购物车列表，属性间使用"=_="相隔，对象间使用"=_==="相隔
     */
    public static String makeCookieValue(List<CartVo> cartVos) {
        StringBuffer buffer_2st = new StringBuffer();
        for (CartVo item : cartVos) {
            buffer_2st.append(item.getProductId() + "=_=" + item.getProductName() + "=_="
                    + item.getMoney() + "=_=" + item.getImgUrl() + "=_=" + item.getNum() + "=_=" + item.getOrderCartCode() + "=_=" + item.getAddDate() + "=_=" + item.getProductUri() + "=_===");
        }
        return buffer_2st.toString().substring(0, buffer_2st.toString().length() - 2);
    }

    public static String[] getCurrentCookies(HttpServletResponse response, HttpServletRequest request) throws
            UnsupportedEncodingException {
        String value_1st = "";
        String[] arr_1st = null;
        // 购物cookie
        Cookie cart_cookie = getCookie(request, "cart");
        // 判断cookie是否为空
        if (cart_cookie != null) {
            // 获取cookie中String类型的value
            value_1st = URLDecoder.decode(cart_cookie.getValue(), "utf-8");//从cookie获取购物车
            // 判断value是否为空或者""字符串
            if (value_1st != null && !"".equals(value_1st)) {
                // 解析字符串中的数据为对象并封装至list中返回给上一级
                arr_1st = value_1st.split("=_===");//分开商品
            }
        }
        return arr_1st;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        // 获取名为"cart"的cookie
        Cookie cookie = CookieUtil.getCookie(request, cookieName);
        // 设置寿命为0秒
        cookie.setMaxAge(0);
        // 设置路径
        cookie.setPath("/");
        // 设置cookie的value为null
        cookie.setValue(null);
        // 更新cookie
        response.addCookie(cookie);
    }


    public static Cookie cookie_getOne(HttpServletRequest request, String cookieName) {
        Cookie[] allCookie = request.getCookies();

        for (Cookie c : allCookie) {
            if (cookieName.equals(c.getName())) {
                return c;
            }
        }

        return null;
    }

    /**
     * 添加一个cookie
     *
     * @param response
     * @param cookieName  名
     * @param cookieValue 值
     * @param cookieAge   最大存活时间
     * @param cookiePath  路径
     */
    public static void cookie_addOne(HttpServletResponse response, String cookieName, String cookieValue, int cookieAge, String cookiePath) {
        Cookie cookie = new Cookie(cookieName, cookieValue);

        cookie.setMaxAge(cookieAge);

        cookie.setPath(cookiePath);

        response.addCookie(cookie);
    }


    /**
     * 删除一个cookie
     *
     * @param request
     * @param response
     * @param cookieName
     */
    public static void cookie_deleteOne(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        // 获取名为"cart"的cookie
        Cookie cookie = CookieUtil.getCookie(request, cookieName);
        // 设置寿命为0秒
        cookie.setMaxAge(0);
        // 设置路径
        cookie.setPath("/");
        // 设置cookie的value为null
        cookie.setValue(null);
        // 更新cookie
        response.addCookie(cookie);
    }

    public static String[] cookie_changeToStringArray(Cookie cookie) {
        String[] arr_value = cookie.getValue().split(splitRegex);
        return arr_value;
    }
}
