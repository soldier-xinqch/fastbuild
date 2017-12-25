package com.fastbuild;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-28
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

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
	 * @param endtime上次结束时间
	 * @param sim1
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
	 * @param endtime上次结束时间
	 * @param sim1 
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
     * @param time
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
	public static void main(String[] args) throws Exception {
//		Calendar nowday = Calendar.getInstance();
//		nowday.add(Calendar.DATE, -7);// 日期回滚一天，也就是最后一天
//		String yesDay = getStrByDate(nowday.getTime(), "yyyy-MM-dd");
//		String yesDay0 = yesDay + " 23:59:59";
//		System.out.println(yesDay0);1451528963
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		System.out.println(isFormat(formatStr, "2016/12/20 16:20:20"));
//		String time="2015-12-22 13:56:37"; 
//		long unixTimestamp = getUnixTimestampByDateStr(formatStr, time);
//		System.out.println(unixTimestamp);
//		long unixTimestamp = 1451438341175L;
//		String dateStr = getDateStrByUnixTimestamp(formatStr, unixTimestamp);
//		System.out.println(dateStr);
	}

}
