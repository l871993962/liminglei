package com.yss.platform.support.dataservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.vo.GetListByCodeAndBuildDateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryDataRight1Vo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryDataRight2Vo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryPortMapVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryPortVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.platform.support.dataservice.service.IPortDataService;
import com.yss.right.pojo.DataRight;

/**
*
* @author neil
* @date 2020-09-14 11:34:48
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.fast.systemmanager.port.PortDataService",
interfaceClass = com.yss.platform.support.dataservice.service.IPortDataService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortDataService", menuId = "portfolio")
public interface IPortDataController extends IBaseController<IPortDataService> {


    @POST
    @Path("/updateByTimestamp")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public CacheData updateByTimestamp(String timestamp);

    @POST
    @Path("/queryByIds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> queryByIds(String ids);

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
    public List<Port> getDataList();

    @POST
    @Path("/getDataListRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getDataListRes();

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port getDataByCode(String dataCode);

    @POST
    @Path("/getPojoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port getPojoByCode(String pojoCode);

    @POST
    @Path("/getDataListByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getDataListByTypes(String[] types);

    @POST
    @Path("/getQueryResByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getQueryResByTypes(String[] types);

    @POST
    @Path("/getDataListByKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getDataListByKeys(String[] keys);

    @POST
    @Path("/getQueryResByKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getQueryResByKeys(String[] keys);

    @POST
    @Path("/getRightManagePortList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortList(HashMap<String,String> paraMap);

    @POST
    @Path("/getTreeViewListByCondion")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getTreeViewListByCondion(HashMap<String,Object> paraMap);

    @POST
    @Path("/getTreeViewResByCondion")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getTreeViewResByCondion(HashMap<String,Object> paraMap);

    @POST
    @Path("/doPortFilterPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> doPortFilterPort();

    @POST
    @Path("/getDefaultPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getDefaultPort(@FormParam("ports") String ports,@FormParam("cTrCode") String cTrCode);

    @POST
    @Path("/getListByCodeAndBuildDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getListByCodeAndBuildDate(GetListByCodeAndBuildDateVo vo);

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void insert(Port port);

    @POST
    @Path("/insertWithRetInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insertWithRetInfo(Port port);

    @POST
    @Path("/updateById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void updateById(Port port);

    @POST
    @Path("/queryByCondition")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> queryByCondition(HashMap<String,Object> paraMap);

    @POST
    @Path("/deleteById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void deleteById(Port pojo);

    @POST
    @Path("/audit")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void audit(Port pojo);

    @POST
    @Path("/antiAudit")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void antiAudit(Port pojo);

    @POST
    @Path("/getPortListByDatClass")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortListByDatClass(String datClass);

    @POST
    @Path("/doPortFilter")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> doPortFilter();

    @POST
    @Path("/doPortFilterRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> doPortFilterRes();

    @POST
    @Path("/doPortFilter1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> doPortFilter(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass);

    @POST
    @Path("/doPortFilterRes1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> doPortFilterRes(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass);

    @POST
    @Path("/doPortFilter2")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> doPortFilter(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode);

    @POST
    @Path("/doPortFilterRes2")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> doPortFilterRes(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode);

    @POST
    @Path("/doPortFilter3")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> doPortFilter(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trCode") String trCode);

    @POST
    @Path("/queryDataRight")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<DataRight> queryDataRight(String trCode);

    @POST
    @Path("/doPortFilterRes3")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> doPortFilterRes(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trCode") String trCode);

    @POST
    @Path("/getPortListByDvPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortListByDvPortCode(String dvPortCode);

    @POST
    @Path("/getPortInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port getPortInfo(String cPortCode);

    @POST
    @Path("/getPortByAssCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port getPortByAssCode(String assCode);

    @POST
    @Path("/getPortTreeList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortTreeList();

    @POST
    @Path("/getStockTypeList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getStockTypeList();

    @POST
    @Path("/getPortListByUserAndPost")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> getPortListByUserAndPost(@FormParam("userCode") String userCode,@FormParam("postCodes") String postCodes);

    @POST
    @Path("/getPortByUserAndPost")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> getPortByUserAndPost(@FormParam("userCode") String userCode,@FormParam("postCodes") String postCodes);

    @POST
    @Path("/getStockTypeList1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getStockTypeList(String userCode);

    @POST
    @Path("/getPortResByUserAndPost")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getPortResByUserAndPost(@FormParam("userCode") String userCode,@FormParam("postCodes") String postCodes);

    @POST
    @Path("/getPortDataByKeysNoFilter")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortDataByKeysNoFilter(String[] keys);

    @POST
    @Path("/getPortInfoList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortInfoList(HashMap<String,Object> paraMap);

    @POST
    @Path("/getAllDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getAllDataList();

    @POST
    @Path("/getPortByPortName")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Port getPortByPortName(String portName);

    @POST
    @Path("/getPortCodePSubCount")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getPortCodePSubCount(String portCodeP);

    @POST
    @Path("/operDQQR")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operDQQR(List<Port> lstPort);

    @POST
    @Path("/operDQQX")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operDQQX(List<Port> lstPort);

    @POST
    @Path("/operQSQR")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operQSQR(List<Port> lstPort);

    @POST
    @Path("/operQSQX")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operQSQX(List<Port> lstPort);

    @POST
    @Path("/getAssPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getAssPort(String trCode) throws Exception;

    @POST
    @Path("/getGroupDataTree")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getGroupDataTree(String trCode) throws Exception;

    @POST
    @Path("/checkPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String checkPortCode(String portCode);

    @POST
    @Path("/queryParentPortTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> queryParentPortTreeViewData(@FormParam("userCode") String userCode,@FormParam("postCode") String postCode);

    @POST
    @Path("/queryProductORPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> queryProductORPort();

    @POST
    @Path("/getTreePortDataByCodes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getTreePortDataByCodes(String[] keys);

    @POST
    @Path("/getAllGroupAndPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getAllGroupAndPort() throws Exception;

    @POST
    @Path("/dueClearedPorts")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> dueClearedPorts(HashMap<String,String> paraMap);

    @POST
    @Path("/getBindPortAndPost")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public Map<String,String> getBindPortAndPost(@FormParam("userCode") String userCode,@FormParam("portCodes") String portCodes);

    @POST
    @Path("/getFilterPortDataForOperRight")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getFilterPortDataForOperRight(String menuId) throws YssException;

    @POST
    @Path("/queryParentPortTreeViewData1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> queryParentPortTreeViewData(@FormParam("userCode") String userCode,@FormParam("postCode") String postCode,@FormParam("menuId") String menuId);

    @POST
    @Path("/PortFilter_ReportCenter")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> PortFilter_ReportCenter(@FormParam("isDataRight") boolean isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trCode") String trCode)  throws Exception;

    @POST
    @Path("/getPortByAssCodes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortByAssCodes(List<String> assCodes);

    @POST
    @Path("/queryDataRight1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<DataRight> queryDataRight1(QueryDataRight1Vo vo);

    @POST
    @Path("/queryDataRight2")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<DataRight> queryDataRight2(QueryDataRight2Vo vo);

    @POST
    @Path("/queryPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> queryPort(QueryPortVo vo);

    @POST
    @Path("/queryPortMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> queryPortMap(QueryPortMapVo vo);

    @POST
    @Path("/doPortFilterResKMmap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public RestfulQueryResult<Port> doPortFilterResKMmap(@FormParam("isDataRight") String isDataRight,@FormParam("datClass") String datClass,@FormParam("dvPortCode") String dvPortCode,@FormParam("trcode") String trcode,@FormParam("getKM") String getKM,@FormParam("mapyear") String mapyear,@FormParam("unMapkm") String unMapkm);

    @POST
    @Path("/getAllPortData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Map<String,String>> getAllPortData() throws Exception;

}