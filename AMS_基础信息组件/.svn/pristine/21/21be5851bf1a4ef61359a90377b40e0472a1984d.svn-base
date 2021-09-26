package com.yss.ams.base.information.modules.bi.hdaygroup.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.controller.IHDayDataController;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class HDayDataControllerImpl extends AbstractBaseController<IHDayDataService> implements IHDayDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<HdayGroup> queryByIds(String ids){
        return getService().queryByIds(ids);
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<HdayGroup> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<HdayGroup> getDataListRes(){
        return queryResToT(getService().getDataListRes(),HdayGroup.class);
    }

    @Override
    public HdayGroup getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public HdayGroup getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<HdayGroup> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<HdayGroup> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),HdayGroup.class);
    }

    @Override
    public List<HdayGroup> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<HdayGroup> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),HdayGroup.class);
    }

    @Override
    public HashMap<Integer,List<Date>> getHDayGroupAllDate(String holidaysCode){
        return getService().getHDayGroupAllDate(holidaysCode);
    }

}