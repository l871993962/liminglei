package com.yss.ams.base.information.support.bi.ie.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.ie.service.impl.IeService",
interfaceClass = com.yss.ams.base.information.support.bi.ie.service.IIeService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IIeService", menuId = "base_ie")
public interface IIeController extends IBaseServiceBusController<Ie,IIeService> {


}