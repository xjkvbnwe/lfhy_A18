package com.buy.intelligentmarketing.schedule;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.OwnerGoodsDao;
import com.buy.intelligentmarketing.entity.database.Owner_goods;
import com.buy.intelligentmarketing.function.SendTemplateMessage;
import com.buy.intelligentmarketing.service.AdProfitService;
import com.buy.intelligentmarketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class ReplenishmentMessage {
    @Autowired
    OwnerGoodsDao ownerGoodsDao;
    @Autowired
    UserService userService;
    @Autowired
    AdProfitService adProfitService;
    @Scheduled(cron = "0 0 * * * *")
    private void doTimePush() throws ParseException {
        RestTemplate client = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        //获取accessToken
        HttpEntity<String> entity = new HttpEntity<String>(null,httpHeaders);
        ResponseEntity<String> response = client.exchange("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx218c72c0749fb364&secret=0d678ff57dc0e0cf4ea0f2ecc725382a", HttpMethod.POST, entity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        String accessToken = jsonObject.getString("access_token");

        String requestJson = "{\n" +
                "\"env\": \"xjkvbnwe-5g4tba0j92d4c844\",\n" +
                "\"query\": \"db.collection('CasualBuying').get()\"\n" +
                "}";
        HttpEntity<String> entity1 = new HttpEntity<String>(requestJson,httpHeaders);
        ResponseEntity<String> response1 = client.exchange("https://api.weixin.qq.com/tcb/databasequery?access_token="+accessToken,HttpMethod.POST, entity1, String.class);
        JSONObject jsonObject1 = JSONObject.parseObject(response1.getBody());
        //获取数据库用户信息
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        //获取日期小时数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        for (Object object : jsonArray) {
            //商品消息获取
            JSONObject result = JSONObject.parseObject((String) object);
            StringBuilder leGoods = new StringBuilder();
            if ((result.getJSONObject("promiseEdit").getInteger("replenishmentRemindDate") == hours) && (result.getJSONObject("promiseEdit").getBoolean("replenishmentRemindSwitch"))) {
                QueryWrapper<Owner_goods> goodsQueryWrapper = new QueryWrapper<>();
                goodsQueryWrapper.eq("owner_id",result.getString("_id")).le("goods_total", result.getJSONObject("promiseEdit").getInteger("replenishmentRemindNum"));
                List<Owner_goods> goodsList = ownerGoodsDao.selectList(goodsQueryWrapper);
                for (Owner_goods goods : goodsList) {
                    leGoods.append(goods.getGoodsName()).append(",");
                }
            }
            String openId = result.getString("open_id");
            if (leGoods.length() > 0) {
                //发送模板信息
                Map<String , String> map = new HashMap<>();
                map.put("functionName", "sendMsgToOwner");
                map.put("ownerName" , result.getString("_id"));
                map.put("goodsName" , leGoods.toString());
                map.put("goodsNum", result.getJSONObject("promiseEdit").getInteger("replenishmentRemindNum")+"");
                map.put("ownerOpenId", openId);
                SendTemplateMessage.sendRestockedMessage(map);
            }
        }
    }
}
