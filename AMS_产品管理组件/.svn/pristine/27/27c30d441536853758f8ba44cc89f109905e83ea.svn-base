package com.yss.fast.right.right.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;
import com.yss.right.service.IPortService;

/**
 * @ClassName: PortService
 * @Description: 从PLATFORM拆分过来的类
 * @author liyaojin
 * @date 2017年11月21日
 */
public class PortService extends ServiceBus<PortService> implements IPortService, IRightPortService {

//	private com.yss.framework.api.common.co.Port productInfomationPortToFrameworkPort(Port port) {
//		return JsonUtil.toBean(JsonUtil.toString(port), com.yss.framework.api.common.co.Port.class);
//	}
//
//	private Port frameworkPortToProductInfoPort(com.yss.framework.api.common.co.Port port) {
//		return JsonUtil.toBean(JsonUtil.toString(port), Port.class);
//	}

	private List<Port> frameworkPortToProductInfoPort(List<com.yss.framework.api.common.co.Port> port) {
		return JsonUtil.toList(JsonUtil.toString(port), Port.class);
	}
	

	private List<com.yss.framework.api.common.co.Port> productInfomationPortToFrameworkPort(List<Port> port) {
		return JsonUtil.toList(JsonUtil.toString(port), com.yss.framework.api.common.co.Port.class);
	}

	@Override
	public List<DataRight> query() {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.query();
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, HashMap<String,String> customParam) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.queryByDimension(dimensionType,customParam);
	}

	@Override
	public List<DataDimension> queryDataDimensions() {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.queryDataDimensions();
	}

	@Override
	public List<DataRight> query(String[] codes, HashMap<String,String> customParam) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.query(codes,customParam);
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, String[] codes, HashMap<String,String> customParam) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.queryByDimension(dimensionType, codes, customParam);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getKeyConvertMap(listKey);
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getDataList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getDataListRes();
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getDataByCode(dataCode);
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getPojoByCode(pojoCode);
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getDataListByTypes(types);
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getQueryResByTypes(types);
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getDataListByKeys(keys);
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getQueryResByKeys(keys);
	}

	@Override
	public List<Port> getRightManagePortList(
			HashMap<String, Object> paraMap) throws Exception {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getRightManagePortList(paraMap);
	}

	@Override
	public List<Port> getRightManagePortList()
			throws Exception {
		return null;
	}

	@Override
	public List<Port> getPortInfoList(
			HashMap<String, Object> paraMap) throws Exception {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return null;
	}

	@Override
	public List<Port> getRightManagePortTree(
			HashMap<String, Object> paraMap) throws Exception {
		com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService.class);
		return service.getRightManagePortList();
	}

	@Override
	public QueryRes getPlanRelaPortAdd(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getPlanRelaPortAdd(paraMap);
	}

	@Override
	public QueryRes getPlanRelaPortBrow(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getPlanRelaPortBrow(paraMap);
	}

	@Override
	public List<com.yss.framework.api.common.co.Port> getParamSetPortList(
			HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return productInfomationPortToFrameworkPort(service
				.getParamSetPortList(paraMap));
	}

	@Override
	public QueryRes getDspPort(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getDspPort(paraMap);
	}

	@Override
	public List<com.yss.framework.api.common.co.Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) throws Exception {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return productInfomationPortToFrameworkPort(service
				.getRightManagePortListExpertAdd(paraMap));
	}

	@Override
	public QueryRes queryDataByBrow(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.queryDataByBrow(paraMap);
	}

	@Override
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getTreeViewData(paraMap);
	}

	@Override
	public QueryRes getUnitPortData(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getUnitPortData(paraMap);
	}

	@Override
	public QueryRes getAssetTreeView(HashMap<String, Object> paraMap) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getAssetTreeView(paraMap);
	}

	@Override
	public QueryRes getPortAssTreeList(HashMap<String, Object> paraMap)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getPortAssTreeList(paraMap);
	}

	@Override
	public QueryRes getPortAssTreeListAddForm(HashMap<String, Object> paraMap)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getPortAssTreeListAddForm(paraMap);
	}

	@Override
	public String operDQQR(List<com.yss.framework.api.common.co.Port> lstPort)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.operDQQR(frameworkPortToProductInfoPort(lstPort));
	}

	@Override
	public String operDQQX(List<com.yss.framework.api.common.co.Port> lstPort)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.operDQQX(frameworkPortToProductInfoPort(lstPort));
	}

	@Override
	public String operQSQR(List<com.yss.framework.api.common.co.Port> lstPort)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.operQSQR(frameworkPortToProductInfoPort(lstPort));
	}

	@Override
	public String operQSQX(List<com.yss.framework.api.common.co.Port> lstPort)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.operQSQX(frameworkPortToProductInfoPort(lstPort));
	}

	@Override
	public String operQSGZ(List<com.yss.framework.api.common.co.Port> lstPort)
			throws ServiceException, Exception {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.operQSGZ(frameworkPortToProductInfoPort(lstPort));
	}

	@Override
	public String operGZCX(List<com.yss.framework.api.common.co.Port> lstPort)
			throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.operGZCX(frameworkPortToProductInfoPort(lstPort));
	}

	@Override
	public List<com.yss.framework.api.common.co.Port> getTheSameAssCodeList(
			String portCode, String assCode) throws ServiceException {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return productInfomationPortToFrameworkPort(service
				.getTheSameAssCodeList(portCode, assCode));
	}

	@Override
	public Map<String, String> getPortDatClsMap(String[] portCodes) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.getPortDatClsMap(portCodes);
	}

	@Override
	public HashMap<String, String> queryConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRight> query(HashMap<String, String> customParam) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return service.query(customParam);
	}

	@Override
	public List<com.yss.framework.api.common.co.Port> getPortListByPortCode(
			String portCodes) {
		com.yss.ams.product.information.support.modules.ab.port.service.IPortService service = YssServiceFactory
				.getInstance()
				.createService(
						com.yss.ams.product.information.support.modules.ab.port.service.IPortService.class);
		return productInfomationPortToFrameworkPort(service
				.getPortListByPortCode(portCodes));
	}
}
