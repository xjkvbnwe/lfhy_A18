package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.dao.RobotDao;
import com.buy.intelligentmarketing.dao.RobotEntryDao;
import com.buy.intelligentmarketing.dao.RobotQEntryDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_robot;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_robot_entry;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_robot_qentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RobotService {
    @Autowired
    RobotDao robotDao;
    @Autowired
    RobotEntryDao robotEntryDao;
    @Autowired
    RobotQEntryDao robotQEntryDao;


    public String selectInitReply() {
        QueryWrapper<Tk_ozwe_robot> robotQueryWrapper = new QueryWrapper<>();
        robotQueryWrapper.eq("fk_ozwe_checkbox",1);
        List<Tk_ozwe_robot> robotList = robotDao.selectList(robotQueryWrapper);
        if (robotList.size() <=0) {
            return null;
        }
        Tk_ozwe_robot robot = robotList.get(0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("initReply",robot.getFkOzweReply().replace("%name%",robot.getFname()));
        JSONArray jsonArray = new JSONArray();
        QueryWrapper<Tk_ozwe_robot_qentry> qentryQueryWrapper = new QueryWrapper<>();
        qentryQueryWrapper.eq("fid",robot.getFid());
        List<Tk_ozwe_robot_qentry> qentryList = robotQEntryDao.selectList(qentryQueryWrapper);
        for (Tk_ozwe_robot_qentry qentry : qentryList) {
            jsonArray.add(qentry.getFkOzweQuestion());
        }
        jsonObject.put("questions",jsonArray);
        return jsonObject.toString();
    }

    public JSONArray reply(String content) {
        JSONArray jsonArray = new JSONArray();
        QueryWrapper<Tk_ozwe_robot> robotQueryWrapper = new QueryWrapper<>();
        robotQueryWrapper.eq("fk_ozwe_checkbox",1);
        List<Tk_ozwe_robot> robotList = robotDao.selectList(robotQueryWrapper);
        if (robotList.size() <=0) {
            return jsonArray;
        }
        Tk_ozwe_robot robot = robotList.get(0);
        QueryWrapper<Tk_ozwe_robot_entry> entryQueryWrapper = new QueryWrapper<>();
        entryQueryWrapper.eq("fid",robot.getFid());
        List<Tk_ozwe_robot_entry> entryList = robotEntryDao.selectList(entryQueryWrapper);
        for (Tk_ozwe_robot_entry entry : entryList) {
            String[] terms = entry.getFkOzweKeyword().split(",");
            for (String term : terms) {
                if (entry.getFkOzweRule().equalsIgnoreCase("等于")) {
                    if (content.equalsIgnoreCase(term)) {
                        jsonArray.add(entry.getFkOzweResult());
                        break;
                    }
                }
                else {
                    if (content.contains(term)) {
                        jsonArray.add(entry.getFkOzweResult());
                        break;
                    }
                }
            }
        }
        return jsonArray;
    }
}
