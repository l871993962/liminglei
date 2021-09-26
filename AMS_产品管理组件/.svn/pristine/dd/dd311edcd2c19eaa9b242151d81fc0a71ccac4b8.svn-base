package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaMemberController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaMember;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaMemberService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaMemberPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaMemberControllerImpl extends AbstractBaseServiceBusController<PortRelaMember,IPortRelaMemberService> implements IPortRelaMemberController {

    @Override
    public RestfulQueryResult<PortRelaMember> queryPortRelaMember(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaMember(paraMap),PortRelaMember.class);
    }

    @Override
    public RestfulQueryResult<PortRelaMember> queryPortRelaMemberPage(QueryPortRelaMemberPageVo vo) throws Exception{
        return queryResToT(getService().queryPortRelaMemberPage(vo.getParaMap(),vo.getPage()),PortRelaMember.class);
    }

}