package com.yss.ams.product.information.modules.ab.assetstree_b.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetstree_b.controller.IAssetsTreeCopyController;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo.AssetsTree_B;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTreeCopyService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class AssetsTreeCopyControllerImpl extends AbstractBaseController<IAssetsTreeCopyService> implements IAssetsTreeCopyController {

    @Override
    public List<AssetsTree_B> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AssetsTree_B> getDataListRes(){
        return queryResToT(getService().getDataListRes(),AssetsTree_B.class);
    }

    @Override
    public AssetsTree_B getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

}