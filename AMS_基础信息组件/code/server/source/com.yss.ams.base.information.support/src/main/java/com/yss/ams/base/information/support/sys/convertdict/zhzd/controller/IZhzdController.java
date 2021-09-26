package com.yss.ams.base.information.support.sys.convertdict.zhzd.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.pojo.Zhzd;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.service.IZhzdService;
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
impl = "com.yss.ams.base.information.modules.sys.convertdict.zhzd.service.impl.ZhzdService",
interfaceClass = com.yss.ams.base.information.support.sys.convertdict.zhzd.service.IZhzdService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IZhzdService", menuId = "base_zhzd")
public interface IZhzdController extends IBaseServiceBusController<Zhzd,IZhzdService> {


}