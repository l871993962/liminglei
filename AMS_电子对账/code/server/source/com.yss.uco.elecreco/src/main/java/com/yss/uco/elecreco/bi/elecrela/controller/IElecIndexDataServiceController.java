package com.yss.uco.elecreco.bi.elecrela.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.service.ServiceException;
import com.yss.uco.elecreco.bi.elecrela.service.IElecIndexDataService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.bi.elecrela.service.impl.ElecIndexDataService",
interfaceClass = com.yss.uco.elecreco.bi.elecrela.service.IElecIndexDataService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IElecIndexDataService")
public interface IElecIndexDataServiceController extends IBaseController<IElecIndexDataService> {
	
	@POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap() throws ServiceException ;

	@POST
    @Path("/getKeyConvertMapByList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException ;
}