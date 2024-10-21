package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.AdvertisementService;
import com.buy.intelligentmarketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("selectAllGoods")
    @ResponseBody
    public String selectAllGoods() {
        return service.selectAllGoods();
    }

    @RequestMapping("selectAllSelllog")
    @ResponseBody
    public String selectAllSelllog() {
        return service.selectAllSelllog();
    }

    @RequestMapping("selectAllTradeInfo")
    @ResponseBody
    public String selectAllTradeInfo() {
        return service.selectAllTradeInfo();
    }

    @RequestMapping("selectSelllog/{filter}&{content}")
    @ResponseBody
    public String selectSelllogByOneFilter(@PathVariable String filter , @PathVariable String content) {
        return service.selectSelllogByOneFilter(filter , content);
    }

    @RequestMapping("selectTradeGoods/{content}")
    @ResponseBody
    public String selectOrderGoods(@PathVariable String content) {
        return service.selectOrderGoods(content);
    }

    @RequestMapping("selectTradeGoodsAll/{userId}&{type}")
    @ResponseBody
    public String selectOrderGoodsAll(@PathVariable String userId, @PathVariable int type) {
        return service.selectOrderGoodsAll(userId,type);
    }

    @RequestMapping("selectTradeInfo/{filter}&{content}")
    @ResponseBody
    public String selectTradeInfoByOneFilter(@PathVariable String filter ,@PathVariable String content) {
        return service.selectTradeInfoByOneFilter(filter , content);
    }

    @RequestMapping("selectGoods/{filter}&{content}")
    @ResponseBody
    public String selectGoodsByOneFilter(@PathVariable String filter ,@PathVariable String content) {
        return service.selectGoodsByOneFilter(filter , content);
    }

    @RequestMapping("selectGoodsByDay/{dateString}&{owner_id}")
    @ResponseBody
    public String selectOrderGoodsByDay(@PathVariable String dateString ,@PathVariable String owner_id) throws ParseException {
        return service.selectOrderGoodsByDay(dateString , owner_id);
    }

    @RequestMapping("updateGoodSingle/{goodsId}&{name}&{total}&{price}")
    @ResponseBody
    public int updateGoodInfo(@PathVariable String goodsId ,@PathVariable String name, @PathVariable int total, @PathVariable double price) {
        return service.updateGoodInfo(goodsId,name,total,price);
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public int delete(@RequestParam(value="goodsId") List<String> numberList) {
        for (String number : numberList) {
            service.deleteGoodsByGoodsId(number);
        }
        return numberList.size();
    }

    @RequestMapping(value = "receiveUserPurchaseInfo" , method = RequestMethod.POST)
    @ResponseBody
    public String receiveUserPurchaseInfo(@RequestParam("value") String jsonInfo) {
        return service.receiveUserPurchaseInfo(jsonInfo);
    }

    @RequestMapping(value = "receiveOwnerPutInfo" , method = RequestMethod.POST)
    @ResponseBody
    public String receiveOwnerPutInfo(@RequestParam("value") String jsonInfo) {
        return service.receiveOwnerPutInfo(jsonInfo);
    }

    @RequestMapping("updateStatusForMoney/{ownerId}&{oldStatus}&{newStatus}")
    @ResponseBody
    public double updateStatusForMoney(@PathVariable String ownerId, @PathVariable String oldStatus, @PathVariable String newStatus){
        return service.updateStatusForMoney(ownerId,oldStatus,newStatus);
    }

    @RequestMapping("updateStatusByOneFilter/{filter}&{content}&{newStatus}")
    @ResponseBody
    public String updateStatusByOneFilter(@PathVariable String filter, @PathVariable String content, @PathVariable String newStatus){
        return service.updateStatusByOneFilter(filter,content,newStatus);
    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestParam(value="goodsId") List<String> numberList , @RequestParam(value="addNum") List<Integer> addNumList) {
        return service.updateGoodsTotal(numberList , addNumList);
    }

    @RequestMapping(value = "/insert/ownerGoods" , method = RequestMethod.POST)
    @ResponseBody
    public int insertOwnerGoodsReal(@RequestParam(value="ownerGoods") List<String> goodsList) {
        for (String goods: goodsList) {
            service.insertOwnerGoodsReal(goods);
        }
        return goodsList.size();
    }
    @RequestMapping(value = "/insert/selllog" , method = RequestMethod.POST)
    @ResponseBody
    public int insertOwnerSelllog(@RequestParam(value="ownerGoods") List<String> selllogList) {
        for (String single: selllogList) {
            service.insertOwnerSelllog(single);
        }
        return selllogList.size();
    }
    @RequestMapping(value = "/insert/tradeInfo/{fid}" , method = RequestMethod.POST)
    @ResponseBody
    public int insertTradeInfo(@RequestParam(value="tradeInfo") List<String> tradeInfoList, @PathVariable long fid) {
        for (String single: tradeInfoList) {
            service.insertTradeInfo(single, fid);
        }
        return tradeInfoList.size();
    }



}
