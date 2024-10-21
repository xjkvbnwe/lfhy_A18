package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.function.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class StatisticsService {
    @Autowired
    AdvertisementDao advertisementDao;
    @Autowired
    AdProfitService adProfitService;
    @Autowired
    UserService userService;

    public String getAllInfo(String ownerId) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("monthProfit" , this.getMonthProfit(ownerId));
        jsonObject.put("yearProfit" , this.getYearProfit(ownerId));
        jsonObject.put("SevenDayOrderTotal" , this.get7DayOrderTotal(ownerId));
        jsonObject.put("orderPercent" , this.orderPercent(ownerId));
        jsonObject.put("get7DayProfit" , this.get7DayProfits(ownerId));
        jsonObject.put("get7DayPlayTime" , this.get7DayPlayTime(ownerId));
        return jsonObject.toString();
    }

    public String getMainPageStatistics(String ownerId) throws ParseException {
        JSONObject jsonObjectResult = new JSONObject();
        JSONObject jsonObjectGoods = JSONObject.parseObject(userService.selectOrderGoodsByDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) , ownerId));
        Set<String> keySet = jsonObjectGoods.keySet();
        double allAmount = 0;
        for (String key : keySet) {
            JSONObject jsonObjectContent = jsonObjectGoods.getJSONObject(key);
            allAmount+=jsonObjectContent.getDouble("buyAmount");
        }
        //获取广告收益
        JSONArray jsonArrayProfit = adProfitService.selectByDay(ownerId , new Date());
        for (Object o: jsonArrayProfit) {
            JSONObject jsonObjectProfit = (JSONObject) o;
            allAmount += jsonObjectProfit.getDouble("allAmount");
        }
        jsonObjectResult.put("todayProfit", allAmount);
        //获取订单数
        JSONArray jsonArrayOrders = JSONArray.parseArray(userService.selectSelllogByOneFilter("owner_id", ownerId));
        jsonObjectResult.put("ordersNumber", jsonArrayOrders.size());
        //获取今日播放时长
        JSONArray jsonArray = adProfitService.selectByDay(ownerId, new Date());
        double time = 0;
        for (Object o : jsonArray) {
            time+= ((JSONObject) o).getDouble("playTime");
        }
        jsonObjectResult.put("ordersPlayTime", time);
        //获取用户总收益
        JSONArray jsonArrayAllProfits = JSONArray.parseArray(adProfitService.selectByOneFilter("fk_ozwe_ownerid",ownerId));
        double adProfitAll = 0;
        for (Object o : jsonArrayAllProfits) {
            adProfitAll+= ((JSONObject) o).getDouble("allAmount");
        }
        jsonObjectResult.put("adProfitAllAmount", adProfitAll);
        return jsonObjectResult.toString();
    }

    public double getMonthProfit(String ownerId) throws ParseException {
        JSONArray jsonArray = adProfitService.selectByMonth(ownerId);
        double amount = 0;
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            amount += jsonObject.getDouble("allAmount");
        }
        return amount;
    }

    public double getYearProfit(String ownerId) throws ParseException {
        JSONArray jsonArray = adProfitService.selectByYear(ownerId);
        double amount = 0;
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            amount += jsonObject.getDouble("allAmount");
        }
        return amount;
    }

    public String get7DayOrderTotal(String ownerId) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        JSONObject jsonObjectResult = new JSONObject();
        for (int i = 1; i <= 7; i++) {
            Date resultDate = simpleDateFormat.parse(date);
            JSONObject jsonObject = JSONObject.parseObject(userService.selectOrderGoodsByDay(simpleDateFormatResult.format(resultDate), ownerId));
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            jsonObjectResult.put(calendar.getTime().getTime()+"" , jsonObject.keySet().size());
            date = UtilClass.getBeforeDay(date);
        }
        return jsonObjectResult.toString();
    }

    public String orderPercent(String ownerId) throws ParseException {
        JSONArray jsonArray = JSONArray.parseArray(userService.selectSelllogByOneFilter("owner_id" , ownerId));
        JSONObject jsonObjectResult = new JSONObject();
        int morning=0,noon=0,night=0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(jsonObject.getString("buyDate")));
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            if (hours <12) {
                morning++;
            } else if (hours<= 18) {
                noon++;
            } else {
                night++;
            }
        }
        jsonObjectResult.put("morning", morning);
        jsonObjectResult.put("noon" , noon);
        jsonObjectResult.put("night", night);
        return jsonObjectResult.toString();
    }

    public String get7DayProfits(String ownerId) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        JSONObject jsonObjectResult = new JSONObject();
        for (int i = 1; i <= 7; i++) {
            JSONArray jsonArray = adProfitService.selectByDay(ownerId, simpleDateFormatResult.parse(date));
            double amount = 0;
            for (Object o : jsonArray) {
                amount+= ((JSONObject) o).getDouble("allAmount");
            }
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            jsonObjectResult.put(calendar.getTime().getTime()+"" , amount);
            date = UtilClass.getBeforeDay(date);
        }
        return jsonObjectResult.toString();
    }

    public String get7DayPlayTime(String ownerId) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        JSONObject jsonObjectResult = new JSONObject();
        for (int i = 1; i <= 7; i++) {
            JSONArray jsonArray = adProfitService.selectByDay(ownerId, simpleDateFormatResult.parse(date));
            double time = 0;
            for (Object o : jsonArray) {
                time+= ((JSONObject) o).getDouble("playTime");
            }
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            jsonObjectResult.put(calendar.getTime().getTime()+"" , time);
            date = UtilClass.getBeforeDay(date);
        }
        return jsonObjectResult.toString();
    }
}
