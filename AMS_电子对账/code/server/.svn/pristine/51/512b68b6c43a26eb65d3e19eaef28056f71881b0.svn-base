package com.yss.uco.elecreco.er.erbbinfo.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.uco.elecreco.er.erbbinfo.service.IElecGeneService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erbbinfo.service.impl.ElecGeneService",
interfaceClass = com.yss.uco.elecreco.er.erbbinfo.service.IElecGeneService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IElecGeneService", menuId = "elecGene")
public interface IElecGeneServiceController extends IBaseServiceBusController<BasePojo,IElecGeneService> {

	@POST
    @Path("/queryByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public RestfulQueryResult<BasePojo> queryByCondition(HashMap<String, Object> paraMap);
}