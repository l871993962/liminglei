package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaIndexController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaIndex;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaIndexService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaIndexPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaIndexControllerImpl extends AbstractBaseServiceBusController<PortRelaIndex,IPortRelaIndexService> implements IPortRelaIndexController {

	//HashMap<String, Object> paraMap,PageInation page
    @Override
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndexPage(QueryPortRelaIndexPageVo vo){
        return queryResToT(getService().queryPortRelaIndexPage(vo.getParaMap(),vo.getPage()),PortRelaIndex.class);
    }

    @Override
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndex(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaIndex(paraMap),PortRelaIndex.class);
    }

}