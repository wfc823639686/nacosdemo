package com.bckj.nacosdemo.commons.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static List<String> rangeDate(Date st, Date et) {
        List<String> ret = new ArrayList<>();
        for (Date i = st; i.getTime() <= et.getTime(); i = addDays(i, 1)) {
            ret.add(DateFormatUtils.format(i, DATE_FORMAT));
        }
        return ret;
    }

    public static List<String> rangeDateByMonth(Date date) {
        Calendar sc = Calendar.getInstance();
        sc.setTime(date);
        sc.set(Calendar.DAY_OF_MONTH,1);
        Date st = sc.getTime();
        Calendar ec = Calendar.getInstance();
        ec.setTime(date);
        ec.set(Calendar.DAY_OF_MONTH, ec.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date et = ec.getTime();
        return rangeDate(st, et);
    }

}
