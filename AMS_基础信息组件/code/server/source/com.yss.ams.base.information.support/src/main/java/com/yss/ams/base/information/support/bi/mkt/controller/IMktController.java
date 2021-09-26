package com.yss.ams.base.information.support.bi.mkt.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.bi.mkt.pojo.MarketVoc;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktExtend;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktVo;
import com.yss.ams.base.information.support.bi.mkt.service.IMktService;
import com.yss.framework.api.common.co.Mkt;
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
impl = "com.yss.ams.base.information.modules.bi.mkt.service.impl.MktService",
interfaceClass = com.yss.ams.base.information.support.bi.mkt.service.IMktService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IMktService", menuId = "base_exchange")
public interface IMktController extends IBaseServiceBusController<Mkt,IMktService> {


    @POST
    @Path("/getCheckStatus")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getCheckStatus(String MktCode);

    @POST
    @Path("/selectByConditionExtend")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<MktExtend> selectByConditionExtend(MktVo vo);

    @POST
    @Path("/getAllMkt")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<MarketVoc> getAllMkt();

    @POST
    @Path("/compareQsjg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String compareQsjg(String mktCode);

}