package com.yss.ams.base.information.modules.sys.ieItem.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.ieItem.controller.IIeItemDataController;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class IeItemDataControllerImpl extends AbstractBaseController<IIeItemDataService> implements IIeItemDataController{

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<IeItem> queryByIds(String ids){
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
    public List<IeItem> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<IeItem> getDataListRes(){
        return queryResToT(getService().getDataListRes(),IeItem.class);
    }

    @Override
    public IeItem getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public IeItem getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<IeItem> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<IeItem> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),IeItem.class);
    }

    @Override
    public List<IeItem> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<IeItem> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),IeItem.class);
    }

    @Override
    public List<IeItem> getDataListByIeTypes(String[] types){
        return getService().getDataListByIeTypes(types);
    }

}