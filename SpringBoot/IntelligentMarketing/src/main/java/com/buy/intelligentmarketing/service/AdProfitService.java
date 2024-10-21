package com.buy.intelligentmarketing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.AdProfitDao;
import com.buy.intelligentmarketing.dao.AdProfitEntryDao;
import com.buy.intelligentmarketing.dao.AdvertisementDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_advertisement;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_profit;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_profit_entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdProfitService {
    @Autowired
    MachineService machineService;
    @Autowired
    AdProfitDao adProfitDao;
    @Autowired
    AdProfitEntryDao adProfitEntryDao;
    @Autowired
    AdvertisementDao advertisementDao;

    public JSONArray selectByMonth(String ownerId) throws ParseException {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq("fk_ozwe_ownerid" , ownerId);
        List<Tk_ozwe_profit> profitsList = adProfitDao.selectList(profitQueryWrapper);
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM");
        return judgeDate(profitsList, jsonArray, simpleDateFormat1);
    }

    public JSONArray selectByYear(String ownerId) throws ParseException {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq("fk_ozwe_ownerid" , ownerId);
        List<Tk_ozwe_profit> profitsList = adProfitDao.selectList(profitQueryWrapper);
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy");
        return judgeDate(profitsList, jsonArray, simpleDateFormat1);
    }

    private JSONArray judgeDate(List<Tk_ozwe_profit> profitsList, JSONArray jsonArray, SimpleDateFormat simpleDateFormat1) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Tk_ozwe_profit profit : profitsList) {
            Date date = simpleDateFormat.parse(profit.getFcreatetime());
            //设置新格式
            String dataDate = simpleDateFormat1.format(date);
            String nowDate = simpleDateFormat1.format(new Date());
            if (dataDate.equalsIgnoreCase(nowDate)) {
                createObject(jsonArray, profit);
            }
        }
        return jsonArray;
    }

    public JSONArray selectByDay(String ownerId , Date selectDate) throws ParseException {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq("fk_ozwe_ownerid" , ownerId);
        List<Tk_ozwe_profit> profitsList = adProfitDao.selectList(profitQueryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_profit profit : profitsList) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(profit.getFcreatetime());
            //设置新格式
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String dataDate = simpleDateFormat1.format(date);
            String nowDate = simpleDateFormat1.format(selectDate);

            if (dataDate.equalsIgnoreCase(nowDate)) {
                createObject(jsonArray, profit);
            }
        }
        return jsonArray;
    }

    public String selectByOwnerIdInfo(String ownerId) throws ParseException {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq("fk_ozwe_ownerid" , ownerId);
        List<Tk_ozwe_profit> profitsList = adProfitDao.selectList(profitQueryWrapper);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_profit profit : profitsList) {
            createObject(jsonArray, profit);
        }
        jsonObject.put("ownerInfo",jsonArray);
        JSONArray jsonArrayDay = this.selectByDay(ownerId , new Date());
        JSONArray jsonArrayMonth = this.selectByMonth(ownerId);
        jsonObject.put("dayInfo",jsonArrayDay);
        jsonObject.put("monthInfo",jsonArrayMonth);
        double sumDay=0,sumMonth=0;
        for (Object o: jsonArrayDay) {
            JSONObject jsonObject1 = (JSONObject) o;
            sumDay += jsonObject1.getDouble("allAmount");
        }

        for (Object o: jsonArrayMonth) {
            JSONObject jsonObject1 = (JSONObject) o;
            sumMonth += jsonObject1.getDouble("allAmount");
        }
        jsonObject.put("dayAmount",sumDay);
        jsonObject.put("monthAmount",sumMonth);

        profitQueryWrapper.eq("fbillstatus","B");
        List<Tk_ozwe_profit> profitsList2 = adProfitDao.selectList(profitQueryWrapper);
        double sumNotProfit = 0;
        for (Tk_ozwe_profit profit : profitsList2) {
            sumNotProfit += profit.getFkOzweAllamount();
        }
        jsonObject.put("sumNotProfit",sumNotProfit);
        return jsonObject.toString();
    }

    /**
     * 筛选某一列eq的数据
     * @param filter
     * @param content
     * @return
     */
    public String selectByOneFilter(String filter , String content) {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq(filter , content);

        List<Tk_ozwe_profit> profitsList = adProfitDao.selectList(profitQueryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Tk_ozwe_profit profit : profitsList) {
            createObject(jsonArray, profit);
        }
        return jsonArray.toString();
    }

    /**
     * 更新状态ForMoney
     * @param ownerId
     * @param oldStatus
     * @param newStatus
     * @return
     */
    public double updateStatusForMoney(String ownerId, String oldStatus, String newStatus) {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq("fk_ozwe_ownerid", ownerId).eq("fbillstatus",oldStatus);
        List<Tk_ozwe_profit> profitList = adProfitDao.selectList(profitQueryWrapper);
        double amount = 0;
        for (Tk_ozwe_profit profit : profitList) {
            profit.setFbillstatus(newStatus);
            profitQueryWrapper = new QueryWrapper<>();
            profitQueryWrapper.eq("fid",profit.getFid());
            adProfitDao.update(profit,profitQueryWrapper);
            amount+= profit.getFkOzweAllamount();
        }
        return amount;
    }

    /**
     * 删除零收益数据
     */
    public int deleteZeroProfit() {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        profitQueryWrapper.eq("fk_ozwe_allamount",0);
        return adProfitDao.delete(profitQueryWrapper);
    }

    /**
     * 私有方法：创建jsonObject
     * @param jsonArray
     * @param profit
     */
    private void createObject(JSONArray jsonArray, Tk_ozwe_profit profit) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fid",profit.getFid());
        jsonObject.put("billNo",profit.getFbillno());
        jsonObject.put("status",profit.getFbillstatus());
        jsonObject.put("createTime",profit.getFcreatetime());
        jsonObject.put("ownerId",profit.getFkOzweOwnerid());
        jsonObject.put("allAmount",profit.getFkOzweAllamount());
        jsonObject.put("playTime",profit.getFkOzwePlaytime());
        jsonArray.add(jsonObject);
    }

    private int updateTodayTime(String advertisementId,String ownerId , int time) {
        QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
        //格式化当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = simpleDateFormat.format(new Date());
        profitQueryWrapper.eq("fk_ozwe_ownerid", ownerId);
        List<Tk_ozwe_profit> profitList = adProfitDao.selectList(profitQueryWrapper);
        for (Tk_ozwe_profit profit : profitList) {
            if (profit.getFcreatetime().contains(dateFormat)) {
                profit.setFkOzwePlaytime(profit.getFkOzwePlaytime() + time);
                profitQueryWrapper = new QueryWrapper<>();
                profitQueryWrapper.eq("fk_ozwe_ownerid", ownerId).eq("fid", profit.getFid());
                QueryWrapper<Tk_ozwe_profit_entry> profitEntryQueryWrapper = new QueryWrapper<>();
                profitEntryQueryWrapper.eq("fid", profit.getFid()).eq("fk_ozwe_advertisementid", advertisementId);
                List<Tk_ozwe_profit_entry> profitEntryList = adProfitEntryDao.selectList(profitEntryQueryWrapper);
                for (Tk_ozwe_profit_entry profitEntry : profitEntryList) {
                    profitEntry.setFkOzweAdtime(profitEntry.getFkOzweAdtime() + time);
                    profitEntry.setFkOzweAmountfield(profitEntry.getFkOzweAdtime()*profitEntry.getFkOzweAdamount());
                    profit.setFkOzweAllamount(profit.getFkOzweAllamount()+profitEntry.getFkOzweAdamount()*time);
                    adProfitDao.update(profit, profitQueryWrapper);
                    adProfitEntryDao.update(profitEntry, profitEntryQueryWrapper);
                    return 1;
                }
                return 2;
            }
        }
        return 0;
    }

    private long insertProfit(String ownerId , int time, double allAmount) {
        long fid = (long)(Math.random()*Math.pow(10,18));
        String fBillNo = ((long)(Math.random()*Math.pow(10,14)))+"";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Tk_ozwe_profit profit = new Tk_ozwe_profit(
                fid,
                fBillNo,
                "A",
                simpleDateFormat.format(new Date()),
                ownerId,
                allAmount,
                time
        );
        adProfitDao.insert(profit);
        return fid;
    }

    private int insertProfitEntry(long fid, String advertisementId, double time) {
        QueryWrapper<Tk_ozwe_advertisement> advertisementQueryWrapper = new QueryWrapper<>();
        long fEntryId = (long) (Math.random()*Math.pow(10,16));
        int seq = (int)(Math.random()*Math.pow(10,5));
        advertisementQueryWrapper.eq("fnumber", advertisementId);
        List<Tk_ozwe_advertisement> advertisementList = advertisementDao.selectList(advertisementQueryWrapper);
        for (Tk_ozwe_advertisement advertisement : advertisementList) {
            Tk_ozwe_profit_entry entry = new Tk_ozwe_profit_entry(
                    fid,
                    fEntryId,
                    seq,
                    advertisementId,
                    advertisement.getFname(),
                    time,
                    advertisement.getFkOzweVideoamount(),
                    time*advertisement.getFkOzweVideoamount()
                    );
            QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
            profitQueryWrapper.eq("fid",fid);
            List<Tk_ozwe_profit> profitList = adProfitDao.selectList(profitQueryWrapper);
            for (Tk_ozwe_profit profit : profitList) {
                profit.setFkOzweAllamount(profit.getFkOzweAllamount()+time*advertisement.getFkOzweVideoamount());
                profit.setFkOzwePlaytime(profit.getFkOzwePlaytime()+time);
                adProfitDao.update(profit,profitQueryWrapper);
            }
            return adProfitEntryDao.insert(entry);
        }
        return 0;
    }

    public int autoEditProfitAPI(String advertisementFileName, String machineName , int time) {
        QueryWrapper<Tk_ozwe_advertisement> advertisementQueryWrapper = new QueryWrapper<>();
        advertisementQueryWrapper.eq("fk_ozwe_filename", advertisementFileName);
        List<Tk_ozwe_advertisement> advertisementList = advertisementDao.selectList(advertisementQueryWrapper);
        String advertisementId = "";
        for (Tk_ozwe_advertisement advertisement : advertisementList) {
            advertisementId = advertisement.getFnumber();
            break;
        }
        JSONArray jsonArrayMachine = JSONArray.parseArray(machineService.selectByOneFilter("fnumber" , machineName));
        String ownerId = "";
        for (Object o : jsonArrayMachine) {
            JSONObject jsonObject = (JSONObject) o;
            ownerId = jsonObject.getString("ownerId");
            break;
        }
        return autoEditProfit(advertisementId,ownerId,time);
    }

    private int autoEditProfit(String advertisementId , String ownerId , int time) {
        int result = this.updateTodayTime(advertisementId,ownerId,time);
        switch (result) {
            case 0: {
                QueryWrapper<Tk_ozwe_advertisement> advertisementQueryWrapper = new QueryWrapper<>();
                advertisementQueryWrapper.eq("fnumber", advertisementId);
                List<Tk_ozwe_advertisement> advertisementList = advertisementDao.selectList(advertisementQueryWrapper);
                for (Tk_ozwe_advertisement advertisement : advertisementList) {
                    return this.insertProfitEntry(this.insertProfit(ownerId,time,advertisement.getFkOzweVideoamount()), advertisementId,time);
                }
                break;
            }
            case 2: {
                QueryWrapper<Tk_ozwe_profit> profitQueryWrapper = new QueryWrapper<>();
                //格式化当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateFormat = simpleDateFormat.format(new Date());
                profitQueryWrapper.eq("fk_ozwe_ownerid", ownerId).like("fcreatetime", dateFormat);
                List<Tk_ozwe_profit> profitList = adProfitDao.selectList(profitQueryWrapper);
                for (Tk_ozwe_profit profit : profitList) {
                    return this.insertProfitEntry(profit.getFid(), advertisementId,time);
                }
                break;
            }
        }
        return -1;
    }

}
