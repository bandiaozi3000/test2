package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.aop.ServiceLimit;
import com.test.aop.Servicelock;
import com.test.bean.User;
import com.test.util.HttpUtil;
import com.test.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author js.ding
 * @Title: TestController
 * @ProjectName bc-moblike-platform
 * @Description: TODO
 * @date 2019/6/1711:10
 */
@Service
@Controller
@RequestMapping
public class TestController {


    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private HttpUtil httpUtil;

    public String test(){
        JSONObject restrictionSubmitParam = new JSONObject();
        restrictionSubmitParam.put("activityNo", "1");
        String str = JSON.toJSONString(restrictionSubmitParam);
        System.out.println(str+"---------");
        ResponseEntity<String> restrictionResponse = httpUtil.postJson("https://uatapi.92jiangbei.com/bc-activity/timingPrice/query", restrictionSubmitParam, String.class);
        return "test";
    }

    @PostMapping(value = "/testPost")
    @ResponseBody
    public void testPost(User user){
        System.out.println("用户名:"+user.getName()+",密码:"+user.getPassword());
        System.out.println("post被调用");
    }

    @GetMapping(value = "/testGet")
    @ResponseBody
    public void testGet(){
        System.out.println("get被调用");
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        System.out.println(attrs.getRequest().getRemoteHost());
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    private AtomicInteger count = new AtomicInteger(0);

    @RequestMapping(value = "/testIp")
    @ServiceLimit(limitType= ServiceLimit.LimitType.IP)
    @ResponseBody
    public String testIP(){
        System.out.println(IPUtils.getIpAddr()+"--------"+count.getAndIncrement());
        return "aaaaa";
    }

    @RequestMapping(value = "/testIp2")
    @Servicelock
    @ResponseBody
    public String testIP2(){
        System.out.println(IPUtils.getIpAddr()+"--------"+count.getAndIncrement());
        return "aaaaa";
    }








}
