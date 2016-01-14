package com.hcb.jingle.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    private final static long MS_OF_DAY = 24 * 3600 * 1000;
    public static final int MINUTE = 60 * 1000;

    private final static String[] weekCN = {
            "周日", "周一", "周二", "周三", "周四", "周五", "周六",
    };

    public static String chineseWeek(int dayOfWeek) {
        if (dayOfWeek < 0) {
            dayOfWeek = 0;
        } else if (dayOfWeek >= weekCN.length) {
            dayOfWeek = weekCN.length - 1;
        }
        return weekCN[dayOfWeek];
    }

    /**
     * @return [0, 6]:[周日,周六]
     */
    public static int dayOfWeekToday() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int countPassDay(final String date) {
        return countPassDay(FormatUtil.dateFormString(date), Integer.MAX_VALUE);
    }

    public static int countPassDay(final Date date, final int max) {
        if (new Date().getTime() - date.getTime() > max * MS_OF_DAY) {
            return max;
        }
        final Calendar c1 = Calendar.getInstance();
        c1.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c1.setTime(new Date());
        int year1 = c1.get(Calendar.YEAR);
        int day1 = c1.get(Calendar.DAY_OF_YEAR);

        final Calendar c2 = Calendar.getInstance();
        c2.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c2.setTime(date);
        int year2 = c2.get(Calendar.YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);

        int pass = 0;
        if (year1 == year2) {
            pass = day1 - day2;
        } else {
            pass = 365 - day2 + day1;
        }
        return pass >= 0 ? pass : 0;
    }

    public static int dayOfMonth(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String friendlyTime(final String date) {
        return friendlyTime(FormatUtil.dateFormString(date));
    }

    public static String friendlyTime(final Date date) {
        switch (TimeUtil.countPassDay(date, 3)) {
            case 0:
                return FormatUtil.formatDate(date, "HH:mm");
            case 1:
                return "昨天 " + FormatUtil.formatDate(date, "HH:mm");
            case 2:
                return "前天 " + FormatUtil.formatDate(date, "HH:mm");
            default:
                return FormatUtil.formatDate(date, "yyyy年MM月dd日 HH:mm");
        }
    }

    public static boolean isToday(String date) {
        return null != date && date.startsWith(FormatUtil.getDateString(FormatUtil.DATE_FORMAT));
    }

}
