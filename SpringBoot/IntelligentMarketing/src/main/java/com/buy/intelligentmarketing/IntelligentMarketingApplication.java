package com.buy.intelligentmarketing;

import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.socket.ServerListen;
import com.buy.intelligentmarketing.socket.ServerSend;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.buy.intelligentmarketing.dao")
public class IntelligentMarketingApplication {

    public static Map<String , Socket> socketMap;

    public static void main(String[] args) {
        SpringApplication.run(IntelligentMarketingApplication.class, args);
        try {
            socketMap = new HashMap<>();
            ServerSocket serverSocket = new ServerSocket(9420);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("设备连接成功，IP地址："+((InetSocketAddress)socket.getRemoteSocketAddress()).getAddress().getHostAddress());
                new Thread(new ServerListen(socket)).start();
//                new Thread(new ServerSend(socket)).start();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

}
