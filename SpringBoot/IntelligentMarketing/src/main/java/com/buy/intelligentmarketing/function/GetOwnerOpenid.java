package com.buy.intelligentmarketing.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetOwnerOpenid {
    public static String getAccessTokenAndOwnerOpenid(String ownerId, String appid,String secret) {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<String>(null,httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret, HttpMethod.POST, entity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        String accessToken = jsonObject.getString("access_token");

        String requestJson = String.format(
                "{\n" +
                        "\"env\": \"xjkvbnwe-5g4tba0j92d4c844\",\n" +
                        "\"query\": \"db.collection('CasualBuying').where({_id:'%s'}).get()\"\n" +
                        "}",ownerId
        );
        HttpEntity<String> entity1 = new HttpEntity<String>(requestJson,httpHeaders);
        ResponseEntity<String> response1 = client.exchange("https://api.weixin.qq.com/tcb/databasequery?access_token="+accessToken,HttpMethod.POST, entity1, String.class);
        JSONObject jsonObject1 = JSONObject.parseObject(response1.getBody());
//        JSONObject data = jsonObject1.getJSONObject("data");
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        String openId = "";
        for (Object object : jsonArray) {
            JSONObject result = JSONObject.parseObject((String) object);
            openId = result.getString("open_id");
        }
        return openId;
    }
}
