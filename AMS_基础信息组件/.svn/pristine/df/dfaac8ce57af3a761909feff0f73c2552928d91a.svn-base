package com.yss.ams.base.information.support.sys.dtatdattr.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtatdAttrService;
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
impl = "com.yss.ams.base.information.modules.sys.dtatdattr.service.impl.DtatdAttrService",
interfaceClass = com.yss.ams.base.information.support.sys.dtatdattr.service.IDtatdAttrService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IDtatdAttrService", menuId = "base_dtatdattrquy")
public interface IDtatdAttrController extends IBaseServiceBusController<DtatdAttr,IDtatdAttrService> {


}