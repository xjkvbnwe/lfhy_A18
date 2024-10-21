package com.buy.intelligentmarketing.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_apply;
import com.buy.intelligentmarketing.entity.truth.Apply;
import com.buy.intelligentmarketing.entity.truth.ApplyHead;
import com.buy.intelligentmarketing.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Advertise")
public class AdvertisementController {

    @Autowired
    private AdvertisementService service;

    @RequestMapping("getAdvertisements")
    @ResponseBody
    public String getAdvertisements() {
        return service.selectAll();
    }

}
