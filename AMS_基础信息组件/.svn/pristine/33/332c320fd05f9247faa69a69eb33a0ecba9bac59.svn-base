package com.yss.ams.base.information.support.sys.indexdi.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.indexdi.pojo.Indexdi;
import com.yss.ams.base.information.support.sys.indexdi.service.IIndexdiService;
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
impl = "com.yss.ams.base.information.modules.sys.indexdi.service.impl.IndexdiService",
interfaceClass = com.yss.ams.base.information.support.sys.indexdi.service.IIndexdiService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IIndexdiService", menuId = "base_indexdiquy")
public interface IIndexdiController extends IBaseServiceBusController<Indexdi,IIndexdiService> {


}