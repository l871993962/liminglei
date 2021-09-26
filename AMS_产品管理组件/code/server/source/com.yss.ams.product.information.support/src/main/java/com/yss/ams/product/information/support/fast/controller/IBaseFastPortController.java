package com.yss.ams.product.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.commonInfo.controller.IFastPortController;
import com.yss.framework.api.commonInfo.service.IFastPortService;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.fast.service.FastPortService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastPortService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastPortService", menuId = "IFastPortService")
public interface IBaseFastPortController extends IFastPortController<IFastPortService> {

}
