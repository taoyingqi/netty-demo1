package com.qed.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
@SuppressWarnings("unused")
public class TimeUtil {

    public static final String PURE_DATE_TYPE = "yyyyMMdd";
    public static final String PURE_DATE_TIME_TYPE = "yyyyMMddHHmmss";
    public static final String PURE_TIME_TYPE = "HHmmss";
    public static final String PURE_TIME_MILLIS_TYPE = "HHmmss";
    public static final String PURE_DATE_TIME_MILLIS_TYPE = "yyyyMMddHHmmssSSS";
    public static final String DATE_TYPE = "yyyy-MM-dd";
    public static final String DATE_TIME_TYPE = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_TYPE = "HH:mm:ss";
    public static final String TIME_MILLIS_TYPE = "HH:mm:ss.SSS";
    public static final String DATE_TIME_MILLIS_TYPE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String UTC_TYPE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String ISO_TYPE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";


    public static String formatDate(Long date) {
        return formatDate(date, DATE_TIME_TYPE);
    }

    public static String formatDate(Long date, String format) {
        if (null == date) {
            return "";
        }
        return DateFormatUtils.format(date, format);
    }

    public static String formatDate(Timestamp date) {
        return formatDate(date.getTime());
    }

    public static String formatDate(Timestamp date, String format) {
        return formatDate(date.getTime(), format);
    }

    public static String formatDate(Date date) {
        return formatDate(date.getTime());
    }

    public static String formatDate(Date date, String format) {
        return formatDate(date.getTime(), format);
    }

    // 日期格式转换
    public static String convertFormat(String date, String originFormat, String targetFormat) {
        if (StringUtils.isBlank(date)) {
            return "";
        }
        return formatDate(parseDate(date, originFormat), targetFormat);
    }

    public static Long parseTime(String date) {
        return parseTime(date, DATE_TIME_TYPE);
    }

    public static Long parseTime(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return parseDate(date.trim(), format).getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return DateUtils.parseDate(date.trim(), format);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getMonthDate() {
        SimpleDateFormat df = new SimpleDateFormat(PURE_DATE_TYPE);// 定义格式，不显示毫秒
        return df.format(new Date());
    }

    public static Timestamp nowTimestamp() {
        return new Timestamp((new Date()).getTime());
    }

    public static Timestamp addDayTimestamp(int day) {
        return new Timestamp((TimeUtil.addDay(day)).getTime());
    }


    /**
     * 得到当前时间所在的年度是第几周、第几天
     *
     * @return
     */
    public static int[] getWeekAndDay() {
        int[] var = new int[2];
        Calendar c = Calendar.getInstance(Locale.CHINA);
        var[0] = c.get(Calendar.WEEK_OF_YEAR);
        var[1] = c.get(Calendar.DAY_OF_WEEK) - 1;
        return var;
    }

    public static int getDayOfWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }


    public static Date convertDate(int week, int day) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY); //将每周第一天设为星期一，默认是星期天
        c.set(Calendar.DAY_OF_WEEK, day + 1);
        return c.getTime();
    }

    public static Date today() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        return c.getTime();
    }

    public static Date addMonth(int month) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static Date addMonth(Date time, int month) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        if (time != null) {
            c.setTime(time);
        }
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static Date addDay(int day) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    public static Date addDay(Date time, int day) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        if (time != null) {
            c.setTime(time);
        }
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    public static Date addHour(int hour) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    public static Date addHour(Date time, int hour) {
        Calendar c = Calendar.getInstance();
        if (time != null) {
            c.setTime(time);
        }
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    public static Date addMinute(Date time, int minute) {
        Calendar c = Calendar.getInstance();
        if (time != null) {
            c.setTime(time);
        }
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static Date addMinute(int minute) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static Date addSecond(int second) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    public static Date addSecond(Date time, int second) {
        Calendar c = Calendar.getInstance();
        if (time != null) {
            c.setTime(time);
        }
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    public static Date startAtDay(Date time) {
        Calendar c = Calendar.getInstance();
        if (time != null) {
            c.setTime(time);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date endAtDay(Date time) {
        Calendar c = Calendar.getInstance();
        if (time != null) {
            c.setTime(time);
        }
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static String getWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

}