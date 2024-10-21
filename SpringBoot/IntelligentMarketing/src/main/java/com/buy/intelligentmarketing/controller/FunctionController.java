package com.buy.intelligentmarketing.controller;

import com.alibaba.fastjson.JSONArray;
import com.buy.intelligentmarketing.function.DivideTerms;
import com.buy.intelligentmarketing.function.GetOwnerOpenid;
import com.buy.intelligentmarketing.function.SendPost;
import com.buy.intelligentmarketing.function.SendTemplateMessage;
import com.buy.intelligentmarketing.service.AdvertisementService;
import com.buy.intelligentmarketing.socket.ServerSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Function")
public class FunctionController {
    @Autowired
    ServerSend serverSend;
    @Autowired
    AdvertisementService advertisementService;

    @RequestMapping("getTerms/{text}")
    @ResponseBody
    public String getTerms(@PathVariable String text) {
        List<String> results = DivideTerms.divideTerms(text);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(results);
        return jsonArray.toString();
    }

    @RequestMapping("getFiles")
    @ResponseBody
    public byte[] getFilesBytes() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("C://AdvertisementFiles/Advertisement-02.mp4"));
        return bufferedInputStream.readAllBytes();
    }

    @RequestMapping("getFilesByStream")
    @ResponseBody
    public void getFilesByStream(HttpServletResponse response) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("C://AdvertisementFiles/Advertisement-02.mp4"));
        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(bufferedInputStream.readAllBytes());
        outputStream.flush();
    }

    @RequestMapping("downloadAdvertisement/{socketName}&{content}")
    @ResponseBody
    public void download(@PathVariable String content , @PathVariable String socketName) throws IOException {
        String[] mid = content.split(",");
        String[] filesName = mid[1].split("_");
        String jsonArrayResult = (advertisementService.getFilesUrl(filesName));
        serverSend.sendRun(socketName,jsonArrayResult);
    }

    @RequestMapping("useChatGPT/{content}")
    @ResponseBody
    public String useChatGPT(@PathVariable String content) throws IOException, InterruptedException {
        return SendPost.sendPostToChatGPT(content);
    }

    @RequestMapping(value = "/sendRestockedMessage" , method = RequestMethod.POST)
    @ResponseBody
    public String sendRestockedMessage(@RequestParam Map<String , String> map) throws IOException {
        return SendTemplateMessage.sendRestockedMessage(map);
    }

    @RequestMapping(value = "/access" , method = RequestMethod.GET)
    @ResponseBody
    public String access(@RequestParam Map<String , String> map) {
        return map.get("echostr");
    }

    @RequestMapping("sendCommand/{socketName}&{content}")
    @ResponseBody
    public void sendCommand(@PathVariable String content , @PathVariable String socketName) throws IOException {
        serverSend.sendRun(socketName,content);
    }

    @RequestMapping("getAccessTokenAndOwnerOpenid/{ownerId}")
    @ResponseBody
    public void getAccessTokenAndOwnerOpenid(@PathVariable String ownerId) throws IOException {
        GetOwnerOpenid.getAccessTokenAndOwnerOpenid(ownerId,"wx218c72c0749fb364","0d678ff57dc0e0cf4ea0f2ecc725382a");
    }

}
