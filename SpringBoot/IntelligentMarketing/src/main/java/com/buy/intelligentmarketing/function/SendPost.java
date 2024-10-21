package com.buy.intelligentmarketing.function;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class SendPost {
    public static String APIKey = "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";

    public static String sendPostToChatGPT(String data) {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+APIKey);
        httpHeaders.add("Content-Type", "application/json"); // 传递请求体时必须设置
//        String requestJson = "{\n" +
//                "    \"model\": \"text-davinci-003\",\n" +
//                "     \"prompt\": \"你好\",\n" +
//                "      \"temperature\": 0, \n" +
//                "      \"max_tokens\": 2048\n" +
//                "}";
        String requestJson = String.format(
                "{\n" +
                        "    \"model\": \"text-davinci-003\",\n" +
                        "     \"prompt\": \"%s\",\n" +
                        "      \"temperature\": 0, \n" +
                        "      \"max_tokens\": 2048\n" +
                        "}",data
        );
        HttpEntity<String> entity = new HttpEntity<String>(requestJson,httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.openai.com/v1/completions", HttpMethod.POST, entity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        JSONArray choices = jsonObject.getJSONArray("choices");
        //        Object o = jsonObject.get("\"choices\"");
        return choices.getJSONObject(0).getString("text");
    }
}
