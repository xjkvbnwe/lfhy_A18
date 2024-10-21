 package com.buy.intelligentmarketing.schedule;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.function.SendTemplateMessage;
import com.buy.intelligentmarketing.service.AdProfitService;
import com.buy.intelligentmarketing.service.InvitationService;
import com.buy.intelligentmarketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class TimedProfit {
    @Autowired
    InvitationService invitationService;

    @Scheduled(cron = "0 0 22 * * ?")
    private void doTimePush() throws ParseException {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        //获取accessToken
        HttpEntity<String> entity = new HttpEntity<String>(null,httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx218c72c0749fb364&secret=0d678ff57dc0e0cf4ea0f2ecc725382a", HttpMethod.POST, entity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        String accessToken = jsonObject.getString("access_token");

        String requestJson = "{\n" +
                "\"env\": \"xjkvbnwe-5g4tba0j92d4c844\",\n" +
                "\"query\": \"db.collection('CasualBuying').get()\"\n" +
                "}";
        HttpEntity<String> entity1 = new HttpEntity<String>(requestJson,httpHeaders);
        ResponseEntity<String> response1 = client.exchange("https://api.weixin.qq.com/tcb/databasequery?access_token="+accessToken,HttpMethod.POST, entity1, String.class);
        JSONObject jsonObject1 = JSONObject.parseObject(response1.getBody());
        //获取数据库用户信息
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        invitationService.settleProfit(jsonArray);
    }
}
