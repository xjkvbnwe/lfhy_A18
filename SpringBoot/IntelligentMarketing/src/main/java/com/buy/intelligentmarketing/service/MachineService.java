package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.dao.MachineDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {
    @Autowired
    MachineDao machineDao;

    public String selectAll() {
        List<Tk_ozwe_machine> headInfo = machineDao.selectList(null);
        return getAdvertisement(headInfo);
    }

    public String selectByOneFilter(String filter , String content) {
        QueryWrapper<Tk_ozwe_machine> machineQueryWrapper = new QueryWrapper<>();
        machineQueryWrapper.eq(filter,content);
        List<Tk_ozwe_machine> machineList = machineDao.selectList(machineQueryWrapper);
        return getAdvertisement(machineList);
    }

    public int updateOwnerId(String number , String ownerId) {
        QueryWrapper<Tk_ozwe_machine> machineQueryWrapper = new QueryWrapper<>();
        machineQueryWrapper.eq("fnumber",number);
        List<Tk_ozwe_machine> machineList = machineDao.selectList(machineQueryWrapper);
        for (Tk_ozwe_machine machine : machineList) {
            machine.setFkOzweOwnerid(ownerId);
            machineDao.update(machine,machineQueryWrapper);
        }
        return machineList.size();
    }

    public int bindMachine(String machineId , String ownerId) {
        QueryWrapper<Tk_ozwe_machine> machineQueryWrapper = new QueryWrapper<>();
        machineQueryWrapper.eq("fnumber",machineId);
        List<Tk_ozwe_machine> machineList = machineDao.selectList(machineQueryWrapper);
        for (Tk_ozwe_machine machine : machineList) {
            String originOwnerId = machine.getFkOzweOwnerid();
            if (originOwnerId.length()>10) {
                return -1;
            } else {
                machine.setFkOzweOwnerid(ownerId);
                machineDao.update(machine,machineQueryWrapper);
                return 1;
            }
        }
        return 0;
    }

    private String getAdvertisement(List<Tk_ozwe_machine> machineList) {
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_machine machine : machineList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fid", machine.getFid());
            jsonObject.put("number", machine.getFnumber());
            jsonObject.put("name", machine.getFname());
            jsonObject.put("machineType",machine.getFkOzweMachinetype());
            jsonObject.put("ownerId",machine.getFkOzweOwnerid());

            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
