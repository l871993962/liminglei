package com.yss.uco.elecreco.support.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.AutoState;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.service.IAutoStateService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;
import com.yss.uco.elecreco.support.vo.AutoStateVo;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:11
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.autostate.service.AutoStateService",
interfaceClass = com.yss.uco.elecreco.support.service.IAutoStateService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IAutoStateService", menuId = "base_erzyzb")
public interface IAutoStateServiceController extends IBaseServiceBusController<AutoState,IAutoStateService> {


    @POST
    @Path("/sendAutoMessage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void sendAutoMessage(@FormParam("status") String status,@FormParam("fsn") String fsn,@FormParam("fileType") String fileType,@FormParam("cAssCode") String cAssCode);

    @POST
    @Path("/getSendResult")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<BEN_RECORD> getSendResult(Map<String,String> conditionMap);

    @POST
    @Path("/getDZResult")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<BEN_RECORD> getDZResult(Map<String,String> conditionMap);

    @POST
    @Path("/getDZResultInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErBbInfo> getDZResultInfo(Map<String,String> conditionMap);

    @POST
    @Path("/getDiffData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,String> getDiffData(AutoStateVo vo) throws Exception;

}