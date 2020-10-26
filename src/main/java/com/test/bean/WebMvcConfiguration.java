//package com.test.bean;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
//
//    @Resource
//    private MyIntercept myIntercept;
//    //配置拦截器
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //指定拦截器，指定拦截路径
//        registry.addInterceptor(myIntercept).addPathPatterns("/**");
//    }
//}