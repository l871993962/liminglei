package com.yss.uco.elecreco.er.reverse.manager.ignore.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.uco.elecreco.er.reverse.manager.ignore.service.IIgnoreItemService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.manager.ignore.service.impl.IgnoreItemService",
interfaceClass = com.yss.uco.elecreco.er.reverse.manager.ignore.service.IIgnoreItemService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IIgnoreItemService", menuId = "reveDzIgnoreItem")
public interface IIgnoreItemServiceController extends IBaseServiceBusController<IgnoreItem,IIgnoreItemService> {


    @POST
    @Path("/getCompareIgnoreItem")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<IgnoreItem> getCompareIgnoreItem(@FormParam("portCode") String portCode,@FormParam("tgh") String tgh,@FormParam("fileType") String fileType);

}