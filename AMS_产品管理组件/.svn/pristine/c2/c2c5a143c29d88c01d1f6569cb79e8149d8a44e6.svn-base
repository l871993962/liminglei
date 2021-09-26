package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaCashAccController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAcc;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaCashAccControllerImpl extends AbstractBaseServiceBusController<PortRelaCashAcc,IPortRelaCashAccService> implements IPortRelaCashAccController {

	//HashMap<String, Object> paraMap,PageInation page
    @Override
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAccPage(QueryPortRelaCashAccPageVo vo) throws Exception{
        return queryResToT(getService().queryPortRelaCashAccPage(vo.getParaMap(),vo.getPage()),PortRelaCashAcc.class);
    }

    @Override
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAcc(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaCashAcc(paraMap),PortRelaCashAcc.class);
    }

	
}