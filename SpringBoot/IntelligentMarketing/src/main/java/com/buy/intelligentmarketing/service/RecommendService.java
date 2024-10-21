package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.dao.RecommendResultDao;
import com.buy.intelligentmarketing.entity.database.Recommend_result;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.entity.truth.Good;
import com.buy.intelligentmarketing.entity.truth.Recommend;
import com.buy.intelligentmarketing.function.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecommendService {
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    RecommendResultDao recommendResultDao;

    public JSONObject get7DayRate(String ownerId) throws ParseException {
        //初始化时间
        JSONObject jsonObjectResult = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        JSONObject jsonObjectOrders = new JSONObject();
        //获取七日之内的订单
        for (int i = 1; i <= 7; i++) {
            Date resultDate = simpleDateFormat.parse(date);
            JSONObject jsonObject = JSONObject.parseObject(userService.selectOrderGoodsByDay(simpleDateFormatResult.format(resultDate), ownerId));
            for (String key : jsonObject.keySet()) {
                jsonObjectOrders.put(key , jsonObject.getString(key));
            }
            date = UtilClass.getBeforeDay(date);
        }
        //获取每个订单中每个商品的售卖数，存放进map
        Map<String , Integer> numberMap = new HashMap<>();
        Map<String, Double> amountMap = new HashMap<>();
        for (String tradeNo : jsonObjectOrders.keySet()) {
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(JSONObject.parseObject(jsonObjectOrders.getString(tradeNo)).getString("array"));
            for (Object o : jsonArrayGoodsList) {
                JSONObject jsonObject = (JSONObject) o;
                String goodsId = jsonObject.getString("goodsId");
                if (numberMap.get(goodsId) != null) {
                    numberMap.put(goodsId , numberMap.get(goodsId)+jsonObject.getInteger("buyTotal"));
                } else {
                    numberMap.put(goodsId , jsonObject.getInteger("buyTotal"));
                }
                amountMap.put(goodsId, jsonObject.getDouble("goodsPrice"));
            }
        }
        //获取已经统计的售卖数
        for (String goodId : numberMap.keySet()) {
            JSONArray jsonArrayGoods = JSONArray.parseArray(userService.selectGoodsByOneFilter("goods_id" , goodId));
            for (Object o : jsonArrayGoods) {
                JSONObject jsonObjectGoodInfo = new JSONObject();
                JSONObject jsonObjectGood = (JSONObject) o;
                double purchaseRate = (double)numberMap.get(goodId) / (numberMap.get(goodId) + jsonObjectGood.getInteger("goodsTotal"));
                jsonObjectGoodInfo.put("purchaseRate", purchaseRate);
                List<Good> goodsList = goodsService.selectByOneFilter("fname",jsonObjectGood.getString("goodsName"));
                for (Good good : goodsList) {
                    double profitRate = (amountMap.get(goodId)-good.getSingleAmount()) / amountMap.get(goodId);
                    jsonObjectGoodInfo.put("profitRate", profitRate);
                }
                jsonObjectResult.put(jsonObjectGood.getString("goodsName") , jsonObjectGoodInfo);
            }
        }

        return jsonObjectResult;
    }

    public String insertRecommendResult(JSONArray jsonArray) {
        recommendResultDao.delete(null);
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Recommend_result recommendResult = new Recommend_result(
                    jsonObject.getString("userId"),
                    jsonObject.getString("goodName"),
                    jsonObject.getDouble("similarity")
            );
            recommendResultDao.insert(recommendResult);
        }
        return "success";
    }

}

