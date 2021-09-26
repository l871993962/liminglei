package com.yss.ams.base.information.modules.bi.ie.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ie.controller.IIeDataController;
import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class IeDataControllerImpl extends AbstractBaseController<IIeDataService> implements IIeDataController{

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<Ie> queryByIds(String ids){
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
    public List<Ie> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Ie> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Ie.class);
    }

    @Override
    public Ie getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Ie getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Ie> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Ie> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Ie.class);
    }

    @Override
    public List<Ie> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Ie> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Ie.class);
    }

    @Override
    public Ie getCacheByIeCode(String ietCode){
        return getService().getCacheByIeCode(ietCode);
    }

    @Override
    public HashMap<String,String> getKeyConvertMap2() throws ServiceException{
        return getService().getKeyConvertMap();
    }

    @Override
    public List<Ie> getDataListByFeeCodes(String[] keys){
        return getService().getDataListByFeeCodes(keys);
    }

}