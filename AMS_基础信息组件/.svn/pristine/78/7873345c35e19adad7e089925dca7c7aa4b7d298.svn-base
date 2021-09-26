package com.yss.ams.base.information.support.sys.accele.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.accele.pojo.AccEle;
import com.yss.ams.base.information.support.sys.accele.service.IAccEleService;
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
impl = "com.yss.ams.base.information.modules.sys.accele.service.impl.AccEleService",
interfaceClass = com.yss.ams.base.information.support.sys.accele.service.IAccEleService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAccEleService", menuId = "base_accelequy")
public interface IAccEleController extends IBaseServiceBusController<AccEle,IAccEleService> {


}