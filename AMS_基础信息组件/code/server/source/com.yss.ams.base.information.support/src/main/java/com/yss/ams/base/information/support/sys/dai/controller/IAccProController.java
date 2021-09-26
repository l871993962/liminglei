package com.yss.ams.base.information.support.sys.dai.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IAccProService;
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
impl = "com.yss.ams.base.information.modules.sys.dai.service.impl.AccProService",
interfaceClass = com.yss.ams.base.information.support.sys.dai.service.IAccProService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAccProService", menuId = "base_accPro")
public interface IAccProController extends IBaseServiceBusController<Dai,IAccProService> {


}