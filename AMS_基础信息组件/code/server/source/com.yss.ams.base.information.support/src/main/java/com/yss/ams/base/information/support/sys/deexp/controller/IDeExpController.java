package com.yss.ams.base.information.support.sys.deexp.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.deexp.pojo.DeExp;
import com.yss.ams.base.information.support.sys.deexp.service.IDeExpService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.deexp.service.impl.DeExpService",
interfaceClass = com.yss.ams.base.information.support.sys.deexp.service.IDeExpService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDeExpService", menuId = "base_deexpquy")
public interface IDeExpController extends IBaseServiceBusController<DeExp,IDeExpService> {


}