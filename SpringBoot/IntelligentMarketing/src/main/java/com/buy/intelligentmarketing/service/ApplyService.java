package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.*;
import com.buy.intelligentmarketing.entity.database.*;
import com.buy.intelligentmarketing.entity.truth.Apply;
import com.buy.intelligentmarketing.entity.truth.ApplyHead;
import com.buy.intelligentmarketing.entity.truth.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyService {
    @Autowired
    ApplyDao applyDao;
    @Autowired
    ApplyHeadDao applyHeadDao;
    @Autowired
    OrderDao orderDao;

    public String selectAll() {
        List<Tk_ozwe_apply> headInfo = applyHeadDao.selectList(null);
        return getApply(headInfo);
    }
    public String selectByOneFilter(String column , String target) {
        QueryWrapper<Tk_ozwe_apply> queryWrapperHead = new QueryWrapper<>();
        queryWrapperHead.eq(column , target);
        List<Tk_ozwe_apply> applyList = applyHeadDao.selectList(queryWrapperHead);
        return getApply(applyList);
    }

    private String getApply(List<Tk_ozwe_apply> applyList) {
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_apply apply : applyList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("billNo", apply.getFbillno());
            jsonObject.put("createDate", apply.getFkOzweCreatedate());
            jsonObject.put("applier", apply.getFkOzweApplier());
            jsonObject.put("payAmount", apply.getFkOzwePay());
            jsonObject.put("status",apply.getFbillstatus());
            jsonObject.put("orgId",apply.getFkOzweOrg());

            QueryWrapper<Tk_ozwe_applyentryentity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fid", apply.getFid());
            List<Tk_ozwe_applyentryentity> applyInfoList = applyDao.selectList(queryWrapper);
            JSONArray applyInfoArray = new JSONArray();
            for (Tk_ozwe_applyentryentity applyInfo : applyInfoList) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("rank", applyInfo.getFseq());
                jsonObject1.put("singleAmount", applyInfo.getFkOzweSingleamount());
                jsonObject1.put("totalAmount", applyInfo.getFkOzweTotalamount());
                jsonObject1.put("sum", applyInfo.getFkOzweIntegerfield());
                jsonObject1.put("size", applyInfo.getFkOzweSize());
                jsonObject1.put("goodNumber", applyInfo.getFkOzweGoodnumber());
                jsonObject1.put("goodName", applyInfo.getFkOzweGoodname());
                jsonObject1.put("goodImg", applyInfo.getFkOzweGoodimg());
                applyInfoArray.add(jsonObject1);
            }
            QueryWrapper<Tk_ozwe_order1> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("fbillno", apply.getFbillno());
            List<Tk_ozwe_order1> orders = orderDao.selectList(queryWrapper2);
            if (orders.size()>0) {
                jsonObject.put("finishOrder" , true);
            }
            jsonObject.put("applyInfoArray", applyInfoArray);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    public String insert(ApplyHead applyHead , List<Apply> applies) {
            double d = Math.random();
            while (d>0.9) {
                d = Math.random();
            }
            long l = (long)(d*Math.pow(10,17));
            long billno = (long)(Math.random()*Math.pow(10,16));
            Tk_ozwe_apply applyData = new Tk_ozwe_apply();
            applyData.setFid(l)
                    .setFbillno(billno+"")
                    .setFbillstatus("A")
                    .setFkOzweOrg(applyHead.getOrgId())
                    .setFkOzweApplier(applyHead.getApplier())
                    .setFkOzwePay(applyHead.getPayAmount())
                    .setFkOzweOrg(applyHead.getOrgId())
                    .setFkOzweCreatedate(applyHead.getCreateDate());
            applyHeadDao.insert(applyData);
            int index = 1;
            for (Apply apply : applies) {
                long entryId = (long)(Math.random()*Math.pow(10,17));
                Tk_ozwe_applyentryentity applyEntryEntity = new Tk_ozwe_applyentryentity();
                applyEntryEntity.setFid(l)
                        .setFentryid(entryId)
                        .setFseq(index++)
                        .setFkOzweSingleamount(apply.getSingleAmount())
                        .setFkOzweTotalamount(apply.getTotalAmount())
                        .setFkOzweIntegerfield(apply.getSum())
                        .setFkOzweSize(apply.getSize())
                        .setFkOzweGoodnumber(apply.getGoodNumber())
                        .setFkOzweGoodname(apply.getGoodName())
                        .setFkOzweGoodimg(apply.getGoodImg());
                applyDao.insert(applyEntryEntity);
            }
        return billno+"";
    }
}
