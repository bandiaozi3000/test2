//package com.test.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.test.bean.DateTest;
//import com.test.bean.People;
//import com.test.util.RequestUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping(value = "/api/user")
//public class LoginController {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @RequestMapping("/login")
//    public String login(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.setAttribute("loginUserId", 1);
//        redisTemplate.opsForValue().set("loginUser: 1", session.getId());
//        return "登录成功";
//    }
//
//    @RequestMapping("/test")
//    public String test(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        return session.getAttribute("loginUserId").toString();
//    }
//
//    @RequestMapping(value = "/getUserInfo")
//    public String get() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(new People(1, "张三"));
//    }
//
//    @RequestMapping("/testUrl")
//    public String testUrl(){
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        System.out.println(request.getMethod());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getRequestURL());
//        System.out.println(request.getUserPrincipal());
//        System.out.println(RequestUtil.getIpAddr(request));
//        return RequestUtil.getBasePath(request);
//    }
//
//    @RequestMapping(value = "/testJack")
//    public String testJack(){
//        return JSONObject.toJSONString(new DateTest());
//    }
//
//    @RequestMapping(value = "/testDate")
//    public void testDate(DateTest dateTest){
//        System.out.println(dateTest);
//    }
//
//
//}
