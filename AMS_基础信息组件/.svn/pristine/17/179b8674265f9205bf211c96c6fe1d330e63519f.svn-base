package com.yss.ams.base.information.support.sys.secvardi.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.secvardi.pojo.SecVarDi;
import com.yss.ams.base.information.support.sys.secvardi.service.ISecVarDiService;
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
impl = "com.yss.ams.base.information.modules.sys.secvardi.service.impl.SecVarDiService",
interfaceClass = com.yss.ams.base.information.support.sys.secvardi.service.ISecVarDiService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISecVarDiService", menuId = "base_seccategoryquy")
public interface ISecVarDiController extends IBaseServiceBusController<SecVarDi,ISecVarDiService> {


}