package com.yss.uco.elecreco.er.reverse.map.kmrela.controller;

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
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaRecordService;
import com.yss.uco.elecreco.er.reverse.out.erkmb.pojo.ErKmbOut;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.reverse.map.kmrela.service.impl.KmRelaRecordService",
interfaceClass = com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaRecordService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IPortKmMapService", menuId = "reveDzPortKmMap")
public interface IKmRelaRecordServiceController extends IBaseServiceBusController<KmRelaRecord,IKmRelaRecordService> {


    @POST
    @Path("/queryInnerKm")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<ErKmb> queryInnerKm(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryOutKm")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<ErKmbOut> queryOutKm(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryIsMappingKm")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<KmRelaRecord> queryIsMappingKm(HashMap<String,Object> paraMap);

    @POST
    @Path("/getCompareKmMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<KmRelaRecord> getCompareKmMap(@FormParam("portCode") String portCode,@FormParam("tgh") String tgh);

    @POST
    @Path("/getPortAndCommKmMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<KmRelaRecord> getPortAndCommKmMap(String portCode);

}