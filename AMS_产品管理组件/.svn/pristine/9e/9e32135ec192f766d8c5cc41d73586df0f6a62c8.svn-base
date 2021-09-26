package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaTradeSeatController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeSeat;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeSeatService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeSeatPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaTradeSeatControllerImpl extends AbstractBaseServiceBusController<PortRelaTradeSeat,IPortRelaTradeSeatService> implements IPortRelaTradeSeatController {

    @Override
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeatPage(QueryPortRelaTradeSeatPageVo vo) throws Exception{
        return queryResToT(getService().queryPortRelaTradeSeatPage(vo.getParaMap(),vo.getPage()),PortRelaTradeSeat.class);
    }

    @Override
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeat(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaTradeSeat(paraMap),PortRelaTradeSeat.class);
    }

}