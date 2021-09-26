package com.yss.uco.elecreco.support.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.service.IErBbInfoService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;
import com.yss.uco.elecreco.support.vo.ErBbInfoVo;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.erbbinfo.service.impl.ErBbInfoService",
interfaceClass = com.yss.uco.elecreco.support.service.IErBbInfoService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErBbInfoService", menuId = "dzBbInfo")
public interface IErBbInfoServiceController extends IBaseServiceBusController<ErBbInfo,IErBbInfoService> {


    @POST
    @Path("/updateBbInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateBbInfo(ErBbInfoVo vo);

    @POST
    @Path("/deleteBbInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String deleteBbInfo(List<ErBbInfo> bbInfoList);

    @POST
    @Path("/getXmlFile")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getXmlFile(ErBbInfo erBbInfo);

    @POST
    @Path("/sendBbInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String sendBbInfo(List<ErBbInfo> bbInfoList);

    @POST
    @Path("/getBbInfoById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ErBbInfo getBbInfoById(String id);

    @POST
    @Path("/reStartDzMgr")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String reStartDzMgr();

    @POST
    @Path("/acceptBbInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String acceptBbInfo(List<ErBbInfo> pojoList);

    @POST
    @Path("/acceptBbInfoForQTDZ")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String acceptBbInfoForQTDZ(List<ErBbInfo> pojoList);

    @POST
    @Path("/UnPortOper")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String UnPortOper(ErBbInfoVo vo);

    @POST
    @Path("/lockEconfirm")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String lockEconfirm(ErBbInfoVo vo);

    @POST
    @Path("/getDzResultInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,ErBbInfo> getDzResultInfo(ErBbInfoVo vo);

    @POST
    @Path("/isManualAccept")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public boolean isManualAccept(String csn);

    @POST
    @Path("/unAcceptClick")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String unAcceptClick(List<ErBbInfo> pojoList);

    @POST
    @Path("/queryNumberOfRows")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String queryNumberOfRows(ErBbInfo erBbInfo);

}