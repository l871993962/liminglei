package com.yss.ifa.szt.tool.para.service.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.ifa.szt.tool.para.service.IElecParaService;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ifa.szt.tool.para.service.impl.DzParaService",
interfaceClass = com.yss.ifa.szt.tool.para.service.IElecParaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IElecParaService", menuId = "dzPara")
public interface IElecParaController extends IBaseController<IElecParaService> {


}