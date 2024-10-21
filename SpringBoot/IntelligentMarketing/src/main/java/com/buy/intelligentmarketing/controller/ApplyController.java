package com.buy.intelligentmarketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.ApplyHeadDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_apply;
import com.buy.intelligentmarketing.entity.truth.Apply;
import com.buy.intelligentmarketing.entity.truth.ApplyHead;
import com.buy.intelligentmarketing.entity.truth.Good;
import com.buy.intelligentmarketing.service.ApplyService;
import com.buy.intelligentmarketing.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Applies")
public class ApplyController {

    @Autowired
    private ApplyService service;
    @Autowired
    private ApplyHeadDao applyHeadDao;

    @RequestMapping("getApplies")
    @ResponseBody
    public String getGoods() {
        return service.selectAll();
    }

    @RequestMapping("getApply/{column}&{target}")
    @ResponseBody
    public String getGoodSingle(@PathVariable String column , @PathVariable String target) {
        return service.selectByOneFilter(column,target);
    }

    @RequestMapping("payResult")
    @ResponseBody
    public String payResult(@RequestParam Map<String , Object> map) {
        try {
            String tradeNo = (String) map.get("out_trade_no");
            QueryWrapper<Tk_ozwe_apply> queryWrapperHead = new QueryWrapper<>();
            queryWrapperHead.eq("fbillno" , tradeNo);
            Tk_ozwe_apply apply = applyHeadDao.selectList(queryWrapperHead).get(0);
            apply.setFbillstatus("B");
            applyHeadDao.update(apply,queryWrapperHead);
            return "success";
        } catch (Exception ee) {
            return null;
        }
    }

    @RequestMapping(value = "/insert" , method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestParam(value = "applyHead")String applyHeadJSON , @RequestParam(value="apply") List<String> appliesJSON) {
        JSONObject jsonObject = JSONObject.parseObject(applyHeadJSON);
        JSONArray jsonArray = JSONArray.parseArray(appliesJSON.toString());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String f = sdf.format(date);
        ApplyHead applyHead = new ApplyHead(f, jsonObject.getString("applier") , jsonObject.getDouble("payAmount"), jsonObject.getLong("orgId"));
        List<Apply> applies = new ArrayList<>();
        long index = 1;
        for (Object object : jsonArray) {
            JSONObject jsonObjectApply = (JSONObject) object;
            Apply apply = new Apply(
                    index++,
                    jsonObjectApply.getDouble("singleAmount"),
                    jsonObjectApply.getDouble("totalAmount"),
                    jsonObjectApply.getInteger("sum"),
                    jsonObjectApply.getString("size"),
                    jsonObjectApply.getString("goodNumber"),
                    jsonObjectApply.getString("goodName"),
                    jsonObjectApply.getString("goodImg")
            );
            applies.add(apply);
        }
        return "A"+service.insert(applyHead , applies);
    }

}
