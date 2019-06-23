package com.github.webshop.Interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器,拦截非法访问
 * 实现handleInterceptor接口
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    //日志对象
    private static Logger logger = Logger.getLogger(BaseInterceptor.class);

    /**
     * 方法拦截器
     * 在所有方法执行之前执行
     * 允许放行就返回true
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.info("启动拦截|(BaseInterceptor start)");
        request.setCharacterEncoding("utf-8");


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
