package com.github.webshop.exception;

/**
 * 自定义的异常处理类
 * 注册到spring中
 */
//@RestControllerAdvice
//public class ExceptionHandler extends ExceptionHandlerExceptionResolver {
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        Map map = new HashMap<String, Object>();
//        map.put("Exception", ex);
//        if (ex instanceof UserInVaildLoginException) {
//            /**
//             * 用户未登录
//             */
//            request.setAttribute("Msg", "please login");
//            return new ModelAndView("login", map);
//
//        } else if (ex instanceof AdminInVaildLoginException) {
//            /**
//             * 管理未登录
//             */
//            request.setAttribute("Msg", "please login");
//            System.out.println(123);
//            return new ModelAndView("404",map);
//
//        } else {
//            /**
//             * 404 NOT FOUND
//             */
//            return new ModelAndView("/err",map);
//        }
//    }
//}
