package com.yss.ams.base.information.modules.bi.accountTreeA.controller.impl;

import java.util.HashMap;

import com.yss.ams.base.information.support.bi.accountTreeA.controller.IAccountTreeAController;
import com.yss.ams.base.information.support.bi.accountTreeA.pojo.AccountTreeA;
import com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeAService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-09-07 14:27:02
*/
public class AccountTreeAControllerImpl extends AbstractBaseServiceBusController<AccountTreeA,IAccountTreeAService> implements IAccountTreeAController {

    @Override
    public RestfulQueryResult<AccountTreeA> getTreeViewData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),AccountTreeA.class);
    }

}