package com.buy.intelligentmarketing.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.IntelligentMarketingApplication;
import com.buy.intelligentmarketing.function.SendTemplateMessage;
import com.buy.intelligentmarketing.websocket.WebSocketLink;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.websocket.Session;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServerListen implements Runnable {
    private Socket socket;

    public ServerListen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream out;
            BufferedInputStream bis= new BufferedInputStream(socket.getInputStream());
            byte[] bys = new byte[1024];
            int len = 0 ;
            String result = "";
            while((len=bis.read(bys))!=-1) {
                result = new String(bys, 0, len);  //通过使用平台的默认字符集解码指定的 byte 子数组，构造一个新的 String。
                String[] resultArray = result.split("_");
                if (resultArray.length > 0) {
                    if (resultArray[0].equalsIgnoreCase("SendFilePrepare")) {
                        InputStream inputStream = new FileInputStream("C://AdvertisementFiles/"+resultArray[1]+".mp4");
                        out = socket.getOutputStream();
                        byte[] bytes = new byte[1024];
                        int fileLen;
                        while ((fileLen=inputStream.read(bytes)) != -1) {
                            out.write(bytes,0,fileLen);
                        }
                        out.flush();
                        //关闭流对象,socket,刷新，添加终止符
                        System.out.println("发送 "+resultArray[1]+" 成功");
                    }
                    else if (resultArray[0].equalsIgnoreCase("LinkServer")) {
                        IntelligentMarketingApplication.socketMap.put(resultArray[1] , socket);
                        System.out.println(IntelligentMarketingApplication.socketMap);
                    }
                    else if (resultArray[0].equalsIgnoreCase("SendAdvertisementTime")) {
                        String machineName = resultArray[1];
                        String fileName = resultArray[2];
                        int time = Integer.parseInt(resultArray[3]);
                        httpRestClient("https://www.dream-y.top:8888/Profit/autoEditProfitAPI/"+fileName+"&"+machineName+"&"+time, null);
                    }
                    else if (resultArray[0].equalsIgnoreCase("UserPurchase")) {
                        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                        map.add("value",resultArray[1]);
                        String httpRestClient = httpRestClient("https://www.dream-y.top:8888/user/receiveUserPurchaseInfo",map);
                        if (httpRestClient != null) {
                            String[] resultHttp = httpRestClient.split(",");
                            Map<String , String> mapResult = new HashMap<>();
                            mapResult.put("functionName", "userPurchaseSendMsg");
                            mapResult.put("ownerOpenId", resultHttp[1]);
                            mapResult.put("order", resultHttp[0]);
                            mapResult.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            String accessToken = SendTemplateMessage.sendRestockedMessage(mapResult);
                            JSONObject jsonObject = JSONObject.parseObject(resultArray[1]);
                            Session session;
                            if ((session = WebSocketLink.map.get(jsonObject.getString("userId"))) != null) {
                                session.getBasicRemote().sendText(httpRestClient);
                            }
                        }
                    }
                    else if (resultArray[0].equalsIgnoreCase("OwnerPut")) {
                        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                        map.add("value",resultArray[1]);
                        String httpRestClient = httpRestClient("https://www.dream-y.top:8888/user/receiveOwnerPutInfo",map);
                        if (httpRestClient != null) {
                            Map<String , String> mapResult = new HashMap<>();
                            mapResult.put("functionName", "ownerPutSendMsg");
                            mapResult.put("ownerOpenId", httpRestClient);
                            mapResult.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            SendTemplateMessage.sendRestockedMessage(mapResult);
                            JSONObject jsonObject = JSONObject.parseObject(resultArray[1]);
                            Session session;
                            if ((session = WebSocketLink.map.get(jsonObject.getString("userId"))) != null) {
                                session.getBasicRemote().sendText(httpRestClient);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String httpRestClient(String url,MultiValueMap<String, String> params) throws IOException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10*1000);
        requestFactory.setReadTimeout(10*1000);
        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = null;
        try{
            response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
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
