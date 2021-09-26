package com.yss.ams.base.information.fast.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.fast.dao.FastHolidayDao;
import com.yss.ams.base.information.fast.dao.FastHolidaySqlBuilder;
import com.yss.ams.base.information.support.bi.hday.service.IHdayService;
import com.yss.ams.base.information.support.util.holidays.HolidaysAide;
import com.yss.ams.base.information.support.util.holidays.IHolidaysAideService;
import com.yss.framework.api.commonInfo.service.IFastHolidayService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.db.DbPoolFactory;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午10:16:05
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastHolidayService implements IFastHolidayService {

	private FastHolidayDao fastHolidayDao = null;
	
	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年3月5日 上午11:29:22
	 * @param 
	 */
	public FastHolidayService() {
		fastHolidayDao = new FastHolidayDao(DbPoolFactory.getInstance().getPool(), new FastHolidaySqlBuilder());
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param specifiedDate
	 * @param @param offset
	 * @param @param holidayCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public Date getWorkDayByHolidayCode(Date specifiedDate, int offset,
			String holidayCode) throws Exception {
		return YssServiceFactory.getInstance()
				.createService(IHolidaysAideService.class)
				.getWorkDayByHolidayCode(specifiedDate, offset, holidayCode);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param date
	 * @param @param holidaysCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public String isHoliday(Date date, String holidaysCode) throws Exception {
		return String.valueOf(YssServiceFactory.getInstance()
				.createService(IHolidaysAideService.class)
				.isHoliday(date, holidaysCode));
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param C_HDAY_CODE
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public List<Date> getHoidayByCode(String C_HDAY_CODE)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(IHdayService.class).getHoidayByCode(C_HDAY_CODE);
	}
	
	@Override
	public List<Date> getAuditHoidayByCode(String C_HDAY_CODE)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(IHdayService.class).getAuditHoidayByCode(C_HDAY_CODE);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param date
	 * @param @param holidaysCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public String isWorkDay(Date date, String holidaysCode) throws Exception {
		return String.valueOf(HolidaysAide.isWorkDay(date, holidaysCode));
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param paramDate
	 * @param @param holidaysCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public String isLastWorkDayOnMonth(Date paramDate, String holidaysCode)
			throws Exception {
		return String.valueOf(HolidaysAide.isLastWorkDayOnMonth(paramDate, holidaysCode));
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param date
	 * @param @param hdayCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public String isFirstWorkDayOfWeek(Date date, String hdayCode)
			throws Exception {
		return String.valueOf(HolidaysAide.isFirstWorkDayOfWeek(date, hdayCode));
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param date
	 * @param @param holidaysCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public String isWorkDayorFirstHoliDay(Date date, String holidaysCode)
			throws Exception {
		return String.valueOf(HolidaysAide.isWorkDayorFirstHoliDay(date, holidaysCode));
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param specifiedDate
	 * @param @param offset
	 * @param @param holidaysCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public Date getWorkDayByHdayCode(Date specifiedDate, int offset,
			String holidaysCode) throws Exception {
		return HolidaysAide.getWorkDayByHdayCode(specifiedDate, offset, holidaysCode);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param specifiedDate
	 * @param @param offset
	 * @param @param marketCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public Date getWorkDay(Date specifiedDate, int offset, String marketCode)
			throws Exception {
		return HolidaysAide.getWorkDay(specifiedDate, offset, marketCode);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param date
	 * @param @param offset
	 * @param @param hdayCode
	 * @param @param hdayType
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public String getDay(String date, String offset, String hdayCode,
			String hdayType) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IHdayService.class).getDay(date, offset, hdayCode, hdayType);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:05
	 * @param @param C_HDAY_CODE
	 * @param @param N_CHECK_STATE
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public List<Date> getHoidayByCodeAndCheck(String C_HDAY_CODE,
			String N_CHECK_STATE) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IHdayService.class).getHoidayByCodeAndCheck(C_HDAY_CODE, N_CHECK_STATE);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月3日 下午2:39:29
	 * @param @param specifiedDate
	 * @param @param holidaysCode
	 * @param @param offset
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public Date getWorkDay(Date specifiedDate, String holidaysCode, int offset)
			throws Exception {
		return HolidaysAide.getWorkDay(specifiedDate, holidaysCode, offset);
	}
	
	 /***
		 * hwh
		 * 获取已审核节假日代码/名称
		 * STORY #68965 FAST分布式分库情况下的sql解耦 
		 * @return
		 */
	@Override
	public HashMap<String,String> getHdayMap(){
		return YssServiceFactory.getInstance().createService(IHdayService.class).getHdayMap();
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年3月5日 上午11:19:01
	 * @param @param portCode
	 * @param @param date
	 * @param @param d
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public Date getWorkDay(Date date, int d) throws Exception {
		return fastHolidayDao.getWorkDay(date, d);
	}

}
