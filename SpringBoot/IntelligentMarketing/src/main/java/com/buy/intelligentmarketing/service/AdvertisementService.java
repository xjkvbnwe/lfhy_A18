package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.dao.ApplyDao;
import com.buy.intelligentmarketing.dao.ApplyHeadDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_applyentryentity;
import com.buy.intelligentmarketing.entity.truth.Apply;
import com.buy.intelligentmarketing.entity.truth.ApplyHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    AdvertisementDao advertisementDao;

    public String selectAll() {
        List<Tk_ozwe_advertisement> headInfo = advertisementDao.selectList(null);
        return getAdvertisement(headInfo);
    }

    public String getFilesUrl(String[] filesName) {
        JSONArray jsonArray = new JSONArray();
        for (String fileName : filesName) {
            QueryWrapper<Tk_ozwe_advertisement> advertisementQueryWrapper = new QueryWrapper<>();
            advertisementQueryWrapper.like("fk_ozwe_filename",fileName);
            List<Tk_ozwe_advertisement> advertisementList = advertisementDao.selectList(advertisementQueryWrapper);
            for (Tk_ozwe_advertisement advertisement : advertisementList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileName", advertisement.getFkOzweFilename());
                jsonObject.put("video", advertisement.getFkOzweVideoaddress());
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray.toString();
    }

    private String getAdvertisement(List<Tk_ozwe_advertisement> advertisementList) {
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_advertisement advertisement : advertisementList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fid", advertisement.getFid());
            jsonObject.put("billNo", advertisement.getFnumber());
            jsonObject.put("name", advertisement.getFname());
            jsonObject.put("status",advertisement.getFstatus());
            jsonObject.put("creatorId",advertisement.getFcreatorid());
            jsonObject.put("createDate",advertisement.getFcreatetime());
            jsonObject.put("videoImg",advertisement.getFkOzweVideoimg());
            jsonObject.put("videoAmount",advertisement.getFkOzweVideoamount());
            jsonObject.put("videoHot",advertisement.getFkOzweVideohot());
            jsonObject.put("videoSign",advertisement.getFkOzweVideosign());
            jsonObject.put("videoAddress",advertisement.getFkOzweVideoaddress());
            jsonObject.put("fileName", advertisement.getFkOzweFilename());

            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
