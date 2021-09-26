package com.yss.ams.base.information.modules.sys.dai.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dai.controller.IAccProDataController;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IAccProDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AccProDataControllerImpl extends AbstractBaseController<IAccProDataService> implements IAccProDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<Dai> queryByIds(String ids){
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
    public List<Dai> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Dai> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Dai.class);
    }

    @Override
    public Dai getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Dai getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Dai> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Dai> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Dai.class);
    }

    @Override
    public List<Dai> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Dai> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Dai.class);
    }

    @Override
    public List<Dai> getAccProDataByKmCls(String[] kmClss){
        return castToListT(getService().getAccProDataByKmCls(kmClss),Dai.class);
    }

}