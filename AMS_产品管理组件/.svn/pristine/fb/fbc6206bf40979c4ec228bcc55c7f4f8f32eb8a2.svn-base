package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaInvestMgrController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaInvestMgr;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaInvestMgrService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaInvestMgrPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaInvestMgrControllerImpl extends AbstractBaseServiceBusController<PortRelaInvestMgr,IPortRelaInvestMgrService> implements IPortRelaInvestMgrController {

    @Override
    public RestfulQueryResult<PortRelaInvestMgr> queryPortRelaInvestMgrPage(QueryPortRelaInvestMgrPageVo vo) throws Exception{
        return queryResToT(getService().queryPortRelaInvestMgrPage(vo.getParaMap(),vo.getPage()),PortRelaInvestMgr.class);
    }

    @Override
    public RestfulQueryResult<PortRelaInvestMgr> queryPortRelaInvestMgr(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaInvestMgr(paraMap),PortRelaInvestMgr.class);
    }

}