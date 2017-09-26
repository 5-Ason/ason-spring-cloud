package com.ason.utils;


import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class TimeUtil {
    private TimeUtil() {
    }

    /**
     * 获取当前时间戳(秒)
     * @return
     */
    public static Long getSecondTimestamp() {
        return System.currentTimeMillis() / DateUnit.SECOND.getMillis();
    }

    /**
     * 时间戳(秒)转日期格式
     * @param timestamp 时间戳(秒)
     * @param format 日期格式
     * @return
     */
    public static String getDateByTimestamp(Long timestamp, String format) {
        return DateUtil.format(new Date(timestamp * DateUnit.SECOND.getMillis()), format);
    }


    public static boolean dateCheck(String dataStr) {
        String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])"
                + "|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|"
                + "(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3"
                + "0)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(dataStr);
        boolean b = m.matches();
        if (b) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(dateCheck("2017-06-04"));
    }
}
