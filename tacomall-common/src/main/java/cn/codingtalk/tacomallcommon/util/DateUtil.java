/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:09
 * @LastEditTime: 2020-06-16 21:50:03
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springcloud/tacomall-common/src/main/java/cn/codingtalk/tacomallcommon/util/DateUtil.java
 * @Just do what I think it is right
 */
package cn.codingtalk.tacomallcommon.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

public class DateUtil {

    public static Date addHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static List<Date> monthToDayList(int year, int month) {
        List<Date> dayArr = new ArrayList<>();
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);

        for (int i = 1; i <= maxDate; i++) {
            Calendar iTime = Calendar.getInstance();
            iTime.set(Calendar.YEAR, year);
            iTime.set(Calendar.MONTH, month - 1);
            iTime.set(Calendar.DATE, i);
            iTime.set(Calendar.AM_PM, 0);
            iTime.set(Calendar.HOUR, 0);
            iTime.set(Calendar.MINUTE, 0);
            iTime.set(Calendar.SECOND, 0);
            iTime.set(Calendar.MILLISECOND, 0);
            dayArr.add(iTime.getTime());
        }
        return dayArr;
    }
}
