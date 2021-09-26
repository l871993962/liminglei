package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaTradeChanDataController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeSeat;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeChanDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaTradeChanDataControllerImpl extends AbstractBaseController<IPortRelaTradeChanDataService> implements IPortRelaTradeChanDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<PortRelaTradeSeat> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortRelaTradeSeat> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortRelaTradeSeat.class);
    }

    @Override
    public PortRelaTradeSeat getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public PortRelaTradeSeat getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PortRelaTradeSeat> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PortRelaTradeSeat> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PortRelaTradeSeat.class);
    }

    @Override
    public List<PortRelaTradeSeat> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PortRelaTradeSeat> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PortRelaTradeSeat.class);
    }

}