package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaTradeOrgController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeOrg;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeOrgService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeOrgPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaTradeOrgControllerImpl extends AbstractBaseServiceBusController<PortRelaTradeOrg,IPortRelaTradeOrgService> implements IPortRelaTradeOrgController {

    @Override
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgPage(QueryPortRelaTradeOrgPageVo vo)  throws Exception{
        return queryResToT(getService().queryPortRelaTradeOrgPage(vo.getParaMap(),vo.getPage()),PortRelaTradeOrg.class);
    }

    @Override
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrg(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaTradeOrg(paraMap),PortRelaTradeOrg.class);
    }

    @Override
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgSet(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaTradeOrgSet(paraMap),PortRelaTradeOrg.class);
    }

}