package com.buy.intelligentmarketing.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.entity.truth.Good;
import com.buy.intelligentmarketing.function.ReplenishmentRecommend;
import com.buy.intelligentmarketing.function.InputRecommend;
import com.buy.intelligentmarketing.service.ApplyService;
import com.buy.intelligentmarketing.service.GoodsService;
import com.buy.intelligentmarketing.service.RecommendService;
import com.buy.intelligentmarketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("Recommend")
public class RecommendController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    ApplyService applyService;
    @Autowired
    UserService userService;

    @Autowired
    private RecommendService service;

    @RequestMapping("getReplenishmentRecommend/{ownerId}")
    @ResponseBody
    public String getReplenishmentRecommend(@PathVariable String ownerId) throws ParseException {
        JSONObject jsonObject = service.get7DayRate(ownerId);
        List<String> list = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            String result = ownerId+","+key+","+jsonObject.getJSONObject(key).getDouble("profitRate")+","+jsonObject.getJSONObject(key).getDouble("purchaseRate");
            list.add(result);
        }
        ReplenishmentRecommend item = new ReplenishmentRecommend(list);
        item.itemWeight();
        Map<String, Double> result = new HashMap<>();
        try {
            result = item.recommend(ownerId, 5);
            JSONObject resultObject = new JSONObject();
            for (String name : result.keySet()) {
                JSONArray jsonArrayGoods = JSONArray.parseArray(userService.selectGoodsByOneFilter("goods_name", name));
                for (Object o : jsonArrayGoods) {
                    JSONObject jsonObjectGood = (JSONObject) o;
                    jsonObjectGood.put("indexResult", result.get(name));
                    resultObject.put(name, jsonObjectGood);
                    break;
                }
            }
            return resultObject.toString();
        } catch (NullPointerException ignored) {
        }
        return result.toString();
    }

    @RequestMapping(value = "/getInputRecommend/{nickName}")
    @ResponseBody
    public String getInputRecommend(@PathVariable String nickName) {
        //获取信息
        List<String> goodInfoStringList = new ArrayList<>();
        JSONArray jsonArrayInfo = JSONArray.parseArray(applyService.selectAll());
        Map<String,String> infoMap = new HashMap<>();
        for (Object o : jsonArrayInfo) {
            JSONObject ordersJsonObject = (JSONObject) o;
            JSONArray ordersGoodsArray = ordersJsonObject.getJSONArray("applyInfoArray");
            for (Object o1 : ordersGoodsArray) {
                JSONObject jsonObjectGood = (JSONObject) o1;
                String result = ordersJsonObject.getString("applier")+","+jsonObjectGood.getDouble("sum")+","+jsonObjectGood.getString("goodName");
                infoMap.put(jsonObjectGood.getString("goodName"),jsonObjectGood.getString("goodImg"));
                goodInfoStringList.add(result);
            }
        }
        JSONObject jsonObjectResult = new JSONObject();
        //获取推荐列表
        InputRecommend recommendFunction = new InputRecommend(goodInfoStringList);
        recommendFunction.itemSimilarity();
        Map<String , Float> result = new HashMap<>();
        try {
            result = recommendFunction.recommend(nickName,5);
            for (String name : result.keySet()) {
                JSONObject jsonObjectSingle = new JSONObject();
                jsonObjectSingle.put("img", infoMap.get(name));
                jsonObjectSingle.put("goodIndex",result.get(name));
                jsonObjectResult.put(name,jsonObjectSingle);
            }
        } catch (Exception ignore) {
            List<Good> goods = goodsService.selectAll();
            Collections.sort(goods);
            for (int i = 0; i < 3; i++) {
                JSONObject jsonObjectSingle = new JSONObject();
                jsonObjectSingle.put("img", goods.get(i).getImg());
                jsonObjectSingle.put("goodIndex",1);
                jsonObjectResult.put(goods.get(i).getGoodName(),jsonObjectSingle);
            }
        }

        return jsonObjectResult.toString();
    }

    @RequestMapping(value = "/insert" , method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestParam String goodJSONString) {
       return service.insertRecommendResult(JSONArray.parseArray(goodJSONString));
    }

}
