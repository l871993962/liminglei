package com.yss.uco.elecreco.automic.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yss.framework.api.util.DateUtil;

public class DateSplit {

	public static List<String> getDatesWithRptType(Date startDate,
			Date endDate, String rptType) {
		List<String> list = new ArrayList<String>();
		String format = "yyyy-MM-dd";
		if ("01".equalsIgnoreCase(rptType))// 日报
		{
			do {
				list.add(DateUtil.dateToString(startDate, format));
				startDate = DateUtil.nextDay(startDate, 1);
			} while (startDate.compareTo(endDate) <= 0);
		} else if ("03".equalsIgnoreCase(rptType))// 月报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnMonth(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnMonth(startDate);
			do {
				list.add(DateUtil.dateToString(startDateQ, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 1);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		} else if ("04".equalsIgnoreCase(rptType))// 季报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnQuarter(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnQuarter(startDate);
			do {
				list.add(DateUtil.dateToString(startDateQ, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 3);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		} else if ("05".equalsIgnoreCase(rptType))// 半年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnHalfYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnHalfYear(startDate);
			do {
				list.add(DateUtil.dateToString(startDateQ, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 6);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		} else if ("06".equalsIgnoreCase(rptType))// 年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnYear(startDate);
			do {
				list.add(DateUtil.dateToString(startDateQ, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 12);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		}
		return list;
	}
	
	/**
	 * @param startDate
	 * @param endDate
	 * @param rptType
	 * @return List<String> 开始日期_结束日期
	 */
	public static List<String> getStartDateAndEndDateWithRptType(Date startDate,
			Date endDate, String rptType) {
		List<String> list = new ArrayList<String>();
		String format = "yyyy-MM-dd";
		if ("03".equalsIgnoreCase(rptType))// 月报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnMonth(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnMonth(startDate);
			do {
				Date endDateE = DateUtil.getLastNatureDayOnMonth(startDateQ);
				list.add(DateUtil.dateToString(startDateQ, format) + "_" + DateUtil.dateToString(endDateE, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 1);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		} else if ("04".equalsIgnoreCase(rptType))// 季报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnQuarter(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnQuarter(startDate);
			do {
				Date endDateE = DateUtil.getLastNatureDayOnQuarter(startDateQ);
				list.add(DateUtil.dateToString(startDateQ, format) + "_" + DateUtil.dateToString(endDateE, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 3);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		} else if ("05".equalsIgnoreCase(rptType))// 半年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnHalfYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnHalfYear(startDate);
			do {
				String date = DateUtil.dateToString(startDateQ, format);
				String endStr = date.replaceAll("01-01", "06-30").replaceAll("07-01", "12-31");
				list.add(date + "_" + endStr);
				startDateQ = DateUtil.nextMonth(startDateQ, 6);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		} else if ("06".equalsIgnoreCase(rptType))// 年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnYear(startDate);
			do {
				Date endDateE = DateUtil.getLastNatureDayOnYear(startDateQ);
				list.add(DateUtil.dateToString(startDateQ, format) + "_" + DateUtil.dateToString(endDateE, format));
				startDateQ = DateUtil.nextMonth(startDateQ, 12);
			} while (startDateQ.compareTo(endDateQ) <= 0);
		}
		return list;
	}
	
}
