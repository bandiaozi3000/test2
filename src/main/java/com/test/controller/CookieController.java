package com.test.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cookie")
@Slf4j
public class CookieController {

    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response) {
        // 创建一个 cookie
        Cookie cookie = new Cookie("username", "Jovan");
        //设置 cookie过期时间
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        //添加到 response 中
        response.addCookie(cookie);

        return "Username is changed!";
    }

    @GetMapping("/")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }
        return "No cookies";
    }

    @GetMapping("/response")
    public String readAllCookies(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String result = null;
        JSONObject jsonObj = JSONObject.parseObject(result);
        if (StringUtils.isEmpty(jsonObj.getString("userid")) || StringUtils.isEmpty(jsonObj.getString("paybackgr")) || StringUtils.isEmpty(jsonObj.getString("rsa_random_num")) || StringUtils.isEmpty(jsonObj.getString("rsa_time_flag"))) {
            log.info("广发支付缺少必要扩展字段 : {}", result);
            return null;
        }
        return result;
    }

}
