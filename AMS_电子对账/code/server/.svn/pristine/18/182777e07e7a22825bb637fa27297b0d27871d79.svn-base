package com.yss.uco.elecreco.er.spilt.rule.controller;

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
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rule.pojo.ErSplitRule;
import com.yss.uco.elecreco.er.spilt.rule.service.IErSplitRuleService;
import com.yss.uco.elecreco.er.spilt.rule.vo.ErSplitRuleVo;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.spilt.rule.service.impl.ErSplitRuleService",
interfaceClass = com.yss.uco.elecreco.er.spilt.rule.service.IErSplitRuleService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErSplitRuleService", menuId = "erSplitRule")
public interface IErSplitRuleServiceController extends IBaseServiceBusController<BasePojo,IErSplitRuleService> {


    @POST
    @Path("/updateRelaDetailKmInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateRelaDetailKmInfo(ErSplitRuleVo vo);

    @POST
    @Path("/showUnSplitDetailKmInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<BasePojo> showUnSplitDetailKmInfo(@FormParam("portCode") String portCode,@FormParam("date") String date);

    @POST
    @Path("/showRelaDetailKmInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<BasePojo> showRelaDetailKmInfo(String id);

    @POST
    @Path("/getSplitRulesBySplitRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErSplitRule> getSplitRulesBySplitRela(ErSplitRela rela);

    @POST
    @Path("/getTghKmTableHeadKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<HeadKey> getTghKmTableHeadKeys();

    @POST
    @Path("/getPortKmTableHeadKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<HeadKey> getPortKmTableHeadKeys();

}