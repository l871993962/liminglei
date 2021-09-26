package com.yss.ams.base.information.modules.sys.dccury.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dccury.controller.IDCDataController;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dccury.service.IDCDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DCDataControllerImpl extends AbstractBaseController<IDCDataService> implements IDCDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<DcCury> queryByIds(String ids){
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
    public List<DcCury> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<DcCury> getDataListRes(){
        return queryResToT(getService().getDataListRes(),DcCury.class);
    }

    @Override
    public DcCury getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public DcCury getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<DcCury> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<DcCury> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),DcCury.class);
    }

    @Override
    public List<DcCury> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<DcCury> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),DcCury.class);
    }

    @Override
    public List<DcCury> getPortCurruncyList() throws Exception{
        return getService().getPortCurruncyList();
    }

    @Override
    public RestfulQueryResult<DcCury> getPortCurruncyListRes() throws Exception{
        return queryResToT(getService().getPortCurruncyListRes(),DcCury.class);
    }

    @Override
    public void reloadCache(){
         getService().reloadCache();
    }

}