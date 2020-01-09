package com.data.masterandslave.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by chenqingwu on 2018/9/11.
 */
public class LocalDateUtil {

	public static Date convertToDate(LocalDate localDate){
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
		return  Date.from(zdt.toInstant());
	}

	public static Date convertToDate(LocalDateTime localDateTime){
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		return Date.from(zdt.toInstant());
	}

	public static LocalDate convertToLocalDate(Date date){
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return instant.atZone(zoneId).toLocalDate();
	}

	public static LocalDateTime convertToLocalDateTime(Date date){
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return instant.atZone(zoneId).toLocalDateTime();
	}

	public static String formatDateToStringYMDHMS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	public static String formatDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static String formatDateStrings(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	public static String formatDateWithPattern(Date date, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 获取当前月第一天的日期
	 * @param
	 * @return
	 */
	public static Date getFirstDayOfMonth(LocalDate localDate){

		LocalDate date = localDate.with(TemporalAdjusters.firstDayOfMonth());
		Date from = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return from;
	}

	/**
	 * 获取当前月最后一天的日期
	 * @param
	 * @return
	 */
	public static Date getLastDayOfMonth(LocalDate localDate){
		LocalDate date = localDate.with(TemporalAdjusters.lastDayOfMonth());
		Date from = Date.from(date.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
		return from;
	}

	public static Date getStartTimeOfDay(LocalDate localDate){
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}

	public static Date getEndTimeOfDay(LocalDate localDate){
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
		return date;
	}


	public static Date addMonth(Date date,int n){
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		LocalDateTime localDateTime1 = null;
		if(n>0){
			localDateTime1.plusMonths(n);
		}else{
			localDateTime1 = localDateTime.minusMonths(n);
		}

		ZoneId zoneId1 = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime1.atZone(zoneId1);
		return Date.from(zdt.toInstant());
	}

	public static void main(String[] args) {

		LocalDate localDate = LocalDate.now().withDayOfMonth(2).minusMonths(1);

		System.out.println(LocalDateUtil.getStartTimeOfDay(localDate));
		System.out.println(LocalDateUtil.getEndTimeOfDay(localDate).toString());


//		LocalDateTime localDateTime=new LocalDateTime( LocalDate(1990,10,11),new LocalTime(23,59,59));
//		Date date = new Date();
//		LocalDateTime localDateTime = convertToLocalDateTime(date);
//		LocalDateTime localDateTime1 = localDateTime.minusMonths(1);
//		Date date1 = convertToDate(localDateTime1);
//		System.out.println(date1);

//		Date firstDayOfCurrMonth = MonthDateUtil.getFirstDayOfCurrMonth();
//		Date lastDayOfCurrMonth = MonthDateUtil.getLastDayOfCurrMonth();
//		System.out.println(firstDayOfCurrMonth);
//		System.out.println(lastDayOfCurrMonth);
//
//
//		Date date = addMonth(firstDayOfCurrMonth, -1);
//		Date date1 = addMonth(lastDayOfCurrMonth, -1);
//
//		System.out.println(date);
//		System.out.println(date1);
//
//		Date date2 = new Date();
//		LocalDate localDate = convertToLocalDate(date2);
//		LocalDate localDate1 = localDate.plusYears(-1);
//		Date date3 = convertToDate(localDate1);
//		System.out.println(date3);
//
//
//		Date date4 = DateUtils.addMonths(date2,-1);
//		System.out.println(date4);


	}
}
