package com.yss.ams.base.information.support.bi.srcsign.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.srcsign.pojo.SrcSign;
import com.yss.ams.base.information.support.bi.srcsign.service.ISrcSignService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.srcsign.service.impl.SrcSignService",
interfaceClass = com.yss.ams.base.information.support.bi.srcsign.service.ISrcSignService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISrcSignService", menuId = "base_baseSourceRecord")
public interface ISrcSignController extends IBaseServiceBusController<SrcSign,ISrcSignService> {


}