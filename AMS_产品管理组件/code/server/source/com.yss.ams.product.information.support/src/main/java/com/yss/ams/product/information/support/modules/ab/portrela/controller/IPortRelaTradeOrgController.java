package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeOrg;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeOrgService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeOrgPageVo;
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
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaTradeOrgService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeOrgService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaTradeOrgService", menuId = "pd_portTradeOrg")
public interface IPortRelaTradeOrgController extends IBaseServiceBusController<PortRelaTradeOrg,IPortRelaTradeOrgService> {


    @POST
    @Path("/queryPortRelaTradeOrgPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgPage(QueryPortRelaTradeOrgPageVo vo)  throws Exception;

    @POST
    @Path("/queryPortRelaTradeOrg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrg(HashMap<String,Object> paraMap)  throws Exception;

    @POST
    @Path("/queryPortRelaTradeOrgSet")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgSet(HashMap<String,Object> paraMap)  throws Exception;

}