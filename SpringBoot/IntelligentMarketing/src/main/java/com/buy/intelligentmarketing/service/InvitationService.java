package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.InvitationInfoDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_invitationinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InvitationService {
    @Autowired
    InvitationInfoDao invitationInfoDao;
    @Autowired
    AdProfitService adProfitService;

    public String settleProfit(JSONArray userJsonArray) throws ParseException {
        JSONObject jsonObjectProfit = new JSONObject();
        //获取用户信息
        for(Object o : userJsonArray) {
            JSONObject jsonObjectUser = JSONObject.parseObject((String) o);
            //获取用户收益信息
            JSONArray jsonArray = adProfitService.selectByDay(jsonObjectUser.getString("_id") , new Date());
            //将用户收益总体加入JSONObject中
            for (Object o1 : jsonArray) {
                JSONObject jsonObjectProfitResult = (JSONObject) o1;
                double allAmount = jsonObjectProfitResult.getDouble("allAmount");
                jsonObjectProfit.put(jsonObjectProfit.getString("_id"),allAmount);
            }
        }
        JSONObject jsonObjectProfitEndResult = new JSONObject();
        //遍历所有用户
        for(Object o : userJsonArray) {
            JSONObject jsonObjectUser = JSONObject.parseObject((String) o);
            //获取用户的邀请ID数组
            JSONArray jsonArrayInvitedId = jsonObjectUser.getJSONArray("invitationUserId");
            double sharingProfit = 0;
            //遍历数组中的每个ID
            for (Object o1 : jsonArrayInvitedId) {
                String id = (String) o1;
                if (jsonObjectProfit.getDouble(id) != null) {
                    sharingProfit += jsonObjectProfit.getDouble(id)*0.01;
                }
            }
            //将每个人的邀请收益计算出来
            jsonObjectProfitEndResult.put(jsonObjectUser.getString("_id"), sharingProfit);
            if (sharingProfit<0.01) {
                continue;
            }
            JSONObject jsonObjectInsert = new JSONObject();
            jsonObjectInsert.put("ownerId" , jsonObjectUser.getString("_id"));
            jsonObjectInsert.put("status", "A");
            jsonObjectInsert.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jsonObjectInsert.put("amount", sharingProfit);
            this.insertInvitationInfo(jsonObjectInsert);
        }
        return jsonObjectProfitEndResult.toString();
    }

    public String selectInvitationByOneFilter(String filter , String content) {
        QueryWrapper<Tk_ozwe_invitationinfo> invitationInfoQueryWrapper = new QueryWrapper<>();
        invitationInfoQueryWrapper.eq(filter,content);
        List<Tk_ozwe_invitationinfo> invitationInfoList = invitationInfoDao.selectList(invitationInfoQueryWrapper);
        JSONArray jsonArray = this.createObject(invitationInfoList);
        return jsonArray.toString();
    }

    public String updateStatus(String ownerId , String oldStatus , String newStatus) {
        QueryWrapper<Tk_ozwe_invitationinfo> invitationInfoQueryWrapper = new QueryWrapper<>();
        invitationInfoQueryWrapper.eq("fnumber",ownerId).eq("fstatus", oldStatus);
        List<Tk_ozwe_invitationinfo> invitationInfoList = invitationInfoDao.selectList(invitationInfoQueryWrapper);
        for (Tk_ozwe_invitationinfo invitationInfo : invitationInfoList) {
            invitationInfo.setFstatus(newStatus);
            invitationInfoQueryWrapper = new QueryWrapper<>();
            invitationInfoQueryWrapper.eq("fid",invitationInfo.getFid());
            invitationInfoDao.update(invitationInfo,invitationInfoQueryWrapper);
        }
        return ownerId;
    }

    public int insertInvitationInfo(JSONObject jsonObject) {
        long fid = (long) (Math.random()*(Math.pow(10,12)));
        long profitNo = (long) (Math.random()*(Math.pow(10,16)));
        Tk_ozwe_invitationinfo info = new Tk_ozwe_invitationinfo(
                fid,
                jsonObject.getString("ownerId"),
                jsonObject.getString("status"),
                1,
                jsonObject.getString("createDate"),
                jsonObject.getDouble("amount"),
                profitNo
        );
        return invitationInfoDao.insert(info);
    }

    private JSONArray createObject(List<Tk_ozwe_invitationinfo> invitationInfoList) {
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_invitationinfo invitationInfo : invitationInfoList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fid", invitationInfo.getFid());
            jsonObject.put("ownerId", invitationInfo.getFnumber());
            jsonObject.put("status", invitationInfo.getFstatus());
            jsonObject.put("createDate" , invitationInfo.getFkOzweCreatedate());
            jsonObject.put("amount" , invitationInfo.getFkOzweAmount());
            jsonObject.put("profitNo" , invitationInfo.getFkOzweProfitno());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
