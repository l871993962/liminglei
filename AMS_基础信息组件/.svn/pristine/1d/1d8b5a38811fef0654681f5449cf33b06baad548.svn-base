package com.yss.ifa.szt.tool.para.service.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.ifa.szt.tool.para.service.IErVocDataService;
import com.yss.ifa.szt.tool.para.service.controller.IErVocDataController;
import com.yss.ifa.szt.tool.pojo.ErVoc;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class ErVocDataControllerImpl extends AbstractBaseController<IErVocDataService> implements IErVocDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<ErVoc> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<ErVoc> getDataListRes(){
        return queryResToT(getService().getDataListRes(),ErVoc.class);
    }

    @Override
    public ErVoc getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public ErVoc getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<ErVoc> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<ErVoc> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),ErVoc.class);
    }

    @Override
    public List<ErVoc> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<ErVoc> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),ErVoc.class);
    }

}