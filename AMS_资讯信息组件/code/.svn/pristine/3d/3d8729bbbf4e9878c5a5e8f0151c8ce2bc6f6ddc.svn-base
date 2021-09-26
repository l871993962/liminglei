package com.yss.ams.sec.information.support.modules.mp.outmkt.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.outmkt.service.IOutMktService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.mp.outmkt.service.impl.OutMktService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.outmkt.service.IOutMktService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IOutMktService", menuId = "sv_outmarketvalue")
public interface IOutMktController extends IBaseController<IOutMktService> {


    @POST
    @Path("/getSYLX")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getSYLX(String secCode);

    @POST
    @Path("/checkDuplicate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String checkDuplicate(@FormParam("secCode") String secCode,@FormParam("d_mkt") String d_mkt,@FormParam("mktCls") String mktCls,@FormParam("dvPlat") String dvPlat) throws Exception;

}