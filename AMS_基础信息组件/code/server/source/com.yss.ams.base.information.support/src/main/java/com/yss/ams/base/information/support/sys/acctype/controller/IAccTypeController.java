package com.yss.ams.base.information.support.sys.acctype.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeService;
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
impl = "com.yss.ams.base.information.modules.sys.acctype.service.impl.AccTypeService",
interfaceClass = com.yss.ams.base.information.support.sys.acctype.service.IAccTypeService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAccTypeService", menuId = "base_acctypequy")
public interface IAccTypeController extends IBaseServiceBusController<AccType,IAccTypeService> {


}