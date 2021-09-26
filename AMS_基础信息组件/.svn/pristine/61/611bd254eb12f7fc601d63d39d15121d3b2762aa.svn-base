package com.yss.ams.base.information.support.fast.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.commonInfo.controller.IFastDttdModeController;
import com.yss.framework.api.commonInfo.service.IFastDttdModeService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.fast.service.FastDttdModeService",
interfaceClass = com.yss.framework.api.commonInfo.service.IFastDttdModeService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IFastDttdModeService", menuId = "IFastDttdModeService")
public interface IBaseFastDttdModeController extends IFastDttdModeController<IFastDttdModeService>{

}
