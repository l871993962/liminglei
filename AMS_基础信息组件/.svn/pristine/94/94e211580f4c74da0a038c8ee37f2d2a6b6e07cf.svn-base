package com.yss.ams.base.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.commonInfo.controller.IFastPortOrgController;
import com.yss.framework.api.commonInfo.service.IFastPortOrgService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.org.service.impl.FastPortOrgServiceImpl",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastPortOrgService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastPortOrgService", menuId = "base_fastPortOrg")
public interface IBaseFastPortOrgController extends IFastPortOrgController<IFastPortOrgService> {

}
