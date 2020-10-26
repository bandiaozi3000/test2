//package com.test.bean;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class MyIntercept extends HandlerInterceptorAdapter {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("*******************拦截器preHandle*******************");
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("*******************拦截器postHandle*******************");
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("*******************拦截器afterCompletion*******************");
//        super.afterCompletion(request, response, handler, ex);
//    }
//}
