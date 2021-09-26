package com.yss.ams.base.information.support.bi.hdaygroup.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHdayGroupService;
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
impl = "com.yss.ams.base.information.modules.bi.hdaygroup.service.impl.HdayGroupService",
interfaceClass = com.yss.ams.base.information.support.bi.hdaygroup.service.IHdayGroupService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IHdayGroupService", menuId = "base_holidays_A")
public interface IHdayGroupController extends IBaseServiceBusController<HdayGroup,IHdayGroupService> {


    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<HdayGroup> getTreeViewData(HashMap<String,String> paraMap);

    @POST
    @Path("/queryAllHdayGroup")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<HdayGroup> queryAllHdayGroup();

}