package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author js.ding
 * @Title: TestController
 * @ProjectName bc-moblike-platform
 * @Description: TODO
 * @date 2019/6/1711:10
 */
@Service
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




}
