package com.yss.uco.elecreco.er.erdblgz.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.erdblgz.service.IErDblgzbService;
import com.yss.uco.elecreco.er.erdblgz.vo.ErDblgzbVo;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erdblgz.service.impl.ErDblgzbService",
interfaceClass = com.yss.uco.elecreco.er.erdblgz.service.IErDblgzbService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErDblgzbService", menuId = "erDblgzb")
public interface IErDblgzbServiceController extends IBaseServiceBusController<ErDblgzb,IErDblgzbService> {


    @POST
    @Path("/getGzData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErDblgzb> getGzData(HashMap<String,Object> paraMap);

    @POST
    @Path("/insertDatas")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void insertDatas(ErDblgzbVo vo);

}