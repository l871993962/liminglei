package com.yss.uco.elecreco.er.reverse.manager.info.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.er.reverse.manager.info.service.IErReveInfoService;
import com.yss.uco.elecreco.er.reverse.manager.info.vo.ErReveInfoVo;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.manager.info.service.impl.ErReveInfoService",
interfaceClass = com.yss.uco.elecreco.er.reverse.manager.info.service.IErReveInfoService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErReveInfoService", menuId = "reveDzInfo")
public interface IErReveInfoServiceController extends IBaseServiceBusController<ErReveInfo,IErReveInfoService> {


    @POST
    @Path("/unSdDzResult")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String unSdDzResult(List<ErReveInfo> list);

    @POST
    @Path("/sdDzResult")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String sdDzResult(List<ErReveInfo> list);

    @POST
    @Path("/editDzResult")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String editDzResult(ErReveInfoVo vo);

}