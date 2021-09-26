package com.yss.ams.base.information.modules.sys.secvar.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.secvar.controller.ISecVarDataController;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SecVarDataControllerImpl extends AbstractBaseController<ISecVarDataService> implements ISecVarDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<SecVar> queryByIds(String ids){
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
    public List<SecVar> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<SecVar> getDataListRes(){
        return queryResToT(getService().getDataListRes(),SecVar.class);
    }

    @Override
    public SecVar getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public SecVar getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<SecVar> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<SecVar> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),SecVar.class);
    }

    @Override
    public List<SecVar> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<SecVar> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),SecVar.class);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public void reloadCache(){
        getService().reloadCache();
    }

}