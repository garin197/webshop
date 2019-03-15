package com.github.webshop.exception;

/**
 * 非法登录异常
 */
public class UserInVaildLoginException extends Exception{

    public UserInVaildLoginException(String message) {
        super(message);
    }
}
