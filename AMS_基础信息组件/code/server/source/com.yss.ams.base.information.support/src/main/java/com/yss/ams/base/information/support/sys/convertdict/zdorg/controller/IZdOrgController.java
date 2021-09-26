package com.yss.ams.base.information.support.sys.convertdict.zdorg.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.convertdict.zdorg.pojo.ZdOrgTreeView;
import com.yss.ams.base.information.support.sys.convertdict.zdorg.service.IZdOrgService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.convertdict.zdorg.service.impl.ZdOrgService",
interfaceClass = com.yss.ams.base.information.support.sys.convertdict.zdorg.service.IZdOrgService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IZdOrgService", menuId = "base_zdorg")
public interface IZdOrgController extends IBaseServiceBusController<ZdOrgTreeView,IZdOrgService> {


    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<ZdOrgTreeView> getTreeViewData(HashMap<String,Object> paraMap);

}