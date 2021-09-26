package com.yss.ams.sec.information.support.modules.mp.secequ.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.mp.secequ.pojo.SecEqu;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquPdService;
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
impl = "com.yss.ams.sec.information.modules.mp.secequ.service.impl.SecEquPdService",
interfaceClass = com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquPdService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecEquPdService", menuId = "sv_securitymatches")
public interface ISecEquPdController extends IBaseServiceBusController<SecEqu,ISecEquPdService> {


}