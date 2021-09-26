package com.yss.uco.elecreco.bi.elecrela.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.service.IElecRelaService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.bi.elecrela.service.impl.ElecRelaService",
interfaceClass = com.yss.uco.elecreco.support.service.IElecRelaService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IElecRelaService", menuId = "dzPubRela")
public interface IElecRelaServiceController extends IBaseServiceBusController<ElecRela,IElecRelaService> {


    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<BasePojo> getDataList();

    @POST
    @Path("/getDataListByName")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<BasePojo> getDataListByName(List<String> paraList);

    @POST
    @Path("/getZbCodeByKeyCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,String> getZbCodeByKeyCode(String paras);

}