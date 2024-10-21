package com.buy.intelligentmarketing.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SendTemplateMessage {

    public static String sendRestockedMessage(Map<String , String > map) {

        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        //获取accessToken
        HttpEntity<String> entity = new HttpEntity<String>(null,httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx218c72c0749fb364&secret=0d678ff57dc0e0cf4ea0f2ecc725382a", HttpMethod.POST, entity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        String accessToken = jsonObject.getString("access_token");
        JSONObject dataParams = (JSONObject) JSON.toJSON(map);
        entity = new HttpEntity<String>(dataParams.toString() , httpHeaders);
        //获取用户数据库
        /*String requestJson = "{\n" +
                "\"env\": \"xjkvbnwe-5g4tba0j92d4c844\",\n" +
                "\"query\": \"db.collection('CasualBuying').get()\"\n" +
                "}";
        HttpEntity<String> entity1 = new HttpEntity<String>(requestJson,httpHeaders);
        ResponseEntity<String> response1 = client.exchange("https://api.weixin.qq.com/tcb/databasequery?access_token="+accessToken,HttpMethod.POST, entity1, String.class);
        JSONObject jsonObject1 = JSONObject.parseObject(response1.getBody());
        //获取数据库用户信息
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        for (Object object : jsonArray) {
            JSONObject result = JSONObject.parseObject((String) object);
            if (!result.getJSONObject("promiseEdit").getBoolean("purchaseRemind")) {
                return 0+"";
            }
        }*/
        //发送请求
        client.exchange("https://api.weixin.qq.com/tcb/invokecloudfunction?access_token="
                +accessToken+"&env=xjkvbnwe-5g4tba0j92d4c844&name="
                +dataParams.getString("functionName"), HttpMethod.POST, entity, String.class);
        return accessToken;
    }


}
