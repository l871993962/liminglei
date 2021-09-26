package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaCashAccountController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAccount;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccountService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaCashAccountControllerImpl extends AbstractBaseServiceBusController<PortRelaCashAccount,IPortRelaCashAccountService> implements IPortRelaCashAccountController {

	//HashMap<String, Object> paraMap, PageInation page
    @Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountPage(
			QueryPortRelaCashAccountPageVo vo) throws Exception {
        return queryResToT(getService().queryPortRelaCashAccountPage(vo.getParaMap(),vo.getPage()),PortRelaCashAccount.class);
    }

    @Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccount(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaCashAccount(paraMap),PortRelaCashAccount.class);
    }

	

}