package com.yss.ams.sec.information.support.modules.mp.secmkt.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMkt;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMktVo;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.secmkt.service.impl.SecMktService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecMktService", menuId = "sv_marketvalue")
public interface ISecMktController extends IBaseServiceBusController<SecMkt,ISecMktService> {


    @POST
    @Path("/getRate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public double getRate(SecMktVo vo);

    @POST
    @Path("/deleteSecMpMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteSecMpMap(List<SecMkt> lstMp);

    @POST
    @Path("/QuerySGSecMpMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> QuerySGSecMpMap(SecMktVo vo) throws Exception;

    @POST
    @Path("/getPortCodeBySecCodeList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getPortCodeBySecCodeList(SecMktVo vo) throws Exception;

}