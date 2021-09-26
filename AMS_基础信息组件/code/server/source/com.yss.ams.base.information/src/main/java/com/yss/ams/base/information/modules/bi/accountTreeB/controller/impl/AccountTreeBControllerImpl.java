package com.yss.ams.base.information.modules.bi.accountTreeB.controller.impl;

import java.util.HashMap;

import com.yss.ams.base.information.support.bi.accountTreeB.controller.IAccountTreeBController;
import com.yss.ams.base.information.support.bi.accountTreeB.pojo.AccountTreeB;
import com.yss.ams.base.information.support.bi.accountTreeB.service.IAccountTreeBService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AccountTreeBControllerImpl extends AbstractBaseServiceBusController<AccountTreeB,IAccountTreeBService> implements IAccountTreeBController {

    @Override
    public RestfulQueryResult<AccountTreeB> getTreeViewData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),AccountTreeB.class);
    }

}