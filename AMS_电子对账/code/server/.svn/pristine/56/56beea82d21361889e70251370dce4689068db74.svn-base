package com.yss.uco.elecreco.er.erresult.controller;

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
import com.yss.uco.elecreco.er.erresult.service.IErResultService;
import com.yss.uco.elecreco.er.erresult.vo.ErResultVo;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erresult.service.impl.ErResultService",
interfaceClass = com.yss.uco.elecreco.er.erresult.service.IErResultService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErResultService", menuId = "dzResult")
public interface IErResultServiceController extends IBaseServiceBusController<BasePojo,IErResultService> {


    @POST
    @Path("/queryOrigDataByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<BasePojo> queryOrigDataByCondition(HashMap<String,Object> paraMap);

    @POST
    @Path("/isExistAssetCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public boolean isExistAssetCode(String assetCode);

    @POST
    @Path("/isSameData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public boolean isSameData(ErResultVo vo);

    @POST
    @Path("/queryUnAcceptCount")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public int queryUnAcceptCount(String csn);

}