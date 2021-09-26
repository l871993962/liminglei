package com.yss.ams.base.information.modules.sys.dvaitem.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dvaitem.controller.IDvaItemDataController;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemDataService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DvaItemDataControllerImpl extends AbstractBaseController<IDvaItemDataService> implements IDvaItemDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<DvaItem> queryByIds(String ids){
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
    public List<DvaItem> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<DvaItem> getDataListRes(){
        return queryResToT(getService().getDataListRes(),DvaItem.class);
    }

    @Override
    public DvaItem getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public DvaItem getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<DvaItem> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<DvaItem> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),DvaItem.class);
    }

    @Override
    public List<DvaItem> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<DvaItem> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),DvaItem.class);
    }

    @Override
    public void doUpdate(CacheRefreshInfo cacheRefreshInfo){
         getService().doUpdate(cacheRefreshInfo);
    }

    @Override
    public List<DvaItem> getTreeViewForDayfItems(){
        return getService().getTreeViewForDayfItems();
    }

}