package com.yss.ams.product.information.modules.pg.portgroup.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.pg.portgroup.controller.IPortGroupConverDataController;
import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupConverDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortGroupConverDataControllerImpl extends AbstractBaseController<IPortGroupConverDataService> implements IPortGroupConverDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<PortGroup> queryByIds(String ids){
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
    public List<PortGroup> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortGroup> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortGroup.class);
    }

    @Override
    public PortGroup getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public PortGroup getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PortGroup> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PortGroup> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PortGroup.class);
    }

    @Override
    public List<PortGroup> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PortGroup> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PortGroup.class);
    }

}