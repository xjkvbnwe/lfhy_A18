package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("Statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    @RequestMapping("getAllInfo/{ownerId}")
    @ResponseBody
    public String getAllInfo(@PathVariable String ownerId) throws ParseException {
        return service.getAllInfo(ownerId);
    }

    @RequestMapping("getMainPageStatistics/{ownerId}")
    @ResponseBody
    public String getMainPageStatistics(@PathVariable String ownerId) throws ParseException {
        return service.getMainPageStatistics(ownerId);
    }

}
