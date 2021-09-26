package com.yss.ams.base.information.modules.bi.mkt.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.mkt.controller.IMktHDayDataController;
import com.yss.ams.base.information.support.bi.mkt.service.IMktHDayDataService;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MktHDayDataControllerImpl extends AbstractBaseController<IMktHDayDataService> implements IMktHDayDataController {

    @Override
    public List<Mkt> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Mkt> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Mkt.class);
    }

    @Override
    public Mkt getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public HashMap<String,String> getMarketHDaysData() throws YssException{
        return getService().getMarketHDaysData();
    }

    @Override
    public HashMap<String,HashMap<Integer,List<Date>>> getHolidays() throws YssException{
        return getService().getHolidays();
    }

    @Override
    public HashMap<String,HashMap<Integer,List<Date>>> getHolidays1(String code) throws YssException{
        return getService().getHolidays(code);
    }



}