package com.test.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.aop.ServiceLimit;
import com.test.aop.Servicelock;
import com.test.bean.User;
import com.test.service.ThreadServiceTest;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Resource
    private ThreadServiceTest threadServiceTest;

    @Resource
    private RestTemplate restTemplate;

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
    @ResponseBody
    public void login(){
        restTemplate.getForEntity("http://localhost:8093/login/2",String.class);
    }

    @GetMapping(value = "/login/2")
    @ResponseBody
    public String login2(HttpServletResponse response){
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write("dasdsadasda");
        } catch (IOException e) {
            // TODO by Ajsgn 写出异常，异常处理
            logger.error(e.getMessage());
        }
        return null;
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

    @RequestMapping(value = "/2DImg")
    @ResponseBody
    public void test2DImg(){
        QrCodeUtil.generate("http://www.baidu.com", 300, 300, FileUtil.file("d:/baidu.jpg"));
        QrCodeUtil.generate("http://192.168.0.208:8159/dist/#/dashboard", 300, 300, FileUtil.file("d:/2d.jpg"));
        System.out.println("测试二维码生成");
    }

    @RequestMapping(value = "/testArr")
    @ResponseBody
    public void testArr(){
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String str = "2020042214202460513015".concat(String.format("%03d", i + 1));
            arr.add(str);
        }
        System.out.println(arr);
    }

    @RequestMapping(value = "/testSpring/bean/thread")
    @ResponseBody
    public void testSpring(){
        threadServiceTest.test();
    }

    @RequestMapping(value = "/testSpring/http")
    @ResponseBody
    public void testSpring(HttpServletRequest request){
        Map<String, String[]> reqMap = request.getParameterMap();
    }

    @GetMapping("/t2")
    @ResponseBody
    public Object t2() throws InterruptedException {
        System.out.println("3123130121321321321321313132123");
        Thread.sleep(10000);
        Map object = new HashMap();
        object.put("name",null);
        JSONObject object2 = new JSONObject();
        object2.put("data",object);
        object2.put("code",22);
        return object2;
    }

}
