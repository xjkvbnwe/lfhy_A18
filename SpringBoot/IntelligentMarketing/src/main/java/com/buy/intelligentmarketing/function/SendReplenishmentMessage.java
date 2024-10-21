package com.buy.intelligentmarketing.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class SendReplenishmentMessage {

    public static String sendReplenishmentMessage(Map<String , String > map, String accessToken) {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        JSONObject dataParams = (JSONObject) JSON.toJSON(map);
        HttpEntity<String> entity = new HttpEntity<String>(dataParams.toString() , httpHeaders);
        client.exchange("https://api.weixin.qq.com/tcb/invokecloudfunction?access_token="
                +accessToken+"&env=xjkvbnwe-5g4tba0j92d4c844&name="
                +map.get("functionName"), HttpMethod.POST, entity, String.class);
        return "success";
    }


}
