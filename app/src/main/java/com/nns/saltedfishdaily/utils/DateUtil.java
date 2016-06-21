package com.nns.saltedfishdaily.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private DateUtil() {
    }

    /**
     * 计算当天的前一天的日期
     */
    public static String getPrevDate(String date) {
        Calendar rightNow = Calendar.getInstance();
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        rightNow.set(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));
        rightNow.add(Calendar.DAY_OF_MONTH, -1);
        Date time = rightNow.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(time);
        return format;
    }
}
