package com.yss.uco.elecreco.support.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.uco.elecreco.support.service.IDzdzAutomaticParamService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;
import com.yss.uco.elecreco.support.vo.AutomaticParamVo;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.service.automatic.DzdzAutomaticParamService",
interfaceClass = com.yss.uco.elecreco.support.service.IDzdzAutomaticParamService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IDzdzAutomaticParamService")
public interface IDzdzAutomaticParamServiceController extends IBaseController<IDzdzAutomaticParamService> {


    @POST
    @Path("/getLinkPortbyEtfPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,List<String>> getLinkPortbyEtfPort(AutomaticParamVo vo);

}