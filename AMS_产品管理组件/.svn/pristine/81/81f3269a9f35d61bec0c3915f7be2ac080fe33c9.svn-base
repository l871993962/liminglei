package com.yss.ams.product.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.commonInfo.controller.IFastPortPdAttributeController;
import com.yss.framework.api.commonInfo.service.IFastPortPdAttributeService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.fast.service.FastPortPdAttributeService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastPortPdAttributeService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastPortService", menuId = "IFastPortService")
public interface IBaseFastPortPdAttributeController extends IFastPortPdAttributeController<IFastPortPdAttributeService>{

}
