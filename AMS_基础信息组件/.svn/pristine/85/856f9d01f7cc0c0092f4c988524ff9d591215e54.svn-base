package com.yss.ams.base.information.modules.bi.mkt.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.mkt.controller.IMktDataController;
import com.yss.ams.base.information.support.bi.mkt.service.IMktDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MktDataControllerImpl extends AbstractBaseController<IMktDataService> implements IMktDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<Mkt> queryByIds(String ids){
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
    public Mkt getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Mkt> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Mkt> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Mkt.class);
    }

    @Override
    public List<Mkt> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Mkt> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Mkt.class);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public List<Mkt> getAllDataSqlByKeys(String[] keys){
        return getService().getAllDataSqlByKeys(keys);
    }

    @Override
    public List<Mkt> getDataListAux(){
        return getService().getDataListAux();
    }

    @Override
    public String getAllDataSql(){
        return getService().getAllDataSql();
    }

}