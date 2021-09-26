package com.yss.ams.product.information.modules.ab.assetstree_b.controller.impl;

import java.sql.SQLException;

import com.yss.ams.product.information.support.modules.ab.assetstree_b.controller.IAssetsTree_BController;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo.AssetsTree_B;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTree_BService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class AssetsTree_BControllerImpl extends AbstractBaseServiceBusController<AssetsTree_B,IAssetsTree_BService> implements IAssetsTree_BController {

    @Override
    public String isSameAssetType(String portCode,String dragPortCode){
        return getService().isSameAssetType(portCode,dragPortCode);
    }

    @Override
    public int updateOrdelete(String id,String trCode,String isParent,String type){
        return getService().updateOrdelete(id,trCode,isParent,type);
    }

    @Override
    public int updateOrdelete(String id,String trCode,String isParent,String type,String trCodeR){
        return getService().updateOrdelete(id,trCode,isParent,type,trCodeR);
    }

    @Override
    public String getUserId(String quyCon) throws SQLException{
        return getService().getUserId(quyCon);
    }

    @Override
    public String getCodeByCId(String id){
    	return getService().getCodeByCId(id);
    }
}