package com.yss.ams.base.information.modules.bi.curypair.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.curypair.controller.ICuryPairDataController;
import com.yss.ams.base.information.support.bi.curypair.pojo.CuryPair;
import com.yss.ams.base.information.support.bi.curypair.service.ICuryPairDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class CuryPairDataControllerImpl extends AbstractBaseController<ICuryPairDataService> implements ICuryPairDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<CuryPair> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<CuryPair> getDataListRes(){
        return queryResToT(getService().getDataListRes(),CuryPair.class);
    }

    @Override
    public CuryPair getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public CuryPair getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<CuryPair> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<CuryPair> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),CuryPair.class);
    }

    @Override
    public List<CuryPair> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<CuryPair> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),CuryPair.class);
    }

}