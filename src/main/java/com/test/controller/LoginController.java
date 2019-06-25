package com.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bean.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("loginUserId", 1);
        redisTemplate.opsForValue().set("loginUser: 1", session.getId());
        System.out.println("dasdsadasd");
        return "登录成功";

    }

    @RequestMapping(value = "/getUserInfo")
    public String get() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(new People(1, "张三"));
    }

}
