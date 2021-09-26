package com.yss.ams.base.information.support.sys.portbusinessrange.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.fast.task.support.automatic.service.controller.IAutoRouteBusiServiceController;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;

@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.portbusinessrange.service.impl.AutoRouteBusiServiceImpl",
interfaceClass = com.yss.fast.task.support.automatic.service.IAutoRouteBusiService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAutoRouteBusiService", menuId = "base_portBusinessRange")
public interface IBaseAutoRouteBusiController extends IAutoRouteBusiServiceController{

}
