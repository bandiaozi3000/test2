package com.test.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:HttpUtil
 * @Description:
 * @Author:lm.sun
 * @Date:2020/1/14 15:20
 */
@Component
public class HttpUtil {


    public <T> ResponseEntity<T> postJson(String url, JSONObject jsonObj, Class<T> tClass) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

        ResponseEntity<T> response = new RestTemplate().exchange(url, HttpMethod.POST, formEntity, tClass);
        return response;
    }
}
