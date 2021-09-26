package com.yss.ams.product.information.modules.ab.assetsTree_a.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.controller.IAssetsTree_AController;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetTreeATreeView;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetsTree_AService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class AssetsTree_AControllerImpl extends AbstractBaseServiceBusController<AssetsTree_A,IAssetsTree_AService> implements IAssetsTree_AController{

    @Override
    public RestfulQueryResult<AssetsTree_A> getTreeViewData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),AssetsTree_A.class);
    }

    @Override
    public List<AssetTreeATreeView> getTreeViewDataList(HashMap<String,Object> paraMap){
        return getService().getTreeViewDataList(paraMap);
    }

    @Override
    public List<AssetsTree_A> getNettingGroup(){
        return getService().getNettingGroup();
    }

    @Override
    public String updateAssOrder(List<String> pojoList){
        return getService().updateAssOrder(pojoList);
    }

}