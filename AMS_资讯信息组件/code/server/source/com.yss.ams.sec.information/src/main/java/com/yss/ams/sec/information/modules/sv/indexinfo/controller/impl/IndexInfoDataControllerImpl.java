package com.yss.ams.sec.information.modules.sv.indexinfo.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.indexinfo.controller.IIndexInfoDataController;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.pojo.IndexInfo;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.service.IIndexInfoDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class IndexInfoDataControllerImpl extends AbstractBaseController<IIndexInfoDataService> implements IIndexInfoDataController{

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<IndexInfo> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<IndexInfo> getDataListRes(){
        return queryResToT(getService().getDataListRes(),IndexInfo.class);
    }

    @Override
    public IndexInfo getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public IndexInfo getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<IndexInfo> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<IndexInfo> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),IndexInfo.class);
    }

    @Override
    public List<IndexInfo> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<IndexInfo> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),IndexInfo.class);
    }

}