package com.yss.ams.base.information.modules.sys.dttdmode.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dttdmode.controller.IDtTdModeDataController;
import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DtTdModeDataControllerImpl extends AbstractBaseController<IDtTdModeDataService> implements IDtTdModeDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<DttdMode> queryByIds(String ids){
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
    public List<DttdMode> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<DttdMode> getDataListRes(){
        return queryResToT(getService().getDataListRes(),DttdMode.class);
    }

    @Override
    public DttdMode getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public DttdMode getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<DttdMode> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<DttdMode> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),DttdMode.class);
    }

    @Override
    public List<DttdMode> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<DttdMode> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),DttdMode.class);
    }

    @Override
    public List<DttdMode> getTreeDataList(){
        return getService().getTreeDataList();
    }

    @Override
    public List<DttdMode> getTreeDataByTypes(String[] types){
        return getService().getTreeDataByTypes(types);
    }

    @Override
    public HashMap<String,String> getShortDataMap(String c_BUSI_TYPE){
        return getService().getShortDataMap(c_BUSI_TYPE);
    }

    @Override
    public List<DttdMode> getTreeDataListForRule(){
        return getService().getTreeDataListForRule();
    }

    @Override
    public List<DttdMode> getTreeDataByCfgCode(String[] cfgCodes){
        return getService().getTreeDataByCfgCode(cfgCodes);
    }

    @Override
    public List<DttdMode> getSQKDataListByTypes(String[] type){
        return castToListT(getService().getSQKDataListByTypes(type),DttdMode.class);
    }

    @Override
    public List<DttdMode> getSQKDataByCode(String[] codes){
        return getService().getSQKDataByCode(codes);
    }

    @Override
    public List<DttdMode> getDataListByFun(String funCode){
        return getService().getDataListByFun(funCode);
    }

}