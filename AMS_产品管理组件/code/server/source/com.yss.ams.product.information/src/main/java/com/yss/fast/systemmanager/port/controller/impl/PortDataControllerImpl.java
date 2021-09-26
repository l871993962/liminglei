package com.yss.fast.systemmanager.port.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.modules.ab.port.vo.GetListByCodeAndBuildDateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryDataRight1Vo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryDataRight2Vo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryPortMapVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryPortVo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.platform.support.dataservice.controller.IPortDataController;
import com.yss.platform.support.dataservice.service.IPortDataService;
import com.yss.right.pojo.DataRight;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortDataControllerImpl extends AbstractBaseController<IPortDataService> implements IPortDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<Port> queryByIds(String ids){
        return getService().queryByIds(ids);
    }

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
    public List<Port> getRightManagePortList(HashMap<String,String> paraMap){
        return getService().getRightManagePortList(paraMap);
    }

    @Override
    public List<Port> getTreeViewListByCondion(HashMap<String,Object> paraMap){
        return getService().getTreeViewListByCondion(paraMap);
    }

    @Override
    public RestfulQueryResult<Port> getTreeViewResByCondion(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewResByCondion(paraMap),Port.class);
    }

    @Override
    public List<Port> doPortFilterPort(){
        return getService().doPortFilterPort();
    }

    @Override
    public RestfulQueryResult<Port> getDefaultPort(String ports,String cTrCode){
        return queryResToT(getService().getDefaultPort(ports,cTrCode),Port.class);
    }

    @Override
    public void insert(Port port){
         getService().insert(port);
    }

    @Override
    public String insertWithRetInfo(Port port){
        return getService().insertWithRetInfo(port);
    }

    @Override
    public void updateById(Port port){
         getService().updateById(port);
    }

    @Override
    public List<Port> queryByCondition(HashMap<String,Object> paraMap){
        return getService().queryByCondition(paraMap);
    }

    @Override
    public void deleteById(Port pojo){
         getService().deleteById(pojo);
    }

    @Override
    public void audit(Port pojo){
         getService().audit(pojo);
    }

    @Override
    public void antiAudit(Port pojo){
         getService().antiAudit(pojo);
    }

    @Override
    public List<Port> getPortListByDatClass(String datClass){
        return castToListT(getService().getPortListByDatClass(datClass),Port.class);
    }

    @Override
    public List<Port> doPortFilter(){
        return castToListT(getService().doPortFilter(),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> doPortFilterRes(){
        return queryResToT(getService().doPortFilterRes(),Port.class);
    }

    @Override
    public List<Port> doPortFilter(String isDataRight,String datClass){
        return castToListT(getService().doPortFilter(isDataRight,datClass),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> doPortFilterRes(String isDataRight,String datClass){
        return queryResToT(getService().doPortFilterRes(isDataRight,datClass),Port.class);
    }

    @Override
    public List<Port> doPortFilter(String isDataRight,String datClass,String dvPortCode){
        return castToListT(getService().doPortFilter(isDataRight,datClass,dvPortCode),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> doPortFilterRes(String isDataRight,String datClass,String dvPortCode){
        return queryResToT(getService().doPortFilterRes(isDataRight,datClass,dvPortCode),Port.class);
    }

    @Override
    public List<Port> doPortFilter(String isDataRight,String datClass,String dvPortCode,String trCode){
        return castToListT(getService().doPortFilter(isDataRight,datClass,dvPortCode,trCode),Port.class);
    }

    @Override
    public List<DataRight> queryDataRight(String trCode){
        return getService().queryDataRight(trCode);
    }

    @Override
    public RestfulQueryResult<Port> doPortFilterRes(String isDataRight,String datClass,String dvPortCode,String trCode){
        return queryResToT(getService().doPortFilterRes(isDataRight,datClass,dvPortCode,trCode),Port.class);
    }

    @Override
    public List<Port> getPortListByDvPortCode(String dvPortCode){
        return castToListT(getService().getPortListByDvPortCode(dvPortCode),Port.class);
    }

    @Override
    public Port getPortInfo(String cPortCode){
        return getService().getPortInfo(cPortCode);
    }

    @Override
    public Port getPortByAssCode(String assCode){
        return getService().getPortByAssCode(assCode);
    }

    @Override
    public List<Port> getPortTreeList(){
        return castToListT(getService().getPortTreeList(),Port.class);
    }

    @Override
    public List<Port> getStockTypeList(){
        return castToListT(getService().getStockTypeList(),Port.class);
    }

    @Override
    public List<Port> getPortListByUserAndPost(String userCode,String postCodes){
        return castToListT(getService().getPortListByUserAndPost(userCode,postCodes),Port.class);
    }

    @Override
    public List<Port> getPortByUserAndPost(String userCode,String postCodes){
        return getService().getPortByUserAndPost(userCode,postCodes);
    }

    @Override
    public List<Port> getStockTypeList(String userCode){
        return castToListT(getService().getStockTypeList(userCode),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> getPortResByUserAndPost(String userCode,String postCodes){
        return queryResToT(getService().getPortResByUserAndPost(userCode,postCodes),Port.class);
    }

    @Override
    public List<Port> getPortDataByKeysNoFilter(String[] keys){
        return getService().getPortDataByKeysNoFilter(keys);
    }

    @Override
    public List<Port> getPortInfoList(HashMap<String,Object> paraMap){
        return getService().getPortInfoList(paraMap);
    }

    @Override
    public List<Port> getAllDataList(){
        return getService().getAllDataList();
    }

    @Override
    public Port getPortByPortName(String portName){
        return getService().getPortByPortName(portName);
    }

    @Override
    public String getPortCodePSubCount(String portCodeP){
        return getService().getPortCodePSubCount(portCodeP);
    }

    @Override
    public String operDQQR(List<Port> lstPort){
        return getService().operDQQR(lstPort);
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
    public List<Port> getAssPort(String trCode) throws Exception{
        return getService().getAssPort(trCode);
    }

    @Override
    public List<Port> getGroupDataTree(String trCode) throws Exception{
        return getService().getGroupDataTree(trCode);
    }

    @Override
    public String checkPortCode(String portCode){
        return getService().checkPortCode(portCode);
    }

    @Override
    public RestfulQueryResult<Port> queryParentPortTreeViewData(String userCode,String postCode){
        return queryResToT(getService().queryParentPortTreeViewData(userCode,postCode),Port.class);
    }

    @Override
    public RestfulQueryResult<Port> queryProductORPort(){
        return queryResToT(getService().queryProductORPort(),Port.class);
    }

    @Override
    public List<Port> getTreePortDataByCodes(String[] keys){
        return castToListT(getService().getTreePortDataByCodes(keys),Port.class);
    }

    @Override
    public List<Port> getAllGroupAndPort() throws Exception{
        return castToListT(getService().getAllGroupAndPort(),Port.class);
    }

    @Override
    public List<Port> dueClearedPorts(HashMap<String,String> paraMap){
        return getService().dueClearedPorts(paraMap);
    }

    @Override
    public Map<String,String> getBindPortAndPost(String userCode,String portCodes){
        return getService().getBindPortAndPost(userCode,portCodes);
    }

    @Override
    public List<Port> getFilterPortDataForOperRight(String menuId) throws YssException{
        return getService().getFilterPortDataForOperRight(menuId);
    }

    @Override
    public RestfulQueryResult<Port> queryParentPortTreeViewData(String userCode,String postCode,String menuId){
        return queryResToT(getService().queryParentPortTreeViewData(userCode,postCode,menuId),Port.class);
    }

    @Override
    public List<Port> PortFilter_ReportCenter(boolean isDataRight,String datClass,String dvPortCode,String trCode) throws Exception{
        return getService().PortFilter_ReportCenter(isDataRight,datClass,dvPortCode,trCode);
    }

    @Override
    public List<Port> getPortByAssCodes(List<String> assCodes){
        return getService().getPortByAssCodes(assCodes);
    }

    @Override
    public RestfulQueryResult<Port> doPortFilterResKMmap(String isDataRight,String datClass,String dvPortCode,String trcode,String getKM,String mapyear,String unMapkm){
        return queryResToT(getService().doPortFilterResKMmap(isDataRight,datClass,dvPortCode,trcode,getKM,mapyear,unMapkm),Port.class);
    }

    @Override
    public List<Map<String,String>> getAllPortData() throws Exception{
        return getService().getAllPortData();
    }

    //String portCode, Date buildDate
	@Override
	public List<Port> getListByCodeAndBuildDate(GetListByCodeAndBuildDateVo vo) {
		return getService().getListByCodeAndBuildDate(vo.getPortCode(),vo.getBuildDate());
	}

	@Override
	public List<DataRight> queryDataRight1(QueryDataRight1Vo vo) {
		return getService().queryDataRight(vo.getTrCode(),vo.getPortCodes().toArray(new String[]{}));
	}

	@Override
	public List<DataRight> queryDataRight2(QueryDataRight2Vo vo) {
		return getService().queryDataRight(vo.getTrCode(),vo.getPortCodes().toArray(new String[]{}),vo.getCustomParam());
	}

	//List<String> portCodes,List<String> productState
	@Override
	public List<Port> queryPort(QueryPortVo vo) {
		return getService().queryPort(vo.getPortCodes(),vo.getProductState());
	}

	//List<String> portCodes,List<String> productState
	@Override
	public HashMap<String, String> queryPortMap(QueryPortMapVo vo) {
		return getService().queryPortMap(vo.getPortCodes(),vo.getProductState());
	}

}