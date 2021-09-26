package com.yss.ams.sec.information.support.modules.mp.secTransferPara.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.secTransferPara.pojo.SecTransferPara;
import com.yss.ams.sec.information.support.modules.mp.secTransferPara.service.ISecTransferParaService;
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
impl = "com.yss.ams.sec.information.modules.mp.secTransferPara.service.impl.SecTransferParaService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.secTransferPara.service.ISecTransferParaService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecTransferParaService", menuId = "sv_secTransfer_para")
public interface ISecTransferParaController extends IBaseServiceBusController<SecTransferPara,ISecTransferParaService> {


    @POST
    @Path("/updateConds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateConds(List<SecTransferPara> pojoList);

}