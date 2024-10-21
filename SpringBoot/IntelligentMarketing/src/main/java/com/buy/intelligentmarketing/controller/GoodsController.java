package com.buy.intelligentmarketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.dao.GoodsDao;
import com.buy.intelligentmarketing.entity.truth.Good;
import com.buy.intelligentmarketing.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("Goods")
public class GoodsController {

    @Autowired
    private GoodsService service;

    @RequestMapping("getGoodsAll")
    @ResponseBody
    public String getGoods() {
        List<Good> goodList = service.selectAll();
        JSONArray jsonArray = new JSONArray();
        for (Good good : goodList) {
            jsonArray.add(JSON.toJSONString(good));
        }
        return jsonArray.toString();
    }

    @RequestMapping("getGoodsByOneFilter/{filter}&{content}")
    @ResponseBody
    public String getGoodsByOneFilter(@PathVariable String filter, @PathVariable String content) {
        List<Good> goodList = service.selectByOneFilter(filter,content);
        JSONArray jsonArray = new JSONArray();
        for (Good good : goodList) {
            jsonArray.add(JSON.toJSONString(good));
        }
        return jsonArray.toString();
    }

    @RequestMapping("getGood/{number}")
    @ResponseBody
    public String getGoodSingle(@PathVariable String number) {
        return service.selectSingle(number).toString();
    }

    @RequestMapping(value = "/insert" , method = RequestMethod.POST)
    @ResponseBody
    public int insert(@RequestParam(value="goods") List<String> goodListJSON) {
        JSONArray jsonArray = JSONArray.parseArray(goodListJSON.toString());
        List<Good> goodList = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Good good = new Good(
                    jsonObject.getLong("fid"),
                    jsonObject.getString("img"),
                    jsonObject.getString("number"),
                    jsonObject.getString("goodName"),
                    jsonObject.getString("category"),
                    jsonObject.getString("isOutput"),
                    jsonObject.getInteger("allAmount"),
                    jsonObject.getInteger("outputAmount"),
                    jsonObject.getInteger("inputAmount"),
                    jsonObject.getLong("orgId"),
                    jsonObject.getLong("creatorId"),
                    jsonObject.getString("introduction"),
                    jsonObject.getString("size"),
                    jsonObject.getDouble("singleAmount"),
                    jsonObject.getString("company"),
                    jsonObject.getString("kcSituation"),
                    jsonObject.getString("inputSituation"),
                    jsonObject.getString("outputSituation"),
                    jsonObject.getString("other")
            );
            goodList.add(good);
        }
        return service.insert(goodList);
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public int delete(@RequestParam(value="goodsNumber") List<String> numberList) {
        return service.deleteByGoodNumber(numberList);
    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestParam(value="goods") List<String> goodListJSON) {
        JSONArray jsonArray = JSONArray.parseArray(goodListJSON.toString());
        List<Good> goodList = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Good good = new Good(
                    jsonObject.getLong("fid"),
                    jsonObject.getString("img"),
                    jsonObject.getString("number"),
                    jsonObject.getString("goodName"),
                    jsonObject.getString("category"),
                    jsonObject.getString("isOutput"),
                    jsonObject.getInteger("allAmount"),
                    jsonObject.getInteger("outputAmount"),
                    jsonObject.getInteger("inputAmount"),
                    jsonObject.getLong("orgId"),
                    jsonObject.getLong("creatorId"),
                    jsonObject.getString("introduction"),
                    jsonObject.getString("size"),
                    jsonObject.getDouble("singleAmount"),
                    jsonObject.getString("company"),
                    jsonObject.getString("kcSituation"),
                    jsonObject.getString("inputSituation"),
                    jsonObject.getString("outputSituation"),
                    jsonObject.getString("other")
            );
            goodList.add(good);
        }
        return service.update(goodList);
    }

}
