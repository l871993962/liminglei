package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaCashAccountIdController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAccount;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccountIdService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountIdPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaCashAccountIdControllerImpl extends AbstractBaseServiceBusController<PortRelaCashAccount,IPortRelaCashAccountIdService> implements IPortRelaCashAccountIdController {

	//HashMap<String, Object> paraMap, PageInation page
    @Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountIdPage(QueryPortRelaCashAccountIdPageVo vo) throws Exception{
        return queryResToT(getService().queryPortRelaCashAccountIdPage(vo.getParaMap(),vo.getPage()),PortRelaCashAccount.class);
    }

    @Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountId(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaCashAccountId(paraMap),PortRelaCashAccount.class);
    }

}