package com.github.webshop.Interceptor;

import com.github.webshop.conf.ReleaseUriConfig;
import com.github.webshop.util.CookieUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器,拦截非法访问
 * 实现handleInterceptor接口
 * <p>
 * 拦截 第一 ：： uri管理 、 登陆检查等
 * <p>
 * 环绕 第二 ：：日志、异常
 */
@Aspect
@Component
public class BaseInterceptor implements HandlerInterceptor {

    //日志对象
    private static Logger logger = Logger.getLogger(BaseInterceptor.class);

    /**
     * 方法拦截器
     * 在所有方法执行之前执行
     * 允许放行就返回true
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");

        String requestUri = request.getRequestURI();

        Cookie currentLoginKeepingCookie = CookieUtil.cookie_getOne(request, ReleaseUriConfig.FLAG_KEEPING_LOGIN);

        if (ReleaseUriConfig.RELEASE_URI_ON_USER.contains(requestUri)) {
            //拦截用户
            HttpSession session = request.getSession();
            String p1 = (String) session.getAttribute("currentUserId");
            String p2 = (String) session.getAttribute("currentUserName");
            String p3 = (String) session.getAttribute("currentUserEmail");
            if (p1 == null || p3 == null || p2 == null) {
                response.sendRedirect("/user/login");
            }
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Around("execution(* com.github.webshop.controller.*.*(..))")
    public Object handleAround(ProceedingJoinPoint pjp) throws Throwable {
        try {

            Object object = pjp.proceed();

            return object;

        } catch (Throwable throwable) {

            logger.info("->" + throwable.getCause() + "/n" +
                    "->" + throwable.getMessage(), throwable);

        }
        return pjp;
    }

}
