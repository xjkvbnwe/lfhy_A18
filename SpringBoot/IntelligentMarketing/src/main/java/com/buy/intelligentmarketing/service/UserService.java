package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.*;
import com.buy.intelligentmarketing.entity.database.*;
import com.buy.intelligentmarketing.entity.truth.Good;
import com.buy.intelligentmarketing.function.GetOwnerOpenid;
import org.apache.ibatis.javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OwnerGoodsDao ownerGoodsDao;
    @Autowired
    OwnerSelllogDao ownerSelllogDao;
    @Autowired
    TradeInfoDao tradeInfoDao;
    @Autowired
    MachineDao machineDao;

    //获取销售记录
    public String selectSelllogByOneFilter(String filter , String content) {
        QueryWrapper<Owner_selllog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(filter, content);
        List<Owner_selllog> selllogList = ownerSelllogDao.selectList(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(selllogList);
        return jsonArray.toString();
    }

    //获取详细消费物品
    public String selectTradeInfoByOneFilter(String filter , String content) {
        QueryWrapper<Trade_info> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(filter, content);
        List<Trade_info> tradeInfoList = tradeInfoDao.selectList(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(tradeInfoList);
        return jsonArray.toString();
    }

    //获取车主的物品
    public String selectGoodsByOneFilter(String filter , String content) {
        QueryWrapper<Owner_goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(filter, content);
        List<Owner_goods> goodsList = ownerGoodsDao.selectList(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(goodsList);
        return jsonArray.toString();
    }

    //获取所有车主物品
    public String selectAllGoods() {
        List<Owner_goods> goodsList = ownerGoodsDao.selectList(null);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(goodsList);
        return jsonArray.toString();
    }

    //获取所有销售记录
    public String selectAllSelllog() {
        List<Owner_selllog> logList = ownerSelllogDao.selectList(null);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(logList);
        return jsonArray.toString();
    }

    //获取所有订单记录
    public String selectAllTradeInfo() {
        List<Trade_info> tradeList = tradeInfoDao.selectList(null);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(tradeList);
        return jsonArray.toString();
    }

    //获取订单物品
    public String selectOrderGoods(String order) {
        QueryWrapper<Trade_info> tradeInfoQueryWrapper = new QueryWrapper<>();
        tradeInfoQueryWrapper.eq("buy_tradeno" , order);
        List<Trade_info> tradeInfo = tradeInfoDao.selectList(tradeInfoQueryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Trade_info info : tradeInfo) {
            String goodId = info.getGoodsId();
            QueryWrapper<Owner_goods> goodsQueryWrapper = new QueryWrapper<>();
            goodsQueryWrapper.eq("goods_id" , goodId);
            List<Owner_goods> goods = ownerGoodsDao.selectList(goodsQueryWrapper);
            for (Owner_goods good: goods) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(good);
                jsonObject.put("buyTotal" , info.getBuyTotal());
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray.toString();
    }

    //获取所有订单物品
    public String selectOrderGoodsAll(String userId , int type) {
        QueryWrapper<Owner_selllog> selllogQueryWrapper = new QueryWrapper<>();
        if (type == 1) {
            selllogQueryWrapper.eq("buy_openid" , userId);
        } else {
            selllogQueryWrapper.eq("owner_id" , userId);
        }
        List<Owner_selllog> selllogList = ownerSelllogDao.selectList(selllogQueryWrapper);
        JSONObject jsonObject = new JSONObject();
        for (Owner_selllog selllog : selllogList) {
            String tradeNo = selllog.getBuyTradeno();
            JSONArray jsonArray = JSONArray.parseArray(this.selectOrderGoods(tradeNo));
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("array" , jsonArray);
            jsonObject1.put("buyAmount" , selllog.getBuyAmount());
            jsonObject1.put("buyDate" , selllog.getBuyDate());
            jsonObject1.put("state" , selllog.getState());
            jsonObject1.put("ownerId" , selllog.getOwnerId());
            jsonObject1.put("buy_openid", selllog.getBuyOpenid());
            jsonObject1.put("fid", selllog.getFid());
            jsonObject.put(tradeNo , jsonObject1);
        }
        return jsonObject.toString();
    }

    public double updateStatusForMoney(String ownerId, String oldStatus, String newStatus) {
        QueryWrapper<Owner_selllog> selllogQueryWrapper = new QueryWrapper<>();
        selllogQueryWrapper.eq("owner_id", ownerId).eq("state",oldStatus);
        List<Owner_selllog> selllogList = ownerSelllogDao.selectList(selllogQueryWrapper);
        double amount = 0;
        for (Owner_selllog selllog : selllogList) {
            selllog.setState(newStatus);
            selllogQueryWrapper = new QueryWrapper<>();
            selllogQueryWrapper.eq("fid",selllog.getFid());
            ownerSelllogDao.update(selllog,selllogQueryWrapper);
            amount+= selllog.getBuyAmount();
        }
        return amount;
    }

    public String updateStatusByOneFilter(String filter, String content, String newStatus) {
        QueryWrapper<Owner_selllog> selllogQueryWrapper = new QueryWrapper<>();
        selllogQueryWrapper.eq(filter, content);
        List<Owner_selllog> selllogList = ownerSelllogDao.selectList(selllogQueryWrapper);
        for (Owner_selllog selllog : selllogList) {
            selllog.setState(newStatus);
            ownerSelllogDao.update(selllog,selllogQueryWrapper);
        }
        return "success";
    }

    public String selectOrderGoodsByDay(String dateString , String owner_id) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(this.selectOrderGoodsAll(owner_id , 0));
        Set<String> keySet = jsonObject.keySet();
        JSONObject jsonObjectResult = new JSONObject();
        for (String key : keySet) {
            JSONObject jsonObjectContent = jsonObject.getJSONObject(key);
            String buyDate = jsonObjectContent.getString("buyDate");
            if (buyDate.contains(dateString)) {
                jsonObjectResult.put(key , jsonObjectContent);
            }
        }
        return jsonObjectResult.toString();
    }

    //更新goods值
    public int updateGoodsTotal(List<String> goodsIdList , List<Integer> addNumList) {
        for (int i = 0; i < goodsIdList.size(); i++) {
            try {
                String id = goodsIdList.get(i);
                int addNum = addNumList.get(i);
                QueryWrapper<Owner_goods> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("goods_id", id);
                Owner_goods good = ownerGoodsDao.selectList(queryWrapper).get(0);
                good.setGoodsTotal(good.getGoodsTotal() + addNum);
                ownerGoodsDao.update(good, queryWrapper);
            } catch (Exception ignored) {}
        }
        return addNumList.size();
    }

    public int updateGoodInfo(String goodsId , String name , int total , double price) {
        QueryWrapper<Owner_goods> goodsQueryWrapper = new QueryWrapper<>();
        goodsQueryWrapper.eq("goods_id",goodsId);
        List<Owner_goods> goodsList = ownerGoodsDao.selectList(goodsQueryWrapper);
        for (Owner_goods good : goodsList) {
            good.setGoodsName(name)
                    .setGoodsTotal(total)
                    .setGoodsPrice(price);
            ownerGoodsDao.update(good , goodsQueryWrapper);
        }
        return goodsList.size();
    }

    //插入货物信息，存在则加数量
    public int insertOwnerGoodsReal(String object) {
        Owner_goods good = new Owner_goods();
        JSONObject jsonObject = JSONObject.parseObject(object);
        QueryWrapper<Owner_goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("owner_id" , jsonObject.getString("ownerId"));
        queryWrapper.eq("goods_number", jsonObject.getString("goodsNumber"));
        List<Owner_goods> goodsList = ownerGoodsDao.selectList(queryWrapper);
        if (goodsList.size()>0) {
            Owner_goods goodSingle = goodsList.get(0);
            goodSingle.setGoodsTotal(goodSingle.getGoodsTotal()+jsonObject.getInteger("goodsTotal"));
            return ownerGoodsDao.update(goodSingle, queryWrapper);
        } else {
            long id = (long)(Math.random()*Math.pow(10,16));
            good.setOwnerId(jsonObject.getString("ownerId"))
                    .setFid(id)
                    .setGoodsId(id+"")
                    .setGoodsNumber(jsonObject.getString("goodsNumber"))
                    .setGoodsName(jsonObject.getString("goodsName"))
                    .setGoodsTotal(jsonObject.getLong("goodsTotal"))
                    .setGoodsCreatedate(jsonObject.getString("createDate"))
                    .setGoodsImg(jsonObject.getString("goodsImg"))
                    .setGoodsPrice(jsonObject.getDouble("goodsPrice"));
            return ownerGoodsDao.insert(good);
        }
    }

    //删除货物信息
    public int deleteGoodsByGoodsId(String goodsId) {
        QueryWrapper<Owner_goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id" , goodsId);
        return ownerGoodsDao.delete(queryWrapper);
    }

    //插入订单
    public String insertOwnerSelllog(String object) {
        long id = (long)(Math.random()*Math.pow(10,16));
        Owner_selllog selllog = new Owner_selllog();
        JSONObject jsonObject = JSONObject.parseObject(object);
        selllog.setOwnerId(jsonObject.getString("ownerId"))
                .setFid(id)
                .setBuyOpenid(jsonObject.getString("openId"))
                .setBuyAmount(jsonObject.getLong("amount"))
                .setBuyDate(jsonObject.getString("date"))
                .setBuyTradeno(jsonObject.getString("tradeNo"))
                .setState(jsonObject.getString("state"));
        ownerSelllogDao.insert(selllog);
        return id+"";
    }

    //插入订单详细信息
    public int insertTradeInfo(String array, long fid) {
        JSONArray jsonArray = JSONArray.parseArray(array);
        int index = 1;
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Trade_info trade = new Trade_info();
            long id = (long)(Math.random()*Math.pow(10,16));
            trade.setOwnerId(jsonObject.getString("ownerId"))
                    .setFid(fid)
                    .setFentryid(id)
                    .setFseq(index)
                    .setBuyTradeno(jsonObject.getString("tradeNo"))
                    .setGoodsId(jsonObject.getString("goodsId"))
                    .setBuyTotal(jsonObject.getLong("total"));
            tradeInfoDao.insert(trade);
            index++;
        }
        return jsonArray.size();
    }

    public String receiveOwnerPutInfo(String jsonInfo) {
        JSONObject jsonObject = JSONObject.parseObject(jsonInfo);
        for (Object o : jsonObject.getJSONArray("goodsData")) {
            JSONObject jsonGoodData = (JSONObject) o;
            JSONObject jsonObjectResult = new JSONObject();
            jsonObjectResult.put("ownerId" , jsonObject.getString("userId"));
            jsonObjectResult.put("goodsNumber", jsonGoodData.getString("goodCode"));
            jsonObjectResult.put("goodsTotal", jsonGoodData.getString("goodTotal"));
            List<Good> goods = goodsService.selectByOneFilter("fnumber", jsonGoodData.getString("goodCode"));
            for (Good good : goods) {
                jsonObjectResult.put("goodsName", good.getGoodName());
                jsonObjectResult.put("goodsTotal", jsonGoodData.getString("goodTotal"));
                jsonObjectResult.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                jsonObjectResult.put("goodsImg", good.getImg());
                jsonObjectResult.put("goodsPrice", good.getSingleAmount());
                this.insertOwnerGoodsReal(jsonObjectResult.toString());
            }
        }
        return GetOwnerOpenid.getAccessTokenAndOwnerOpenid(jsonObject.getString("userId"),"wx218c72c0749fb364","0d678ff57dc0e0cf4ea0f2ecc725382a");
    }

    public String receiveUserPurchaseInfo(String jsonInfo) {
        System.out.println(jsonInfo);
        JSONObject jsonObject = JSONObject.parseObject(jsonInfo);
        JSONObject jsonObjectResult = new JSONObject();
        //新建订单头部信息对象
        //放入userId
        jsonObjectResult.put("openId", jsonObject.getString("userId"));
        //查询机器的ownerId
        QueryWrapper<Tk_ozwe_machine> machineQueryWrapper = new QueryWrapper<>();
        machineQueryWrapper.eq("fnumber", jsonObject.getString("machineName"));
        List<Tk_ozwe_machine> machineList = machineDao.selectList(machineQueryWrapper);
        String ownerId = "";
        long tradeNo = 0;
        for (Tk_ozwe_machine machine : machineList) {
            //放入ownerId
            jsonObjectResult.put("ownerId", machine.getFkOzweOwnerid());
            //格式化日期，加入日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonObjectResult.put("date", simpleDateFormat.format(new Date()));
            //生成订单编号
            tradeNo = (long)(Math.random()*Math.pow(10,14));
            jsonObjectResult.put("tradeNo", tradeNo);
            jsonObjectResult.put("state", "待支付");
            //获取用户购买商品及总金额
            JSONArray jsonArray = jsonObject.getJSONArray("goodsData");
            JSONArray jsonArrayGoods = new JSONArray();
            for (Object object : jsonArray) {
                //获取用户购买商品
                JSONObject goodsObject = (JSONObject) object;
                String code = goodsObject.getString("goodCode");
                QueryWrapper<Owner_goods> goodsQueryWrapper = new QueryWrapper<>();
                goodsQueryWrapper.eq("owner_id",machine.getFkOzweOwnerid()).eq("goods_number",code);
                List<Owner_goods> goodsList = ownerGoodsDao.selectList(goodsQueryWrapper);
                double amount = 0;
                //搜索商品之后处理信息
                for (Owner_goods good : goodsList) {
                    good.setGoodsTotal(good.getGoodsTotal()-1);
                    ownerGoodsDao.update(good,goodsQueryWrapper);
                    //添加商品信息
                    JSONObject jsonObjectGood = new JSONObject();
                    jsonObjectGood.put("ownerId",machine.getFkOzweOwnerid());
                    ownerId = machine.getFkOzweOwnerid();
                    jsonObjectGood.put("tradeNo", tradeNo);
                    jsonObjectGood.put("goodsId", good.getGoodsId());
                    jsonObjectGood.put("total", goodsObject.getInteger("goodTotal"));
                    jsonArrayGoods.add(jsonObjectGood);
                    amount+=good.getGoodsPrice()*goodsObject.getInteger("goodTotal");
                }
                jsonObjectResult.put("amount",amount);
            }
            String fid = this.insertOwnerSelllog(jsonObjectResult.toString());
            this.insertTradeInfo(jsonArrayGoods.toString(), Long.parseLong(fid));
        }
        return tradeNo+","+GetOwnerOpenid.getAccessTokenAndOwnerOpenid(ownerId,"wx218c72c0749fb364","0d678ff57dc0e0cf4ea0f2ecc725382a");
    }

}
