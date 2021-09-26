package com.yss.ams.product.information.support.modules.ab.assetsTree_report.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.assetsTree_report.service.IAssetsTreeReportService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.port.service.impl.AssetsTreeReportService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.assetsTree_report.service.IAssetsTreeReportService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IAssetsTreeReportService", menuId = "pd_assetstype")
public interface IAssetsTreeReportController extends IBaseController<IAssetsTreeReportService> {


    @POST
    @Path("/portFilter")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> portFilter(@FormParam("isDataRight") boolean isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trCode") String trCode,@FormParam("userCode") String userCode) throws Exception;

}