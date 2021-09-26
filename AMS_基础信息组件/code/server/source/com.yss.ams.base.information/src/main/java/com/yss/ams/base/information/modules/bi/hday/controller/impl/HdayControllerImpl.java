package com.yss.ams.base.information.modules.bi.hday.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hday.controller.IHdayController;
import com.yss.ams.base.information.support.bi.hday.pojo.Hday;
import com.yss.ams.base.information.support.bi.hday.service.IHdayService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class HdayControllerImpl extends AbstractBaseServiceBusController<Hday,IHdayService> implements IHdayController {

    @Override
    public List<String> getAllYear(String code){
        return getService().getAllYear(code);
    }

    @Override
    public List<String> getAllHoiday(HashMap<String,Object> hashMap){
        return getService().getAllHoiday(hashMap);
    }

    @Override
    public List<Date> getHoidayByCode(String C_HDAY_CODE){
        return getService().getHoidayByCode(C_HDAY_CODE);
    }
    
    @Override
    public List<Date> getAuditHoidayByCode(String C_HDAY_CODE){
        return getService().getAuditHoidayByCode(C_HDAY_CODE);
    }

    @Override
    public List<Date> getHoidayByCodeAndCheck(String C_HDAY_CODE,String N_CHECK_STATE){
        return getService().getHoidayByCodeAndCheck(C_HDAY_CODE,N_CHECK_STATE);
    }

    @Override
    public String getSameHoiday(HashMap<String,Object> hashMap){
        return getService().getSameHoiday(hashMap);
    }

    @Override
    public String getDay(String date,String offset,String hdayCode,String hdayType){
        return getService().getDay(date,offset,hdayCode,hdayType);
    }

    @Override
    public String getWorkDayInMonth(String date,int indexDay,String C_HDAY_CODE){
        return getService().getWorkDayInMonth(date,indexDay,C_HDAY_CODE);
    }

    @Override
    public String getWorkday(String specifiedDate,String offset,String portCode){
        return getService().getWorkday(specifiedDate,offset,portCode);
    }

    @Override
    public HashMap<String,String> getHdayMap(){
        return getService().getHdayMap();
    }

    @Override
    public Hday getHdayByHday(String hdayCode,String hday){
        return getService().getHdayByHday(hdayCode,hday);
    }

    @Override
    public List<Hday> getHdayByYearOrMonth(String hdayCode,String yearOrMonth){
        return getService().getHdayByYearOrMonth(hdayCode,yearOrMonth);
    }

}