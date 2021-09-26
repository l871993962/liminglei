package com.yss.ams.sec.information.support.modules.mp.secTransfer.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo.SecTransfer;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.service.ISecTransferService;
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
impl = "com.yss.ams.sec.information.modules.mp.secTransfer.service.impl.SecTransferService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.secTransfer.service.ISecTransferService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecTransferService", menuId = "sv_secTransfer")
public interface ISecTransferController extends IBaseServiceBusController<SecTransfer,ISecTransferService> {


    @POST
    @Path("/getSecTranMapByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getSecTranMapByCondition(HashMap<String,String> paramList);

    @POST
    @Path("/getParamValue")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getParamValue(@FormParam("portCode") String portCode,@FormParam("dateStr") String dateStr,@FormParam("dspCode") String dspCode);

}