package com.maxmall.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 获取当前时间之后 day 天 的时间
     * @param now
     * @param day
     * @return
     */
    public static Date getDateAfterDay(Date now,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH,day);

        return calendar.getTime();
    }

    /**
     * 当前时间获取 yyyyMMddHHmmss
     * @return
     */
    public static String formatYMDHMS(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        return format.format(new Date());
    }

    /**
     * 当前时间获取 yyyyMMddHHmmss
     * @return
     */
    public static String formatYMD(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        return format.format(new Date());
    }

}
