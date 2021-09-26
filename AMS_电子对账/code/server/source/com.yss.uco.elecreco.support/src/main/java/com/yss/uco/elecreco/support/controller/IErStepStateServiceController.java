package com.yss.uco.elecreco.support.controller;

import java.util.HashMap;
import java.util.List;

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
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;
import com.yss.uco.elecreco.support.service.IErStepStateService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erinfostate.service.ErStepStateService",
interfaceClass = com.yss.uco.elecreco.support.service.IErStepStateService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErStepStateService", menuId = "erDetail")
public interface IErStepStateServiceController extends IBaseServiceBusController<ErStepState,IErStepStateService> {


    @POST
    @Path("/insertPojo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insertPojo(ErStepState erStatePojo);

    @POST
    @Path("/buildPojo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public ErStepState buildPojo(@FormParam("assCode") String assCode,@FormParam("fsn") String fsn,@FormParam("c_FILE_TYPE") String c_FILE_TYPE,@FormParam("c_RPT_TYPE") String c_RPT_TYPE,@FormParam("c_STATE") String c_STATE,@FormParam("d_DATE") String d_DATE,@FormParam("errInfo") String errInfo);

    @POST
    @Path("/queryListByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<BasePojo> queryListByTypes(HashMap<String,String> paraMap);

}