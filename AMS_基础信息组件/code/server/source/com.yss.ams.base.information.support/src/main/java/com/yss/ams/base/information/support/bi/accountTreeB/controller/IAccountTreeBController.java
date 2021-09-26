package com.yss.ams.base.information.support.bi.accountTreeB.controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.accountTreeB.pojo.AccountTreeB;
import com.yss.ams.base.information.support.bi.accountTreeB.service.IAccountTreeBService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.accountTreeB.service.impl.AccountTreeBService",
interfaceClass = com.yss.ams.base.information.support.bi.accountTreeB.service.IAccountTreeBService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAccountTreeBService", menuId = "base_accountTreeB")
public interface IAccountTreeBController extends IBaseServiceBusController<AccountTreeB,IAccountTreeBService> {


    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<AccountTreeB> getTreeViewData(HashMap<String,Object> paraMap);

}