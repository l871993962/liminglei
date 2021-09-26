package com.yss.ams.product.information.modules.ab.port.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.modules.ab.port.controller.IPortController;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortService;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnDeleteVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnInsertVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnUpdateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.OperDQQRVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryUnitPortVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.UpdateDataByPortCodeVo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortControllerImpl extends AbstractBaseServiceBusController<Port,IPortService> implements IPortController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Port> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Port> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Port.class);
    }

    @Override
    public Port getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Port getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Port> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Port> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Port.class);
    }

    @Override
    public List<Port> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Port> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getPlanRelaPortAdd(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPlanRelaPortAdd(paraMap),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getPlanRelaPortBrow(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPlanRelaPortBrow(paraMap),Port.class);
    }

    @Override
    public List<Port> getParamSetPortList(HashMap<String,Object> paraMap){
        return getService().getParamSetPortList(paraMap);
    }

    @Override
    public RestfulQueryResult<Port> getDspPort(HashMap<String,Object> paraMap){
        return queryResToT(getService().getDspPort(paraMap),Port.class);
    }

    @Override
    public List<Port> getRightManagePortListExpertAdd(HashMap<String,Object> paraMap) throws Exception{
        return getService().getRightManagePortListExpertAdd(paraMap);
    }

    @Override
    public RestfulQueryResult<Port> queryDataByBrow(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryDataByBrow(paraMap),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getTreeViewData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),Port.class);
    }
    
    @Override
    public RestfulQueryResult<Port> getTreeViewDataRight(QueryUnitPortVo vo) {
        return queryResToT(getService().getTreeViewDataRight(vo.getIsDataRight(), vo.getParaMap()), Port.class);
    }
    
    @Override
    public List<String> getDataRightListForReportCenter() {
    	return getService().getDataRightListForReportCenter();
    }
    
    @Override
    public RestfulQueryResult<Port> getUnitPortData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getUnitPortData(paraMap),Port.class);
    }
    
    @Override
	public RestfulQueryResult<Port> getUnitPortDataRight(QueryUnitPortVo vo) {
		return queryResToT(getService().getUnitPortDataRight(vo.getIsDataRight(), vo.getParaMap()), Port.class);
	}

    @Override
    public RestfulQueryResult<Port> getAssetTreeView(HashMap<String,Object> paraMap){
        return queryResToT(getService().getAssetTreeView(paraMap),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getPortAssTreeList(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortAssTreeList(paraMap),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getPortAssTreeListAddForm(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortAssTreeListAddForm(paraMap),Port.class);
    }

    @Override
    public String operDQQR(List<Port> lstPort){
        return getService().operDQQR(lstPort);
    }

    //List<Port> lstPort, String date, String isUpdate
    @Override
	public String operDQQR(OperDQQRVo vo) {
    	 return getService().operDQQR(vo.getLstPort(),vo.getDate(),vo.getIsUpdate());
	}

    @Override
    public String operDQQX(List<Port> lstPort){
        return getService().operDQQX(lstPort);
    }

    @Override
    public String operQSQR(List<Port> lstPort){
        return getService().operQSQR(lstPort);
    }

    @Override
    public String operQSQX(List<Port> lstPort){
        return getService().operQSQX(lstPort);
    }

    @Override
    public String operQSGZ(List<Port> lstPort) throws ServiceException, Exception{
        return getService().operQSGZ(lstPort);
    }

    @Override
    public String operGZCX(List<Port> lstPort){
        return getService().operGZCX(lstPort);
    }

    @Override
    public List<Port> getTheSameAssCodeList(String portCode,String assCode){
        return getService().getTheSameAssCodeList(portCode,assCode);
    }

    @Override
    public Map<String,String> getPortDatClsMap(String[] portCodes){
        return getService().getPortDatClsMap(portCodes);
    }

    //BasePojo pojo,Connection conn
    @Override
	public String connInsert(ConnInsertVo vo) {
    	 return getService().connInsert(vo.getPojo(),vo.getConn());
	}

    @Override
    public List<Port> getPortType(){
        return getService().getPortType();
    }

    @Override
    public List<Port> getRightManagePortList(HashMap<String,Object> paraMap) throws Exception{
        return getService().getRightManagePortList(paraMap);
    }

    @Override
    public String queryByParaCode(String paramCode){
        return getService().queryByParaCode(paramCode);
    }

    @Override
    public String operQSQRForGz(List<Port> lstPort){
        return getService().operQSQRForGz(lstPort);
    }

    @Override
    public String operQSQXForGz(List<Port> lstPort){
        return getService().operQSQXForGz(lstPort);
    }

    @Override
    public RestfulQueryResult<Port> getBusinessRangePortAdd(HashMap<String,Object> paraMap){
        return queryResToT(getService().getBusinessRangePortAdd(paraMap),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getBusinessRangePortBrow(HashMap<String,Object> paraMap){
        return queryResToT(getService().getBusinessRangePortBrow(paraMap),Port.class);
    }

    @Override
    public List<Port> getPortListByPortCode(String portCodes){
        return getService().getPortListByPortCode(portCodes);
    }

    @Override
    public void delPortRela(String[] portCodes){
         getService().delPortRela(portCodes);
    }

    @Override
    public RestfulQueryResult<Port> getMomPortSub(HashMap<String,Object> paraMap){
        return queryResToT(getService().getMomPortSub(paraMap),Port.class);
    }

	
	
    //List<BasePojo> list,Connection conn
	@Override
	public void connDelete(ConnDeleteVo vo) {
		 getService().connDelete(castToBasePojoList(vo.getList()),vo.getConn());
		
	}

	@Override
	public void connUpdate(ConnUpdateVo vo) {
		 getService().connUpdate(vo.getPojo(),vo.getConn());
		
	}

	//Port updatePortData,String portCode,Connection conn
	@Override
	public void updateDataByPortCode(UpdateDataByPortCodeVo vo) {
		 getService().updateDataByPortCode(vo.getUpdatePortData(),vo.getPortCode(),vo.getConn());
		
	}
	
	@Override
    public RestfulQueryResult<Port> getAutomaticSetPortAdd(HashMap<String,Object> paraMap) {
        return queryResToT(getService().getAutomaticSetPortAdd(paraMap), Port.class);
    }

	@Override
	public RestfulQueryResult<Port> getSourcesBxzhViewData(HashMap<String, Object> paraMap) {
		 return queryResToT(getService().getSourcesBxzhViewData(paraMap), Port.class);
	}

	@Override
	public String getCacodeByAccountType(String portCode, String dcCode) {
		return getService().getCacodeByAccountType(portCode,dcCode);
	}

	@Override
	public String getCacodeByAccountType1(String openName, String openNo, String openAddr, String dcCode) {
		return getService().getCacodeByAccountType1(openName,openNo,openAddr,dcCode);
	}
	
	

}