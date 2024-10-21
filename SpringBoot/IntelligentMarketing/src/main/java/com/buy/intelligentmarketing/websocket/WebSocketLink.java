package com.buy.intelligentmarketing.websocket;

import com.buy.intelligentmarketing.socket.ServerSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{content}")
@Component
@ResponseBody
public class WebSocketLink {
    public static Map<String , Session> map = new ConcurrentHashMap<>();
    private Session session;

    @Autowired
    ServerSend serverSend;

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "content") String content) throws IOException {
        this.session = session;
        String[] contentList = content.split(",");
        if (contentList[0].contains("Unlock")) {
            map.put(contentList[2], session);
        }
        System.out.printf("用户：%s 连接成功\n",content);
        httpRestClient(contentList[1], content);
    }

    @OnClose
    public void onClose() {
        for (String key : map.keySet()) {
            if (map.get(key) == this.session) {
                map.remove(key);
                System.out.println("用户关闭连接");
                return;
            }
        }
    }

    private static String httpRestClient(String machineName,String content) throws IOException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10*1000);
        requestFactory.setReadTimeout(10*1000);
        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = null;
        try{
            response = client.exchange("https://www.dream-y.top:8888/Function/sendCommand/"+machineName+"&"+content, HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        }
        catch (HttpClientErrorException e){
            System.out.println( "------------- 出现异常 HttpClientErrorException -------------");
            System.out.println(e.getMessage());
            System.out.println(e.getStatusText());
            System.out.println( "-------------responseBody-------------");
            System.out.println( e.getResponseBodyAsString());
            e.printStackTrace();
            return "";
        }
        catch (Exception e) {
            System.out.println( "------------- HttpRestUtils.httpRestClient() 出现异常 Exception -------------");
            System.out.println(e.getMessage());
            return "";
        }
    }
}
