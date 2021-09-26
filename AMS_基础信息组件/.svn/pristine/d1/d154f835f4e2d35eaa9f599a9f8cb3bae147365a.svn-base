package com.yss.ams.base.information.fast.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.fast.controller.IBaseFastHolidayController;
import com.yss.framework.api.commonInfo.service.IFastHolidayService;
import com.yss.framework.api.commonInfo.vo.GetWorkByMarketCodeVo;
import com.yss.framework.api.commonInfo.vo.HolidayDateVo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午10:16:05
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastHolidayControllerImpl extends AbstractBaseController<IFastHolidayService> implements IBaseFastHolidayController{

	@Override
	public Date getWorkDayByHolidayCode(HolidayDateVo vo) throws Exception {
		return getService().getWorkDayByHolidayCode(vo.getSpecifiedDate(),vo.getOffset(),vo.getHolidayCode());
	}

	@Override
	public String isHoliday(HolidayDateVo vo) throws Exception {
		return getService().isHoliday(vo.getDate(),vo.getHolidayCode());
	}

	@Override
	public List<Date> getHoidayByCode(String C_HDAY_CODE)
			throws ServiceException {
		return getService().getHoidayByCode(C_HDAY_CODE);
	}
	
	@Override
	public List<Date> getAuditHoidayByCode(String C_HDAY_CODE)
			throws Exception {
		return getService().getAuditHoidayByCode(C_HDAY_CODE);
	}

	@Override
	public String isWorkDay(HolidayDateVo vo) throws Exception {
		return getService().isWorkDay(vo.getDate(),vo.getHolidayCode());
	}

	@Override
	public String isLastWorkDayOnMonth(HolidayDateVo vo) throws Exception {
		return getService().isLastWorkDayOnMonth(vo.getParamDate(),vo.getHolidayCode());
	}

	@Override
	public String isFirstWorkDayOfWeek(HolidayDateVo vo) throws Exception {
		return getService().isFirstWorkDayOfWeek(vo.getDate(),vo.getHdayCode());
	}

	@Override
	public String isWorkDayorFirstHoliDay(HolidayDateVo vo) throws Exception {
		return getService().isWorkDayorFirstHoliDay(vo.getDate(),vo.getHolidayCode());
	}

	@Override
	public Date getWorkDayByHdayCode(HolidayDateVo vo) throws Exception {
		return getService().getWorkDayByHdayCode(vo.getSpecifiedDate(),vo.getOffset(),vo.getHolidayCode());
	}

	@Override
	public Date getWorkDay(GetWorkByMarketCodeVo vo) throws Exception {
		return getService().getWorkDay(vo.getSpecifiedDate(),vo.getOffset(),vo.getMarketCode());
	}

	@Override
	public String getDay(String date, String offset, String hdayCode,
			String hdayType) throws ServiceException {
		return getService().getDay(date,offset,hdayCode,hdayType);
	}

	@Override
	public List<Date> getHoidayByCodeAndCheck(String C_HDAY_CODE,
			String N_CHECK_STATE) throws ServiceException {
		return getService().getHoidayByCodeAndCheck(C_HDAY_CODE,N_CHECK_STATE);
	}

	@Override
	public Date getWorkDay(HolidayDateVo vo) throws Exception {
		return getService().getWorkDay(vo.getSpecifiedDate(),vo.getHdayCode(),vo.getOffset());
	}

	@Override
	public HashMap<String, String> getHdayMap() {
		return getService().getHdayMap();
	}

	@Override
	public Date getWorkDay(Date date, int d) throws Exception {
		return getService().getWorkDay(date,d);
	}

	

}
