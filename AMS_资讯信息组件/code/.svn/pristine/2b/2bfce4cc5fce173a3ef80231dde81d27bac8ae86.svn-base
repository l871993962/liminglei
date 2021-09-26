package com.yss.ams.sec.information.support.modules.sv.secSoldBack.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.secSoldBack.pojo.SecSoldBack;
import com.yss.ams.sec.information.support.modules.sv.secSoldBack.service.ISecSoldBackService;
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
impl = "com.yss.ams.sec.information.modules.sv.secSoldBack.service.impl.SecSoldBackService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.secSoldBack.service.ISecSoldBackService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecSoldBackService", menuId = "sv_secSoldBack")
public interface ISecSoldBackController extends IBaseServiceBusController<SecSoldBack,ISecSoldBackService> {


}