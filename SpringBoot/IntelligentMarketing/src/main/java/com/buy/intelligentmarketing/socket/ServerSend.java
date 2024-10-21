package com.buy.intelligentmarketing.socket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.IntelligentMarketingApplication;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class ServerSend{

    @Async("taskExecutor")
    public void sendRun(String socketName, String content) {
        OutputStream out = null;
        try {
            Socket socket = IntelligentMarketingApplication.socketMap.get(socketName);
            String[] resultArray = content.split(",");
            if (resultArray.length>0) {
                BufferedOutputStream bufferedOutputStream;

                if (resultArray[0].equalsIgnoreCase("Unlock")) {
                    bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    bufferedOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
                    bufferedOutputStream.flush();
                } else {
//                    bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
//                    bufferedOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
//                    BufferedInputStream bufferedInputStream = null;

                    out = socket.getOutputStream();
                    out.write(content.getBytes());
                    out.flush();
                }
            }
        } catch (Exception e) {
            System.out.println("======Catch报错======");
            e.printStackTrace();
            System.out.println("======Catch报错======");
        }
    }
}
