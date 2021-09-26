package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAcc;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccPageVo;
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
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaCashAccService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaCashAccService", menuId = "pd_portCashAcc")
public interface IPortRelaCashAccController extends IBaseServiceBusController<PortRelaCashAcc,IPortRelaCashAccService> {


    @POST
    @Path("/queryPortRelaCashAccPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAccPage(QueryPortRelaCashAccPageVo vo)  throws Exception;

    @POST
    @Path("/queryPortRelaCashAcc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAcc(HashMap<String,Object> paraMap)  throws Exception;

}