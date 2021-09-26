package com.yss.ams.base.information.support.sys.mktsdi.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.mktsdi.pojo.Mktsdi;
import com.yss.ams.base.information.support.sys.mktsdi.service.IMktsdiService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.mktsdi.service.impl.MktsdiService",
interfaceClass = com.yss.ams.base.information.support.sys.mktsdi.service.IMktsdiService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IMktsdiService", menuId = "base_mktsdiquy")
public interface IMktsdiController extends IBaseServiceBusController<Mktsdi,IMktsdiService> {


    @POST
    @Path("/updateByPk")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateByPk(List<Mktsdi> pojoList);

}