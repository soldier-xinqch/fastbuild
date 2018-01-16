package com.fastbuild.common;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 *  时间工具类
 */
public class DateCustomUtil {
    public static final String FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YEAR_MON_DAY = "yyyy-MM-dd";
    private static final String FORMAT_YEAR_MON = "yyyy-MM";
    private static final String FORMAT_YEARMONDAY = "yyMMdd";
    private static final String FORMAT_HMS = "HH:mm:ss";
    private static final String FORMAT_YEARMONDAYDAY = "yyyyMMdd";
    public static final String[] dayNames = { "0", "1", "2", "3", "4", "5", "6" };
    public static final String HM = "HH:mm";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final SimpleDateFormat DF_SHORT_CN = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat SDF_HM = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DF_CN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final int REALTIME = 0;
    public static final int HOURLY = 1;
    public static final int DAILY = 2;
    public static final int BIWEEKLY = 3;
    public static final int WEEKLY = 4;
    public static final int MONTHLY = 5;
    public static final int QUARTLY = 6;
    public static final int BIYEARLY = 7;
    public static final int YEARLY = 8;
    public static final long day = 86400000L;

    public static Date getCurrentDate()
    {
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime()
    {
        return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTimes()
    {
        return getCurrentTime("yyyyMMddHHmmss");
    }

    public static String getYearMonthDate()
    {
        return getCurrentTime("yyyy-MM-dd");
    }

    public static String getYearMonth()
    {
        return getCurrentTime("yyyy-MM");
    }

    public static String getHms()
    {
        return getCurrentTime("HH:mm:ss");
    }

    public static String getYearMonthDateBill(Date date)
    {
        try
        {
            return
                    date.getYear() + 1900 + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日";
        }
        catch (Exception e) {}
        return null;
    }

    public static String getYearMonthDate(Date date)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(Long.valueOf(date.getTime()));
        }
        catch (Exception e) {}
        return null;
    }

    public static String getCurrentTime(Date date)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(Long.valueOf(date.getTime()));
        }
        catch (Exception e) {}
        return null;
    }

    public static String getDateFormat(Date date, String format)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(Long.valueOf(date.getTime()));
        }
        catch (Exception e) {}
        return null;
    }

    public static String getYearMonthDateYYMMDD()
    {
        return getCurrentTime("yyMMdd");
    }

    public static String getYearMonthDateYYYYMMDD()
    {
        return getCurrentTime("yyyyMMdd");
    }

    public static Date getYearMonthDay()
    {
        return getCurrentDate("yyyy-MM-dd");
    }

    public static Date getCurrentDate(String format)
    {
        try
        {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateFormat.format(date.getTime()));
        }
        catch (Exception e) {}
        return null;
    }

    public static Date getDate(String date, String pattern)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(date);
        }
        catch (Exception e) {}
        return null;
    }

    public static Date parseString2Date(String date)
    {
        return getDate(date, "yyyy-MM-dd");
    }

    public static String getCurrentTime(String format)
    {
        try
        {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date.getTime());
        }
        catch (Exception e) {}
        return "";
    }

    public static String formatDate(Date date, String format)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(Long.valueOf(date.getTime()));
        }
        catch (Exception e) {}
        return "";
    }

    public static long getTimeInMillis()
    {
        Calendar date = Calendar.getInstance();
        return date.getTimeInMillis();
    }

    public static String getDateAddDays(int days)
    {
        try
        {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            date.add(5, days);
            return dateFormat.format(date.getTime());
        }
        catch (Exception e) {}
        return null;
    }

    public static String getDateAddMonths(int months)
    {
        try
        {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            date.add(2, months);
            return dateFormat.format(date.getTime());
        }
        catch (Exception e) {}
        return null;
    }

    public static String lastToday(String begindate, String enddate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date bdate = null;
        Date edate = null;
        try
        {
            bdate = df.parse(begindate);
            edate = df.parse(enddate);
            long times = edate.getTime() - bdate.getTime();
            long days = times / 86400000L;
            cal.setTime(bdate);
            cal.add(6, -(int)days);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return df.format(cal.getTime());
    }

    public static String lastYear(String mdate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try
        {
            date = df.parse(mdate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(1, -1);
        return df.format(cal.getTime());
    }

    public static String lastToday(String mdate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try
        {
            date = df.parse(mdate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(6, -1);
        return df.format(cal.getTime());
    }

    public static String lastWeekToday(String mdate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try
        {
            date = df.parse(mdate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(3, -1);
        return df.format(cal.getTime());
    }

    public static String lastMonthToday(String mdate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try
        {
            date = df.parse(mdate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(2, -1);
        return df.format(cal.getTime());
    }

    public static String weekFirstDay(String mdate)
            throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = df.parse(mdate);
        cal.setTime(date);
        cal.set(7, 1);
        return df.format(cal.getTime());
    }

    public static String weekLastDay(String mdate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try
        {
            date = df.parse(mdate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.set(7, 7);
        return df.format(cal.getTime());
    }

    public static String monthEndDay(String mdate)
            throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = df.parse(mdate);
        cal.setTime(date);

        int endDay = cal.getActualMaximum(5);
        cal.set(5, endDay);
        return df.format(cal.getTime());
    }

    public static String monthStartDay(String mdate)
            throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = df.parse(mdate);
        cal.setTime(date);

        int endDay = cal.getActualMinimum(5);
        cal.set(5, endDay);
        return df.format(cal.getTime());
    }

    public static int getCurrentMonthDays()
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(5, 1);
            calendar.roll(5, -1);
            return calendar.get(5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getCurrentMonthDays(String yearMonth)
    {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(yearMonth);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(5, 1);
            calendar.roll(5, -1);
            return calendar.get(5);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args)
    {
        Date data = new Date();

        System.out.println(getCurrentTime(data));
    }

    public static int getCurrentDays()
    {
        Calendar a = Calendar.getInstance();
        int currDay = a.get(5);
        return currDay;
    }

    public static String getWeek(String curDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(curDate));
            int dayOfWeek = calendar.get(7);
            return dayNames[(dayOfWeek - 1)];
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean getCompareDate(String startDate, String endDate)
    {
        DateFormat df = DateFormat.getDateInstance();
        try
        {
            if ((startDate != null) && (endDate != null) && (!"".equals(startDate)) && (!"".equals(endDate)))
            {
                long date1 = df.parse(startDate).getTime();
                long date2 = df.parse(endDate).getTime();
                if (date1 > date2) {
                    return true;
                }
                return false;
            }
            return false;
        }
        catch (ParseException e) {}
        return false;
    }

    public static boolean getCompareDate(String date)
    {
        return getCompareDate(date, getYearMonthDate());
    }

    public static Long getDaysBetween(String startDate, String endDate)
    {
        DateFormat df = DateFormat.getDateInstance();


        long jiange = 0L;
        try
        {
            long date1 = df.parse(startDate).getTime();
            long date2 = df.parse(endDate).getTime();
            if (date1 > date2) {
                jiange = date1 - date2;
            } else {
                jiange = date2 - date1;
            }
        }
        catch (ParseException e)
        {
            return Long.valueOf(0L);
        }
        long date2;
        long date1;
        return Long.valueOf(jiange / 86400000L);
    }

    public static Long getDaysMinusBetween(String startDate, String endDate)
    {
        long jiange = 0L;
        try
        {
            Date start = parseString2Date(startDate);
            Date end = parseString2Date(endDate);
            jiange = end.getTime() - start.getTime();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Long.valueOf(0L);
        }
        return Long.valueOf(jiange / 86400000L);
    }

    public static String format(Calendar cal)
    {
        return format(cal.getTime());
    }

    public static String format(Calendar cal, String pattern)
    {
        return format(cal.getTime(), pattern);
    }

    public static String format(Calendar cal, DateFormat df)
    {
        return format(cal.getTime(), df);
    }

    public static String format(Date date)
    {
        return format(date, DF_CN);
    }

    public static String format(Date date, String pattern)
    {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return format(date, df);
    }

    public static String format(long ts, DateFormat df)
    {
        return format(new Date(ts), df);
    }

    public static String format(long ts, String format, Locale local)
    {
        SimpleDateFormat df = new SimpleDateFormat(format, local);
        return format(new Date(ts), df);
    }

    public static String format(Date date, DateFormat df)
    {
        if (date == null) {
            return "";
        }
        return getRealDateFormat(df).format(date);
    }

    public static long getGapMinutes(long minsec)
    {
        return minsec / 60000L;
    }

    public static long getGapMinByAddtime(long addtime)
    {
        return getGapMinByAddtime(addtime, System.currentTimeMillis());
    }

    public static long getGapMinByAddtime(long addtime, long current)
    {
        return getGapMinutes(current - addtime);
    }

    public static String getGapMinStirngByAddtime(long addtime, long current)
    {
        return getMinStirngBySubTime(getGapMinutes(current - addtime));
    }

    public static String getMinStirngBySubTime(long min)
    {
        long hour = min / 60L;
        long restmin = min % 60L;
        return (hour > 0L ? hour + "时" : "") + restmin + "分";
    }

    public static Calendar parseDateString(String str, String format)
    {
        if (str == null) {
            return null;
        }
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat(format);
        try
        {
            date = getRealDateFormat(df).parse(str);
        }
        catch (Exception localException) {}
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String getToday()
    {
        return format(new Date());
    }

    public static Date getYesterday()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(5, -1);

        return cal.getTime();
    }

    public static Calendar getFirstDayOfMonth()
    {
        Calendar cal = getNow();
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);

        return cal;
    }

    public static Calendar getFirstDayOfMonth(int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(2, month);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);

        return cal;
    }

    public static Calendar getNow()
    {
        return Calendar.getInstance();
    }

    public static Date addMonth(Date date, int n)
            throws Exception
    {
        Calendar cal = getNow();
        cal.setTime(date);
        cal.add(2, n);
        return cal.getTime();
    }

    public static int daysBetween(Date returnDate)
    {
        return daysBetween(null, returnDate);
    }

    public static long tirmDay(Calendar time)
    {
        time.set(11, 0);
        time.set(12, 0);
        time.set(13, 0);
        time.set(14, 0);
        return time.getTimeInMillis();
    }

    public static int daysBetween(Date now, Date returnDate)
    {
        if (returnDate == null) {
            return 0;
        }
        Calendar cNow = getNow();
        Calendar cReturnDate = getNow();
        if (now != null) {
            cNow.setTime(now);
        }
        cReturnDate.setTime(returnDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long nowMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        return millisecondsToDays(nowMs - returnMs);
    }

    private static int millisecondsToDays(long intervalMs)
    {
        return (int)(intervalMs / 86400000L);
    }

    private static void setTimeToMidnight(Calendar calendar)
    {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
    }

    public static String formatDate(Object obj, String format)
    {
        String result = "";
        try
        {
            Date date = (Date)obj;
            result = format(date, format);
        }
        catch (Exception localException) {}
        return result;
    }

    public static String formatDate(Object obj)
    {
        return formatDate(obj, "yyyy-MM-dd");
    }

    public static String getSunday(String date)
    {
        Calendar c = parseDateString(date, "yyyy-MM-dd");
        int dayofweek = c.get(7) - 1;
        if (dayofweek == 0) {
            dayofweek = 0;
        }
        c.add(5, -dayofweek);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    public static Calendar getStartTime(Calendar calendar, int interval)
    {
        if (calendar == null) {
            return null;
        }
        Calendar fromtime = Calendar.getInstance();
        fromtime.setTimeZone(calendar.getTimeZone());
        fromtime.set(14, 0);
        int y = calendar.get(1);
        int m = calendar.get(2);
        int d = calendar.get(5);
        if ((interval == 2) || (interval == 1))
        {
            fromtime.set(y, m, d, 0, 0, 0);
        }
        else if (interval == 4)
        {
            fromtime.set(y, m, d, 0, 0, 0);
            fromtime.add(5, 2 - fromtime.get(7));
        }
        else if (interval == 5)
        {
            fromtime.set(y, m, 1, 0, 0, 0);
        }
        else if (interval == 3)
        {
            fromtime.set(y, m, d, 0, 0, 0);
            fromtime.add(3, -1 * (fromtime.get(3) + 1) % 2);
            fromtime.add(5, 1 - fromtime.get(7));
        }
        else if (interval == 8)
        {
            fromtime.set(y, m, d, 0, 0, 0);
        }
        else if (interval == 6)
        {
            fromtime.set(y, m / 3 * 3, 1, 0, 0, 0);
        }
        else if (interval == 7)
        {
            fromtime.set(y, m / 6 * 6, 1, 0, 0, 0);
        }
        return fromtime;
    }

    public static Calendar getEndTime(Calendar calendar, int interval)
    {
        if (calendar == null) {
            return null;
        }
        Calendar endtime = Calendar.getInstance();
        endtime.setTimeZone(calendar.getTimeZone());
        endtime.set(14, 0);
        int y = calendar.get(1);
        int m = calendar.get(2);
        int d = calendar.get(5);
        if (interval == 2)
        {
            endtime.set(y, m, d, 0, 0, 0);
            endtime.add(5, 1);
        }
        else if (interval == 4)
        {
            endtime.set(y, m, d, 0, 0, 0);
            endtime.add(5, 9 - endtime.get(7));
        }
        else if (interval == 5)
        {
            endtime.set(y, m, 1, 0, 0, 0);
            endtime.add(2, 1);
        }
        else if (interval == 3)
        {
            endtime.set(y, m, d, 0, 0, 0);
            endtime.add(3, endtime.get(3) % 2);
            endtime.add(5, 8 - endtime.get(7));
        }
        else if (interval == 8)
        {
            endtime.set(y + 1, m, d, 0, 0, 0);
        }
        else if (interval == 6)
        {
            if (m / 3 == 3) {
                endtime.set(y + 1, 0, 1, 0, 0, 0);
            } else {
                endtime.set(y, (m / 3 + 1) * 3, 1, 0, 0, 0);
            }
        }
        else if (interval == 7)
        {
            if (m / 6 == 1) {
                endtime.set(y + 1, 0, 1, 0, 0, 0);
            } else {
                endtime.set(y, (m / 6 + 1) * 6, 1, 0, 0, 0);
            }
        }
        return endtime;
    }

    public static long getDays(String startdate, String enddate, String format)
    {
        Calendar s1 = parseDateString(startdate, format);
        Calendar s2 = parseDateString(enddate, format);
        if ((s1 != null) && (s2 != null)) {
            return getDays(s1.getTimeInMillis(), s2.getTimeInMillis());
        }
        return 0L;
    }

    public static long getMonthDays(String date, String format)
    {
        Calendar cal = parseDateString(date, format);
        if (cal != null)
        {
            Calendar starttime = getStartTime(cal, 5);
            Calendar endtime = getEndTime(cal, 5);
            return getDays(starttime.getTimeInMillis(), endtime.getTimeInMillis());
        }
        return 0L;
    }

    public static long getDays(long startdate, long enddate)
    {
        return (enddate - startdate) / 86400000L;
    }

    public static String format(Long l, String pattern)
    {
        if (l == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(l.longValue());
        return format(cal.getTime(), pattern);
    }

    public static DateFormat getRealDateFormat(DateFormat df)
    {
        return df == null ? new SimpleDateFormat("yyyy-MM-dd", Locale.US) : df;
    }

    public static String daysBetween(String bdate, String smdate, String formate)
            throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat(formate);
        Date now = null;
        Date date = null;
        String result = "";
        try
        {
            now = df.parse(smdate);
            date = df.parse(bdate);
            long l = now.getTime() - date.getTime();
            long day = l / 86400000L;
            long hour = l / 3600000L - day * 24L;
            long min = l / 60000L - day * 24L * 60L - hour * 60L;
            long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;

            result = day + "天";
            result = result + (hour == 0L ? "" : new StringBuilder(String.valueOf(hour)).append("小时").toString());
            result = result + (min == 0L ? "" : new StringBuilder(String.valueOf(min)).append("分").toString());
            result = result + (s == 0L ? "" : new StringBuilder(String.valueOf(s)).append("秒").toString());
        }
        catch (ParseException e)
        {
            result = "";
        }
        return result;
    }

    public static List<String> getCurrentMonths(int n)
    {
        List<String> months = new ArrayList();
        for (int i = n - 1; i >= 0; i--)
        {
            Calendar cal = Calendar.getInstance();
            cal.add(2, i - n + 1);
            int month = cal.get(2) + 1;
            int year = cal.get(1);
            months.add(year + (month < 10 ? "0" + month : new StringBuilder().append(month).toString()));
        }
        return months;
    }

    public static List<String> get12MonthsInCurrentYear(int year)
    {
        List<String> months = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            months.add(year + "-" + (i < 10 ? "0" + i : new StringBuilder().append(i).toString()));
        }
        return months;
    }

    public static Date getCurrYearLastDay(int year)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        calendar.roll(6, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    public static Date firstDayInNextMonth(Date day)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(2, c.get(2) + 1);
        c.set(5, 1);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        return c.getTime();
    }

    public static int getDateInWeek(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(7);
    }

    public static Date getDateNextDay(Date date, int n)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, n);
        return c.getTime();
    }

    public static Date getDateNextMonth(int n)
    {
        Calendar now = Calendar.getInstance();
        now.set(2, now.get(2) + n);
        now.set(11, 0);
        now.set(12, 0);
        now.set(13, 0);
        return now.getTime();
    }

    public static int getDateOfMoth()
    {
        return Calendar.getInstance().get(5);
    }

    public static List<Date> getDatesInMonth()
    {
        List<Date> dates = new ArrayList();

        Calendar c = Calendar.getInstance();
        c.set(5, 1);

        int maxDay = c.getActualMaximum(5);
        for (int i = 1; i <= maxDay; i++)
        {
            c.set(5, i);
            dates.add(c.getTime());
        }
        return dates;
    }

    public static int getMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(2) + 1;
    }

    public static int getYear()
    {
        return Calendar.getInstance().get(1);
    }

    public static Date getMonthEnd()
    {
        int length = getDateOfMoth();
        Calendar c = Calendar.getInstance();
        c.set(5, length);
        c.set(10, 24);
        c.set(12, 0);
        c.set(13, 0);
        return c.getTime();
    }

    public static Date getMonthEnd(Date date)
    {
        if (date == null) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(2, start.get(2) + 1);
        start.set(5, 1);
        start.set(10, 0);
        start.set(12, 0);
        start.set(13, 0);
        return start.getTime();
    }

    public static Date getMonthStart(Date date)
    {
        if (date == null) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(5, 1);
        start.set(10, 0);
        start.set(12, 0);
        start.set(13, 0);
        return start.getTime();
    }

    public static Date getMorning()
    {
        return getMorning(new Date());
    }

    public static Date getMorning(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        return c.getTime();
    }

    public static Date getMorningNextDate(int n)
    {
        Calendar now = Calendar.getInstance();
        now.set(5, now.get(5) + n);
        now.set(11, 0);
        now.set(12, 0);
        now.set(13, 0);
        return now.getTime();
    }

    public static Date getNextMonth(int nextStep)
    {
        Calendar c = Calendar.getInstance();
        int m = c.get(2);
        c.set(2, m + nextStep);
        return c.getTime();
    }

    public static Date getNextMonthToday(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(2, c.get(2) + 1);
        return c.getTime();
    }

    public static int getYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(1);
    }

    public static Date getYearEnd(int year)
    {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, 11);
        c.set(5, 31);
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        return c.getTime();
    }

    public static Date getYearStart(int year)
    {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, 0);
        c.set(5, 1);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        return c.getTime();
    }

    public static boolean month(Calendar theDate)
    {
        boolean flag = false;
        int NowDate = theDate.get(5);
        int maxDay = theDate.getActualMaximum(5);
        if (NowDate == maxDay) {
            flag = true;
        }
        return flag;
    }

    public static long daysOfTwo(Date fDate, Date oDate)
    {
        Calendar calf = Calendar.getInstance();
        calf.setTime(fDate);
        calf.add(10, 8);
        Date date1 = calf.getTime();

        Calendar calo = Calendar.getInstance();
        calo.setTime(oDate);
        calo.add(10, 8);
        Date date2 = calo.getTime();

        long day1 = date1.getTime() / 86400000L;
        long day2 = date2.getTime() / 86400000L;




        return day2 - day1;
    }

    /**
     * 获取当前时间零点时间
     *
     * @return
     */
    public static Date getStartCurrentDate() {

        Calendar instanceCa = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = new Date();
        try {
            parse = simpleDateFormat2.parse(instanceCa.get(Calendar.YEAR) + "-" + (instanceCa.get(Calendar.MONTH) + 1)
                    + "-" + instanceCa.get(Calendar.DAY_OF_MONTH) + " 00:00:00");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return parse;
    }

    public  static Date getEndCurrentDate() {
        Calendar instanceCa = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = new Date();
        try {
            parse = simpleDateFormat2.parse(instanceCa.get(Calendar.YEAR) + "-" + (instanceCa.get(Calendar.MONTH) + 1)
                    + "-" + instanceCa.get(Calendar.DAY_OF_MONTH) + " 23:59:59");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 获取过期时间 秒，到今日的凌晨过期
     * @return
     */
    public static  Long getExpiiredSeconds() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime tommorrow = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                localDateTime.getDayOfMonth(), 23, 59, 59);
        ZoneId zone = ZoneId.systemDefault();
        Long expiredSeconds = tommorrow.atZone(zone).toInstant().toEpochMilli() / 1000;
        return expiredSeconds;
    }

    public static String currentYYYYMMDD(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }
    public static String currentYYYYMM(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(new Date());
    }
    public static String currentYYYYMM(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(date);
    }
    public static String currentYYYYMMDDHHmmss(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
    public static String currentYYYYMMDDHHmmssSSS(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(new Date());
    }
    public static String currentYYYYMMDDHHmmss(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }
    public static String currentYYYY_MM_DDHHmmss(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
    public static String currentYYMMDDHHmm(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmm");
        return format.format(new Date());
    }
    public static Date currentDate(){
        return new Date();
    }
    /**
     * 将yyyyMMddHHmmss格式的时间字符串转换为Date
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date getDateyyyyMMddHHmmss(String dateStr) throws Exception {
        try {
            return getDateByStr(dateStr, "yyyyMMddHHmmss");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 得到昨天
     * @return
     */
    public static Date getYestoday() {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return nowday.getTime();
    }
    /**
     * 得到上个月
     * @return
     */
    public static Date getLastMonth() {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.MONTH, -1);//日期回滚一天，也就是最后一天
        return nowday.getTime();
    }

    /**
     * 得到昨天
     * @return
     */
    public static String getYestodayYYYYMMDD() throws Exception {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return getStrByDate(nowday.getTime(), "yyyyMMdd");
    }
    /**
     * 得到今天YYYYMMDD
     * @return
     */
    public static String getTodayYYYYMMDD() throws Exception {
        Calendar nowday = Calendar.getInstance();
        return getStrByDate(nowday.getTime(), "yyyyMMdd");
    }
    /**
     * 得到今天YYYY_MM_DD
     * @return
     */
    public static String getTodayYYYY_MM_DD() throws Exception {
        Calendar nowday = Calendar.getInstance();
        return getStrByDate(nowday.getTime(), "yyyy-MM-dd");
    }
    /**
     * 得到昨天
     * @return
     */
    public static String getYestodayYYYY_mm_dd() throws Exception {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return getStrByDate(nowday.getTime(), "yyyy-MM-dd");
    }
    /**
     * 得到昨天
     * @return
     */
    public static Date getYestodayByDate(Date date) throws Exception {
        Calendar nowday = Calendar.getInstance();
        nowday.setTime(date);
        nowday.add(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return nowday.getTime();
    }
    /**
     * 得到昨天0点
     * @return
     */
    public static Date getYestoday0() throws Exception {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.DATE, -1);
        String yesDay = getStrByDate(nowday.getTime(), "yyyy-MM-dd");
        String yesDay0 = yesDay +" 00:00:00";
        return getDateByStr(yesDay0,"yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 得到昨天24点
     * @return
     */
    public static Date getYestoday24() throws Exception {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        String yesDay = getStrByDate(nowday.getTime(), "yyyy-MM-dd");
        String yesDay0 = yesDay +" 23:59:59";
        return getDateByStr(yesDay0,"yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 根据给定的字符串如：yyyy-MM-dd HH:mm:ss，（必须是这种格式）
     * 返回一个日期日期形式
     *
     * @param strDate
     * 			要抛析的字符串,且字符串的形式必须：2007-09-10 07:00:00
     * @return  将字符串抛析成日期的格式返回
     * @throws Exception
     */
    public final static Date getDateByStr(String strDate,String format) throws Exception{
        try{
            if(StringUtils.isEmpty(strDate) || StringUtils.isEmpty(format)){
                return null;
            }else{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                return simpleDateFormat.parse(strDate);
            }
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * 根据给定的日期，返回给定的字符串，
     * 返回 字符串的形式是：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * 			要格式化的日期
     * @return  将日期格式化后返回的字符串，以这中格式返回：yyyy-MM-dd HH:mm:ss
     * @throws Exception
     */
    public final static String getStrByDate(Date date,String format) throws Exception{
        try{
            if(date == null || StringUtils.isEmpty(format)){
                return null;
            }else{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                return simpleDateFormat.format(date);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 得到每月最后一天
     * @return
     */
    public static Date getLastDayOfMonth() {
        Calendar nowday = Calendar.getInstance();

        nowday.set(Calendar.DATE, 1);//把日期设置为当月第一天
        nowday.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return nowday.getTime();
    }

    /**
     * 得到此日期的最后一天
     *
     * @param d
     *            日期
     * @return
     * @throws Exception
     */
    public static Date getLastDayByDate(Date d) {
        Calendar newday = Calendar.getInstance();
        newday.setTime(d);
        int lastday;
        int month = newday.get(Calendar.MONTH);
        do {
            lastday = newday.get(Calendar.DAY_OF_MONTH);
            newday.add(Calendar.DAY_OF_MONTH, 1);
        } while (newday.get(Calendar.MONTH) == month);
        newday.set(Calendar.MONTH, month);

        newday.set(Calendar.DAY_OF_MONTH, lastday);
        return newday.getTime();
    }
    /**
     * 将 yyyyMMdd 的字符窜 转化成 yyyy-MM-dd
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static String  formatyyyyMMdd(String  dateString ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date=	simpleDateFormat.parse(dateString);
        SimpleDateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd");
        return    formatStr.format(date);
    }

    /**
     * 将 yyyyMMdd 的字符窜 转化成 yyyy-MM-dd HH:mm:ss
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static String  formatyyyyMMddHHmmss(String  dateString ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date=	simpleDateFormat.parse(dateString);
        SimpleDateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return    formatStr.format(date);
    }
    /**
     * 得到当天零点
     */
    public static Date getDayZero(Date date) throws Exception {
        String yyyyMMdd = formatyyyyMMdd(date);
        String zero = yyyyMMdd+" 00:00:00";
        return getDateByStr(zero,"yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 根据格式化字符串得到当天零点
     */
    public static Date getDayZeroByStr(String dateStr,String formatStr) throws Exception {
        if(dateStr == null || "".equals(dateStr)) {
            return null;
        }
        return getDayZero(getDateByStr(dateStr,formatStr));
    }
    /**
     * 根据日期格式字符串得到当天零点
     */
    public static Date getDayEndByStr(String dateStr,String formatStr) throws Exception {
        if(dateStr == null || "".equals(dateStr)) {
            return null;
        }
        return getDayEnd(getDateByStr(dateStr,formatStr));
    }
    /**
     * 得到最后一秒
     */
    public static Date getDayEnd(Date date) throws Exception {
        String yyyyMMdd = formatyyyyMMdd(date);
        String end = yyyyMMdd+" 23:59:59";
        return getDateByStr(end,"yyyy-MM-dd HH:mm:ss");
    }
    /*
        格式化日期为yyyy-MM-dd格式
     */
    public static String formatyyyyMMdd(Date date) {
        SimpleDateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd");
        return    formatStr.format(date);
    }
    //   当前日期加减n天后的日期，返回String   (yyyy-mm-dd)
    public static  String   nDaysAftertoday(int   n)   {
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
        Calendar   rightNow   =   Calendar.getInstance();
        //rightNow.add(Calendar.DAY_OF_MONTH,-1);
        rightNow.add(Calendar.DAY_OF_MONTH,+n);
        return   df.format(rightNow.getTime());
    }

    //   当前日期加减n天后的日期，返回String   (yyyy-mm-dd)
    public static  Date   nDaysAfterNowDate(int   n)   {
        Calendar   rightNow   =   Calendar.getInstance();
        //rightNow.add(Calendar.DAY_OF_MONTH,-1);
        rightNow.add(Calendar.DAY_OF_MONTH,+n);
        return   rightNow.getTime();
    }

    //   给定一个日期型字符串，返回加减n天后的日期型字符串
    public static  String   nDaysAfterOneDateString(String   basicDate,int   n)   {
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
        Date   tmpDate   =   null;
        try   {
            tmpDate   =   df.parse(basicDate);
        }
        catch(Exception   e){
            //   日期型字符串格式错误
        }
        long   nDay=(tmpDate.getTime()/(24*60*60*1000)+1+n)*(24*60*60*1000);
        tmpDate.setTime(nDay);

        return   df.format(tmpDate);
    }

    //   给定一个日期，返回加减n天后的日期
    public static  Date   nDaysAfterOneDate(Date   basicDate,int   n)   {
        long   nDay=(basicDate.getTime()/(24*60*60*1000)+1+n)*(24*60*60*1000);
        basicDate.setTime(nDay);

        return   basicDate;
    }

    //   计算两个日期相隔的天数
    public static  int   nDaysBetweenTwoDate(Date   firstDate,Date   secondDate)   {
        int   nDay=(int)((secondDate.getTime()-firstDate.getTime())/(24*60*60*1000));
        return   nDay;
    }
    /**
     * 获得下个月第一天的日期
     * @return
     */
    public static Date getLastMonthFirstDay(Date date){
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);
        //加一个月
        lastDate.add(Calendar.MONTH,1);
        //把日期设置为当月第一天
        lastDate.set(Calendar.DATE, 1);
        lastDate.set(Calendar.HOUR_OF_DAY ,0);
        lastDate.set(Calendar.MINUTE,0);
        lastDate.set(Calendar.SECOND,0);
        lastDate.set(Calendar.MILLISECOND, 0);
        return lastDate.getTime();
    }
    /**
     * 增加月份
     */
    public static Date addMonths(Date baseDate,int months) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(baseDate);
        lastDate.add(Calendar.MONTH,months);
        return lastDate.getTime();
    }
    /**
     * 计算相差月份
     */
    public static int betweenMonth(Date startDate,Date endDate) {
        if(startDate.getTime()>endDate.getTime()) {
            throw new RuntimeException("计算月份时发生异常,传入的日期中，起始月份比终止月份大");
        }
        int startYear = startDate.getYear();
        int startMonths = startDate.getMonth();
        int endYear = endDate.getYear();
        int endMonths = endDate.getMonth();
        int monthsub = (endYear-startYear)*12+(endMonths-startMonths);
        return monthsub;
    }
    /**
     * 根据日期字符串获取年份
     * @throws ParseException
     */
    public static int getYearByDateStr(String dateStr,String formatStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        Date date=	simpleDateFormat.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        return year;
    }

    /**
     * 根据日期字符串获取月份
     * @throws ParseException
     */
    public static String getMonthByDateStr(String dateStr,String formatStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        Date date=	simpleDateFormat.parse(dateStr);
        int month = date.getMonth()+1;
        String monthStr = null;
        if(month<=9){
            monthStr = "0" + month;
        }else{
            monthStr = month + "";
        }
        return  monthStr;
    }
    /**
     * 用多种日期格式解析时间
     * @throws ParseException
     */
    public static Date getDateByManyForm(String dateStr,String[] formatStrAry) {
        for (String formatStr : formatStrAry) {
            try {
                return getDateByStr(dateStr, formatStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("无法正确解析日期字符串,"+dateStr);
    }
    /**
     * 格式化日期(取当月10号)
     * @return
     * @throws Exception
     */
    public static Date getDateFormatByStr() throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-");
        String str=sdf.format(new Date());
        str+="10 00:00:00";
        Date date=getDateByStr(str,"yyyy-MM-dd HH:mm:ss");
        return date;
    }

    /**
     * 获得当前时间
     * @return
     */
    public static String currentYYYY_MM_DDHHmmssSSS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");
        return format.format(new Date());
    }

    /**
     * 将yyyy-MM-DD HH24:mm:ss格式的时间字符串转换为Date
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date getDateyyyy_MM_ddHHmmss(String dateStr) throws Exception {
        try {
            return getDateByStr(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     *
     * @param timeStamp
     * @return
     */
    public static Date getDateByTimeStamp(Timestamp timeStamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeDate = null;
        try {
            timeDate = dateFormat.parse(timeStamp.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeDate;

    }
    /**
     *
     * @return
     */
    public static Date getNextEndTime(int timeIntervalParam) {
        SimpleDateFormat sim1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long sys=System.currentTimeMillis()-timeIntervalParam*60*1000;
        Date nextDate=new Date(sys);
        String format = sim1.format(nextDate);
        try {
            nextDate=sim1.parse(format);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextDate;
    }

    /**
     *
     * @param timeIntervalParam 上次结束时间
     * @return
     */
    public static Date getEndTime(int timeIntervalParam) {
        SimpleDateFormat sim1= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long sys=System.currentTimeMillis()-timeIntervalParam*60*1000;
        Date nextDate=new Date(sys);
        String format = sim1.format(nextDate);
        try {
            nextDate=sim1.parse(format);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextDate;
    }

    /**
     * 获取yyyy-MM-DD HH24:mm:ss格式Date
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date getDateForyyyy_MM_ddHHmmss(String dateStr) throws Exception {
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sim1.parse(dateStr);
    }

    /**
     * 获取yyyy-MM-dd格式Date
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date getDateForyyyy_MM_dd(String dateStr) throws Exception {
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd");
        return sim1.parse(dateStr);
    }
    /**
     * 获得N天后0点
     * @param day 天
     * @return
     * @throws Exception
     */
    public static Date getNDay0(int day) throws Exception {
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        String nDay = getStrByDate(calendar.getTime(), "yyyy-MM-dd");
        String nDay0 = nDay +" 00:00:00";
        return getDateByStr(nDay0,"yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 获得下个月10号
     * @return
     */
    public static Date getNextMonth10()throws Exception{
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, 1);
        calendar.set(calendar.DATE, 10);
        String nextMonthDay = getStrByDate(calendar.getTime(), "yyyy-MM-dd");
        String nextMonthDay10 = nextMonthDay + " 00:00:00";
        return getDateByStr(nextMonthDay10, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 获得当月1号
     * @return
     */
    public static Date getMonthNo1()throws Exception{
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //把日期设置为当月第一天
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY ,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    /**
     * 判断时间大小
     * @param time
     * @return
     */
    public static boolean isTimeSize(String time)throws Exception{
        if(getMonthNo1().getTime()>getDateByStr(time, "yyyy-MM-dd HH:mm:ss").getTime()){
            return true;
        }
        return false;
    }

    /**
     * 获取年份
     * @param offsetYearNum
     * @return
     * @throws Exception
     */
    public static String getYearNum(int offsetYearNum) throws Exception {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR)-offsetYearNum;//获取年份
        return String.valueOf(year);
    }
    /**
     * 得到当前时间多少天之前24点
     * @return
     * @throws Exception
     */
    public static Date getdaysAgo24(int day) throws Exception {
        Calendar nowday = Calendar.getInstance();
        nowday.add(Calendar.DATE, -day);
        String yesDay = getStrByDate(nowday.getTime(), "yyyy-MM-dd");
        String yesDay0 = yesDay +" 23:59:59";
        return getDateByStr(yesDay0,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据普通时间(yyyy-MM-dd HH:mm:ss)获取时间戳
     * @param formatStr
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Long getUnixTimestampByDateStr(String formatStr, String dateStr) throws Exception {
        SimpleDateFormat format =  new SimpleDateFormat(formatStr);
        Long unixTimestamp = format.parse(dateStr).getTime();
        return unixTimestamp;
    }

    /**
     * 根据时间戳(带毫秒)获取普通时间(yyyy-MM-dd HH:mm:ss SSS)
     * @param formatStr
     * @param unixTimestamp
     * @return
     * @throws Exception
     */
    public static String getDateStrByUnixTimestamp(String formatStr, long unixTimestamp) throws Exception {
        SimpleDateFormat formatInfo =  new SimpleDateFormat(formatStr);
        String dateStr = formatInfo.format(new Date(unixTimestamp));
        return dateStr;
    }
    /**
     * 方法: isFormat <br>
     * 描述: 时间格式校验 <br>
     * 作者: 李少青 lsq_job@163.com<br>
     * 时间: 2016-12-20 下午4:18:48
     * @param formatStr
     * @param dateStr
     * @return
     */
    public static boolean isFormat(String formatStr,String dateStr){
        SimpleDateFormat formatInfo =  new SimpleDateFormat(formatStr);
        try {
            formatInfo.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
//    public static void main(String[] args) throws Exception {
//		Calendar nowday = Calendar.getInstance();
//		nowday.add(Calendar.DATE, -7);// 日期回滚一天，也就是最后一天
//		String yesDay = getStrByDate(nowday.getTime(), "yyyy-MM-dd");
//		String yesDay0 = yesDay + " 23:59:59";
//		System.out.println(yesDay0);1451528963
//        String formatStr = "yyyy-MM-dd HH:mm:ss";
//        System.out.println(isFormat(formatStr, "2016/12/20 16:20:20"));
//		String time="2015-12-22 13:56:37";
//		long unixTimestamp = getUnixTimestampByDateStr(formatStr, time);
//		System.out.println(unixTimestamp);
//		long unixTimestamp = 1451438341175L;
//		String dateStr = getDateStrByUnixTimestamp(formatStr, unixTimestamp);
//		System.out.println(dateStr);
//    }
}
