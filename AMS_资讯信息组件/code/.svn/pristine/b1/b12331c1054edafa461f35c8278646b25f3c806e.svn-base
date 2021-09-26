package com.yss.ams.sec.information.support.modules.sv.fipay.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;
import com.yss.ams.sec.information.support.modules.sv.fipay.service.IFiPayService;
import com.yss.framework.api.common.co.BasePojo;
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
impl = "com.yss.ams.sec.information.modules.sv.fipay.service.impl.FiPayService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.fipay.service.IFiPayService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IFiPayService", menuId = "sv_bondpayinterest")
public interface IFiPayController extends IBaseServiceBusController<FiPay,IFiPayService> {


    @POST
    @Path("/checkDeleteData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String checkDeleteData(List<BasePojo> pojoList);

    @POST
    @Path("/singleSecFiPayInit")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String singleSecFiPayInit(FiPay fiPay) throws Exception;

    @POST
    @Path("/multipleFiPayInit")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String multipleFiPayInit(List<FiPay> fiPayList) throws Exception;

}