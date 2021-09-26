package com.yss.ams.base.information.support.sys.dttdmode.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDttdModeService;
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
impl = "com.yss.ams.base.information.modules.sys.dttdmode.service.impl.DttdModeService",
interfaceClass = com.yss.ams.base.information.support.sys.dttdmode.service.IDttdModeService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDttdModeService", menuId = "base_dttdmodequy")
public interface IDttdModeController extends IBaseServiceBusController<DttdMode,IDttdModeService> {


}