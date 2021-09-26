package com.yss.ams.base.information.support.sys.dsppara.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dsppara.pojo.DspPara;
import com.yss.ams.base.information.support.sys.dsppara.service.IDspParaService;
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
impl = "com.yss.ams.base.information.modules.sys.dsppara.service.impl.DspParaService",
interfaceClass = com.yss.ams.base.information.support.sys.dsppara.service.IDspParaService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDspParaService", menuId = "base_dspparaquy")
public interface IDspParaController extends IBaseServiceBusController<DspPara,IDspParaService> {


}