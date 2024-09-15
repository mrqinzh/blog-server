package com.mrqinzh.framework.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String TIME_ZONE = "GMT+8";
    public static final String YYYY_MM_DD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date string2Date(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            return sdf.parse(dateStr);
        } catch (ParseException ignored) {

        }
        return null;
    }

    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }

}
