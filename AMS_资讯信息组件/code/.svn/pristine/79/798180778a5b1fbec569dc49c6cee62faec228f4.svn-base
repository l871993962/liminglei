package com.yss.ams.sec.information.support.modules.sv.indexstock.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.indexstock.pojo.IndexStock;
import com.yss.ams.sec.information.support.modules.sv.indexstock.service.IIndexStockService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.sv.indexstock.service.impl.IndexStockService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.indexstock.service.IIndexStockService.class,
productLine = ProductLine.SECINFO, serviceMapId = "IIndexStockService", menuId = "sv_indexStock")
public interface IIndexStockController extends IBaseServiceBusController<IndexStock,IIndexStockService> {


    @POST
    @Path("/getUnSelectedSecBase")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<IndexStock> getUnSelectedSecBase(@FormParam("c_Index_Code") String c_Index_Code,@FormParam("d_Begin") String d_Begin)throws Exception;

    @POST
    @Path("/getSelectedSecBase")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<IndexStock> getSelectedSecBase(@FormParam("c_Index_Code") String c_Index_Code,@FormParam("d_Begin") String d_Begin)throws Exception;

    @POST
    @Path("/getLastSelectedSecBase")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<IndexStock> getLastSelectedSecBase(@FormParam("c_Index_Code") String c_Index_Code,@FormParam("d_Begin") String d_Begin) throws Exception;

    @POST
    @Path("/getLastUnSelectedSecBase")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<IndexStock> getLastUnSelectedSecBase(@FormParam("c_Index_Code") String c_Index_Code,@FormParam("d_Begin") String d_Begin) throws Exception;

}