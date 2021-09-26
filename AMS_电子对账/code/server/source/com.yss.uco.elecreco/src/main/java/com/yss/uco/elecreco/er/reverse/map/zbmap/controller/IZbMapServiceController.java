package com.yss.uco.elecreco.er.reverse.map.zbmap.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.er.reverse.map.zbmap.service.IZbMapService;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.map.zbmap.service.impl.ZbMapService",
interfaceClass = com.yss.uco.elecreco.er.reverse.map.zbmap.service.IZbMapService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IZbMapService", menuId = "reveDzZbMap")
public interface IZbMapServiceController extends IBaseServiceBusController<ZbMap,IZbMapService> {


    @POST
    @Path("/getZbItems")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,ElecRela> getZbItems(String fileType);

    @POST
    @Path("/getCompareZbItems")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<ZbMap> getCompareZbItems(@FormParam("portCode") String portCode,@FormParam("tgh") String tgh,@FormParam("fileType") String fileType);

}