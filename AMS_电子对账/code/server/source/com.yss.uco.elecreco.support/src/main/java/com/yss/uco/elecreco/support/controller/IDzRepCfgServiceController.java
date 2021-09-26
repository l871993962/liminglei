package com.yss.uco.elecreco.support.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.reportdesign.template.pojo.ReportTemplateTreeView;
import com.yss.uco.elecreco.support.bean.DzRepCfgInsert;
import com.yss.uco.elecreco.support.service.IDzRepCfgService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.repcfg.service.impl.DzRepCfgService",
interfaceClass = com.yss.uco.elecreco.support.service.IDzRepCfgService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IDzRepCfgService", menuId = "dzRepCfg")
public interface IDzRepCfgServiceController extends IBaseServiceBusController<DzRepCfgInsert,IDzRepCfgService> {


    @POST
    @Path("/getReportTemplateTreeView")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<ReportTemplateTreeView> getReportTemplateTreeView(String ports);

}