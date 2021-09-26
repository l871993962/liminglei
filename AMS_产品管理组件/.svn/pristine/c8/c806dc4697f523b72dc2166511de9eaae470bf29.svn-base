package com.yss.ams.product.information.modules.ab.assetsTree_a.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.controller.IAssetTrees_ADataController;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetTrees_ADataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class AssetTrees_ADataControllerImpl extends AbstractBaseController<IAssetTrees_ADataService> implements IAssetTrees_ADataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<AssetsTree_A> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AssetsTree_A> getDataListRes(){
        return queryResToT(getService().getDataListRes(),AssetsTree_A.class);
    }

    @Override
    public AssetsTree_A getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public AssetsTree_A getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<AssetsTree_A> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<AssetsTree_A> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),AssetsTree_A.class);
    }

    @Override
    public List<AssetsTree_A> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<AssetsTree_A> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),AssetsTree_A.class);
    }

}