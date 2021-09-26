package com.yss.uco.elecreco.er.ergzb.controller;

import java.util.HashMap;
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
import com.yss.uco.elecreco.support.bean.ErGzb;
import com.yss.uco.elecreco.support.service.IErGzbService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;
import com.yss.uco.elecreco.support.vo.ErGzbVo;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.ergzb.service.impl.ErGzbService",
interfaceClass = com.yss.uco.elecreco.support.service.IErGzbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErGzbService", menuId = "erGzb")
public interface IErGzbServiceController extends IBaseServiceBusController<ErGzb,IErGzbService> {


    @POST
    @Path("/getGzData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErGzb> getGzData(HashMap<String,Object> paraMap);

    @POST
    @Path("/getRealIndexCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<String> getRealIndexCode(@FormParam("dzCode") String dzCode,@FormParam("zbCode") String zbCode);

    @POST
    @Path("/formatSSZBValue")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String formatSSZBValue(ErGzbVo vo);

    @POST
    @Path("/formatedData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> formatedData(ErGzbVo vo);

}