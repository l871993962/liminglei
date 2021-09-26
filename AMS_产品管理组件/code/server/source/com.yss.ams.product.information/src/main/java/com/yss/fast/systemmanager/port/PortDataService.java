package com.yss.fast.systemmanager.port;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.platform.support.dataservice.service.IPortDataService;
import com.yss.right.pojo.DataRight;
import com.yss.systemmanager.port.PortCopyUtils;

/**
 * @ClassName
 * @Description
 * @author houjiaqi
 * @CreateDate 2019年1月10日 上午10:26:54
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class PortDataService implements IPortDataService {

	private com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService getServiceFromProductInfomation() {
		return
		(com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService) YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService.class);
	}

	/*private Port productInfomationPortToFrameworkPort(
			Port port) {
		Port port2return = new Port();
		try {
			PortCopyUtils.copyProperties2(port2return, port);
		} catch (Exception localException) {
		}
		return port2return;
	}*/

	/*private Port frameworkPortToProductInfoPort(
			Port port) {
		Port port2return = new Port();
		try {
			PortCopyUtils.copyProperties(port2return, port);
		} catch (Exception localException) {
		}
		return port2return;
	}*/
/*
	private List<Port> productInfomationPortToFrameworkPort(
			List<Port> port) {
		List<Port> list = new ArrayList<Port>();
		if (port == null) {
			return list;
		}
		for (Port p : port) {
			try {
				Port port2return = new Port();
				PortCopyUtils.copyProperties2(port2return, p);
				list.add(port2return);
			} catch (Exception localException) {
			}
		}
		return list;
	}*/

	/*private List<Port> frameworkPortToProductInfoPort(
			List<Port> port) {
		List<Port> list = new ArrayList<Port>();
		for (Port p : port) {
			try {
				Port port2return = new Port();
				PortCopyUtils.copyProperties(port2return, p);
				list.add(port2return);
			} catch (Exception localException) {
			}
		}
		return list;
	}*/

	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return getServiceFromProductInfomation().getPojoByCode(pojoCode);
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		return getServiceFromProductInfomation().getDataListByTypes(types);
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		return getServiceFromProductInfomation().getQueryResByTypes(types);
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		return getServiceFromProductInfomation().getDataListByKeys(keys);
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		return getServiceFromProductInfomation().getQueryResByKeys(keys);
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return getServiceFromProductInfomation().getDataList();
	}

	public QueryRes getDataListRes() throws ServiceException {
		return getServiceFromProductInfomation().getDataListRes();
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return getServiceFromProductInfomation().getDataByCode(dataCode);
	}

	public String getMenuId() {
		return getServiceFromProductInfomation().getMenuId();
	}

	public void setMenuId(String menuId) {
		getServiceFromProductInfomation().setMenuId(menuId);
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return getServiceFromProductInfomation().getKeyConvertMap();
	}

	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return getServiceFromProductInfomation().getKeyConvertMap(listKey);
	}

	public <K extends BasePojo> List<K> getTreeViewList() throws Exception {
		return null;
	}

	public QueryRes getTreeViewListRes() throws Exception {
		return null;
	}

	public CacheData updateByTimestamp(String timestamp) {
		return getServiceFromProductInfomation().updateByTimestamp(timestamp);
	}

	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return getServiceFromProductInfomation().queryByIds(ids);
	}

	public List<Port> getRightManagePortList(
			HashMap<String, String> paraMap) throws ServiceException {
		return getServiceFromProductInfomation()
				.getRightManagePortList(paraMap);
	}

	public List<Port> getTreeViewListByCondion(
			HashMap<String, Object> paraMap) throws ServiceException {
		return getServiceFromProductInfomation()
				.getTreeViewListByCondion(paraMap);
	}

	public QueryRes getTreeViewResByCondion(HashMap<String, Object> paraMap)
			throws ServiceException {
		return getServiceFromProductInfomation().getTreeViewResByCondion(
				paraMap);
	}

	public List<Port> doPortFilterPort()
			throws ServiceException {
		return null;
	}

	public QueryRes getDefaultPort(String ports, String cTrCode)
			throws ServiceException {
		return getServiceFromProductInfomation().getDefaultPort(ports, cTrCode);
	}

	public List<Port> getListByCodeAndBuildDate(
			String portCode, Date buildDate) throws ServiceException {
		return getServiceFromProductInfomation()
				.getListByCodeAndBuildDate(portCode, buildDate);
	}

	public void insert(Port port)
			throws ServiceException {
		getServiceFromProductInfomation().insert(
				port);
	}

	public String insertWithRetInfo(Port port)
			throws ServiceException {
		return getServiceFromProductInfomation().insertWithRetInfo(
				port);
	}

	public void updateById(Port port)
			throws ServiceException {
		getServiceFromProductInfomation().updateById(
				port);
	}

	public List<Port> queryByCondition(
			HashMap<String, Object> paraMap) throws ServiceException {
		return getServiceFromProductInfomation()
				.queryByCondition(paraMap);
	}

	public void deleteById(Port pojo)
			throws ServiceException {
		getServiceFromProductInfomation().deleteById(
				pojo);
	}

	public void audit(Port pojo)
			throws ServiceException {
		getServiceFromProductInfomation().audit(
				pojo);
	}

	public void antiAudit(Port pojo)
			throws ServiceException {
		getServiceFromProductInfomation().antiAudit(
				pojo);
	}

	public List<BasePojo> getPortListByDatClass(String datClass)
			throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortListByDatClass(datClass);
	}

	public List<BasePojo> doPortFilter() throws ServiceException {
		return getServiceFromProductInfomation().doPortFilter();
	}

	public QueryRes doPortFilterRes() throws ServiceException {
		return getServiceFromProductInfomation().doPortFilterRes();
	}

	public List<BasePojo> doPortFilter(String isDataRight, String datClass)
			throws ServiceException {
		return getServiceFromProductInfomation().doPortFilter(isDataRight,
				datClass);
	}

	public QueryRes doPortFilterRes(String isDataRight, String datClass)
			throws ServiceException {
		return getServiceFromProductInfomation().doPortFilterRes(isDataRight,
				datClass);
	}

	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException {
		return getServiceFromProductInfomation().doPortFilter(isDataRight,
				datClass, dvPortCode);
	}

	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException {
		return getServiceFromProductInfomation().doPortFilterRes(isDataRight,
				datClass, dvPortCode);
	}

	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException {
		return getServiceFromProductInfomation().doPortFilter(isDataRight,
				datClass, dvPortCode, trCode);
	}

	public List<DataRight> queryDataRight(String trCode)
			throws ServiceException {
		return getServiceFromProductInfomation().queryDataRight(trCode);
	}

	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException {
		return getServiceFromProductInfomation().doPortFilterRes(isDataRight,
				datClass, dvPortCode, trCode);
	}

	public List<BasePojo> getPortListByDvPortCode(String dvPortCode)
			throws ServiceException {
		return getServiceFromProductInfomation().getPortListByDvPortCode(
				dvPortCode);
	}

	public Port getPortInfo(String cPortCode)
			throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortInfo(cPortCode);
	}

	public Port getPortByAssCode(String assCode)
			throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortByAssCode(assCode);
	}

	public List<BasePojo> getPortTreeList() throws ServiceException {
		return null;
	}

	public List<BasePojo> getStockTypeList() throws ServiceException {
		return null;
	}

	public List<BasePojo> getPortListByUserAndPost(String userCode,
			String postCodes) throws ServiceException {
		return getServiceFromProductInfomation().getPortListByUserAndPost(
				userCode, postCodes);
	}

	public List<Port> getPortByUserAndPost(
			String userCode, String postCodes) throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortByUserAndPost(userCode, postCodes);
	}

	public List<BasePojo> getStockTypeList(String userCode)
			throws ServiceException {
		return getServiceFromProductInfomation().getStockTypeList(userCode);
	}

	public QueryRes getPortResByUserAndPost(String userCode, String postCodes)
			throws ServiceException {
		return getServiceFromProductInfomation().getPortResByUserAndPost(
				userCode, postCodes);
	}

	public <K extends BasePojo> List<K> getPortDataByKeysNoFilter(String[] keys)
			throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortDataByKeysNoFilter(keys);
	}

	public List<Port> getPortInfoList(
			HashMap<String, Object> paraMap) throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortInfoList(paraMap);
	}

	public <K extends BasePojo> List<K> getAllDataList()
			throws ServiceException {
		return getServiceFromProductInfomation().getAllDataList();
	}

	public Port getPortByPortName(
			String portName) throws ServiceException {
		return getServiceFromProductInfomation()
				.getPortByPortName(portName);
	}

	public String getPortCodePSubCount(String portCodeP) {
		return getServiceFromProductInfomation()
				.getPortCodePSubCount(portCodeP);
	}

	public String operDQQR(List<Port> lstPort)
			throws ServiceException {
		return getServiceFromProductInfomation().operDQQR(
				lstPort);
	}

	public String operDQQX(List<Port> lstPort)
			throws ServiceException {
		return getServiceFromProductInfomation().operDQQX(
				lstPort);
	}

	public String operQSQR(List<Port> lstPort)
			throws ServiceException {
		return null;
	}

	public String operQSQX(List<Port> lstPort)
			throws ServiceException {
		return null;
	}

	public List<Port> getAssPort(String trCode)
			throws Exception {
		return getServiceFromProductInfomation()
				.getAssPort(trCode);
	}

	public List<Port> getGroupDataTree(
			String trCode) throws Exception {
		return getServiceFromProductInfomation()
				.getGroupDataTree(trCode);
	}

	public String checkPortCode(String portCode) {
		return getServiceFromProductInfomation().checkPortCode(portCode);
	}

	public QueryRes queryParentPortTreeViewData(String userCode, String postCode)
			throws ServiceException {
		return getServiceFromProductInfomation().queryParentPortTreeViewData(
				userCode, postCode);
	}

	public QueryRes queryProductORPort() throws ServiceException {
		return getServiceFromProductInfomation().queryProductORPort();
	}

	public List<BasePojo> getTreePortDataByCodes(String[] keys)
			throws ServiceException {
		return getServiceFromProductInfomation().getTreePortDataByCodes(keys);
	}

	public List<BasePojo> getAllGroupAndPort() throws Exception {
		return getServiceFromProductInfomation().getAllGroupAndPort();
	}

	public List<Port> dueClearedPorts(
			HashMap<String, String> paraMap) {
		return getServiceFromProductInfomation()
				.dueClearedPorts(paraMap);
	}

	public Map<String, String> getBindPortAndPost(String userCode,
			String portCodes) {
		return getServiceFromProductInfomation().getBindPortAndPost(userCode,
				portCodes);
	}

	public <K extends BasePojo> List<K> getFilterPortDataForOperRight(
			String menuId) throws YssException {
		return getServiceFromProductInfomation().getFilterPortDataForOperRight(
				menuId);
	}

	public QueryRes queryParentPortTreeViewData(String userCode,
			String postCode, String menuId) {
		return getServiceFromProductInfomation().queryParentPortTreeViewData(
				userCode, postCode, menuId);
	}

	public List<Port> PortFilter_ReportCenter(
			boolean isDataRight, String datClass, String dvPortCode,
			String trCode) throws Exception {
		return getServiceFromProductInfomation()
				.PortFilter_ReportCenter(isDataRight, datClass, dvPortCode,
						trCode);
	}

	public List<Port> getPortByAssCodes(
			List<String> assCodes) {
		return getServiceFromProductInfomation()
				.getPortByAssCodes(assCodes);
	}

	public List<DataRight> queryDataRight(String trCode, String[] portCodes) {
		return getServiceFromProductInfomation().queryDataRight(trCode,
				portCodes);
	}
	
	public List<DataRight> queryDataRight(String trCode, String[] portCodes, HashMap<String,String> customParam) {
		return getServiceFromProductInfomation().queryDataRight(trCode,
				portCodes, customParam);
	}

	public List<Port> queryPort(
			List<String> portCodes, List<String> productState) {
		return getServiceFromProductInfomation()
				.queryPort(portCodes, productState);
	}

	public HashMap<String, String> queryPortMap(List<String> portCodes,
			List<String> productState) {
		return getServiceFromProductInfomation().queryPortMap(portCodes,
				productState);
	}

	public QueryRes doPortFilterResKMmap(String isDataRight, String datClass,
			String dvPortCode, String trcode, String getKM, String mapyear,
			String unMapkm) throws ServiceException {
		return null;
	}
	
	/**
	 * @Desc  获取所有组合数据（默认资产树结构）
	 * @author houjiaqi
	 * @date 2019年3月12日 下午4:49:52
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<Map<String, String>> getAllPortData() throws Exception {
		return getServiceFromProductInfomation().getAllPortData();
	}

}
