package com.buy.intelligentmarketing.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilClass {
    public static String getBeforeDay(String dateTime){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try{
            date=simpleDateFormat.parse(dateTime);
        }catch (ParseException e){
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        //往前一天
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        return simpleDateFormat.format(calendar.getTime());
    }
}
