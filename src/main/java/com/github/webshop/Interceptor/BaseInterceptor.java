package com.github.webshop.Interceptor;

import com.github.webshop.conf.ReleaseUriConfig;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

        String medthod = request.getMethod();

        //拦截POST请求
        if (medthod.equals(ReleaseUriConfig.FLAG_METHOD_POST)) {

            if (ReleaseUriConfig.RELEASE_URI_ON_USER.contains(requestUri)) {
                //拦截用户
                HttpSession session = request.getSession();
                try {
                    int p1 = (int) session.getAttribute("currentUserId");
                    String p2 = (String) session.getAttribute("currentUserName");
                    String p3 = (String) session.getAttribute("currentUserEmail");

                } catch (Exception e) {
                    response.getWriter().print("<script>window.parent.location.href='/user/login'</script>");
                    throw new NullPointerException(e.getMessage());
                }
            } else {
                //拦截管理员
                HttpSession session = request.getSession();
                try {
                    String p = (String) session.getAttribute("currentAdmin");

                } catch (Exception e) {
                    response.getWriter().print("<script>window.parent.location.href='/admin'</script>");
                    throw new NullPointerException(e.getMessage());
                }
            }


        }


        //页面地址缓存
        if (medthod.equals(ReleaseUriConfig.FLAG_METHOD_GET) && !requestUri.contains("undefined")) {
            // /page/s_by_prdct/1
            // /product/index_product_detail    ?pid=91
            // /page/s_by_cat/全部

            if (requestUri.contains("/product/index_product_detail")) {
                String pid = request.getParameter("pid");
                request.getSession().setAttribute(ReleaseUriConfig.FLAG_BEFORE_LOGIN, requestUri + "?pid=" + pid);
            }

            if (requestUri.contains("/page/s_by_prdct/")
                    || requestUri.contains("/page/s_by_cat/")) {

                request.getSession().setAttribute(ReleaseUriConfig.FLAG_BEFORE_LOGIN, requestUri);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


        if (ex != null) {
            System.out.println(ex);
            response.sendRedirect("/user/login");
        }

    }

    @Around("execution(* com.github.webshop.controller.*.*(..))")
    public Object handleAround(ProceedingJoinPoint pjp) {
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
