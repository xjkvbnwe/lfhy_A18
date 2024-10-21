package com.buy.intelligentmarketing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buy.intelligentmarketing.dao.GoodsDao;
import com.buy.intelligentmarketing.dao.GoodsMoreDao;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_goodlist;
import com.buy.intelligentmarketing.entity.database.Tk_ozwe_information;
import com.buy.intelligentmarketing.entity.truth.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    GoodsMoreDao goodsMoreDao;

    public List<Good> selectAll() {
        List<Tk_ozwe_goodlist> basicInfo = goodsDao.selectList(null);
        List<Tk_ozwe_information> moreInfo = goodsMoreDao.selectList(null);
        int length = basicInfo.size();
        List<Good> goodList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Tk_ozwe_goodlist basicInfoSingle = basicInfo.get(i);
            Tk_ozwe_information moreInfoSingle = moreInfo.get(i);
            Good good = new Good(
                    basicInfoSingle.getFid(),
                    basicInfoSingle.getFkOzwePicturefield(),
                    basicInfoSingle.getFnumber(),
                    basicInfoSingle.getFname(),
                    basicInfoSingle.getFkOzweGoodcategory(),
                    basicInfoSingle.getFkOzweIsoutput(),
                    basicInfoSingle.getFkOzweTotal(),
                    basicInfoSingle.getFkOzweOutput(),
                    basicInfoSingle.getFkOzweInput(),
                    basicInfoSingle.getFkOzweOrgfield(),
                    basicInfoSingle.getFcreatorid(),
                    basicInfoSingle.getFkOzweTextareafield(),
                    moreInfoSingle.getFkOzweSize(),
                    moreInfoSingle.getFkOzweSingleamount(),
                    moreInfoSingle.getFkOzweCompany(),
                    moreInfoSingle.getFkOzweKcsituation(),
                    moreInfoSingle.getFkOzweInputsituation(),
                    moreInfoSingle.getFkOzweOutputsituation(),
                    moreInfoSingle.getFkOzweOther()
            );
            goodList.add(good);
        }
        return goodList;
    }

    public List<Good> selectByOneFilter(String filter , String content) {
        QueryWrapper<Tk_ozwe_goodlist> goodlistQueryWrapper = new QueryWrapper<>();
        goodlistQueryWrapper.eq(filter,content);
        List<Tk_ozwe_goodlist> basicInfo = goodsDao.selectList(goodlistQueryWrapper);
        List<Good> goodList = new ArrayList<>();
        for (Tk_ozwe_goodlist basicInfoSingle : basicInfo) {
            QueryWrapper<Tk_ozwe_information> informationQueryWrapper = new QueryWrapper<>();
            informationQueryWrapper.eq("fid", basicInfoSingle.getFid());
            List<Tk_ozwe_information> moreInfo = goodsMoreDao.selectList(informationQueryWrapper);
            Tk_ozwe_information moreInfoSingle = moreInfo.get(0);
            Good good = new Good(
                    basicInfoSingle.getFid(),
                    basicInfoSingle.getFkOzwePicturefield(),
                    basicInfoSingle.getFnumber(),
                    basicInfoSingle.getFname(),
                    basicInfoSingle.getFkOzweGoodcategory(),
                    basicInfoSingle.getFkOzweIsoutput(),
                    basicInfoSingle.getFkOzweTotal(),
                    basicInfoSingle.getFkOzweOutput(),
                    basicInfoSingle.getFkOzweInput(),
                    basicInfoSingle.getFkOzweOrgfield(),
                    basicInfoSingle.getFcreatorid(),
                    basicInfoSingle.getFkOzweTextareafield(),
                    moreInfoSingle.getFkOzweSize(),
                    moreInfoSingle.getFkOzweSingleamount(),
                    moreInfoSingle.getFkOzweCompany(),
                    moreInfoSingle.getFkOzweKcsituation(),
                    moreInfoSingle.getFkOzweInputsituation(),
                    moreInfoSingle.getFkOzweOutputsituation(),
                    moreInfoSingle.getFkOzweOther()
            );
            goodList.add(good);
        }
        return goodList;
    }

    public Good selectSingle(String goodsNumber) {
        QueryWrapper<Tk_ozwe_goodlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fnumber" , goodsNumber);
        List<Tk_ozwe_goodlist> goodsList = goodsDao.selectList(queryWrapper);
        Tk_ozwe_goodlist basicInfoSingle = goodsList.get(0);
        Tk_ozwe_information moreInfoSingle = goodsMoreDao.selectById(basicInfoSingle.getFid());

        return new Good(
                basicInfoSingle.getFid(),
                basicInfoSingle.getFkOzwePicturefield(),
                basicInfoSingle.getFnumber(),
                basicInfoSingle.getFname(),
                basicInfoSingle.getFkOzweGoodcategory(),
                basicInfoSingle.getFkOzweIsoutput(),
                basicInfoSingle.getFkOzweTotal(),
                basicInfoSingle.getFkOzweOutput(),
                basicInfoSingle.getFkOzweInput(),
                basicInfoSingle.getFkOzweOrgfield(),
                basicInfoSingle.getFcreatorid(),
                basicInfoSingle.getFkOzweTextareafield(),
                moreInfoSingle.getFkOzweSize(),
                moreInfoSingle.getFkOzweSingleamount(),
                moreInfoSingle.getFkOzweCompany(),
                moreInfoSingle.getFkOzweKcsituation(),
                moreInfoSingle.getFkOzweInputsituation(),
                moreInfoSingle.getFkOzweOutputsituation(),
                moreInfoSingle.getFkOzweOther()
        );
    }

    public int deleteByGoodNumber(List<String> numbers) {
        int amount = 0;
        for (String number: numbers) {
            try {
                QueryWrapper<Tk_ozwe_goodlist> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("fnumber", number);
                List<Tk_ozwe_goodlist> goodsList = goodsDao.selectList(queryWrapper);
                Tk_ozwe_goodlist basicInfoSingle = goodsList.get(0);
                QueryWrapper<Tk_ozwe_information> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("fid", basicInfoSingle.getFid());
                goodsMoreDao.delete(queryWrapper2);
                amount += goodsDao.delete(queryWrapper);
            }
            catch (Exception ignored) {
            }
        }
        return amount;
    }
    public int insert(List<Good> goodList) {
        for (Good good : goodList) {
            long l = (long)(Math.random()*Math.pow(10,17));
            Tk_ozwe_goodlist basicInfoSingle = new Tk_ozwe_goodlist();
            basicInfoSingle.setFkOzwePicturefield(good.getImg())
                    .setFid(l)
                    .setFnumber(good.getNumber())
                    .setFname(good.getGoodName())
                    .setFenable(1)
                    .setFstatus("B")
                    .setFkOzweGoodcategory(good.getCategory())
                    .setFkOzweIsoutput(good.getIsOutput())
                    .setFkOzweTotal(good.getAllAmount())
                    .setFkOzweOutput(good.getOutputAmount())
                    .setFkOzweInput(good.getInputAmount())
                    .setFcreatorid(good.getCreatorId())
                    .setFkOzweTextareafield(good.getIntroduction());
            Tk_ozwe_information moreInfoSingle = new Tk_ozwe_information();
            moreInfoSingle.setFid(l)
                    .setFentryid(l+1)
                    .setFkOzweSize(good.getSize())
                    .setFkOzweSingleamount(good.getSingleAmount())
                    .setFkOzweKcsituation(good.getKcSituation())
                    .setFkOzweInputsituation(good.getInputSituation())
                    .setFkOzweOutputsituation(good.getOutputSituation())
                    .setFkOzweCompany(good.getCompany())
                    .setFkOzweOther(good.getOther());
            goodsDao.insert(basicInfoSingle);
            goodsMoreDao.insert(moreInfoSingle);
        }
        return goodList.size();
    }
    public int update(List<Good> goodList) {
        int amount = 0;
        for (Good good : goodList) {
            Tk_ozwe_goodlist basicInfoSingle = new Tk_ozwe_goodlist();
            basicInfoSingle.setFkOzwePicturefield(good.getImg())
                    .setFid(good.getFid())
                    .setFnumber(good.getNumber())
                    .setFname(good.getGoodName())
                    .setFkOzweGoodcategory(good.getCategory())
                    .setFkOzweIsoutput(good.getIsOutput())
                    .setFkOzweTotal(good.getAllAmount())
                    .setFkOzweOutput(good.getOutputAmount())
                    .setFkOzweInput(good.getInputAmount())
                    .setFcreatorid(good.getCreatorId())
                    .setFkOzweTextareafield(good.getIntroduction());
            Tk_ozwe_information moreInfoSingle = new Tk_ozwe_information();
            moreInfoSingle.setFid(good.getFid())
                    .setFkOzweSingleamount(good.getSingleAmount())
                    .setFkOzweKcsituation(good.getKcSituation())
                    .setFkOzweInputsituation(good.getInputSituation())
                    .setFkOzweOutputsituation(good.getOutputSituation())
                    .setFkOzweCompany(good.getCompany())
                    .setFkOzweOther(good.getOther());
            QueryWrapper<Tk_ozwe_goodlist> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fnumber", basicInfoSingle.getFnumber());
            int a = goodsDao.update(basicInfoSingle , queryWrapper);
            QueryWrapper<Tk_ozwe_information> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("fid", basicInfoSingle.getFid());
            int b = goodsMoreDao.update(moreInfoSingle,queryWrapper2);
            if (a==b) {
                amount+=a;
            }
        }
        return amount;
    }
}
