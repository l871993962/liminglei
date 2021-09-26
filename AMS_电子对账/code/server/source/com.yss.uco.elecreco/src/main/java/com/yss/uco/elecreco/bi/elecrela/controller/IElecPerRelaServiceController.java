package com.yss.uco.elecreco.bi.elecrela.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.bi.elecrela.service.IElecPerRelaService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.bi.elecrela.service.impl.ElecPerRelaService",
interfaceClass = com.yss.uco.elecreco.bi.elecrela.service.IElecPerRelaService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IElecPerRelaService", menuId = "dzPerRela")
public interface IElecPerRelaServiceController extends IBaseServiceBusController<ElecPerRela,IElecPerRelaService> {


    @POST
    @Path("/getPerRelaByCodeAndName")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public BasePojo getPerRelaByCodeAndName(@FormParam("c_ZB_CODE") String c_ZB_CODE,@FormParam("c_ZB_Name") String c_ZB_Name,@FormParam("c_DZ_CODE") String c_DZ_CODE);

    @POST
    @Path("/getPerRelaByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public BasePojo getPerRelaByCode(String c_ZB_CODE);

    @POST
    @Path("/getPerRelaByPortAndDZCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public HashMap<String,ElecPerRela> getPerRelaByPortAndDZCode(@FormParam("c_PORT_CODE") String c_PORT_CODE,@FormParam("c_DZ_CODE") String c_DZ_CODE);

}