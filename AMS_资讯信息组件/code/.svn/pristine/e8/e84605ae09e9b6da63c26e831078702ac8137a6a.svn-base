package com.yss.ams.sec.information.support.modules.sv.base.controller;

import javax.ws.rs.Path;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseQhService;
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
impl = "com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseQhService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseQhService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecBaseQhService", menuId = "sv_future")
public interface ISecBaseQhController extends IBaseServiceBusController<SecBase,ISecBaseQhService> {


}