package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.AdProfitService;
import com.buy.intelligentmarketing.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("Profit")
public class ProfitController {

    @Autowired
    private AdProfitService service;

    /*@RequestMapping("getProfitByMonth/ownerId")
    @ResponseBody
    public String getProfitByMonth() throws ParseException {
        return service.selectByMonth();
    }

    @RequestMapping("getProfitByDay")
    @ResponseBody
    public String getProfitByDay() throws ParseException {
        return service.selectByDay();
    }*/

    @RequestMapping("autoEditProfitAPI/{advertisementFileName}&{machineName}&{time}")
    @ResponseBody
    public int autoEditProfitAPI(@PathVariable String advertisementFileName , @PathVariable String machineName, @PathVariable int time) {
        return service.autoEditProfitAPI(advertisementFileName,machineName,time);
    }

    @RequestMapping("getProfitByOneFilter/{filter}&{content}")
    @ResponseBody
    public String getProfitByOneFilter(@PathVariable String filter , @PathVariable String content) {
        return service.selectByOneFilter(filter,content);
    }

    @RequestMapping("getProfitByOwnerId/{ownerId}")
    @ResponseBody
    public String getProfitByDay(@PathVariable String ownerId) throws ParseException {
        return service.selectByOwnerIdInfo(ownerId);
    }

    @RequestMapping("updateStatusForMoney/{ownerId}&{oldStatus}&{newStatus}")
    @ResponseBody
    public double updateStatusForMoney(@PathVariable String ownerId, @PathVariable String oldStatus, @PathVariable String newStatus){
        return service.updateStatusForMoney(ownerId,oldStatus,newStatus);
    }

}
