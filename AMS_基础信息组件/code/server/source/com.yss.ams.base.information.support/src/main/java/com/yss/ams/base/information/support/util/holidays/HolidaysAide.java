package com.yss.ams.base.information.support.util.holidays;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayCacheDataService;
import com.yss.ams.base.information.support.bi.mkt.service.IMktCacheDataService;
import com.yss.ams.base.information.support.cache.HDayCache;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.util.DateUtil;


/**
 * 节假日的功能类，根据系统节假日设置提供辅助方法
 * 
 * @author yh 2011.08.19
 * 
 */
public class HolidaysAide {
	/**
	 * 市场代码和节假日群代码的对应关系缓存，key值为市场代码，value值节假日群代码
	 */
//	private static Cache mktHolidaysCache = MktHDayCache.MKTHOLIDAYS.getCacheById();
	//private static IMktHDayDataService mktHolidaysCache = YssServiceFactory.getInstance().createService(
	//		IMktHDayDataService.class);

	/**
	 * 节假日群数据缓存，key值节假日群代码，value值节假日期信息的hash表(key值为年份，value值该年份的节假日list)
	 */
//	private static Cache holidaysCache = MktHDayCache.HOLIDAYS.getCacheById();
	/**
	 * 根据市场代码获取对应的节假日群代码
	 * 
	 * @param marketCode
	 *            市场代码
	 * @return
	 */
	public static String getHolidaysCode(String marketCode) {
		String holidaysCode = null;
		if (null != marketCode) {
			//Mkt mkt = mktHolidaysCache.getDataByCode(marketCode);
			// 添加缓存byleeyu20130810
			MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
			Mkt mkt = null;
			if(mktCache == null){
				IMktCacheDataService mktCacheDataService = YssServiceFactory.getInstance().createService(IMktCacheDataService.class);
				mkt = mktCacheDataService.getCacheByKey(marketCode);
			}else{
				mkt = mktCache.getCacheByKey(marketCode);
			}
			// 先判断获得的mkt 是否 By Jinghehe bug 81699 2013-10-22
			if(null != mkt)
			{
				holidaysCode = mkt.getC_HDAY_CODE();
			}
//			Element ele = mktHolidaysCache.get(marketCode);
//			if (null != ele)
//				holidaysCode = (String) ele.getObjectValue();
		}
		return holidaysCode;
	}
	
	/***
	 * 根据核算日期获取当前的 所有的天数
	 * ADD BY ZXL 20130812
	 * @param actDate
	 *            核算日期
	 * @return 当年的天数
	 */
	public static double getDateOfYear(Date actDate) {
		return YssFun.isLeapYear(actDate) ? 366.0 : 365.0;
	}

	
	/**
	 * 获取特定年份和节假日群的节假日数组
	 * 
	 * @param year
	 *            年份
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 节假日期数组
	 * @throws YssException 
	 */
	private static List<Date> getHolidaysOnYear(int year, String holidaysCode) throws YssException {
		List<Date> holidays = null;
		HashMap<Integer, List<Date>> yearHolidays = null;
		if (null != holidaysCode) {
			// 从缓存中获取指定节假日群代码的节假日信息
//			Element ele = holidaysCache.get(holidaysCode);
//			if (null != ele) {
//				// 获取指定年份的节假日数据
//				yearHolidays = (HashMap<Integer, List<Date>>) ele.getObjectValue();
//				holidays = (ArrayList) yearHolidays.get(year);
//				if (null != holidays) {
//					holidays.trimToSize();
//				}
//			}
			// 添加缓存byleeyu20130810
			HDayCache hdayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
			if(hdayCache == null){
				IHDayCacheDataService hdayCacheDataService = YssServiceFactory.getInstance().createService(IHDayCacheDataService.class);
				yearHolidays = hdayCacheDataService.getHDayGroupAllDate(holidaysCode);
			}else{
				yearHolidays = hdayCache.getHDayGroupAllDate(holidaysCode);
			}
			
			
			if(null != yearHolidays)
			{
				holidays = yearHolidays.get(year);
			}
			
		}
		return holidays;
	}

	/**
	 * 判断某市场的指定日期是否是节假日
	 * 
	 * @param date
	 *            需要判断的日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 是节假日，返回真，反之为假
	 * @throws YssException 
	 */
	public static boolean isHoliday(Date date, String holidaysCode) throws YssException {
		boolean result = false;
		Calendar calendar = null;
		if (null != date && null != holidaysCode) {
			// 格式化日期
			date = DateUtil.standardDate(date);
			// 转换Date类为Calendar类
			calendar = DateUtil.convertToCalendar(date);
			// 获取指定市场和日期所在年份的节假日数据
			List<Date> holidays = getHolidaysOnYear(calendar.get(Calendar.YEAR), holidaysCode);
			// 判断日期是否包含在节假日期数组中
			if (null != holidays && (holidays.indexOf(date) != -1)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 判断指定市场的指定日期是否是工作日
	 * 
	 * 
	 * @param date
	 *            需要判断的日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 是工作日，返回真，反之为假
	 * @throws YssException 
	 */
	public static boolean isWorkDay(Date date, String holidaysCode) throws YssException {
		// 调用判断是否为节假日的方法
		return isHoliday(date, holidaysCode) == true ? false : true;
	}

	/**
	 * 判断指定节假日群的指定日期是否是日期所在月最后一个工作日
	 * 
	 * @param paramDate
	 *            需要判断的日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 是当月最后一个工作日，返回真，反之为假
	 * @throws YssException 
	 */
	public static boolean isLastWorkDayOnMonth(Date paramDate, String holidaysCode) throws YssException {
		boolean result = false;
		if (null != paramDate && null != holidaysCode) {
			// 如果日期不是工作日则无需再做判断
			if (isWorkDay(paramDate, holidaysCode)) {
				// 格式化日期
				paramDate = DateUtil.standardDate(paramDate);
				// 如果参数日期和月末工作日相同，则返回真
				if (paramDate.equals(getEndWorkDayOnMonth(paramDate, holidaysCode))) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 根据参数日期和节假日群代码获取参数日期所在月月初工作日
	 * 
	 * @param paramDate
	 *            参数日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 月初工作日
	 * @throws YssException 
	 */
	public static Date getBeginWorkDayOnMonth(Date paramDate, String holidaysCode) throws YssException {
		Calendar calendar = null;
		List<Date> holidays = null;
		Calendar beginWorkDay = null;
		if (null != paramDate && null != holidaysCode) {
			// 转化日期类型
			calendar = DateUtil.convertToCalendar(paramDate);
			// 获取指定市场和日期所在年份的节假日数据
			holidays = getHolidaysOnYear(calendar.get(Calendar.YEAR), holidaysCode);
			// 找到本月最后一个工作日，和参数日期进行比较
			if (null != holidays) {
				// 获取本月最大天数
				int maxDays = calendar.getActualMaximum(Calendar.DATE);
				beginWorkDay = Calendar.getInstance();
				// 从本月的第一天开始查找，找到的第一个不是节假日的日期既为月初工作日
				for (int i = 1; i <= maxDays; i++) {
					// 设定日期值，从1号开始查找
					beginWorkDay.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), i);
					// 如果节假日中不包含该日期，则该日期即为月初工作日,中断循环
					if (holidays.indexOf(DateUtil.standardDate(beginWorkDay.getTime())) == -1) {
						break;
					}
				}
			}
		}
		return beginWorkDay != null ? DateUtil.standardDate(beginWorkDay.getTime()) : null;
	}

	/**
	 * 根据参数日期和市场代码获取参数日期所在月月末工作日
	 * 
	 * @param paramDate
	 *            参数日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 月末工作日
	 * @throws YssException 
	 */
	public static Date getEndWorkDayOnMonth(Date paramDate, String holidaysCode) throws YssException {
		Calendar calendar = null;
		List<Date> holidays = null;
		Calendar endWorkDay = null;
		if (null != paramDate && null != holidaysCode) {
			// 转化日期类型
			calendar = DateUtil.convertToCalendar(paramDate);
			// 获取指定市场和日期所在年份的节假日数据
			holidays = getHolidaysOnYear(calendar.get(Calendar.YEAR), holidaysCode);
			// 找到本月最后一个工作日
			if (null != holidays) {
				// 获取本月最大天数
				int maxDays = calendar.getActualMaximum(Calendar.DATE);
				endWorkDay = Calendar.getInstance();
				// 从本月的最后一天开始倒序查找，找到的第一个不是节假日的日期既为最后一个工作日
				for (int i = maxDays; i >= 1; i--) {
					// 设定日期值，从月底最后一天开始
					endWorkDay.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i);
					// 如果不包含该日期，则该日期即为最后一个工作日,中断循环
					if (holidays.indexOf(DateUtil.standardDate(endWorkDay.getTime())) == -1) {
						break;
					}
				}
			}
		}
		return endWorkDay != null ? DateUtil.standardDate(endWorkDay.getTime()) : null;
	}

	/**
	 * 根据基准日期和市场代码，返回基准日期所在日期区间的首日期。例如，基准日期为节假日，首日期即为向前查找第一个工作日的前一日期
	 * 
	 * @param markDate
	 *            基准日期
	 * @param marketCode
	 *            市场代码
	 * @return 期间首日期
	 * @throws YssException 
	 */
	public static Date getBeginDayOnInterval(Date markDate, String marketCode) throws YssException {
		Calendar beginCalendar = null;
		if (null != markDate && null != marketCode) {
			//Mkt mkt = mktHolidaysCache.getDataByCode(marketCode);

			// 添加缓存byleeyu20130810
			MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
			Mkt mkt = null;
			if(mktCache == null){
				IMktCacheDataService mktCacheDataService = YssServiceFactory.getInstance().createService(IMktCacheDataService.class);
				mkt = mktCacheDataService.getCacheByKey(marketCode);
			}else{
				mkt = mktCache.getCacheByKey(marketCode);
			}

			// 获取和市场代码对应的节假日群代码
			String holidaysCode = mkt.getC_HDAY_CODE();
			Date tempDate = null;
			// 当基准日期是节假日时
			if (isHoliday(markDate, marketCode)) {

				// 基准日期向前递减，找到的第一个工作日的前一日期即为区间内的首日期
				tempDate = getTargetDateOnHoliday(markDate, holidaysCode, -1, VocabularyConsts.DATETYPE_WORK);
				if (null != tempDate) {
					beginCalendar = Calendar.getInstance();
					beginCalendar.setTime(tempDate);
					;
					beginCalendar.add(Calendar.DATE, -1);
				}
			}
			// 当基准日期是工作日时
			else {
				// 基准日期向前递减，找到的第一个节假日的前一日期即为区间内的首日期
				tempDate = getTargetDateOnHoliday(markDate, holidaysCode, -1, VocabularyConsts.DATETYPE_HOLIDAY);
				if (null != tempDate) {
					beginCalendar = Calendar.getInstance();
					beginCalendar.setTime(tempDate);
					beginCalendar.add(Calendar.DATE, -1);
				}
			}
		}
		return (beginCalendar != null) ? DateUtil.standardDate(beginCalendar.getTime()) : null;
	}

	/**
	 * 根据基准日期和市场代码，返回基准日期所在日期区间的尾日期。例如，基准日期为节假日，首日期即为向后查找第一个工作日的前一日期
	 * 
	 * @param markDate
	 *            基准日期
	 * @param marketCode
	 *            市场代码
	 * @return 期间尾日期
	 * @throws YssException 
	 */
	public static Date getEndDayOnInterval(Date markDate, String marketCode) throws YssException {
		Calendar endCalendar = null;
		//Mkt mkt = mktHolidaysCache.getDataByCode(marketCode);
		// 添加缓存byleeyu20130810
		MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		Mkt mkt = null;
		if(mktCache == null){
			IMktCacheDataService mktCacheDataService = YssServiceFactory.getInstance().createService(IMktCacheDataService.class);
			mkt = mktCacheDataService.getCacheByKey(marketCode);
		}else{
			mkt = mktCache.getCacheByKey(marketCode);
		}
		if (null != markDate && null != marketCode) {
			// 获取和市场代码对应的节假日群代码
			String holidaysCode = mkt.getC_HDAY_CODE();
			Date tempDate = null;
			// 当基准日期是节假日时
			if (isHoliday(markDate, marketCode)) {

				// 基准日期向后递增，找到的第一个工作日的前一日期即为区间内的尾日期
				tempDate = getTargetDateOnHoliday(markDate, holidaysCode, 1, VocabularyConsts.DATETYPE_WORK);
				if (null != tempDate) {
					endCalendar = Calendar.getInstance();
					endCalendar.setTime(tempDate);
					endCalendar.add(Calendar.DATE, -1);
				}
			}
			// 当基准日期是工作日时
			else {

				// 基准日期向后递增，找到的第一个节假日的前一日期即为区间内的尾日期
				tempDate = getTargetDateOnHoliday(markDate, holidaysCode, 1, VocabularyConsts.DATETYPE_HOLIDAY);
				if (null != tempDate) {
					endCalendar = Calendar.getInstance();
					endCalendar.setTime(tempDate);
					endCalendar.add(Calendar.DATE, -1);
				}
			}
		}
		return (endCalendar != null) ? DateUtil.standardDate(endCalendar.getTime()) : null;
	}

	/**
	 * 从基准日期开始，根据指定市场的节假日设置，返回根据偏移天数和偏移天数类型查找的目标日期
	 * 
	 * @param markDate
	 *            基准日期
	 * @param marketCode
	 *            市场代码
	 * @param offsetDays
	 *            偏移天数 天数为正，向右查找，反之向右查找，为0则返回原日期
	 * @param offsetDayType
	 *            偏移天数类型，包含三种自然日，工作日，节假日
	 * @return 目标日期
	 * @throws YssException 
	 */
	public static Date getTargetDateOnMkt(Date markDate, String marketCode, int offsetDays, String offsetDayType) throws YssException {
		// 根据市场代码获取节假日群代码
		//Mkt mkt = mktHolidaysCache.getDataByCode(marketCode);
		// 添加缓存byleeyu20130810
		MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		Mkt mkt = null;
		if(mktCache == null){
			IMktCacheDataService mktCacheDataService = YssServiceFactory.getInstance().createService(IMktCacheDataService.class);
			mkt = mktCacheDataService.getCacheByKey(marketCode);
		}else{
			mkt = mktCache.getCacheByKey(marketCode);
		}
		String holidaysCode = mkt.getC_HDAY_CODE();
//		Element ele = mktHolidaysCache.get(marketCode);
//		String holidaysCode = (ele != null) ? ((String) ele.getObjectValue()) : null;
		return getTargetDateOnHoliday(markDate, holidaysCode, offsetDays, offsetDayType);
	}

	/**
	 * 从基准日期开始，根据指定市场的节假日设置，返回根据偏移天数和偏移天数类型查找的目标日期，其中查找是不包含当日的
	 * 
	 * @param markDate
	 *            基准日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @param offsetDays
	 *            偏移天数 天数为正，向右查找，反之向左查找，为0则返回原日期
	 * @param offsetDayType
	 *            偏移天数类型，包含三种自然日，工作日，节假日
	 * @return 目标日期 注意：返回结果包含两种情况
	 *         (1)输入参数不满足或者偏移量过大，超出基准日期所在年节假日设置，但其他年份的节假日设置不存在返回null (2)返回正确的日期
	 * @throws YssException 
	 */
	public static Date getTargetDateOnHoliday(Date markDate, String holidaysCode, int offsetDays, String offsetDayType) throws YssException {
		Calendar markCalendar = null;
		Calendar targetCalendar = null;
		List<Date> holidays = null;
		if (null != markDate) {
			// 如果偏移天数为0，直接返回日期
			if (offsetDays == 0)
				return markDate;
			// 转换日期类型
			markCalendar = DateUtil.convertToCalendar(markDate);
			// 初始化目标日期，初始值和基准日期相同
			targetCalendar = Calendar.getInstance();
			targetCalendar.set(markCalendar.get(Calendar.YEAR), markCalendar.get(Calendar.MONTH), markCalendar
					.get(Calendar.DAY_OF_MONTH));

			// 根据偏移天数类型进行不同的逻辑判断

			// 如果偏移天数类型为自然日
			if (offsetDayType.equals(VocabularyConsts.DATETYPE_NATURE)) {
				// 按偏移天数修改日期
				targetCalendar.add(Calendar.DATE, offsetDays);
			}
			// 获取偏移量的绝对值
			int offset = Math.abs(offsetDays);
			// 获取偏移方向
			int offWay = (offsetDays > 0) ? 1 : -1;
			// 获取基准日期所在年的节假日设置
			holidays = getHolidaysOnYear(markCalendar.get(Calendar.YEAR), holidaysCode);
			// 如果偏移天数类型为工作日
			if (offsetDayType.equals(VocabularyConsts.DATETYPE_WORK)) {

				do {
					// 目标日期偏移一个单位
					if(targetCalendar != null){
						targetCalendar.add(Calendar.DATE, offWay);
						// 如果偏移一个单位后，目标日期跨年，重新获取节假日设置
						if (targetCalendar.get(Calendar.YEAR) != markCalendar.get(Calendar.YEAR)) {
							holidays = getHolidaysOnYear(targetCalendar.get(Calendar.YEAR), holidaysCode);
						}
						// 如果没有节假日设置，返回空
						if (null == holidays) {
							targetCalendar = null;
							break;
						}
						// 如果偏移一个单位后的日期，是工作日，则偏移量-1,反之不变
						if ((holidays.indexOf(DateUtil.standardDate(targetCalendar.getTime()))) == -1) {
							offset--;
						}
					}

				} while (offset != 0);
			}
			// 如果偏移天数类型为节假日
			if (offsetDayType.equals(VocabularyConsts.DATETYPE_HOLIDAY)) {
				do {
					// 目标日期偏移一个单位
					if(targetCalendar != null){
						// 目标日期偏移一个单位
						targetCalendar.add(Calendar.DATE, offWay);
						// 如果偏移一个单位后，目标日期跨年，重新获取节假日设置
						if (targetCalendar.get(Calendar.YEAR) != markCalendar.get(Calendar.YEAR)) {
							holidays = getHolidaysOnYear(targetCalendar.get(Calendar.YEAR), holidaysCode);
						}
						// 如果没有节假日设置，返回空
						if (null == holidays) {
							targetCalendar = null;
							break;
						}
						// 如果偏移一个单位后的日期，是节假日，则偏移量-1,反之不变
						if ((holidays.indexOf(DateUtil.standardDate(targetCalendar.getTime()))) != -1) {
							offset--;
						}
					}
					
				} while (offset != 0);
			}
		}
		return (targetCalendar != null) ? DateUtil.standardDate(targetCalendar.getTime()) : null;
	}

	/**
	 * 获取日期区间之间的工作日天数,计算范围不包含起始日期
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param marketCode
	 *            市场代码
	 * @return
	 * @throws YssException 
	 */
	public static int getWorkDaysNotContainBeginDate(Date beginDate, Date endDate, String marketCode) throws YssException {
		int workDays = 0;
		if (null != beginDate && null != endDate && null != marketCode) {
			String holidaysCode = HolidaysAide.getHolidaysCode(marketCode);
			// 从开始日期从后递增日期,对每一个日期判断其是否是工作日
			Calendar beginCalendar = DateUtil.convertToCalendar(beginDate);
			if (YssFun.dateDiff(beginDate, endDate) == 0)
				return 0;
			else {
				Date curDate = DateUtil.standardDate(beginCalendar.getTime());
				do {
					beginCalendar.add(Calendar.DATE, 1);
					curDate = DateUtil.standardDate(beginCalendar.getTime());
					if (HolidaysAide.isWorkDay(curDate, holidaysCode))
						workDays++;
				} while (curDate.before(endDate));
			}

		}

		return workDays;
	}

	/**
	 * 获取日期区间之间的工作日天数,计算范围包含起始日期
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param marketCode
	 *            市场代码
	 * @return
	 * @throws YssException 
	 */
	public static int getWorkDaysContainBeginDate(Date beginDate, Date endDate, String marketCode) throws YssException {
		int workDays = 0;
		workDays = getWorkDaysNotContainBeginDate(beginDate, endDate, marketCode);
		if (HolidaysAide.isWorkDay(beginDate, HolidaysAide.getHolidaysCode(marketCode))) {
			workDays += 1;
		}
		return workDays;
	}

	/**
	 * 获取指定日期偏离固定天数的工作日
	 * 
	 * @param specifiedDate
	 *            指定日期
	 * @param offset
	 *            偏移天数
	 * @param marketCode
	 *            市场代码
	 * @return 计算得到的工作日
	 * @throws YssException 
	 */
	public static Date getWorkDay(Date specifiedDate, int offset, String marketCode) throws YssException {
		int direction = offset > 0 ? 1 : -1;
		offset = offset * direction;
		String holidaysCode = HolidaysAide.getHolidaysCode(marketCode);
		Date curDate = specifiedDate;
		while (offset > 0) {
			curDate = YssFun.addDay(curDate, direction); // 按照direction方向一天天递增(递减)
			if (HolidaysAide.isWorkDay(curDate, holidaysCode)) {
				offset--; // 工作日才算
			}
		}

		return curDate;
	}

	

	/***
	 * 根据核算日期，节假日代码获取 获取当年的工作日的天数
	 * 
	 * @param actDate
	 *            核算日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 当年的工作日的天数
	 * @throws YssException 
	 */
	public static double getWorkDateOfYear(String holidaysCode, Date actDate) throws YssException {
		// 获取节假日群下面的所有的节假日信息
		//HashMap<Integer, List<Date>> map = mktHolidaysCache.getHolidays(holidaysCode).get(holidaysCode);
		// 添加缓存byleeyu20130810
		HDayCache hdayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
		HashMap<Integer,List<Date>> map = null;
		if(hdayCache == null){
			IHDayCacheDataService hdayCacheDataService = YssServiceFactory.getInstance().createService(IHDayCacheDataService.class);
			map = hdayCacheDataService.getHDayGroupAllDate(holidaysCode);
		}else{
			map = hdayCache.getHDayGroupAllDate(holidaysCode);
		}
		List<Date> list = map.get(YssFun.getYear(actDate));
//		HashMap<Integer, List<Date>> map = MktHDayCache.HOLIDAYS.get(holidaysCode);
//		// 获取当前的所有的节假日信息
//		List<Date> list = map.get(YssFun.getYear(actDate));
		return ((YssFun.isLeapYear(actDate) ? 366.0 : 365.0) - list.size());
	}

	/***
	 * 获取当月工作日的天数
	 * 
	 * @param actDate
	 *            基准日期
	 * @param holidaysCode
	 *            节假日代码
	 * @return
	 */
	public static double getWorkDateOfMonth(String holidaysCode, Date actDate) throws YssException {
		// 获取当前月份的天数
		Calendar calendar = null;
		calendar = DateUtil.convertToCalendar(actDate);
		int dateCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 获取当前的月份
		int month = YssFun.getMonth(actDate);
		
		// 获取当前节假日群对应的所有节假日
		//HashMap<Integer, List<Date>> map = mktHolidaysCache.getHolidays(holidaysCode).get(holidaysCode);
		// 添加缓存byleeyu20130810
		HDayCache hdayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
		HashMap<Integer,List<Date>> map = null;
		if(hdayCache == null){
			IHDayCacheDataService hdayCacheDataService = YssServiceFactory.getInstance().createService(IHDayCacheDataService.class);
			map = hdayCacheDataService.getHDayGroupAllDate(holidaysCode);
		}else{
			map = hdayCache.getHDayGroupAllDate(holidaysCode);
		}
		List<Date> dateList = map.get(YssFun.getYear(actDate));
//		List<Date> dateList = MktHDayCache.HOLIDAYS.get(holidaysCode).get(YssFun.getYear(actDate));
		// 循环节假日列表减去所有的节假日天数
		for (Date date : dateList) {
			// 如果date为当前月份的节假日dateCount - 1
			if (month == YssFun.getMonth(date)) {
				dateCount = dateCount - 1;
			}
		}
		return dateCount;
	}
	
	/***
	 * 判断是否是节假区间的最后一天
	 * 
	 * @param holidaysCode
	 *            节假日代码
	 * @param markDate
	 *            日期
	 * @return
	 * @throws YssException 
	 */
	public static boolean isEndHolidayDayOfHolidayPeriod(String holidaysCode,Date markDate) throws YssException{
		// 如果当日和前一日为节假日，后一日不为节假日，则返回true
		if (isHoliday(markDate, holidaysCode) && isHoliday(YssFun.addDay(markDate, -1), holidaysCode) 
				&& !isHoliday(YssFun.addDay(markDate, 1), holidaysCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 获取当日是否为每周第一个工作日
	 * @param date 估值日
	 * @param hdayCode 节假日代码
	 * @return
	 * @throws YssException
	 */
	public static boolean isFirstWorkDayOfWeek(Date date,String hdayCode) throws YssException{
		if(StringUtil.IsNullOrEmpty(hdayCode)){
			hdayCode = "CN";
		}
		
		int weekDay = YssFun.getWeekDay(date)-1;//周1到周7
		if(weekDay == 0){
			//周日
			weekDay = 7;
		}
		int days = weekDay - 1;//本周当日之前的天数
		Date tmpDate = date;
		for(int i = 0;i < days ; i++){
			tmpDate = YssFun.addDay(tmpDate, -1);
			if(isWorkDay(tmpDate, hdayCode)){
				// 循环当天之前的本周前几天，如果有工作日，则当日不是第一个工作日
				return false;
			}
		}
		return isWorkDay(date, hdayCode);
	}
	
	/** 获取上周或上上周的第一个工作日
	 * STORY #25234 磐厚：管理费计提需求 代码合并
	 * @param date 估值日
	 * @param hdayCode 节假日代码
	 * @return
	 * @throws YssException
	 */
	public static Date getLastWorkDayOfWeek(Date date,String hdayCode) throws YssException{
		Date result = null;
		if(StringUtil.IsNullOrEmpty(hdayCode)){
			hdayCode = "CN";
		}
		//获取上周一的日期
		Date lastMonday = getLastWeekMonday(date);
		for(int i=0;i<7;i++){
			//遍历上周日期，返回第一个工作日
			result = YssFun.addDay(lastMonday, i);
			if(isWorkDay(result, hdayCode)){
				break;
			}
		}
		
		while(result == null){
			// 上周没有工作日,则取上上周一开始查
			result = getLastWorkDayOfWeek(lastMonday,hdayCode);
			lastMonday = getLastWeekMonday(lastMonday);
		}
		return result;
	}
	
	/** 获取上周一日期
	 * STORY #25234 磐厚：管理费计提需求 代码合并
	 * @param date
	 * @return
	 */
	public static Date getLastWeekMonday(Date date) {    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(date);    
       cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
       return cal.getTime();    
   }
	
	/**
	 * 获取日期区间之间的工作日天数,计算范围不包含起始日期
	 * 
	 * STORY #25234 磐厚：管理费计提需求 代码合并
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param holidaysCode
	 *            节假日代码
	 * @return
	 * @throws YssException 
	 */
	public static int getWorkDaysNotContainBeginDateByHdayCode(Date beginDate, Date endDate, String holidaysCode) throws YssException {
		int workDays = 0;
		if(StringUtil.IsNullOrEmpty(holidaysCode)){
			holidaysCode = "CN";
		}
		if (null != beginDate && null != endDate && null != holidaysCode) {
			// 从开始日期从后递增日期,对每一个日期判断其是否是工作日
			Calendar beginCalendar = DateUtil.convertToCalendar(beginDate);
			if (YssFun.dateDiff(beginDate, endDate) == 0)
				return 0;
			else {
				Date curDate = DateUtil.standardDate(beginCalendar.getTime());
				do {
					beginCalendar.add(Calendar.DATE, 1);
					curDate = DateUtil.standardDate(beginCalendar.getTime());
					if (HolidaysAide.isWorkDay(curDate, holidaysCode))
						workDays++;
				} while (curDate.before(endDate));
			}

		}

		return workDays;
	}

	/**
	 * 判断指定市场的指定日期是否是工作日或节假日第一天
	 * 20160507 STORY30909
	 * @author @wangtangyao 
	 * @param date
	 *            需要判断的日期
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 是工作日，返回真，反之为假
	 * @throws YssException 
	 */
	public static boolean isWorkDayorFirstHoliDay(Date date, String holidaysCode) throws YssException {
		// 调用判断是否为节假日的方法
		if(isHoliday(date, holidaysCode)){
			if(!isHoliday(YssFun.addDay(date, -1), holidaysCode)){
				return true;
			}
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获取当前节假日区间第一个节假日
	 * 20160507 STORY30909
	 * @author @wangtangyao 
	 * @param date
	 *            指定日期
	 * @param marketCode
	 *            市场代码
	 * @return 节假日第一天
	 * @throws YssException 
	 */
	public static Date getFirstHoliDay(Date date, String marketCode) throws YssException {
		Date firstHoliDate = YssFun.addDay(getWorkDay(date,-1,marketCode),1);
		return firstHoliDate;
	}
	
	/**
	 * 获取当前月份第一天
	 * 20160507 STORY30909
	 * @author @wangtangyao 
	 * @param date
	 *            指定日期
	 * @param marketCode
	 *            市场代码
	 * @return 节假日第一天
	 * @throws YssException 
	 */
	public static Date getFirstDay(Date date) throws YssException {
		Calendar calendar = null;
		Calendar beginWorkDay = Calendar.getInstance();
			// 转化日期类型
		calendar = DateUtil.convertToCalendar(date);
					// 设定日期值，从1号开始查找
		beginWorkDay.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), 1);
		return date != null ? DateUtil.standardDate(beginWorkDay.getTime()) : null;
	}
	
	/**
	 * 根据节假日群代码获取指定日期偏离固定天数的工作日
	 * 
	 * @param specifiedDate
	 *            指定日期
	 * @param offset
	 *            偏移天数
	 * @param marketCode
	 *            市场代码
	 * @return 计算得到的工作日
	 * @throws YssException 
	 */
	public static Date getWorkDay(Date specifiedDate,String holidaysCode, int offset) throws YssException {
		int direction = offset > 0 ? 1 : -1;
		offset = offset * direction;
		Date curDate = specifiedDate;
		while (offset > 0) {
			curDate = YssFun.addDay(curDate, direction); // 按照direction方向一天天递增(递减)
			if (HolidaysAide.isWorkDay(curDate, holidaysCode)) {
				offset--; // 工作日才算
			}
		}

		return curDate;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2015-09-30
	 * Status : Add
	 * Comment: 根据节假日代码获取工作日日期
	 * @param specifiedDate 指定日期
	 * @param offset 偏移量
	 * @param holidayCode 节假日代码
	 * @return 计算偏移量得到的工作日日期
	 */
	public static Date getWorkDayByHolidayCode(Date specifiedDate, int offset, String holidayCode){
		int direction = offset > 0 ? 1 : -1;
		offset = offset * direction;
		Date curDate = specifiedDate;
		try{
			while (offset > 0) {
				curDate = YssFun.addDay(curDate, direction); // 按照direction方向一天天递增(递减)
				if (HolidaysAide.isWorkDay(curDate, holidayCode)) {
					offset--; // 工作日才算
				}
			}
		}catch(Exception ex){
			throw new BusinessException("获取工作日日期出错!",ex);
		}

		return curDate;
	}
	
	
	/**
	 * 获取日期区间之间的工作日天数,计算范围包含起始日期
	 * add by xiongdaolin 2016-12-21
	 * STORY #36921 嘉实基金QD-预提待摊业务增加节假日不计提控制参数
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param holidaysCode
	 *            节假日代码
	 * @return
	 * @throws YssException 
	 */
	public static int getWorkDaysContainBeginDateByHdayCode(Date beginDate, Date endDate, String holidaysCode) throws YssException {
		int workDays = 0;
		workDays = getWorkDaysNotContainBeginDateByHdayCode(beginDate, endDate, holidaysCode);
		if (HolidaysAide.isWorkDay(beginDate, holidaysCode)) {
			workDays += 1;
		}
		return workDays;
	}
	
	/**
	 * 获取指定日期偏离固定天数的工作日
	 * 
	 * @param specifiedDate
	 *            指定日期
	 * @param offset
	 *            偏移天数
	 * @param holidaysCode
	 *            节假日群代码
	 * @return 计算得到的工作日
	 * @throws YssException 
	 */
	public static Date getWorkDayByHdayCode(Date specifiedDate, int offset, String holidaysCode) throws YssException {
		int direction = offset > 0 ? 1 : -1;
		offset = offset * direction;
		Date curDate = specifiedDate;
		while (offset > 0) {
			curDate = YssFun.addDay(curDate, direction); // 按照direction方向一天天递增(递减)
			if (HolidaysAide.isWorkDay(curDate, holidaysCode)) {
				offset--; // 工作日才算
			}
		}
		return curDate;
	}
}
