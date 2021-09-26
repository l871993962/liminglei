package com.yss.ams.product.information.support.modules.dataCopy.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyData;
import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyDataCheck;
import com.yss.ams.product.information.support.modules.dataCopy.service.IDataCopyService;
import com.yss.ams.product.information.support.modules.dataCopy.vo.ExeVo;
import com.yss.ams.product.information.support.modules.dataCopy.vo.QueryCopyCheckDataVo;
import com.yss.ams.product.information.support.modules.dataCopy.vo.QueryCopyDataVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.dataCopy.service.impl.DataCopyService",
interfaceClass = com.yss.ams.product.information.support.modules.dataCopy.service.IDataCopyService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IDataCopyService", menuId = "dataCopy")
public interface IDataCopyController extends IBaseServiceBusController<CopyData,IDataCopyService> {


    @POST
    @Path("/exe")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String exe(ExeVo vo);

    @POST
    @Path("/exe1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String exe(HashMap<String,Object> map);

    @POST
    @Path("/verify")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> verify(HashMap<String,Object> map);

    @POST
    @Path("/queryCustom")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> queryCustom();

    @POST
    @Path("/queryCreateCopy")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<CopyData> queryCreateCopy(String portCode);

    @POST
    @Path("/queryPortClsCreateCopy")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<CopyData> queryPortClsCreateCopy();

    @POST
    @Path("/queryCopyCheckData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<CopyDataCheck> queryCopyCheckData(QueryCopyCheckDataVo vo);

    @POST
    @Path("/getCopyCheckDataTotal")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getCopyCheckDataTotal(HashMap<String,Object> paraMap);

    @POST
    @Path("/doBusOperEx")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String doBusOperEx(HashMap<String,Object> map) throws Exception;

    @POST
    @Path("/getService")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getDataCopyService() throws Exception;

    @POST
    @Path("/queryCopyData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<CopyData> queryCopyData(QueryCopyDataVo vo) throws Exception;

}