package com.yss.ams.base.information.util.holidays.controller.impl;

import java.util.Date;

import com.yss.ams.base.information.support.util.holidays.IHolidaysAideService;
import com.yss.ams.base.information.support.util.holidays.controller.IHolidaysAideController;
import com.yss.ams.base.information.support.util.holidays.vo.HolidaysAideVo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class HolidaysAideControllerImpl extends AbstractBaseController<IHolidaysAideService> implements IHolidaysAideController {

	//Date date, String holidaysCode
    @Override
    public boolean isHoliday(HolidaysAideVo vo) throws YssException{
        return getService().isHoliday(vo.getDate(),vo.getHolidaysCode());
    }

    //Date specifiedDate, int offset, String holidayCode
    @Override
    public Date getWorkDayByHolidayCode(HolidaysAideVo vo) throws YssException{
        return getService().getWorkDayByHolidayCode(vo.getSpecifiedDate(),vo.getOffset(),vo.getHolidayCode());
    }

    @Override
    public Date getLastTradeDate(String bdhy,String marketCode,String bdpz) throws YssException{
        return getService().getLastTradeDate(bdhy,marketCode,bdpz);
    }

    //Date specifiedDate,String holidaysCode, int offset
    @Override
    public Date getWorkDay(HolidaysAideVo vo) throws YssException{
        return getService().getWorkDay(vo.getSpecifiedDate(),vo.getHolidaysCode(),vo.getOffset());
    }

}