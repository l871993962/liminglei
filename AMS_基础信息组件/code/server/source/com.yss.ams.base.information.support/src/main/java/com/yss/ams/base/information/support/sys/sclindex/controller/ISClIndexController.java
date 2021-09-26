package com.yss.ams.base.information.support.sys.sclindex.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.sclindex.pojo.SClIndex;
import com.yss.ams.base.information.support.sys.sclindex.service.ISClIndexService;
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
impl = "com.yss.ams.base.information.modules.sys.sclindex.service.impl.SClIndexService",
interfaceClass = com.yss.ams.base.information.support.sys.sclindex.service.ISClIndexService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISClIndexService", menuId = "base_sClIndex")
public interface ISClIndexController extends IBaseServiceBusController<SClIndex,ISClIndexService> {


}