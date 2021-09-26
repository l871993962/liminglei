package com.yss.ams.base.information.modules.bi.ieLink.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ieLink.controller.IFeeDataController;
import com.yss.ams.base.information.support.bi.ieLink.pojo.Fee;
import com.yss.ams.base.information.support.bi.ieLink.service.IFeeDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class FeeDataControllerImpl extends AbstractBaseController<IFeeDataService> implements IFeeDataController{

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Fee> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Fee> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Fee.class);
    }

    @Override
    public Fee getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Ie getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Fee> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Fee> getQueryResByTypes(String[] types){
        return queryResToT( getService().getQueryResByTypes(types),Fee.class);
    }

    @Override
    public List<Fee> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Fee> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Fee.class);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap2(){
        return getService().getKeyConvertMap();
    }

}