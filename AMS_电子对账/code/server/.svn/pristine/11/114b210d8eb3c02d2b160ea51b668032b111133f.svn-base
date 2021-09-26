package com.yss.uco.elecreco.er.reverse.map.assmap.controller;

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
import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.uco.elecreco.er.reverse.map.assmap.service.IAssMapService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.map.assmap.service.impl.AssMapService",
interfaceClass = com.yss.uco.elecreco.er.reverse.map.assmap.service.IAssMapService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IAssMapService", menuId = "reveDzAssMap")
public interface IAssMapServiceController extends IBaseServiceBusController<AssMap,IAssMapService> {


    @POST
    @Path("/getDzMode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getDzMode(@FormParam("portCode") String portCode,@FormParam("fileType") String fileType);

    @POST
    @Path("/getCommonAssMapByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<AssMap> getCommonAssMapByPortCode(String portCode);

    @POST
    @Path("/getAssMapByPortCodeAndFileType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<AssMap> getAssMapByPortCodeAndFileType(@FormParam("portCode") String portCode,@FormParam("fileType") String fileType);

    @POST
    @Path("/getTghCodesByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getTghCodesByPortCode(String portCode);

}