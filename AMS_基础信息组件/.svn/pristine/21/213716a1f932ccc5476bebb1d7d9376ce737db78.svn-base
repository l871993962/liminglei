package com.yss.ams.base.information.support.sys.ieItem.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemService;
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
impl = "com.yss.ams.base.information.modules.sys.ieItem.service.impl.IeItemService",
interfaceClass = com.yss.ams.base.information.support.sys.ieItem.service.IIeItemService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IIeItemService", menuId = "base_ieitemquy")
public interface IIeItemController extends IBaseServiceBusController<IeItem,IIeItemService> {


    @POST
    @Path("/getAllDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<IeItem> getAllDataList() throws Exception;

}