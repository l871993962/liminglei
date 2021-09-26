package com.yss.ams.product.information.support.modules.aa.portcls.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetDataListByPortsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetPortClsByDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.PortClsRecordsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryFjSyfpInfoVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByClassVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByClsLevelVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByDvClsAndDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByDvClsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByLiquidVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsSortVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsYxqVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPreviousPortClsVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.aa.portcls.service.impl.PortClsDataService",
interfaceClass = com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IClsPortDataService", menuId = "pd_productgrade")
public interface IClsPortDataController extends IBaseController<IClsPortDataService> {


    @POST
    @Path("/updateByTimestamp")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public CacheData updateByTimestamp(String timestamp);

    @POST
    @Path("/queryByIds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryByIds(String ids);

    @POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getKeyConvertMap();

    @POST
    @Path("/getKeyConvertMapByList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getKeyConvertMap(List<String> listKey);

    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getDataList();

    @POST
    @Path("/getDataListRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortCls> getDataListRes();

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls getDataByCode(String dataCode);

    @POST
    @Path("/getPojoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls getPojoByCode(String pojoCode);

    @POST
    @Path("/getDataListByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getDataListByTypes(String[] types);

    @POST
    @Path("/getQueryResByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortCls> getQueryResByTypes(String[] types);

    @POST
    @Path("/getDataListByKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getDataListByKeys(String[] keys);

    @POST
    @Path("/getQueryResByKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortCls> getQueryResByKeys(String[] keys);

    @POST
    @Path("/getPortClsCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getPortClsCode(String code);

    @POST
    @Path("/getShortDataMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getShortDataMap(String portCode);

    @POST
    @Path("/getDataListByPorts")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getDataListByPorts(String[] ports);

    @POST
    @Path("/getClsCodesByPortCodeAndType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getClsCodesByPortCodeAndType(@FormParam("portCode") String portCode,@FormParam("types") String types);

    @POST
    @Path("/getDataListByPorts1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getDataListByPorts1(GetDataListByPortsVo vo);

    @POST
    @Path("/getPortClsByDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> getPortClsByDate(GetPortClsByDateVo vo);

    @POST
    @Path("/queryPortCls")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls queryPortCls(QueryPortClsVo vo);

    @POST
    @Path("/queryPortCls1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public PortCls queryPortCls1(@FormParam("portCode") String portCode,@FormParam("classPort") String classPort);

    @POST
    @Path("/portClsRecords")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> portClsRecords(PortClsRecordsVo vo);

    @POST
    @Path("/queryPortCls_Date")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls queryPortCls_Date(QueryPortClsDateVo vo);

    @POST
    @Path("/queryPortCls2")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortCls2(String portCode);

    @POST
    @Path("/queryPortClsByTypeAndClass")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public PortCls queryPortClsByTypeAndClass(@FormParam("portCode") String portCode,@FormParam("portClsType") String portClsType,@FormParam("classPort") String classPort);

    @POST
    @Path("/queryPortClsByClass")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsByClass(QueryPortClsByClassVo Vo);

    @POST
    @Path("/queryPortCls3")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<PortCls> queryPortCls(@FormParam("portCode") String portCode,@FormParam("portClsType") String portClsType,@FormParam("portClsLevel") String portClsLevel);

    @POST
    @Path("/queryPortClsMx")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsMx(String portCode);

    @POST
    @Path("/queryPortClsList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsList(String portCode);

    @POST
    @Path("/queryPortClsYxq")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls queryPortClsYxq(QueryPortClsYxqVo Vo);

    @POST
    @Path("/queryPortCls4")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortCls4(QueryPortClsVo vo);

    @POST
    @Path("/queryPortClsByLiquid")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsByLiquid(QueryPortClsByLiquidVo vo);

    @POST
    @Path("/queryPreviousPortCls")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls queryPreviousPortCls(QueryPreviousPortClsVo vo);

    @POST
    @Path("/queryPortClsByDvCls")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsByDvCls(QueryPortClsByDvClsVo vo);

    @POST
    @Path("/queryPortClsByDvCls1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsByDvCls1(QueryPortClsByDvClsVo vo);

    @POST
    @Path("/queryPortClsByDvClsAndDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryPortClsByDvClsAndDate(QueryPortClsByDvClsAndDateVo vo);

    @POST
    @Path("/queryPortClsByClsLevel")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls queryPortClsByClsLevel(QueryPortClsByClsLevelVo vo);

    @POST
    @Path("/queryPortClsSort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public PortCls queryPortClsSort(QueryPortClsSortVo vo);

    @POST
    @Path("/queryFjSyfpInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortCls> queryFjSyfpInfo(QueryFjSyfpInfoVo vo);

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void insert(PortCls pojo);

    @POST
    @Path("/updateById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void updateById(PortCls pojo);

    @POST
    @Path("/deleteById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteById(PortCls pojo);

}