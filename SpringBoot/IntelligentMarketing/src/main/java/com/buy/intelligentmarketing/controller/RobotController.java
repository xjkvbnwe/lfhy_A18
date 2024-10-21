package com.buy.intelligentmarketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.buy.intelligentmarketing.function.SendPost;
import com.buy.intelligentmarketing.service.AdvertisementService;
import com.buy.intelligentmarketing.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Robot")
public class RobotController {

    @Autowired
    private RobotService service;

    @RequestMapping("getInitReply")
    @ResponseBody
    public String selectInitReply() {
        return service.selectInitReply();
    }

    @RequestMapping(value = "/sendMsg" , method = RequestMethod.POST)
    @ResponseBody
    public String reply(@RequestParam(value="content") String content) {
        JSONArray jsonArray = service.reply(content);
        if (jsonArray.size() > 0) {
            return jsonArray.toString();
        }
        else {
            JSONArray jsonArray1 = new JSONArray();
            jsonArray1.add("未找到相关回复，请询问其他问题或者电话咨询");
            return jsonArray1.toString();
        }
    }

}
