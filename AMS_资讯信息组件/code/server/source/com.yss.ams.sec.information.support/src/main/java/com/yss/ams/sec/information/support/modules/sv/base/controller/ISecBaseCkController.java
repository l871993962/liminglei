package com.yss.ams.sec.information.support.modules.sv.base.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseCkService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseCkService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseCkService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecBaseCkService", menuId = "sv_depositInfo")
public interface ISecBaseCkController extends IBaseServiceBusController<SecBase,ISecBaseCkService> {


    @POST
    @Path("/queryForSjsszq")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> queryForSjsszq();

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insert(List<SecBase> pojoList);

    @POST
    @Path("/updateById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateById(List<SecBase> pojoList);

}