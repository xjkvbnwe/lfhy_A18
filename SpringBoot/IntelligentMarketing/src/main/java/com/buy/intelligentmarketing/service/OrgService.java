package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.dao.OrgDao;
import com.buy.intelligentmarketing.entity.database.T_org_org;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgService {
    @Autowired
    OrgDao orgDao;

    public String selectAll() {
        List<T_org_org> orgList = orgDao.selectList(null);
        return getOrg(orgList);
    }

    private String getOrg(List<T_org_org> orgList) {
        JSONArray jsonArray = new JSONArray();
        for (T_org_org org : orgList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fid", org.getFid());
            jsonObject.put("number", org.getFnumber());
            jsonObject.put("name", org.getFname());
            jsonObject.put("telephone", org.getFkOzweTelephone());
            jsonObject.put("openTime", org.getFkOzweOpentime());
            jsonObject.put("addressText", org.getFkOzweAddresstext());
            jsonObject.put("longitude", org.getFkOzweLongitude());
            jsonObject.put("latitude", org.getFkOzweLatitude());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    public String selectByOneFilter(String filter , String content) {
        QueryWrapper<T_org_org> orgQueryWrapper = new QueryWrapper<>();
        orgQueryWrapper.like(filter,content);
        List<T_org_org> orgList = orgDao.selectList(orgQueryWrapper);
        return getOrg(orgList);
    }
}
