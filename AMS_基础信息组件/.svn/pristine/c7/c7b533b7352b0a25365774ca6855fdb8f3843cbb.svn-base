package com.yss.ifa.szt.tool.thread.mgr.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.ifa.szt.tool.thread.mgr.service.IDzdzMgrService;

@Path("")
@RestfulClient(serviceId = "osgi-elecreco",
impl = "com.yss.ifa.szt.tool.thread.mgr.service.impl.DzdzMgrServiceImpl",
interfaceClass =  com.yss.ifa.szt.tool.thread.mgr.service.IDzdzMgrService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IDzdzMgrService", menuId = "")
public interface IDzdzMgrController extends IBaseController<IDzdzMgrService>{

	@POST
    @Path("/isConnect")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public boolean isConnect();
}
