package com.yss.ams.base.information.modules.sys.accele.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.accele.controller.IDaeElemDataController;
import com.yss.ams.base.information.support.sys.accele.pojo.AccEle;
import com.yss.ams.base.information.support.sys.accele.service.IDaeElemDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DaeElemDataControllerImpl extends AbstractBaseController<IDaeElemDataService> implements IDaeElemDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<AccEle> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AccEle> getDataListRes(){
        return queryResToT(getService().getDataListRes(),AccEle.class);
    }

    @Override
    public AccEle getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public AccEle getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<AccEle> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<AccEle> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),AccEle.class);
    }

    @Override
    public List<AccEle> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<AccEle> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),AccEle.class);
    }

}