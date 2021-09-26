package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAccount;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccountIdService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountIdPageVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaCashAccountIdService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccountIdService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaCashAccountIdService", menuId = "pd_portcashaccountid")
public interface IPortRelaCashAccountIdController extends IBaseServiceBusController<PortRelaCashAccount,IPortRelaCashAccountIdService> {


    @POST
    @Path("/queryPortRelaCashAccountIdPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountIdPage(QueryPortRelaCashAccountIdPageVo vo) throws Exception;

    @POST
    @Path("/queryPortRelaCashAccountId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountId(HashMap<String,Object> paraMap) throws Exception;

}