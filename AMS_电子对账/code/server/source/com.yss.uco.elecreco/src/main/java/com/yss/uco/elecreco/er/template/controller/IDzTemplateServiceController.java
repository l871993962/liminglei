package com.yss.uco.elecreco.er.template.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;
import com.yss.uco.elecreco.er.template.pojo.FileStreamParam;
import com.yss.uco.elecreco.er.template.service.IDzTemplateService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.template.service.impl.DzTemplateService",
interfaceClass = com.yss.uco.elecreco.er.template.service.IDzTemplateService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IDzTemplateService", menuId = "dzTemplate")
public interface IDzTemplateServiceController extends IBaseServiceBusController<DzTemplate,IDzTemplateService> {


    @POST
    @Path("/getTemplateByTypeCodeAndPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public DzTemplate getTemplateByTypeCodeAndPortCode(@FormParam("typeCode") String typeCode,@FormParam("portCode") String portCode);

    @POST
    @Path("/updateStatus")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateStatus(List<BasePojo> basePojoList) throws Exception;

    @POST
    @Path("/deploy")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String deploy(String zipFiles) throws Exception;

    @POST
    @Path("/unDeploy")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String unDeploy(List<BasePojo> templateList) throws Exception;

    @POST
    @Path("/downLoad")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String downLoad(BasePojo basePojo) throws Exception;

    @POST
    @Path("/getDeployTemplate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getDeployTemplate() throws Exception;

    @POST
    @Path("/upload")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String upload(FileStreamParam fileStreamParam) throws Exception;

}