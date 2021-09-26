package com.yss.ams.product.information.support.modules.ab.port.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.service.IPortService;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnDeleteVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnInsertVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnUpdateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.OperDQQRVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryUnitPortVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.UpdateDataByPortCodeVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;

/**
*
* @author neil
* @date 2020-09-14 11:34:48
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.port.service.impl.PortService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortService", menuId = "pd_portfolio")
public interface IPortController extends IBaseServiceBusController<Port,IPortService> {


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
    @Path("/getPlanRelaPortAdd")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getPlanRelaPortAdd(HashMap<String,Object> paraMap);

    @POST
    @Path("/getPlanRelaPortBrow")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getPlanRelaPortBrow(HashMap<String,Object> paraMap);

    @POST
    @Path("/getParamSetPortList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getParamSetPortList(HashMap<String,Object> paraMap);

    @POST
    @Path("/getDspPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getDspPort(HashMap<String,Object> paraMap);

    @POST
    @Path("/getRightManagePortListExpertAdd")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortListExpertAdd(HashMap<String,Object> paraMap) throws Exception;

    @POST
    @Path("/queryDataByBrow")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> queryDataByBrow(HashMap<String,Object> paraMap);

    @POST
    @Path("/getTreeViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getTreeViewData(HashMap<String,Object> paraMap);
    
    @POST
    @Path("/getTreeViewDataRight")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getTreeViewDataRight(QueryUnitPortVo vo);
    
    @POST
    @Path("/getDataRightListForReportCenter")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getDataRightListForReportCenter();
    
    @POST
    @Path("/getUnitPortData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getUnitPortData(HashMap<String,Object> paraMap);
    
    @POST
    @Path("/getUnitPortDataRight")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getUnitPortDataRight(QueryUnitPortVo vo);

    @POST
    @Path("/getAssetTreeView")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getAssetTreeView(HashMap<String,Object> paraMap);

    @POST
    @Path("/getPortAssTreeList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getPortAssTreeList(HashMap<String,Object> paraMap);

    @POST
    @Path("/getPortAssTreeListAddForm")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getPortAssTreeListAddForm(HashMap<String,Object> paraMap);

    @POST
    @Path("/operDQQR")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operDQQR(List<Port> lstPort);

    @POST
    @Path("/operDQQR1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operDQQR(OperDQQRVo vo);

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
    @Path("/operQSGZ")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operQSGZ(List<Port> lstPort) throws ServiceException, Exception;

    @POST
    @Path("/operGZCX")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operGZCX(List<Port> lstPort);

    @POST
    @Path("/getTheSameAssCodeList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<Port> getTheSameAssCodeList(@FormParam("portCode") String portCode,@FormParam("assCode") String assCode);

    @POST
    @Path("/getPortDatClsMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,String> getPortDatClsMap(String[] portCodes);

    @POST
    @Path("/connInsert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String connInsert(ConnInsertVo vo);

    @POST
    @Path("/connDelete")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void connDelete(ConnDeleteVo vo);

    @POST
    @Path("/getPortType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortType();

    @POST
    @Path("/connUpdate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void connUpdate(ConnUpdateVo vo);

    @POST
    @Path("/getRightManagePortList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getRightManagePortList(HashMap<String,Object> paraMap) throws Exception;

    @POST
    @Path("/queryByParaCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String queryByParaCode(String paramCode);

    @POST
    @Path("/operQSQRForGz")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operQSQRForGz(List<Port> lstPort);

    @POST
    @Path("/operQSQXForGz")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String operQSQXForGz(List<Port> lstPort);

    @POST
    @Path("/getBusinessRangePortAdd")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getBusinessRangePortAdd(HashMap<String,Object> paraMap);

    @POST
    @Path("/getBusinessRangePortBrow")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getBusinessRangePortBrow(HashMap<String,Object> paraMap);

    @POST
    @Path("/updateDataByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void updateDataByPortCode(UpdateDataByPortCodeVo vo);

    @POST
    @Path("/getPortListByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> getPortListByPortCode(String portCodes);

    @POST
    @Path("/delPortRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void delPortRela(String[] portCodes);

    @POST
    @Path("/getMomPortSub")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getMomPortSub(HashMap<String,Object> paraMap);
    
    @POST
    @Path("/getAutomaticSetPortAdd")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<Port> getAutomaticSetPortAdd(HashMap<String,Object> paraMap);

    /**
	 * STORY #75531 【广发基金】支持美元本位币记账组合的人民币转换和核对的需求 (#2 #1 ) 
	 * 查询并行组合
	 * @author zengguowei
	 * @since 2019-07-18
	 * @param paraMap
	 * @return
	 */
    @POST
    @Path("/getSourcesBxzhViewData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public RestfulQueryResult<Port> getSourcesBxzhViewData(HashMap<String, Object> paraMap);
     
    /**
	 * STORY #102352 账户信息设置中现金账户“默认值”
	 * @param portCode
	 * @param dcCode
	 * @return
	 */
    @POST
    @Path("/getCacodeByAccountType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getCacodeByAccountType(@FormParam("portCode") String portCode,@FormParam("dcCode") String dcCode);
    
    /**
	 * STORY #102352 账户信息设置中现金账户“默认值”
	 * @param portCode
	 * @param dcCode
	 * @return
	 */
    @POST
    @Path("/getCacodeByAccountType1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getCacodeByAccountType1(@FormParam("openName") String openName,@FormParam("openNo") String openNo,@FormParam("openAddr") String openAddr,@FormParam("dcCode") String dcCode);
    
}