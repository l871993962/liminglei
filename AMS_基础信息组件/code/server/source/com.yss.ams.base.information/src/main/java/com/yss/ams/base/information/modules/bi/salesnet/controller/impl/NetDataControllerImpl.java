package com.yss.ams.base.information.modules.bi.salesnet.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.salesnet.controller.INetDataController;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.ams.base.information.support.bi.salesnet.service.INetDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class NetDataControllerImpl extends AbstractBaseController<INetDataService> implements INetDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<SalesNet> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<SalesNet> getDataListRes(){
        return queryResToT(getService().getDataListRes(),SalesNet.class);
    }

    @Override
    public SalesNet getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public SalesNet getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<SalesNet> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<SalesNet> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),SalesNet.class);
    }

    @Override
    public List<SalesNet> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<SalesNet> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),SalesNet.class);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public SalesNet getSalesNetByCode(String code){
        return getService().getSalesNetByCode(code);
    }

    @Override
    public HashMap<String,String> getAllNetCodeAndName(){
        return getService().getAllNetCodeAndName();
    }

    @Override
    public SalesNet getSalesNetByVendorCode(String vendorCode){
        return getService().getSalesNetByVendorCode(vendorCode);
    }

}