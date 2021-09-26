package com.yss.fast.systemmanager.port.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.platform.support.dataservice.controller.IPortSTDataController;
import com.yss.platform.support.dataservice.service.IPortSTDataService;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortSTDataControllerImpl extends AbstractBaseController<IPortSTDataService> implements IPortSTDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Port> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Port> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Port.class);
    }

    @Override
    public Port getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Port getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Port> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Port> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Port.class);
    }

    @Override
    public List<Port> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Port> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Port.class);
    }

}