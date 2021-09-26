package com.yss.ams.product.information.fast.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.fast.controller.IBaseFastPortController;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A;
import com.yss.framework.api.commonInfo.pojo.FastPortData;
import com.yss.framework.api.commonInfo.service.IFastPortService;
import com.yss.framework.api.commonInfo.vo.QueryByDimensionByCodes;
import com.yss.framework.api.commonInfo.vo.QueryByDimensionVo;
import com.yss.framework.api.commonInfo.vo.QueryParamVo;
import com.yss.framework.api.commonInfo.vo.QueryPortListVo;
import com.yss.framework.api.commonInfo.vo.QueryPortMapVo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;

public class FastPortcontrollerImpl extends AbstractBaseController<IFastPortService>  implements IBaseFastPortController {

	@Override
	public HashMap<String, String> queryPortMap(QueryPortMapVo vo) throws ServiceException {
		return getService().queryPortMap(vo.getPortCodes(),vo.getProductState());
	}

	@Override
	public List<Port> queryByCondition(HashMap<String, Object> paraMap)
			throws ServiceException {
		return getService().queryByCondition(paraMap);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return getService().getKeyConvertMap();
	}

	@Override
	public List<Port> queryPort(QueryPortMapVo vo) {
		return getService().queryPort(vo.getPortCodes(),vo.getProductState());
	}

	@Override
	public List<Port> doPortFilter(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException {
		return getService().doPortFilter(isDataRight,datClass,dvPortCode,trCode);
	}

	@Override
	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException {
		return getService().getShowType(codeMap);
	}

	@Override
	public Port getPortInfo(String cPortCode) throws ServiceException {
		return getService().getPortInfo(cPortCode);
	}

	@Override
	public List<Port> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public List<Port> getAllDataList() throws ServiceException {
		return getService().getAllDataList();
	}

	@Override
	public List<Port> getPortByUserAndPost(String userCode, String postCodes)
			throws ServiceException {
		return getService().getPortByUserAndPost(userCode,postCodes);
	}

	@Override
	public List<Port> getPortDataByKeysNoFilter(String[] keys)
			throws ServiceException {
		return getService().getPortDataByKeysNoFilter(keys);
	}

	@Override
	public Port getDataByCode(String dataCode) throws ServiceException {
		return getService().getDataByCode(dataCode);
	}

	@Override
	public List<Port> getPortByAssCodes(List<String> assCodes) {
		return getService().getPortByAssCodes(assCodes);
	}

	@Override
	public RestfulQueryResult<Port> getAssetTreeView(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getAssetTreeView(paraMap),Port.class);
	}

	@Override
	public List<Port> getPortType() throws ServiceException {
		return getService().getPortType();
	}

	@Override
	public Port getPojoByCode(String pojoCode) throws ServiceException {
		return getService().getPojoByCode(pojoCode);
	}

	@Override
	public Map<String, String> getAllPortGroup() throws ServiceException {
		return getService().getAllPortGroup();
	}

	@Override
	public String getClsCodesByPortCodeAndType(String portCode, String types)
			throws ServiceException {
		return getService().getClsCodesByPortCodeAndType(portCode,types);
	}

	@Override
	public List<Port> getRightManagePortList(HashMap<String, String> paraMap)
			throws Exception {
		return getService().getRightManagePortList(paraMap);
	}

	@Override
	public List<Port> getRightManagePortList() throws Exception {
		return getService().getRightManagePortList();
	}

	@Override
	public List<Port> getPortInfoList(HashMap<String, String> paraMap)
			throws Exception {
		return getService().getPortInfoList(paraMap);
	}

	@Override
	public List<Port> getRightManagePortTree(HashMap<String, String> paraMap)
			throws Exception {
		return getService().getRightManagePortTree(paraMap);
	}

	@Override
	public Map<String, Port> getPortByGroupCode(String groupCode)
			throws ServiceException {
		return getService().getPortByGroupCode(groupCode);
	}

	@Override
	public List<Port> getDataListByKeys(String[] keys) throws ServiceException {
		return getService().getDataListByKeys(keys);
	}

	@Override
	public FastPortData getAllPortByGroups(List<String> groupCodes)
			throws Exception {
		return getService().getAllPortByGroups(groupCodes);
	}

	@Override
	public List<DataRight> query() throws ServiceException {
		return getService().query();
	}

	@Override
	public List<DataRight> queryByDimension(QueryByDimensionVo vo)
			throws ServiceException {
		return getService().queryByDimension(vo.getDimensionType(),vo.getCustomParam());
	}

	@Override
	public List<DataDimension> queryDataDimensions() throws ServiceException {
		return getService().queryDataDimensions();
	}

	@Override
	public List<DataRight> query(QueryParamVo vo) throws ServiceException {
		return getService().query(vo.getCodes().toArray(new String[]{}),vo.getCustomParam());
	}

	@Override
	public List<DataRight> queryByDimension(QueryByDimensionByCodes vo)
			throws ServiceException {
		return getService().queryByDimension(vo.getDimensionType(),vo.getCodes().toArray(new String[]{}),vo.getCustomParam());
	}

	@Override
	public HashMap<String, String> queryConditions() {
		return getService().queryConditions();
	}

	@Override
	public List<DataRight> query(HashMap<String, String> customParam) {
		return getService().query(customParam);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return getService().getKeyConvertMap(listKey);
	}

	@Override
	public RestfulQueryResult<Port> getDataListRes() throws ServiceException {
		return queryResToT(getService().getDataListRes(),Port.class);
	}

	@Override
	public List<Port> getDataListByTypes(String[] types)
			throws ServiceException {
		return getService().getDataListByTypes(types);
	}

	@Override
	public RestfulQueryResult<Port> getQueryResByTypes(String[] types)
			throws ServiceException {
		return queryResToT(getService().getQueryResByTypes(types),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getQueryResByKeys(String[] keys)
			throws ServiceException {
		return queryResToT(getService().getQueryResByKeys(keys),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getPlanRelaPortAdd(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getPlanRelaPortAdd(paraMap),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getPlanRelaPortBrow(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getPlanRelaPortBrow(paraMap),Port.class);
	}

	@Override
	public List<Port> getParamSetPortList(HashMap<String, String> paraMap)
			throws ServiceException {
		return getService().getParamSetPortList(paraMap);
	}

	@Override
	public RestfulQueryResult<Port> getDspPort(HashMap<String, String> paraMap)
			throws ServiceException {
		return queryResToT(getService().getDspPort(paraMap),Port.class);
	}

	@Override
	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, String> paraMap) throws ServiceException, Exception {
		return getService().getRightManagePortListExpertAdd(paraMap);
	}

	@Override
	public RestfulQueryResult<Port> queryDataByBrow(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().queryDataByBrow(paraMap),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getTreeViewData(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getTreeViewData(paraMap),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getUnitPortData(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getUnitPortData(paraMap),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getPortAssTreeList(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getPortAssTreeList(paraMap),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> getPortAssTreeListAddForm(
			HashMap<String, String> paraMap) throws ServiceException {
		return queryResToT(getService().getPortAssTreeListAddForm(paraMap),Port.class);
	}

	@Override
	public String operDQQR(List<Port> lstPort) throws ServiceException {
		return getService().operDQQR(lstPort);
	}

	@Override
	public String operDQQX(List<Port> lstPort) throws ServiceException {
		return getService().operDQQX(lstPort);
	}

	@Override
	public String operQSQR(List<Port> lstPort) throws ServiceException {
		return getService().operQSQR(lstPort);
	}

	@Override
	public String operQSQX(List<Port> lstPort) throws ServiceException {
		return getService().operQSQX(lstPort);
	}

	@Override
	public String operQSGZ(List<Port> lstPort) throws ServiceException,
			Exception {
		return getService().operQSGZ(lstPort);
	}

	@Override
	public String operGZCX(List<Port> lstPort) throws ServiceException {
		return getService().operGZCX(lstPort);
	}

	@Override
	public List<Port> getTheSameAssCodeList(String portCode, String assCode)
			throws ServiceException {
		return getService().getTheSameAssCodeList(portCode,assCode);
	}

	@Override
	public Map<String, String> getPortDatClsMap(String[] portCodes)
			throws ServiceException {
		return getService().getPortDatClsMap(portCodes);
	}

	@Override
	public List<String> findIsPortData(List<String> sourceData)
			throws Exception {
		return getService().findIsPortData(sourceData);
	}

	@Override
	public List<DataRight> queryDataByPortList(QueryPortListVo vo) {
		return getService().queryDataByPortList(vo.getDimensionType(),vo.getPortList());
	}

	@Override
	public List<Port> queryPortByCodes(List<String> portCodes) throws Exception {
		return getService().queryPortByCodes(portCodes);
	}

	@Override
	public String getProductNum(String portCode) throws Exception {
		return getService().getProductNum(portCode);
	}

	@Override
	public List<String> getPortCodeByGroupCode(List<String> groupCodes)
			throws Exception {
		return getService().getPortCodeByGroupCode(groupCodes);
	}

	@Override
	public List<String> getPortCodes4ReportSet() throws Exception {
		return getService().getPortCodes4ReportSet();
	}

	@Override
	public List<String> getPortCodes4ReportSetOfPortCls() throws Exception {
		return getService().getPortCodes4ReportSetOfPortCls();
	}

	@Override
	public List<Port> getPortByUser(String userCode) throws Exception {
		return getService().getPortByUser(userCode);
	}

	@Override
	public FastPortData getAllAssPortByGroups(List<String> arrayList)
			throws ServiceException, Exception {
		return getService().getAllAssPortByGroups(arrayList);
	}

	@Override
	public List<Port> getAllGroupAndPort() throws Exception {
		return castToListT(getService().getAllGroupAndPort(),Port.class);
	}

	@Override
	public List<Port> PortFilter_ReportCenter(boolean isDataRight,
			String datClass, String dvPortCode, String asset) throws Exception {
		return getService().PortFilter_ReportCenter(isDataRight,datClass,dvPortCode,asset);
	}

	@Override
	public RestfulQueryResult<Port> getGroupDataTree() throws Exception {
		return queryResToT(getService().getGroupDataTree(),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> querySelectedPort(String groupCode)
			throws Exception {
		return queryResToT(getService().querySelectedPort(groupCode),Port.class);
	}

	@Override
	public List<Port> PortFilter_ByTrCode_YFP(boolean isDataRight,
			String datClass, String dvPortCode, String asset) throws Exception {
		return getService().PortFilter_ByTrCode_YFP(isDataRight,datClass,dvPortCode,dvPortCode);
	}

	@Override
	public List<Port> getAllGroupAndPortNoRight() throws Exception {
		return castToListT(getService().getAllGroupAndPortNoRight(),Port.class);
	}

	@Override
	public List<Port> getAssPort(String asset) throws Exception {
		return getService().getAssPort(asset);
	}

	@Override
	public RestfulQueryResult<Port> getGroupDataTreeWithoutRight()
			throws Exception {
		return queryResToT(getService().getGroupDataTreeWithoutRight(),Port.class);
	}

	@Override
	public RestfulQueryResult<Port> querySelectedPortWithoutRight(
			String groupCode) throws Exception {
		return queryResToT(getService().querySelectedPortWithoutRight(groupCode),Port.class);
	}

	@Override
	public List<Port> queryPortByProductBusiCode(String busiCode,
			boolean isUserDataRight) {
		return getService().queryPortByProductBusiCode(busiCode,isUserDataRight);
	}

	@Override
	public List<String> queryAssetTreeByPortCode(String portCode) {
		return getService().queryAssetTreeByPortCode(portCode);
	}

	@Override
	public List<FastAssetsTree_A> queryAssetTreeWithLeafNode() {
		return getService().queryAssetTreeWithLeafNode();
	}

	@Override
	public List<Port> getTreePortByCode(boolean isDataRight, String trCode,
			String trCodeR) {
		return getService().getTreePortByCode(isDataRight,trCode,trCodeR);
	}

	@Override
	public List<Port> querySubPortByPortCode(String portCode) {
		return getService().querySubPortByPortCode(portCode);
	}

	@Override
	public List<Port> queryAllGroupInfos() {
		return getService().queryAllGroupInfos();
	}
	
	@Override
	public List<Port> queryPortByTypeCondition(QueryPortListVo vo) {
		return getService().queryPortByTypeCondition(vo.getType(), vo.getCodes(), vo.isDataRight());
	}
	
	@Override
	public String CreatePort(Port port) {
		return getService().CreatePort(port);
	}
	
}
