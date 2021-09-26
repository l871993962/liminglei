package com.yss.ams.base.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.commonInfo.controller.IFastOrgController;
import com.yss.framework.api.commonInfo.service.IFastOrgService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.fast.service.FastOrgService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastOrgService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastOrgService", menuId = "IFastOrgService")
public interface IBaseFastOrgController extends IFastOrgController<IFastOrgService> {

}
