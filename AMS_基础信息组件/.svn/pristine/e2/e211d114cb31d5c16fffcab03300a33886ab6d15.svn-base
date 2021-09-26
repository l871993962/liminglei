package com.yss.ams.base.information.support.sys.feeRelation.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.feeRelation.pojo.FeeRelation;
import com.yss.ams.base.information.support.sys.feeRelation.service.IFeeRelationService;
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
impl = "com.yss.ams.base.information.modules.sys.feeRelation.service.impl.FeeRelationService",
interfaceClass = com.yss.ams.base.information.support.sys.feeRelation.service.IFeeRelationService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IFeeRelationService", menuId = "base_feeRela")
public interface IFeeRelationController extends IBaseServiceBusController<FeeRelation,IFeeRelationService> {


    @POST
    @Path("/queryRealDateByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FeeRelation> queryRealDateByCondition(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryByConditionFee")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<FeeRelation> queryByConditionFee(HashMap<String,Object> paraMap);

}