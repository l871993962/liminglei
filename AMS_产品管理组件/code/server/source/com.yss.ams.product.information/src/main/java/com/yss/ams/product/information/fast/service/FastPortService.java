package com.yss.ams.product.information.fast.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.sys.portbusinessrange.service.IPortBusinessRangeService;
import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupDao;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupSqlBuilder;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomService;
import com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortService;
import com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService;
import com.yss.ams.product.information.support.modules.pg.operport.service.IOperPortService;
import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataService;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupService;
import com.yss.ams.product.information.util.port.PortUtil;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.commonInfo.pojo.FastPortData;
import com.yss.framework.api.commonInfo.service.IFastPortService;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.platform.support.dataservice.service.IRightManageDataService;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;

public class FastPortService implements IFastPortService {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	private PortDao portDao = null;
	private PortGroupDao portGroupDao = null;
	
	public FastPortService() {
		portDao = new PortDao(DbPoolFactory.getInstance().getPool(), new PortSqlBuilder());
		portGroupDao = new PortGroupDao(DbPoolFactory.getInstance().getPool(), new PortGroupSqlBuilder());
	}
	
	

	public HashMap<String, String> queryPortMap(List<String> portCodes, List<String> productState) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortDataService.class).queryPortMap(portCodes, productState);
	}

	public List<Port> queryByCondition(
			HashMap<String, Object> paraMap) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortDataService.class).queryByCondition(paraMap);
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortDataService.class).getKeyConvertMap();
	}

	public List<Port> queryPort(
			List<String> portCodes, List<String> productState)
			throws ServiceException {
		List<Port> portList = ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.queryPort(portCodes, productState);
		return portList;
	}

	public List<Port> doPortFilter(
			String isDataRight, String datClass, String dvPortCode,
			String trCode) throws ServiceException {
		List<BasePojo> basePojoList = ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.doPortFilter(isDataRight, datClass, dvPortCode, trCode);
		List<Port> portList = new ArrayList<Port>();
		for (BasePojo basePojo : basePojoList) {
			Port port = (Port) basePojo;
			portList.add(port);
		}
		return portList;
	}

	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException {
		return ((IPortCustomService) YssServiceFactory.getInstance()
				.createService(IPortCustomService.class)).getShowType(codeMap);
	}

	public Port getPortInfo(String cPortCode)
			throws ServiceException {
		return ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.getPortInfo(cPortCode);
	}

	public List<Port> getDataList()
			throws ServiceException {
		return ((IPortDataService) YssServiceFactory.getInstance()
				.createService(IPortDataService.class)).getDataList();
	}

	public List<Port> getAllDataList()
			throws ServiceException {
		return ((IPortDataService) YssServiceFactory.getInstance()
				.createService(IPortDataService.class)).getAllDataList();
	}

	public List<Port> getPortByUserAndPost(
			String userCode, String postCodes) throws ServiceException {
		return ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.getPortByUserAndPost(userCode, postCodes);
	}

	public List<Port> getPortDataByKeysNoFilter(
			String[] keys) throws ServiceException {
		return  ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.getPortDataByKeysNoFilter(keys);
	}

	public Port getDataByCode(String dataCode)
			throws ServiceException {
		return (Port) ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.getDataByCode(dataCode);
	}

	public List<Port> getPortByAssCodes(
			List<String> assCodes) throws ServiceException {
		return ((IPortDataService) YssServiceFactory
				.getInstance().createService(IPortDataService.class))
				.getPortByAssCodes(assCodes);
	}

	public QueryRes getAssetTreeView(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = ((IPortService) YssServiceFactory.getInstance()
				.createService(IPortService.class))
				.getAssetTreeView(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public List<Port> getPortType()
			throws ServiceException {
		List<Port> portList = YssServiceFactory.getInstance().createService(IPortService.class).getPortType();
		if (portList.size() > 0) {
			return portList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public Port getPojoByCode(String pojoCode)
			throws ServiceException {
		Port port = (Port)YssServiceFactory.getInstance().createService(IPortCacheDataService.class).getPojoByCode(pojoCode);
		if (null == port) {
			return null;
		} else {
			return port;
		}
	}

	public Map<String, String> getAllPortGroup() throws ServiceException {
		return YssServiceFactory.getInstance().createService(IOperPortService.class).getAllPortGroup();
	}

	public String getClsCodesByPortCodeAndType(String portCode, String types)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(IClsPortDataService.class).getClsCodesByPortCodeAndType(portCode, types);
	}

	public List<Port> getRightManagePortList(
			HashMap<String, String> paraMap) throws Exception {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		List<Port> portList = YssServiceFactory.getInstance().createService(IRightPortService.class).getRightManagePortList(inputParaMap);
		if (portList.size() > 0) {
			return portList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public List<Port> getRightManagePortList()
			throws Exception {
		List<Port> portList = YssServiceFactory.getInstance().createService(IRightPortService.class).getRightManagePortList();
		if (portList.size() > 0) {
			return portList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public List<Port> getPortInfoList(
			HashMap<String, String> paraMap) throws Exception {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		List<Port> portList = YssServiceFactory.getInstance().createService(IRightPortService.class).getPortInfoList(inputParaMap);
		if (portList.size() > 0) {
			return portList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public List<Port> getRightManagePortTree(
			HashMap<String, String> paraMap) throws Exception {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		List<Port> portList = YssServiceFactory.getInstance().createService(IRightPortService.class).getRightManagePortTree(inputParaMap);
		if (portList.size() > 0) {
			return portList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public Map<String, Port> getPortByGroupCode(
			String groupCode) throws ServiceException {
		Map<String, Port> commPortMap = YssServiceFactory.getInstance().createService(IOperPortService.class).getPortByGroupCode(groupCode);
		HashMap<String, Port> fastPortMap = new HashMap<String, Port>();
		for (String key : commPortMap.keySet()) {
			fastPortMap.put(key, commPortMap.get(key));
		}
		return fastPortMap;
	}

	public List<Port> getDataListByKeys(
			String[] keys) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortDataService.class).getDataListByKeys(keys);
	}

	public FastPortData getAllPortByGroups(
			List<String> groupCodes) throws Exception {
		return YssServiceFactory.getInstance().createService(IPortDataService.class).getAllPortByGroups(groupCodes);
	}

	public List<DataRight> query() throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).query();
	}

	public List<DataRight> queryByDimension(String dimensionType, HashMap<String,String> customParam)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).queryByDimension(dimensionType,customParam);
	}

	public List<DataDimension> queryDataDimensions() throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).queryDataDimensions();
	}

	public List<DataRight> query(String[] codes, HashMap<String,String> customParam) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).query(codes,customParam);
	}

	public List<DataRight> queryByDimension(String dimensionType, String[] codes, HashMap<String,String> customParam)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).queryByDimension(dimensionType, codes, customParam);
	}
	
	public HashMap<String,String> queryConditions(){
		return YssServiceFactory.getInstance().createService(IPortService.class).queryConditions();
	}
	
	public List<DataRight> query(HashMap<String,String> customParam){
		return YssServiceFactory.getInstance().createService(IPortService.class).query(customParam);
	}

	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).getKeyConvertMap(listKey);
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getDataListRes();
		if (null == res) {
			return null;
		}
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public List<Port> getDataListByTypes(
			String[] types) throws ServiceException {
		return YssServiceFactory.getInstance().createService(IPortService.class).getDataListByTypes(types);
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getQueryResByTypes(types);
		if (null == res) {
			return null;
		}
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getQueryResByKeys(keys);
		if (null == res) {
			return null;
		}
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public QueryRes getPlanRelaPortAdd(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getPlanRelaPortAdd(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public QueryRes getPlanRelaPortBrow(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = ((IPortService) YssServiceFactory.getInstance()
				.createService(IPortService.class))
				.getPlanRelaPortBrow(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			 pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public List<Port> getParamSetPortList(
			HashMap<String, String> paraMap) throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		List<Port> returnList = YssServiceFactory.getInstance().createService(IPortService.class).getParamSetPortList(inputParaMap);
		if (returnList.size() > 0) {
			return returnList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public QueryRes getDspPort(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getDspPort(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, String> paraMap) throws ServiceException, Exception {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		return YssServiceFactory.getInstance().createService(IPortService.class).getRightManagePortListExpertAdd(inputParaMap);
	}

	public QueryRes queryDataByBrow(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = ((IPortService) YssServiceFactory.getInstance()
				.createService(IPortService.class))
				.queryDataByBrow(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class
				.getSimpleName());
		return res;
	}

	public QueryRes getTreeViewData(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getTreeViewData(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public QueryRes getUnitPortData(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getUnitPortData(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public QueryRes getPortAssTreeList(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = YssServiceFactory.getInstance().createService(IPortService.class).getPortAssTreeList(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public QueryRes getPortAssTreeListAddForm(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> inputParaMap = new HashMap<String, Object>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key));
		}
		QueryRes res = ((IPortService) YssServiceFactory.getInstance()
				.createService(IPortService.class))
				.getPortAssTreeListAddForm(inputParaMap);
		List<Port> pojoList = new ArrayList<Port>();
		for (BasePojo pojo : res.getDataList()) {
			pojoList.add((Port) pojo);
		}
		//List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (Port port : pojoList) {
			basePojoList.add(port);
		}
		res.setDataList(basePojoList);
		res.setListDataClass(Port.class.getSimpleName());
		return res;
	}

	public String operDQQR(List<Port> lstPort)
			throws ServiceException {
		if (lstPort.size() > 0) {
			return YssServiceFactory.getInstance().createService(IPortService.class).operDQQR(lstPort);
		} else {
			return YssServiceFactory.getInstance().createService(IPortService.class).operDQQR(new ArrayList<Port>());
		}
	}

	public String operDQQX(List<Port> lstPort)
			throws ServiceException {
		if (lstPort.size() > 0) {
			return YssServiceFactory.getInstance().createService(IPortService.class).operDQQX(lstPort);
		} else {
			return YssServiceFactory.getInstance().createService(IPortService.class).operDQQX(new ArrayList<Port>());
		}
	}

	public String operQSQR(List<Port> lstPort)
			throws ServiceException {
		if (lstPort.size() > 0) {
			return YssServiceFactory.getInstance().createService(IPortService.class).operQSQR(lstPort);
		} else {
			return YssServiceFactory.getInstance().createService(IPortService.class).operQSQR(new ArrayList<Port>());
		}
	}

	public String operQSQX(List<Port> lstPort)
			throws ServiceException {
		if (lstPort.size() > 0) {
			return YssServiceFactory.getInstance().createService(IPortService.class).operQSQX(lstPort);
		} else {
			return YssServiceFactory.getInstance().createService(IPortService.class).operQSQX(new ArrayList<Port>());
		}
	}

	public String operQSGZ(List<Port> lstPort)
			throws ServiceException, Exception {
		if (lstPort.size() > 0) {
			return YssServiceFactory.getInstance().createService(IPortService.class).operQSGZ(lstPort);
		} else {
			return YssServiceFactory.getInstance().createService(IPortService.class).operQSGZ(new ArrayList<Port>());
		}
	}

	public String operGZCX(List<Port> lstPort)
			throws ServiceException {
		if (lstPort.size() > 0) {
			return YssServiceFactory.getInstance().createService(IPortService.class).operGZCX(lstPort);
		} else {
			return YssServiceFactory.getInstance().createService(IPortService.class).operGZCX(new ArrayList<Port>());
		}
	}

	public List<Port> getTheSameAssCodeList(
			String portCode, String assCode) throws ServiceException {
		List<Port> returnList = YssServiceFactory.getInstance().createService(IPortService.class).getTheSameAssCodeList(portCode, assCode);
		if (returnList.size() > 0) {
			return returnList;
		} else {
			return new ArrayList<Port>();
		}
	}

	public Map<String, String> getPortDatClsMap(String[] portCodes)
			throws ServiceException {
		return YssServiceFactory.getInstance().createService(
				IPortService.class).getPortDatClsMap(portCodes);
	}

	@Override
	public List<String> findIsPortData(List<String> sourceData)
			throws Exception {
		List<String> result = null;
		try {
			result = portDao.findIsPortData(sourceData);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年3月8日 上午10:37:36
	 * @param @param portCodes
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<Port> queryPortByCodes(
			List<String> portCodes) throws Exception {
		List<Port> tmpList = null;
		try {
			 tmpList = portDao.getPortListByCodes(portCodes.toArray(new String[portCodes.size()]));
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return tmpList;
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年2月26日 上午11:36:52
	 * @param @param portCode
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public String getProductNum(String portCode) throws Exception {
		return portDao.getProductNum(portCode);
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年2月27日 上午10:07:21
	 * @param @param groupCode
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<String> getPortCodeByGroupCode(List<String> groupCodes)
			throws Exception {
		return portGroupDao.getPortCodeByGroupCode(groupCodes);
	}

	@Override
	public List<DataRight> queryDataByPortList(String dimensionType,
			List<String> portList) {
		List<DataRight> returnList=new ArrayList<DataRight>();
		try{
			String[] portCodes=returnList.toArray(new String[returnList.size()]);
			returnList=YssServiceFactory.getInstance().createService(IPortDataService.class).queryDataRight(dimensionType, portCodes);
		}
		catch(Exception e){
			logger.error("查询失败",e);
		}
		return returnList;
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年3月5日 上午10:21:51
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<String> getPortCodes4ReportSet() throws Exception {
		return portDao.getPortCodes4ReportSet();
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年3月5日 上午10:34:38
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<String> getPortCodes4ReportSetOfPortCls() throws Exception {
		return portDao.getPortCodes4ReportSetOfPortCls();
	}

	/**
	 * @Desc  BUG #260010 [webservice接口]估值表下载接口
	 * 根据用户先从组合岗位权限数据中关联组合数据，再拼装资产树结构
	 * @author houjiaqi
	 * @date 2019年5月27日 上午10:44:09
	 * @param @param userCode
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<Port> getPortByUser(String userCode) throws Exception {
		List<Port> tmpList = null;
		try {
			 tmpList = portDao.getPortByUser(userCode);
			//result = PortUtil.convert2FastListPort(tmpList);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return tmpList;
	}
	
	public FastPortData getAllAssPortByGroups(List<String> arrayList) throws ServiceException, Exception {
		try {
			//Map<String, List<Port>> fastPortMap = new HashMap<String, List<Port>>();
			Map<String,List<Port>> portMap =   YssServiceFactory.getInstance().createService(IPortDataService.class).getAllAssPortByGroups(arrayList);
			FastPortData fastPort = new FastPortData();
			/*for (String key : portMap.keySet()) {
				fastPortMap.put(key, PortUtil.convert2FastListPort(portMap.get(key)));
			}*/
			fastPort.setFastPort(portMap);
			return fastPort;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<BasePojo> getAllGroupAndPort() throws Exception {
		try {
			return YssServiceFactory.getInstance().createService(IPortDataService.class).getAllGroupAndPort();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<Port> PortFilter_ReportCenter (boolean isDataRight, String datClass, String dvPortCode, String asset)  throws Exception {
		try {
			return YssServiceFactory.getInstance().createService(IPortDataService.class).PortFilter_ReportCenter(isDataRight, datClass, dvPortCode, asset);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public QueryRes getGroupDataTree() throws Exception{
		try {
		   IPortGroupDataService groupDataServie = YssServiceFactory.getInstance().createService(IPortGroupDataService.class);
		   QueryRes res = groupDataServie.getGroupDataTree();
		   List<Port> pojoList = new ArrayList<Port>();
		   for (BasePojo pojo : res.getDataList()) {
			   pojoList.add((Port) pojo);
		   }
		   //List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		   List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		   for (Port port : pojoList) {
			   basePojoList.add(port);
		   }
		   res.setDataList(basePojoList);
		   res.setListDataClass(Port.class.getSimpleName());
	   
		   return res;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public QueryRes querySelectedPort(String groupCode) throws Exception {
		try {
		   IPortGroupDataService groupDataServie = YssServiceFactory.getInstance().createService(IPortGroupDataService.class);
		   QueryRes res = groupDataServie.querySelectedPort(groupCode);
		   List<Port> pojoList = new ArrayList<Port>();
		   for (BasePojo pojo : res.getDataList()) {
			   pojoList.add((Port) pojo);
		   }
		   //List<Port> fastPortList = PortUtil.convert2FastListPort(pojoList);
		   List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		   for (Port port : pojoList) {
			   basePojoList.add(port);
		   }
		   res.setDataList(basePojoList);
		   res.setListDataClass(Port.class.getSimpleName());
	   
		   return res;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<Port> PortFilter_ByTrCode_YFP(boolean isDataRight, String datClass, String dvPortCode,String asset) throws Exception{
		 try {
			 return YssServiceFactory.getInstance().createService(IPortDataService.class).PortFilter_ByTrCode_YFP(isDataRight, datClass, dvPortCode, asset);
		 } catch (Exception e) {
			 throw new Exception(e);
		 }
	}
	
	@Override
	 public List<BasePojo> getAllGroupAndPortNoRight() throws Exception {
	  try {
	   return YssServiceFactory.getInstance().createService(IPortDataService.class).getAllGroupAndPortNoRight();
	  } catch (Exception e) {
	   throw new Exception(e);
	  }
	 }

	 @Override
	 public List<Port> getAssPort(String asset)
	   throws Exception {
	  try {
	   return YssServiceFactory.getInstance().createService(IPortDataService.class).getAssPort(asset);
	  }catch (Exception e) {
	   throw new Exception(e);
	  }
	 }
	 
	@Override
	public QueryRes getGroupDataTreeWithoutRight() throws Exception {
		try {
			IPortGroupDataService groupDataServie = YssServiceFactory
					.getInstance().createService(IPortGroupDataService.class);
			QueryRes res =groupDataServie.getGroupDataTreeWithoutRight();
			List<Port> pojoList = new ArrayList<Port>();
			for (BasePojo pojo : res.getDataList()) {
				pojoList.add((Port) pojo);
			}
			/*List<Port> fastPortList = PortUtil
					.convert2FastListPort(pojoList);*/
			List<BasePojo> basePojoList = new ArrayList<BasePojo>();
			for (Port port : pojoList) {
				basePojoList.add(port);
			}
			res.setDataList(basePojoList);
			res.setListDataClass(Port.class
					.getSimpleName());

			return res;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public QueryRes querySelectedPortWithoutRight(String groupCode)
			throws Exception {
		try {
			IPortGroupDataService groupDataServie = YssServiceFactory
					.getInstance().createService(IPortGroupDataService.class);
			QueryRes res = groupDataServie
					.querySelectedPortWithoutRight(groupCode);
			List<Port> pojoList = new ArrayList<Port>();
			for (BasePojo pojo : res.getDataList()) {
				pojoList.add((Port) pojo);
			}
			/*List<Port> fastPortList = PortUtil
					.convert2FastListPort(pojoList);*/
			List<BasePojo> basePojoList = new ArrayList<BasePojo>();
			for (Port port : pojoList) {
				basePojoList.add(port);
			}
			res.setDataList(basePojoList);
			res.setListDataClass(Port.class
					.getSimpleName());

			return res;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
     * STORY #86378 【华宝基金】二期自动化应用范围增加其他自动化组合 
     * @param busiCode 业务类型代码
     * @param isUserDataRight 是否过滤岗位权限
     * @return
     */
	@Override
    public List<Port> queryPortByProductBusiCode(String busiCode, boolean isUserDataRight) {
    	List<Port> portList = new ArrayList<Port>();
    	// 创建服务
    	IPortBusinessRangeService portBusiServie = YssServiceFactory.getInstance().createService(IPortBusinessRangeService.class);
    	IPortCacheDataService portCacheService = YssServiceFactory.getInstance().createService(IPortCacheDataService.class);
    	// 根据业务类型代码获取组合集合
    	List<String> list = portBusiServie.getPortListByBusiCode(busiCode);
    	// 获取组合数据权限
    	List<String> dataRights = null;
    	if (isUserDataRight && !list.isEmpty()) {
    		dataRights = getDataRightList();
    	}
    	for (String portCode : list) {
    		Port port = portCacheService.getPojoByCode(portCode);
    		if (port == null || port.getAuditState() == 0) {
    			continue;
    		}
    		if (isUserDataRight) {
    			for (String right : dataRights) {
    				if (portCode.equals(right)) {
        				portList.add(port);
        			}
    			}
    		}
    		else {
    			portList.add(port);
    		}
    	}
    	return portList;
    }
    
    /**
	 * 获取组合数据权限
	 */
	private List<String> getDataRightList() {
		Context context = ContextFactory.getContext();
		List<String> list = new ArrayList<String>();
		String userCode = "";
		String postCode = "";
		if (context.getLogInfo() == null) {
			userCode = context.getUserCode();
		} else {
			userCode = context.getLogInfo().getLoggingUserCode();
			postCode = context.getLogInfo().getLoggingPostCode();
			if (postCode == null || postCode.equals("")) {
				logger.error("岗代码为空。");
			}
		}
		try {
			IRightManageDataService rms = HttpServiceFactory.getInstance().createService(IRightManageDataService.class);
			list = rms.getUserDataRight(userCode, postCode);
		} catch (Exception e) {
			logger.error("查询失败", e);
		}
		return list;
	}
	
	/**
     * STORY #86378 【华宝基金】二期自动化应用范围增加其他自动化组合
     * 根据组合Code查询返回 它所属的树形结构Code集合
     * @param portCode 组合代码
     * @return
     */
	@Override
    public List<String> queryAssetTreeByPortCode(String portCode) {
    	return portDao.queryAssetTreeByPortCode(portCode);
    }
	
	@Override
	public List<com.yss.framework.api.common.co.Port> getTreePortByCode(
			boolean isDataRight, String trCode, String trCodeR) {
		try {
			return YssServiceFactory
					.getInstance().createService(IPortDataService.class)
					.getTreePortByCode(isDataRight, trCode, trCodeR);
		} catch (Exception e) {
			logger.error("getTreePortByCode出错！", e);
			List<com.yss.framework.api.common.co.Port> resList = new ArrayList<com.yss.framework.api.common.co.Port>();
			return resList;
		}
	}

	@Override
	public List<com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A> queryAssetTreeWithLeafNode() {
		try {
			return PortUtil.convert2FastListAssetsTree(YssServiceFactory
					.getInstance().createService(IPortDataService.class)
					.queryAssetTreeWithLeafNode());
		} catch (Exception e) {
			logger.error("queryAssetTreeWithLeafNode出错！", e);
			List<com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A> resList = new ArrayList<com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A>();
			return resList;
		}
	}

	
	public List<Port> querySubPortByPortCode(String portCode) {
		List<Port> resList = new ArrayList<Port>();
		try {
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("C_PORT_CODE", portCode);
			List<BasePojo> pojoList = portDao.queryMomPortSub(paraMap);
			for (BasePojo pojo : pojoList) {
				resList.add((Port) pojo);
			}
			return resList;
		} catch (Exception e) {
			logger.error("querySubPortByPortCode出错！", e);
			return resList;
		}
	}
	
	/**
	 * 查询所有群组信息，不带组合
	 * @return
	 */
	public List<Port> queryAllGroupInfos() {
		List<Port> list = new ArrayList<Port>();
		IPortGroupService portGroupService = YssServiceFactory.getInstance().createService(IPortGroupService.class);
		QueryRes res = portGroupService.getPortGroupA(new HashMap<String, Object>());
		List<BasePojo> dataList = res.getDataList();
		for (BasePojo pojo : dataList) {
			PortGroup portGroup = (com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup) pojo;
			Port port = new Port();
			port.setC_DAT_CODE("PortGroup");
			port.setAuditState(portGroup.getAuditState());
			port.setC_PORT_CODE(portGroup.getC_GROUP_CODE());
			port.setC_PORT_NAME(portGroup.getC_GROUP_NAME());
			list.add(port);
		}
		return list;
	}
	
	/**
	 * STORY #105671 工银瑞信—流程实例新增按资产类型等筛选
     * 根据类型条件 查询 组合数据
     * @param type C_DAT_CODE 资产类型  C_DAT_CLS 资产类别 GROUP 群组 
     * 过滤掉未审核的 群组 和 组合， 组合信息需要产品状态，组合层级等字段 等信息
     * @param codes
     * @param isDataRight 是否过滤权限
     * @return
     */
    public List<Port> queryPortByTypeCondition(String type, List<String> codes, boolean isDataRight) {
    	List<Port> portList = new ArrayList<Port>();
    	try {
    		List<Port> list = YssServiceFactory.getInstance().createService(IPortDataService.class).queryPortByTypeCondition(type, codes, isDataRight);
    		if (list != null && list.size() > 0) {
    			portList.addAll(list);
    		}
		} catch (Exception e) {
			logger.error("queryPortByTypeCondition出错！", e);
		}
    	return portList;
    }

	/**
	 * STORY #106999 【易方达】估值系统自动化流程新增建账相关组件（STORY105253拆出给估值） 建帐组合
	 */
	@Override
	public String CreatePort(Port port) {
		String result = "success";
		// 先删除后新增
		try {
			portDao.deleteById(port);
			portDao.insert(port);
			//刷新组合缓存
			PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
			portCache.updateByIds(port.getId());
		} catch (Exception e) {
			logger.error("建帐组合出错！", e);
			result = "fail";
			return result;
		}
		return result;
	}
	
}
