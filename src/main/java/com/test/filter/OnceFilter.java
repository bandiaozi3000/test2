//package com.test.filter;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.regex.Pattern;
//
//@Component
//public class OnceFilter extends OncePerRequestFilter {
//
//    private static Pattern patterns = Pattern.compile("/test1");
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
////        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
////        if(patterns.matcher(url).find()){
////            filterChain.doFilter(httpServletRequest,httpServletResponse);
////        }else{
////            try {
////                throw new MyException("009","没有权限访问");
////            } catch (MyException e) {
////                e.printStackTrace();
////            }
////        }
//    }
//}
//
