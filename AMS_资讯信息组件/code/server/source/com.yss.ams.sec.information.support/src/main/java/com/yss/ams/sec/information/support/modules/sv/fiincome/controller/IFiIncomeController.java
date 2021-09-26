package com.yss.ams.sec.information.support.modules.sv.fiincome.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.fiincome.pojo.FiIncome;
import com.yss.ams.sec.information.support.modules.sv.fiincome.service.IFiIncomeService;
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
impl = "com.yss.ams.sec.information.modules.sv.fiincome.service.impl.FiIncomeService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.fiincome.service.IFiIncomeService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IFiIncomeService", menuId = "sv_secmbylx")
public interface IFiIncomeController extends IBaseServiceBusController<FiIncome,IFiIncomeService> {


    @POST
    @Path("/calcBeforeTaxAndDue")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String calcBeforeTaxAndDue(HashMap<String,Object> paraMap) throws Exception;

}