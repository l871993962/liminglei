package com.yss.ams.base.information.modules.bi.region.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.region.controller.IAreaDataController;
import com.yss.ams.base.information.support.bi.region.pojo.Area;
import com.yss.ams.base.information.support.bi.region.service.IAreaDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AreaDataControllerImpl extends AbstractBaseController<IAreaDataService> implements IAreaDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Area> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Area> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Area.class);
    }

    @Override
    public Area getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Area getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Area> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Area> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Area.class);
    }

    @Override
    public List<Area> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Area> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Area.class);
    }

    @Override
    public List<Area> getAllAreas(){
        return getService().getAllAreas();
    }

    @Override
    public List<Area> getAllNotTopAreas(){
        return getService().getAllNotTopAreas();
    }

}