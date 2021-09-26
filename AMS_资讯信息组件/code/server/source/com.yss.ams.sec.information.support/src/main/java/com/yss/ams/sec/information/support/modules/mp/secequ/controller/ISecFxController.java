package com.yss.ams.sec.information.support.modules.mp.secequ.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecFxService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
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
impl = "com.yss.ams.sec.information.modules.mp.secequ.service.impl.SecFxService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecFxService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecFxService", menuId = "sv_securityPublish")
public interface ISecFxController extends IBaseServiceBusController<SecBase,ISecFxService> {


}