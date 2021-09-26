package com.yss.ams.base.information.support.bi.salesnet.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNetVo;
import com.yss.ams.base.information.support.bi.salesnet.service.ISalesNetService;
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
impl = "com.yss.ams.base.information.modules.bi.salesnet.service.impl.SalesNetService",
interfaceClass = com.yss.ams.base.information.support.bi.salesnet.service.ISalesNetService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISalesNetService", menuId = "base_taselnet")
public interface ISalesNetController extends IBaseServiceBusController<SalesNet,ISalesNetService> {


    @POST
    @Path("/queryPdNet")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SalesNet> queryPdNet(HashMap<String,String> paraDict);

    @POST
    @Path("/deleteNetInfoByVendorCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteNetInfoByVendorCode(SalesNetVo vo);

}