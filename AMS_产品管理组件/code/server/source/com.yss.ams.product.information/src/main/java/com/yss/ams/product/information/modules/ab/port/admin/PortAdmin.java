package com.yss.ams.product.information.modules.ab.port.admin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortTansferService;
import com.yss.ams.product.information.util.port.PortDataType;
import com.yss.fast.right.support.right.pojo.UserPostData;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.fast.systemmanager.support.productState.service.ISwitchProductStateService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.api.IFastAutomaticService;
import com.yss.framework.api.util.StringUtil;
import com.yss.platform.support.dataservice.service.IRightManageDataService;
import com.yss.right.constants.RightConstants;



/**
 * <产品基本信息>管理类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortAdmin extends BaseAdmin {
	private PortDao svcDao = null;

	private static String portType = "1";

	protected String _rootMark = "[root]";

	public PortAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new PortDao(pool, sqlBuilder);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getPortTreeView() throws Exception {
		return (List<T>) svcDao.getPortTreeViewCommon();
	}

	public HashMap<String, String> getKeyConvertMap() throws Exception {
		try {
			// 优先调用缓存中的数据
			IPortCacheDataService portCacheDataService = HttpServiceFactory
					.getInstance().createService(IPortCacheDataService.class);
			return portCacheDataService.getKeyConvertMap();
		} catch (Exception ex) {
			// 如果缓存调用失败，则再从表中取
			return svcDao.getKeyConvertMap();
		}
	}

	public HashMap<String, String> getKeyConvertMap2() throws Exception {
		return svcDao.getKeyConvertMap2();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String codeStr)
			throws Exception {
		BasePojo basePojo = null;
		try {
			// 优先调用缓存中的数据
			IPortCacheDataService portCacheDataService = HttpServiceFactory
					.getInstance().createService(IPortCacheDataService.class);
			// BY Jinghehe 2017-10-12 先从缓存中获取，没有数据，在从数据库中查询
			basePojo = portCacheDataService.getDataByCode(codeStr);
			if(null == basePojo)
			{
				basePojo = svcDao.getDataByCode(codeStr);
			}
//			return portCacheDataService.getDataByCode(codeStr);
		} catch (Exception ex) {
			// 如果缓存调用失败，则再从表中取
			return (T) svcDao.getDataByCode(codeStr);
		}
		return (T) basePojo;
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getPortTreeViewByCondition(
			HashMap<String, Object> paraMap) throws Exception {
		return (List<T>) svcDao.queryTreeViewData(paraMap);
	}

	public List<Port> getListByCodeAndBuildDate(String portCode, Date buildDate) {
		return this.svcDao.getListByCodeAndBuildDate(portCode, buildDate);
	}

	public List<BasePojo> getDefaultUserPort(String ports, String cTRCode)
			throws Exception {
		return this.svcDao.getDefaultUserPort(ports, cTRCode);
	}

	public void updateById(Port port) throws BusinessException {
		try {
			this.svcDao.updateById(port);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public void insert(Port port) throws BusinessException {
		try {
			this.svcDao.insert(port);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	public String insertWhithRetInfo(Port port) throws BusinessException {
		try {
			this.svcDao.insert(port);
		} catch (Exception ex) {

			if (ex instanceof YssRuntimeException) {
				throw (BusinessException) ex;
			} else {
				Logger logger = LogManager.getLogger(this.getClass());
				logger.log(ex.getMessage());
				throw new BusinessException(ex);
			}

			// String retInfo = "";
			// if
			// (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
			// // 修改对数据唯一提示参数 需求号: YSSUCO赢时胜2013年03月28日01_A byleeyu 20130910
			// retInfo = ReturnInfoGenerator.getChkUniqueErrStr(ex
			// .getMessage(), svcDao, port);
			// } else {
			// retInfo = ReturnInfoGenerator
			// .getOperErrMsg(MvcConstant._CodeSaveErr);
			// }
			// return retInfo;
		}
		return "";
	}

	public void insertList(List<Port> datalist) throws BusinessException {
		try {
			this.svcDao.insert(datalist);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public List<Port> queryByCondition(HashMap<String, Object> map) {
		List<Port> portList = new ArrayList<Port>();
		List<BasePojo> list = this.svcDao.queryByCondition(map, Port.class);
		if (list != null) {
			for (BasePojo pojo : list) {
				portList.add((Port) pojo);
			}
		}
		return portList;
	}

	public void antiAudit(Port pojo) {
		this.svcDao.antiAudit(pojo);
	}

	public void audit(Port pojo) {
		this.svcDao.audit(pojo);
	}

	public void deleteById(Port pojo) throws BusinessException {
		try {
			this.svcDao.deleteById(pojo);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public List<BasePojo> getPortAssTreeList(HashMap<String, Object> paraMap) {
		return svcDao.getPortAssTreeList(paraMap);
	}

	public List<BasePojo> getPortAssTreeListAddForm(
			HashMap<String, Object> paraMap) {
		return svcDao.getPortAssTreeListAddForm(paraMap);
	}

	/**
	 * 根据用户和岗位获取组合树
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPortTreeByUserAndPost(
			HashMap<String, Object> paraMap) {
		if (RightConstants.isFastRight) {
			List<String> portCodes = getDataRightList(portType,
					"" + paraMap.get("C_USER_CODE"),
					"" + paraMap.get("C_POST_CODE"));
			if (portCodes == null || portCodes.size() == 0) {
				return new ArrayList<BasePojo>();
			}
			String[] postCodesArray = new String[portCodes.size()];
			for (int i = 0; i < portCodes.size(); i++) {
				postCodesArray[i] = portCodes.get(i);
			}
			return svcDao.getPortTreeByCodes(postCodesArray);

		}
		return svcDao.getPortTreeByUserAndPost(paraMap);
	}

	public List<BasePojo> getPortTreeByUserAndPostAndProdState(
			HashMap<String, Object> paraMap) {
		return svcDao.getPortTreeByUserAndPostAndProdState(paraMap);
	}

	// added by wangzhiye 2014-03-18 获取证券品种树
	public List<BasePojo> getStockTypeTree(HashMap<String, Object> paraMap) {
		return svcDao.getStockTypeTree(paraMap);
	}

	/* START 组合数据处理器 */
	// public <T extends BasePojo> List<T> portFilter(String datClass) {
	// List<T> dataList = new ArrayList<T>();
	// List<String> dataRight = new ArrayList<String>();
	// HashMap<String, PortAssTree> dataMap = svcDao.getPortDataMap();
	// HashMap<String, PortAssTree> rsMap = new HashMap<String, PortAssTree>();
	// AppContext context = (AppContext) ContextFactory.getContext();
	// dataRight = context.getLogInfo().getDataRithtList();
	//
	// PortAssTree port = null;
	// for (String portCode : dataRight) {
	// if (dataMap.containsKey(portCode)) {
	// port = dataMap.get(portCode);
	//
	// if (datClass.equals(port.getC_DAT_CLS())) {
	// setRsMap(portCode, dataMap, rsMap);
	// }
	// }
	//
	// }
	//
	// rsMapToList(rsMap, dataList);
	// return dataList;
	// }

	// /**
	// * 在应用中实现组合的筛选
	// *
	// * 实现步骤如下： 1、从数据库中获取所有组合并且存放到HashMap中 2、根据参数筛选Map中的数据 3、将Map中的数据转为列表返回
	// *
	// * @throws Exception
	// *
	// */
	// public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
	// String datClass, String dvPortCode, String trCode) throws Exception {
	// List<T> dataList = new ArrayList<T>();
	//
	// // 获取数据
	// HashMap<String, PortAssTree> dataMap = svcDao.getPortDataMap();
	//
	// // 对返回结果集作空值处理
	// if (dataMap == null) {
	// dataMap = new HashMap<String, PortAssTree>();
	// }
	//
	// HashMap<String, PortAssTree> rsMap = new HashMap<String, PortAssTree>();
	//
	// List<String> dataRights = null;
	// setDataRightList(dataRights);
	//
	// HashMap<String, String> defaultPortMap = null;
	// setDefaultPortMap(defaultPortMap, dataRights, trCode);
	//
	// Iterator<PortAssTree> dataIt = null;
	//
	// // 如果查询集合不为空，则执行筛选操作
	// if (!dataMap.isEmpty()) {
	// // 数据权限筛选
	// filterRsMapWithDataRight(isDataRight, dataMap, rsMap);
	// dataIt =
	//
	// if(dataRightCheck(isDataRight, port, dataRights))
	//
	// // 资产类型级别筛选
	// filterRsMapWithDatClass(datClass, dataMap, rsMap);
	//
	// // 资产分组筛选
	// filterRsMapWithDvPortCode(dvPortCode, dataMap, rsMap);
	//
	// filterRsMapWithTrCode(trCode, dataMap, rsMap);
	//
	// // 将最终结果转为列表
	// rsMapToList(rsMap, dataList);
	// }
	//
	// return dataList;
	// }

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws YssException {
		List<T> dataList = new ArrayList<T>();
		// 调整缓存这里的取数，直接取原表中的数据，调用查询的方法byleeyu20130815
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("SearchAudit", 1); // 取已审核的数据
		// 获取数据
		dataList = (List<T>) svcDao.getPortList(map);

		return dataList;
	}

	/**
	 * 根据资产树节点获取组合树MAP（没有数据权限过滤）
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Port> getPortDataMapByTrCode(String trCode) throws Exception{
		HashMap<String, Port> dataMap = null;		
		if (trCode == null || trCode.trim().length() == 0) {
			String key = "NULL_TRCODE";
			dataMap = PortCache.getCachedTrCodeMap().get(key);
			if(dataMap == null){
				dataMap = svcDao.getPortDataMap();
				//synchronized(PortCache.getCachedTrCodeMap()){
					PortCache.getCachedTrCodeMap().clear();
					PortCache.getCachedTrCodeMap().put(key, dataMap);
				//}
			}else{
				//查找资产类型的数据，资产类型数据,这里资产类型数量很少，故查询不影响性能
				HashMap<String,Port> tssTypeMap = svcDao.getPortDataMapOnlyWithTssType();
				if(tssTypeMap != null){
					//synchronized(PortCache.getCachedTrCodeMap()){
						PortCache.getCachedTrCodeMap().get(key).putAll(tssTypeMap);
					//}
				}
			}
		} else if (trCode .equals("ZCTG")) {
			dataMap = svcDao.getPortDataForZCTGMap();			
		} else if (trCode .equals("TGZC")) {
			dataMap = svcDao.getPortDataForTGZCMap();
		} else if (trCode .equals("GLZC")) { //add by chenyoucai 20180120 STORY #51993 财务报表优化-区分不同管理人出具纳税报表
			dataMap = svcDao.getPortDataForGLZCMap();
		}else if (trCode .equals("ZCGL")) { //add by xyh 20180122
			dataMap = svcDao.getPortDataForZCGLMap();
		}else if (trCode .equals("NSPL")) { //add by xyh 20180122  
			dataMap = svcDao.getPortDataForNSPLMap();
		}else if (trCode.equals("ZCZL")) { //add by yz 20181219 STORY #61285 【中金公司】资产类型调整
			dataMap = svcDao.getPortDataForZCZLMap();
		}else if (trCode.equals("ZCWT")) { //add by yz 20190711 STORY #67195 人保资产-4.5测试系统中产品建账环节：产品树信息建议设置为账户基本信息【3其它需求-079】
			dataMap = svcDao.getPortDataForZCWTMap();
		}else if (trCode.equals("CPZT")) {
			dataMap = svcDao.getPortDataForCPZTMap();
		}else if (trCode.equals("ZCMXLX")) {
			dataMap = svcDao.getPortDataForZCMXLXMap();
		}
		//解決当前A区由于清算系统引起的A区加载问题
		else if (trCode.equals("TAQS")) {
			dataMap = svcDao.getPortNoClsDataMap();
		}
		else {
			dataMap = getDefaultAllPortMap(trCode);
		}
		
		return dataMap;
	}
	
	/**
	 * 根据资产树节点获取组合树
	 * 优先从缓存中获取
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	private HashMap<String, Port> getDataMapFromCache(String trCode) throws Exception{
		HashMap<String, Port> dataMap = null;
		//从缓存中获取已缓存的组合树数据KEY
		IPortCacheDataService portCacheDataService = HttpServiceFactory.getInstance()
				.createService(IPortCacheDataService.class);
		List<String> cacheKeys = portCacheDataService.getCacheTrCodeDataKey();
		
		String cacheKey = trCode;
		if(trCode == null || trCode.trim().length() == 0){
			cacheKey = "NULL_TRCODE";
		}
		if(null != cacheKeys && cacheKeys.contains(trCode)){
			//先从缓存中获取组合树数据
			PortCacheData cacheMap = portCacheDataService.getCacheTrCodeDataMapByKey(cacheKey);
			if(null != cacheMap && null != cacheMap.getPortData()){
				dataMap = cacheMap.getPortData();
			}else{
				//若缓存MAP中为空，再走原有逻辑从数据库获取
				dataMap = getPortDataMapByTrCode(trCode);
			}
		}
		else{
			//若缓存MAP中没有对应的资产树KEY，再走原有逻辑从数据库获取
			dataMap = getPortDataMapByTrCode(trCode);
		}
		//STORY #72592 中金-资产类型调整完善  当新增或修改产品时确保'资产类型-种类'是查出库中最新数据。
		if("ZCZL".equals(trCode)){			
			dataMap.putAll(svcDao.getPortDataForZCZLMap());
		}
		return dataMap;
	}
	
	/**
	 * trCode为空时 查询组合信息
	 * 
	 * */
	public HashMap<String, Port> getPortDataMapWithNullTrCode(){
		HashMap<String, Port> tmpMap = svcDao.getPortDataMap();
		if(tmpMap == null || tmpMap.isEmpty()){
			return null;
		}
		return tmpMap;
	}
	
	
	/**
	 * 在应用中实现组合的筛选
	 * 
	 * 实现步骤如下： 1、从数据库中获取所有组合并且存放到HashMap中 2、根据参数筛选Map中的数据 3、将Map中的数据转为列表返回
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode) throws Exception {
		List<T> dataList = new ArrayList<T>();
		List<String> productState = new ArrayList<String>();

		// 修改 资产结构树不会根据trCode改变而改变 edit by weijj 20130902
		List<String> dataRights = getDataRightList();
		
		if(!isDataRight){
			Logger logger = LogManager.getLogger(getClass());
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
			
			//切换产品状态
			ISwitchProductStateService switchProductStateService = YssServiceFactory
					.getInstance().createService(ISwitchProductStateService.class);
			productState = switchProductStateService.getProdState(userCode, postCode);
		}
		
		// 获取组合树形结构数据
		HashMap<String, Port> dataMap =  getDataMapFromCache(trCode);
		
		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		Iterator<Port> dataIt = null;

		Port port = null;
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!dataRightCheck(isDataRight, port, dataRights)) {
					continue;
				}

				if (!checkDatClass(datClass, port)) {
					continue;
				}

				if (!checkDvPortCode(dvPortCode, port)) {
					continue;
				}

				if (!isLeafPort(port)) {
					continue;
				}
				
				//BUG #239882 【鹏华自动化】产品树形结构反审核的组合也显示出来
				if(0 == port.getAuditState()){
					continue;
				}
				
				if(!isDataRight && !productState.contains(port.getC_DV_PROD_STATE())){
					continue;
				}
				
				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}
			//补充没有组合的分类节点--STORY #55709 公共组合区树形结构优化
			//BUG #216967 【招商基金】A区树形结构中有很多不需要的节点  mzy 20180830
//			dataIt = dataMap.values().iterator();
//			while (dataIt.hasNext()) {
//				port = dataIt.next();
//				if(!isLeafPort(port) && !rsMap.keySet().contains(port.getC_PORT_CODE())  
//						&& null != port.getC_PORT_NAME_ST() && !port.getC_PORT_NAME_ST().equals("未分配")){
//					rsMap.put(port.getC_PORT_CODE(),port);
//				}
//			}
			
			// STORY #63176 计划层组合的赋权 过滤掉返回结果中的没有权限的计划层数据
			if (isDataRight) {
				rsMapFilter(rsMap,dataRights);
			}
			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}
		
		return dataList;
	}
	
	// STORY #63176 计划层组合的赋权 过滤掉返回结果中的没有权限的计划层数据
	private void rsMapFilter(HashMap<String, Port> rsMap,List<String> dataRights){
		Set<String> keySet = rsMap.keySet();
		List<String> temp = new ArrayList<String>();
		Set<String> dataSet = new HashSet<String>(dataRights);
		for (String key : keySet) {
			if (!dataSet.contains(key) && rsMap.get(key).getdATA_TYPE().equalsIgnoreCase("PORT_TYPE")) {
				temp.add(key);
			}
		}
		for (String key : temp) {
			rsMap.remove(key);
		}
		
	}

	/**
	 * 在应用中实现组合的筛选
	 * 
	 * 实现步骤如下： 1、从数据库中获取所有组合并且存放到HashMap中 2、根据参数筛选Map中的数据 3、将Map中的数据转为列表返回
	 * 
	 * @throws Exception
	 * 
	 */
	public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode, List<String> dataRights) throws Exception {
		List<T> dataList = new ArrayList<T>();
		
		// FAST权限体系
		if (RightConstants.isFastRight) {
			//前端传来的数据权限（可避免多次重复查询权限的问题），还要过滤产品状态
			dataRights = getPortsByState(dataRights);
		}else{
			//兼容老版本
			dataRights = getDataRightList();
		}
		
		// 获取组合树形结构数据
		HashMap<String, Port> dataMap =  getDataMapFromCache(trCode);

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!dataRightCheck(isDataRight, port, dataRights)) {
					continue;
				}

				if (!checkDatClass(datClass, port)) {
					continue;
				}

				if (!checkDvPortCode(dvPortCode, port)) {
					continue;
				}

				if (!isLeafPort(port)) {
					continue;
				}

				// BUG #345770 【太平金控4.5（300.7.20201031.1125）】用户权限分配，只给小组合赋权限，默认将大组合权限赋上
				//setRsMap(port.getC_PORT_CODE(), dataMap, rsMap, false);
				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}
	
	/**
	 * 在应用中实现组合的筛选
	 * 
	 * 实现步骤如下： 1、从数据库中获取所有组合并且存放到HashMap中 2、根据参数筛选Map中的数据 3、将Map中的数据转为列表返回
	 * 
	 * @throws Exception
	 * 
	 */
	public <T extends BasePojo> List<T> queryPort(String trCode)
			throws Exception {
		List<T> dataList = new ArrayList<T>();

		// 修改 资产结构树不会根据trCode改变而改变 edit by weijj 20130902
		HashMap<String, Port> dataMap = null;
		// 获取数据
		if("ASS".equalsIgnoreCase(trCode)){
			trCode = null; 
		}
		dataMap = getPortDataMapByTrCode(trCode);

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		// HashMap<String, String> defaultPortMap =
		// getDefaultPortMap(dataRights,
		// trCode);

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();
				// if (!checkTrCode(trCode, port, defaultPortMap)) {
				// continue;
				// }

				if (!isLeafPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> portFilterByKeys(String[] conds)
			throws Exception {
		List<T> dataList = new ArrayList<T>();

		// 获取数据
		HashMap<String, Port> dataMap = svcDao.getPortDataMap();

		HashMap<String, String> condMap = new HashMap<String, String>();
		filterCondArrToMap(condMap, conds);

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		List<String> dataRights = getDataRightList();

		Iterator<Port> dataIt = null;

		Port port = null;
		
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();
			
			while (dataIt.hasNext()) {
				port = dataIt.next();
				
				if (!dataRightCheck(true, port, dataRights)) {
					continue;
				}
				
				if (!condMap.containsKey(port.getC_PORT_CODE())) {
					continue;
				}
				
				if (!isLeafPort(port)) {
					continue;
				}
				
				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}
			
			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}
		
		return dataList;
	}

	public <T extends BasePojo> List<T> getPortByKeys(String[] conds)
			throws Exception {
		List<T> dataList = new ArrayList<T>();

		// 获取数据
		HashMap<String, Port> dataMap = svcDao.getPortDataMap();

		HashMap<String, String> condMap = new HashMap<String, String>();
		filterCondArrToMap(condMap, conds);

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		List<String> dataRights = getDataRightList();

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!dataRightCheck(true, port, dataRights)) {
					continue;
				}

				if (!condMap.containsKey(port.getC_PORT_CODE())) {
					continue;
				}

				if (!isLeafPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}

	public List<BasePojo> getPortDataByKeysNoFilter(String[] conds)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		for (String s : conds) {
			sb.append(",").append(s);
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", sb.toString().replaceFirst(",", ""));
		List<BasePojo> list = svcDao.getPortAssTreeListALL(paraMap);
		return list;
	}

	private void filterCondArrToMap(HashMap<String, String> condMap,
			String[] condArr) {
		if (condArr != null) {
			for (String condStr : condArr) {
				condMap.put(condStr, condStr);
			}
		}
	}

	/**
	 * 获取组合数据权限
	 * 
	 * @param rights
	 */
	public List<String> getDataRightList(String c_DATA_TYPE) {
		// 通过 RightManageDataService 服务去取 不再保存在session中 edit by weijj 20130722
		// AppContext context = (AppContext) ContextFactory.getContext();
		// return context.getLogInfo().getDataRithtList();
		Logger logger = LogManager.getLogger(getClass());
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
			// 老版本
			// IRightManageDataService rms =
			// HttpServiceFactory.getInstance().createService(IRightManageDataService.class);
			// list = rms.getUserDataRight(userCode, postCode);

			// FAST版本 begin
			/*IFASTDataAuthorityService fastDataAuthorityService = HttpServiceFactory
					.getInstance().createService(
							IFASTDataAuthorityService.class);*/
			IFASTDataAuthorityService fastDataAuthorityService = YssServiceFactory
					.getInstance().createService(
							IFASTDataAuthorityService.class);
			/*List<UserPostData> userPostDatas = fastDataAuthorityService
					.getUserDataRight(userCode, postCode);
			for (UserPostData userPostData : userPostDatas) {
				if (userPostData.getC_DATA_TYPE().equals(c_DATA_TYPE)) {
					list.add(userPostData.getC_DATA_CODE());
				}
			}*/
			list = fastDataAuthorityService.queryByUser(c_DATA_TYPE, userCode, postCode);
			
			//BUG #201583 【21.6海通证券升级测试】-产品状态中勾掉【到期】但是到期产品没有过滤掉
			if(c_DATA_TYPE.equals(portType)){
				ISwitchProductStateService switchProductStateService = YssServiceFactory
						.getInstance().createService(
								ISwitchProductStateService.class);
				//List<String> productState = new SwitchProductStateService()
				//.getProdState(userCode, postCode);
				List<String> productState = switchProductStateService.getProdState(userCode, postCode);
				return getPortsByState(productState, list);
			}

			// FAST版本 end
		} catch (Exception e) {
			logger.log("查询失败", e);
			// e.printStackTrace();
		}
		
		return list;
	}
    public List<String> getDataRightListForReportCenter(String c_DATA_TYPE) {
        Logger logger = LogManager.getLogger(getClass());
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
            IFASTDataAuthorityService fastDataAuthorityService = YssServiceFactory
                    .getInstance().createService(
                            IFASTDataAuthorityService.class);
            List<UserPostData> userPostDatas = null;
            if (!StringUtil.IsNullOrEmptyT(postCode)) {
                userPostDatas = fastDataAuthorityService.getUserDataRight(userCode, postCode, RightConstants.portType);
                 for (UserPostData userPostData : userPostDatas) {
                    if (userPostData.getC_DATA_TYPE().equals(c_DATA_TYPE)) {
                        list.add(userPostData.getC_DATA_CODE());
                    }
                 }
            } else {
                list = fastDataAuthorityService.queryByUser(RightConstants.portType, userCode);
            }
            if(c_DATA_TYPE.equals(portType)){
                ISwitchProductStateService switchProductStateService = YssServiceFactory
                        .getInstance().createService(
                                ISwitchProductStateService.class);
                List<String> productState = switchProductStateService.getProdState(userCode, postCode);
                return getPortsByState(productState, list);
            }
        } catch (Exception e) {
            logger.log("查询失败", e);
        }
		return list;
	}

	/**
	 * 根据状态和权限查询
	* @param productState
	* @param dataCodes
	* @return List<String>
	* @author mazhongyuan
	* @date 2018年5月11日下午6:59:06
	 */
	private List<String> getPortsByState(List<String> productState,List<String> dataCodes){
		List<String> list = new ArrayList<String>();
		String state = "";
		if(null ==  productState){
			return list;
		}
		Map<String,String> portStateMap = svcDao.getPortStateByCode(dataCodes);
		for(String dataCode : dataCodes){
			state = portStateMap.get(dataCode);
			if(null != state && productState.contains(state)){
				list.add(dataCode);
			}
		}
		
		return list;
	}

	/**
	 * 当前用户根据状态和权限去查询组合编号
	 * @param dataRights
	 * @return
	 */
	private List<String> getPortsByState(List<String> dataRights){
		Logger logger = LogManager.getLogger(getClass());
		String userCode = null;
		String postCode = null;
		if (context.getLogInfo() == null) {
			userCode = context.getUserCode();
		} else {
			userCode = context.getLogInfo().getLoggingUserCode();
			postCode = context.getLogInfo().getLoggingPostCode();
			if (postCode == null || postCode.equals("")) {
				logger.error("岗代码为空。");
			}
		}
		ISwitchProductStateService switchProductStateService = YssServiceFactory
				.getInstance().createService(
						ISwitchProductStateService.class);
		List<String> productState = switchProductStateService
				.getProdState(userCode, postCode);
		dataRights = getPortsByState(productState, dataRights);
		return dataRights;
	}
	
	/**
	 * 获取组合数据权限
	 * 
	 * @param rights
	 */
	public List<String> getDataRightList() {

		// FAST权限体系
		if (RightConstants.isFastRight) {
			// 此方法走FAST权限体系
			return getDataRightList(portType);
		}
		// 通过 RightManageDataService 服务去取 不再保存在session中 edit by weijj 20130722
		// AppContext context = (AppContext) ContextFactory.getContext();
		// return context.getLogInfo().getDataRithtList();
		Logger logger = LogManager.getLogger(getClass());
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
			IRightManageDataService rms = HttpServiceFactory.getInstance()
					.createService(IRightManageDataService.class);
			list = rms.getUserDataRight(userCode, postCode);
		} catch (Exception e) {
			logger.log("查询失败", e);
			// e.printStackTrace();
		}
		return list;
	}
    public List<String> getDataRightListForReportCenter() {

        // FAST权限体系
        if (RightConstants.isFastRight) {
            // 此方法走FAST权限体系
            return getDataRightListForReportCenter(portType);
        }
        // 通过 RightManageDataService 服务去取 不再保存在session中 edit by weijj 20130722
        // AppContext context = (AppContext) ContextFactory.getContext();
        // return context.getLogInfo().getDataRithtList();
        Logger logger = LogManager.getLogger(getClass());
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
            IRightManageDataService rms = HttpServiceFactory.getInstance()
                    .createService(IRightManageDataService.class);
            list = rms.getUserDataRight(userCode, postCode);
        } catch (Exception e) {
            logger.log("查询失败", e);
        }
        return list;
    }

	/**
	 * 获取组合数据权限
	 * 
	 * @param rights
	 */
	private List<String> getDataRightList(String c_DATA_TYPE, String userCode,
			String postCodes) {
		// 通过 RightManageDataService 服务去取 不再保存在session中 edit by weijj 20130722
		// AppContext context = (AppContext) ContextFactory.getContext();
		// return context.getLogInfo().getDataRithtList();
		Logger logger = LogManager.getLogger(getClass());
		List<String> list = new ArrayList<String>();
		try {
			// FAST版本 begin
			IFASTDataAuthorityService fastDataAuthorityService = HttpServiceFactory
					.getInstance().createService(
							IFASTDataAuthorityService.class);

			List<UserPostData> userPostDatas = fastDataAuthorityService
					.getUserDataRight(userCode, postCodes);
			for (UserPostData userPostData : userPostDatas) {
				if (userPostData.getC_DATA_TYPE().equals(c_DATA_TYPE)) {
					list.add(userPostData.getC_DATA_CODE());
				}
			}
			// FAST版本 end
		} catch (Exception e) {
			logger.log("查询失败", e);
			// e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据资产树节点获取组合树（没有数据权限过滤）
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	private HashMap<String, Port> getDefaultAllPortMap(
			String trCode) throws Exception {
		HashMap<String, Port> defaultPortMap = new HashMap<String, Port>();
		if (trCode == null || trCode.trim().equals("")) {
			return defaultPortMap;
		}
//		List<BasePojo> list = svcDao.getDefaultAllPort(trCode);
//		for (BasePojo pojo : list) {
//			Port port = (Port) pojo;
//			defaultPortMap.put(port.getC_PORT_CODE(), port);
//		}
		
		//根据产品树形结构的自动转入类型查询不同的数据
		//STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
		//add by yangru 20190717
		HashMap<String,String> assetsTree = svcDao.getAssetsTree(trCode);
		if(assetsTree.size() > 0){
			if("TREE_TYPE_CUS".equals(assetsTree.get("C_DV_TR"))){
				if("TREE_AUTO_DTZH".equals(assetsTree.get("C_AUTO_ZR_TYPE"))){
					defaultPortMap = svcDao.getPortDataForDtcrMap("",assetsTree,true);
				}else{
					defaultPortMap = svcDao.getPortDataForZdyMap("",assetsTree,true);
				}
			}else{
				List<BasePojo> list = svcDao.getDefaultAllPort(trCode);
				for (BasePojo pojo : list) {
					Port port = (Port) pojo;
					defaultPortMap.put(port.getC_PORT_CODE(), port);
				}
				
			}
		}
		return defaultPortMap;
	}
	
	private HashMap<String, Port> getDefaultPortMap(List<String> rights,
			String trCode) throws Exception {
		HashMap<String, Port> defaultPortMap = new HashMap<String, Port>();
		if (trCode == null || trCode.trim().equals("")) {
			return defaultPortMap;
		}
		String rightStr = "";
		for (String right : rights) {
			rightStr += right + ",";
		}
		rightStr = StringUtil.delLastSplitMark(rightStr, ",");
//		List<BasePojo> list = svcDao.getDefaultUserPort(rightStr, trCode);
//		for (BasePojo pojo : list) {
//			Port port = (Port) pojo;
//			defaultPortMap.put(port.getC_PORT_CODE(), port);
//		}
		//根据产品树形结构的自动转入类型查询不同的数据
		//STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
		//add by yangru 20190717
		HashMap<String,String> assetsTree = svcDao.getAssetsTree(trCode);
		if(assetsTree.size() > 0){
			if("TREE_TYPE_CUS".equals(assetsTree.get("C_DV_TR"))){
				if("TREE_AUTO_DTZH".equals(assetsTree.get("C_AUTO_ZR_TYPE"))){
					defaultPortMap = svcDao.getPortDataForDtcrMap(rightStr,assetsTree,false);
				}else{
					defaultPortMap = svcDao.getPortDataForZdyMap(rightStr,assetsTree,false);
				}
			}else{
				List<BasePojo> list = svcDao.getDefaultUserPort(rightStr, trCode);
				for (BasePojo pojo : list) {
					Port port = (Port) pojo;
					defaultPortMap.put(port.getC_PORT_CODE(), port);
				}
				
			}
		}
		return defaultPortMap;
	}

	private HashMap<String, Port> getDefaultPortMap(String trCode)
			throws Exception {
		HashMap<String, Port> defaultPortMap = new HashMap<String, Port>();
		if (trCode == null || trCode.trim().equals("")) {
			return defaultPortMap;
		}
		List<BasePojo> list = svcDao.getDefaultPort(trCode);
		for (BasePojo pojo : list) {
			Port port = (Port) pojo;
			defaultPortMap.put(port.getC_PORT_CODE(), port);
		}
		return defaultPortMap;
	}

	/**
	 * 根据数据权限筛选
	 * 
	 * @param isDataRight
	 * @param dataMap
	 * @param rsMap
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private void filterRsMapWithDataRight(boolean isDataRight,
			HashMap<String, Port> dataMap, HashMap<String, Port> rsMap) {
		List<String> dataRight = new ArrayList<String>();

		if (isDataRight) {
			// AppContext context = (AppContext) ContextFactory.getContext();
			// dataRight = context.getLogInfo().getDataRithtList();
			dataRight = getDataRightList();
			initRsMap(dataMap, rsMap);
			for (String portCode : dataRight) {
				if (dataMap.containsKey(portCode)) {
					if (isLeafPort(dataMap.get(portCode))) {
						setRsMap(portCode, dataMap, rsMap);
					}

				}
			}
		}
	}

	private void initRsMap(HashMap<String, Port> dataMap,
			HashMap<String, Port> rsMap) {
		dataMap.clear();
		if (!rsMap.isEmpty()) {
			Set<String> keys = rsMap.keySet();
			for (String key : keys) {
				dataMap.put(key, rsMap.get(key));
			}
		}

	}

	private boolean dataRightCheck(boolean isDataRight, Port port,
			List<String> dataRight) {
		boolean checkFlag = false;

		if (isDataRight) {
			String portCode = port.getC_PORT_CODE();
			for (String right : dataRight) {
				if (portCode.equals(right)) {
					checkFlag = true;
					break;
				}
			}
		} else {
			checkFlag = true;
		}

		return checkFlag;
	}

	private boolean checkDatClass(String datClass, Port port) {
		boolean checkFlag = false;

		if (datClass == null) {
			datClass = "";
		}

		if ("".equals(datClass)) {
			checkFlag = true;
		} else {
			String portDatClass = port.getC_DAT_CLS();
			if (datClass.indexOf(portDatClass) > -1) {
				checkFlag = true;
			}
		}

		return checkFlag;
	}

	private boolean checkDvPortCode(String dvPortCode, Port port) {
		boolean checkFlag = false;

		if (dvPortCode == null) {
			dvPortCode = "";
		}

		if ("".equals(dvPortCode)) {
			checkFlag = true;
		} else {
			String portDvPortCode = port.getC_DV_PORT_CODE();
			if (portDvPortCode.equals(dvPortCode)) {
				checkFlag = true;
			}
		}

		return checkFlag;
	}

	private boolean isLeafPort(Port port) {
		boolean isLeaf = false;

		if (PortDataType._Port.toString().equals(port.getdATA_TYPE())) {
			isLeaf = true;
		}

		return isLeaf;
	}

	/**
	 * 递归是过滤组合权限
	 * @param portCode
	 * @param dataMap
	 * @param rsMap
	 * @param flag 是否递归调用
	 */
	private void setRsMap(String portCode, HashMap<String, Port> dataMap, HashMap<String, Port> rsMap, boolean flag) {
		Port port = dataMap.get(portCode);

		if (port != null) {
			if (!rsMap.containsKey(port.getC_PORT_CODE()) && (!flag || flag && "ASS_TYPE".equals(port.getdATA_TYPE()))) {
				rsMap.put(port.getC_PORT_CODE(), port);
			}

			if (!_rootMark.equals(port.getC_PORT_CODE_P())) {
				setRsMap(port.getC_PORT_CODE_P(), dataMap, rsMap, true);
			}
		}
	}
	
	private void setRsMap(String portCode, HashMap<String, Port> dataMap,
			HashMap<String, Port> rsMap) {
		Port port = dataMap.get(portCode);

		if (port != null) {
			if (!rsMap.containsKey(port.getC_PORT_CODE())) {
				rsMap.put(port.getC_PORT_CODE(), port);
			}

			if (!_rootMark.equals(port.getC_PORT_CODE_P())) {
				setRsMap(port.getC_PORT_CODE_P(), dataMap, rsMap);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private <T extends BasePojo> void rsMapToList(HashMap<String, Port> rsMap,
			List<T> dataList) {
		Iterator<Port> rsIt = rsMap.values().iterator();
		while (rsIt.hasNext()) {
			dataList.add((T) rsIt.next());
		}
	}

	/* END 组合数据处理器 */

	/**
	 * 根据参数查询组合信息
	 */
	public List<Port> getPortInfoList(HashMap<String, Object> paraMap)
			throws BusinessException {
		// 从组合PortDao中获取
		List<Port> ports = this.svcDao.getPortList(paraMap);
		return ports;
	}

	/**
	 * 根据组合代码获取组合的信息
	 * 
	 * @param cPortCode
	 * @return
	 * @throws ServiceException
	 */
	public Port getPortInfo(String cPortCode) throws ServiceException {
		return this.svcDao.getPortInfo(cPortCode);
	}

	/**
	 * 根据组合名称获取组合的信息，用于银行间Api
	 * 
	 * @param portName
	 * @return
	 * @throws ServiceException
	 */
	public Port getPortInfoByPortName(String portName) throws ServiceException {
		return this.svcDao.getPortInfoByPortName(portName);
	}

	/**
	 * 到期确认
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 * @throws YssException 
	 */
	public String operDQQR(List<Port> lstPort) throws ServiceException, YssException {
		return svcDao.operDQQR(lstPort);
	}

	/**
	 * 到期撤消
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operDQQX(List<Port> lstPort) throws ServiceException {
		return svcDao.operDQQX(lstPort);
	}

	/**
	 * 清算确认
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operQSQR(List<Port> lstPort) throws ServiceException {
		/*Product product = null;
		IProductDataService productDataService = HttpServiceFactory
				.getInstance().createService(IProductDataService.class);
		ITaSettDataService taSettDataService = HttpServiceFactory.getInstance()
				.createService(ITaSettDataService.class);
		for (Port port : lstPort) {
			// 到销售结转设置中查询结算账户信息，条件为：组合、币种、节假日、类型(赎回)、结算日期
			String cCaCode = taSettDataService.getSettCa(port.getC_PORT_CODE(),
					"TAXS_SH", port.getC_DC_CODE(), port.getC_HDAY_CODE(),
					YssFun.formatDate(port.getD_CLEAR()));
			product = new Product();
			product.setC_PD_CODE(port.getC_PORT_CODE()); // 组合代码
			product.setD_SETT_DUE(YssFun.formatDate(port.getD_CLEAR())); // 实际日期
			product.setC_DC_CODE(port.getC_DC_CODE()); // 实际币种
			product.setC_CA_CODE(cCaCode); // 实际清算账户
			product.setC_DV_PROD_STATE(port.getC_DV_PROD_STATE()); // 产品状态

			product.setYieldList(new ArrayList<PdYield>());
			product.setD_BEGIN("1900-01-01");
			product.setD_CL_BEGIN("1900-01-01");
			product.setD_CL_END("1900-01-01");
			product.setD_END("1900-01-01");
			product.setD_SL_BEGIN("1900-01-01");
			product.setD_SL_END("1900-01-01");
			productDataService.qsqrChange(product,
					YssFun.formatDate(port.getD_CLEAR()));
		}
		return svcDao.operQSQR(lstPort);*/
		IPortTansferService portTansferService =  YssServiceFactory
				.getInstance().createService(IPortTansferService.class);
		return portTansferService.operQSQR(lstPort);
	}

	/**
	 * 清算撤消
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operQSQX(List<Port> lstPort) throws ServiceException {
		/*Product product = null;
		IProductDataService productDataService = HttpServiceFactory
				.getInstance().createService(IProductDataService.class);
		for (Port port : lstPort) {
			product = new Product();
			product.setC_PD_CODE(port.getC_PORT_CODE()); // 组合代码
			product.setD_SETT_DUE(YssFun.formatDate(port.getD_CLEAR())); // 实际日期
			product.setC_DV_PROD_STATE(port.getC_DV_PROD_STATE()); // 产品状态

			product.setYieldList(new ArrayList<PdYield>());
			product.setD_BEGIN("1900-01-01");
			product.setD_CL_BEGIN("1900-01-01");
			product.setD_CL_END("1900-01-01");
			product.setD_END("1900-01-01");
			product.setD_SL_BEGIN("1900-01-01");
			product.setD_SL_END("1900-01-01");
			productDataService.cxqsqrChange(product);
		}

		return svcDao.operQSQX(lstPort);*/
		IPortTansferService portTansferService =  YssServiceFactory
				.getInstance().createService(IPortTansferService.class);
		return portTansferService.operQSQX(lstPort);
	}

	public String getPortSubCount(String portCode) {
		return svcDao.getPortSubCount(portCode);
	}

	public void deleteByCodes(List<Port> portList) {
		svcDao.deleteById(portList);
	}

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}

	public Port getPortByAssCode(String assCode) throws ServiceException {
		return svcDao.getPortByAssCode(assCode);
	}

	/**
	 * Fixed by huangsq 20160811 STORY #26296 [招商证券]估值表发布及获取接口需求
	 */
	public List<Port> getPortByAssCodes(List<String> assCodes) {
		return svcDao.getPortByAssCodes(assCodes);
	}

	public List<Port> getAssPort(String trCode) throws Exception {
		if (trCode == null || trCode.isEmpty()) {
			return svcDao.getPortTreeViewCommon();
		} else {
			return svcDao.getAssPort(trCode);
		}
	}

	/**
	 * 获取群list挂组合树形结构显示 STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * 
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	public List<Port> getGroupDataTree(String trCode) throws Exception {
		return svcDao.getGroupDataTree(trCode);
	}
	public List<Port> getGroupDataTree(String trCode, List<String> portList) throws Exception {
		return svcDao.getGroupDataTree(trCode, portList);
	}

	/**
	 * 获取群list挂组合树形结构显示，无用户和岗位权限
	 * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	public List<Port> getGroupDataTreeWithoutRight(String trCode) throws Exception {
		return svcDao.getGroupDataTreeWithoutRight(trCode);
	}

	/**
	 * 查询A区资产类型数据，以树形展示
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * neil 2019-07-26
	 * @return
	 */
	public List<Port> getAssGroupDataTree(String trCode) throws Exception {
		return svcDao.getAssGroupDataTree(trCode);
	}
	/**
	 * 检查组合代码是否在群组中已经存在true:已经存在；false:不存在 STORY #16818 产品群组需求 add by chenwenhai
	 * 20140603
	 * 
	 * @param groupCode
	 * @return
	 */
	public String checkPortCode(String portCode) {
		return svcDao.checkPortCode(portCode);
	}

	/**
	 * 询下面存在子组合的组合信息 By Jinghehe 2014-7-28 根据用户和用户岗位来加载数据
	 * 
	 * @param userCode
	 * @param postCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> queryParentPortTreeViewData(
			String userCode, String postCode) throws Exception {
		return (List<T>) svcDao.queryParentPortTreeViewData(userCode, postCode);
	}

	/**
	 * 询产品和组合信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<BasePojo> queryProductORPort() {
		return svcDao.queryProductORPort();
	}

	/**
	 * 询产品和组合信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<BasePojo> getTreePortDataByCodes(String[] keys) {
		return svcDao.getTreePortDataByCodes(keys);
	}

	/**
	 * 查询群组和组合(有权限控制) add by liuxiang 2015/3/11
	 * 
	 * @return
	 */
	public <T extends BasePojo> List<BasePojo> getAllGroupAndPort() {
		List<String> dataRights = getDataRightList();
		return svcDao.getAllGroupAndPort(dataRights);
	}
	
	/**
	 * 查询群组和组合(无权限控制)
	 * 
	 * @return
	 */
	public <T extends BasePojo> List<BasePojo> getAllGroupAndPortNoRight() {
		return svcDao.getAllGroupAndPortNoRight();
	}
	
	 /* 查询资产类型和资产组合数据
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * neil 2019-07-26
	 * @return
	 */
	public <T extends BasePojo> List<BasePojo> getAllAssGroupAndPort() {
		List<String> dataRights = getDataRightList();
		return svcDao.getAllAssGroupAndPort(dataRights);
	}
	/**
	 * 获取群组下的所有组合（无权限控制）
	 * @param groupCodes
	 * @return
	 */
	public Map<String,List<Port>> getAllPortByGroups(List<String> groupCodes) {
		return svcDao.getAllPortByGroups(groupCodes);
	}
	/**
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * 获取所有资产类型和组合	 
	 * neil
	 * 2019-07-26 
	 * @param rightsList
	 *            权限列表
	 * @return
	 */
	public Map<String,List<Port>> getAllAssPortByGroups(List<String> groupCodes) {
		return svcDao.getAllAssPortByGroups(groupCodes);
	}
	
	public List<Port> dueClearedPorts(HashMap<String, String> paraMap) {
		return svcDao.duePorts(paraMap);
	}

	/**
	 * 在应用中实现总账组合的筛选,调价总账查询权限筛选方法 chenyoulong 20141225 实现步骤如下：
	 * 1、从数据库中获取所有组合并且存放到HashMap中 2、根据参数筛选Map中的数据 3、将Map中的数据转为列表返回
	 * 
	 * @throws Exception
	 * 
	 */
	public <T extends BasePojo> List<T> ZzPortFilter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode) throws Exception {
		List<T> dataList = new ArrayList<T>();

		// 修改 资产结构树不会根据trCode改变而改变 edit by weijj 20130902
		List<String> dataRights = getDataRightList();
		HashMap<String, Port> dataMap = null;
		// 获取数据
		if (trCode == null || trCode.trim().length() == 0) {
			dataMap = svcDao.getPortDataMap();
		} else {
			dataMap = getDefaultPortMap(dataRights, trCode);
		}

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		// HashMap<String, String> defaultPortMap =
		// getDefaultPortMap(dataRights,
		// trCode);

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!dataRightCheck(isDataRight, port, dataRights)) {
					continue;
				}

				if (!checkDatClass(datClass, port)) {
					continue;
				}

				if (!checkDvPortCode(dvPortCode, port)) {
					continue;
				}

				// if (!checkTrCode(trCode, port, defaultPortMap)) {
				// continue;
				// }

				if (!isLeafPort(port)) {
					continue;
				}

				if (!isZzPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}

	/**
	 * 是否总账组合 chenyoulong 2014-12-25
	 * 
	 * @param port
	 * @return
	 */
	private boolean isZzPort(Port port) {
		boolean isZzPort = false;

		if ("PORT_LAYER".equals(port.getC_DV_PORT_CODE())) {
			isZzPort = true;
		}

		return isZzPort;
	}

	public <T extends BasePojo> List<T> portFilter(
			Map<String, String> portCodeMap) {
		List<T> dataList = new ArrayList<T>();

		HashMap<String, Port> dataMap = svcDao.getPortDataMap();

		if (dataMap != null && dataMap.size() > 0) {
			List<String> dataRights = getDataRightList();
			HashMap<String, Port> rsMap = new HashMap<String, Port>();
			for (Port port : dataMap.values()) {
				if (!dataRightCheck(true, port, dataRights)) {
					continue;
				}

				if (!isLeafPort(port)) {
					continue;
				}

				if (!portCodeMap.containsKey(port.getC_PORT_CODE())) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}

	/**
	 * 20150424 added by liubo.STORY #22372 【紧急】招商现场提出的关于报表中组合下拉树调整
	 * 为报表中心的查询出符合用户权限和产品树的trcode，获取组合列表数据。
	 * 不用之前的旧方法，是因为包装处理后的List对象包含的都是BasePojo,在报表中心的包中无法转换成子类Port
	 * 这就必须写一个方法，专门用来返回一个List<Port>
	 * 
	 * @param isDataRight
	 * @param datClass
	 * @param dvPortCode
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	public List<Port> PortFilter_ReportCenter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode) throws Exception {
		List<Port> dataList = new ArrayList<Port>();
		HashMap<String, Port> dataMap = null;
		List<String> dataRights = new ArrayList<String>();
		if(isDataRight) { 
			dataRights = getDataRightListForReportCenter();
		}
		// 获取数据
//		if (trCode == null || trCode.trim().length() == 0) {
//			dataMap = svcDao.getPortDataMap();
//		} else {
//			dataMap = getDefaultPortMap(dataRights, trCode);
//		}
		dataMap = this.getPortDataMapByTrCode(trCode);
		HashMap<String, Port> rsMap = new HashMap<String, Port>();
		Iterator<Port> dataIt = null;
		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!dataRightCheck(isDataRight, port, dataRights)) {
					continue;
				}

				if (!checkDatClass(datClass, port)) {
					continue;
				}

				if (!checkDvPortCode(dvPortCode, port)) {
					continue;
				}

				if (!isLeafPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			Iterator<Port> rsIt = rsMap.values().iterator();
			while (rsIt.hasNext()) {
				dataList.add(rsIt.next());
			}
		}
		return dataList;
	}
	
	
	public <T extends BasePojo> List<T> queryPort(String trCode,String[] portCodes)
			throws Exception {
		List<T> dataList = new ArrayList<T>();

		// 修改 资产结构树不会根据trCode改变而改变 edit by weijj 20130902
		HashMap<String, Port> dataMap = new HashMap<String, Port>();
		// 获取数据
		dataMap = getDefaultPortMap(trCode,portCodes);

	/*	// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}*/

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!isLeafPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}
	
	
	private HashMap<String, Port> getDefaultPortMap(String trCode,String[] portCodes)
			throws Exception {
		HashMap<String, Port> defaultPortMap = new HashMap<String, Port>();
		if (trCode == null || trCode.trim().equals("")) {
			return defaultPortMap;
		}
		List<BasePojo> list = svcDao.getDefaultPort(trCode,portCodes);
		for (BasePojo pojo : list) {
			Port port = (Port) pojo;
			defaultPortMap.put(port.getC_PORT_CODE(), port);
		}
		return defaultPortMap;
	}
	
	public List<Port> queryPort(List<String> portCodes,List<String> productState){
		return svcDao.queryPort(portCodes, productState);
	}
	
	public HashMap<String, String> queryPortMap(List<String> portCodes,List<String> productState){
		return svcDao.queryPortMap(portCodes, productState);
	}
	
	/**
	 * 根据ID获取数据，这里调用基类的queryByIds方法
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月28日下午8:20:43
	 * @param ids
	 * @param clazz
	 * @return List<Port>
	 */
	public List<Port> querybyIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
	
	/**
	 * 根据属性标识 获取过滤后的组合列表中所有某个属性的值
	 * <产品信息组件拆分>
	 * 
	 * @Title getFilteredPortPropertyByCode
	 * @Description 根据属性标识 获取过滤后的组合列表中所有某个属性的值
	 * @author HeLiang@ysstech.com
	 * @date 2017年6月12日下午3:47:24
	 * @param propertyCode
	 * @return
	 * @return List<K>
	 */
	public List<Object> getFilteredPortPropertyByCode(String propertyCode) {
		List<Object> lstPortPerporty = new ArrayList<Object>();
		Method method = null;
		Class<?> clazz = null;
		String methodName = null;

		try {
			List<Port> lstPort = this.portFilter(true, "", "", "");

			clazz = Port.class;
			methodName = "get" + propertyCode;

			method = clazz.getMethod(methodName,
					new Class[] { java.lang.String.class });

			for (Port port : lstPort) {
				// lstPortPerporty.add(port.getC_PORT_CODE());
				lstPortPerporty.add(method.invoke(port));
			}

			return lstPortPerporty;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	/**
	 * STORY #45114 估值系统，根据组合树类型获取组合树
	 * @param userName 用户
	 * @param postCode 岗位列表
	 * @param portTreeType 组合树类型
	 * @return 根据权限过滤
	 * @throws Exception
	 */
	public <T extends BasePojo> List<T> getPortTreeList(String userCode,
			String postCode, String portTreeType) throws Exception{
		List<T> dataList = new ArrayList<T>();
		
		IRightManageDataService rms = HttpServiceFactory.getInstance().createService(IRightManageDataService.class);
		List<String> dataRights = rms.getUserDataRight(userCode, postCode);
		
		HashMap<String, Port> dataMap = null;
		// 获取数据
		// add huangshui 2016-12-10 STORY #34789 南方基金-产品树形结构按托管行自动分类(增加资产托管及托管资产两个默认项)
		if (portTreeType == null || portTreeType.trim().length() == 0) {
			dataMap = svcDao.getPortDataMap();
		}
//		else if (portTreeType .equals("ZCTG")) {
//			dataMap = svcDao.getPortDataForZCTGMap();			
//		} else if (portTreeType .equals("TGZC")) {
//			dataMap = svcDao.getPortDataForTGZCMap();
//		} 
		else {
			dataMap = getDefaultPortMap(true,userCode, dataRights, portTreeType);
		}

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		// HashMap<String, String> defaultPortMap =
		// getDefaultPortMap(dataRights,
		// trCode);

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!dataRightCheck(true, port, dataRights)) {
					continue;
				}

				if (!isLeafPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}
	
	/**
	 * STORY #45114 估值系统，根据组合树类型获取组合树
	 * @param portTreeType 组合树类型(不考虑权限)
	 * @return
	 * @throws Exception
	 */
	public <T extends BasePojo> List<T> getPortTreeList(String portTreeType) throws Exception{
		List<T> dataList = new ArrayList<T>();
		
		HashMap<String, Port> dataMap = null;
		// 获取数据
		// add huangshui 2016-12-10 STORY #34789 南方基金-产品树形结构按托管行自动分类(增加资产托管及托管资产两个默认项)
		if (portTreeType == null || portTreeType.trim().length() == 0) {
			dataMap = svcDao.getPortDataMap();
		} 
//		else if (portTreeType .equals("ZCTG")) {
//			dataMap = svcDao.getPortDataForZCTGMap();			
//		} else if (portTreeType .equals("TGZC")) {
//			dataMap = svcDao.getPortDataForTGZCMap();
//		} 
		else {
			dataMap = getDefaultPortMap(false,null, null, portTreeType);
		}

		// 对返回结果集作空值处理
		if (dataMap == null) {
			dataMap = new HashMap<String, Port>();
		}

		HashMap<String, Port> rsMap = new HashMap<String, Port>();

		// HashMap<String, String> defaultPortMap =
		// getDefaultPortMap(dataRights,
		// trCode);

		Iterator<Port> dataIt = null;

		Port port = null;
		// 如果查询集合不为空，则执行筛选操作
		if (!dataMap.isEmpty()) {
			// 数据权限筛选
			dataIt = dataMap.values().iterator();

			while (dataIt.hasNext()) {
				port = dataIt.next();

				if (!isLeafPort(port)) {
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}

			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}

	
	/**
	 * 获取资产类型--组合树类型
	 * STORY #45114 估值系统支持查询“组合树类型”列表的接口
	 * @return
	 * @throws ServiceException
	 */
	public ArrayList<String> getAssetType() throws ServiceException {
		return svcDao.getAssetType();
	}
	
	private HashMap<String, Port> getDefaultPortMap(boolean isDataRight,String userCode , List<String> rights,
			String trCode) throws Exception {
		HashMap<String, Port> defaultPortMap = new HashMap<String, Port>();
		if (trCode == null || trCode.trim().equals("")) {
			return defaultPortMap;
		}
		List<BasePojo> list = null;
		if(isDataRight){
			String rightStr = "";
			for (String right : rights) {
				rightStr += right + ",";
			}
			rightStr = StringUtil.delLastSplitMark(rightStr, ",");
			list = svcDao.getDefaultUserPort( rightStr, trCode);
			//list = svcDao.getDefaultUserPort(userCode, rightStr, trCode);
		}else{
			list = svcDao.getDefaultUserPortNoRight(trCode);
		}
		for (BasePojo pojo : list) {
			Port port = (Port) pojo;
			defaultPortMap.put(port.getC_PORT_CODE(), port);
		}
		
		return defaultPortMap;
	}
	
	/**
	 * 清算关账
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operQSGZ(List<Port> lstPort) throws ServiceException {
		return svcDao.operQSGZ(lstPort);
	}

	/**
	 * 关账撤消
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operGZCX(List<Port> lstPort) throws ServiceException {
		return svcDao.operGZCX(lstPort);
	}
	
	/**
	 * 调整功能实现位置到估值
	 * 关账撤销，删除损益结转凭证和对冲凭证，
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 * @param lstPort
	 * @return
	 */
//	public String deleteByGZCX(List<Port> lstPort) {
//		return svcDao.deleteByGZCX(lstPort);
//	}
	
	/**
	 * edit by yuanyafeng 20180522 STORY #54942 【鹏华基金】产品基本信息维护了到期及清算处理连带处理
	 * 更新运营费用设置中的组合的结束日期
	 * @param lstPort
	 */
	public void updateInveFeeDate(List<Port> lstPort){
		svcDao.updateInveFeeDate(lstPort);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByCodes(String codes)
			throws Exception {
		BasePojo basePojo = null;
		List<BasePojo> result = new ArrayList<BasePojo>();
		String undotCodes ="";
		String[] codestr = codes.split(",");
		try {
			// 优先调用缓存中的数据
			IPortCacheDataService portCacheDataService = HttpServiceFactory
					.getInstance().createService(IPortCacheDataService.class);
			for(String portCode:codestr){
				if(StringUtil.IsNullOrEmptyT(portCode)){	
					continue;
				}	
				basePojo = portCacheDataService.getDataByCode(portCode);		
				if(null == basePojo){
					undotCodes += portCode+",";
				}else{
					result.add(basePojo);
				}
			}
		} catch (Exception ex) {
			// 如果缓存调用失败，则再从表中取
			for(String portCode:codestr){
				undotCodes += portCode+",";
			}
		}
		if(undotCodes.length() > 0){
			HashMap<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("C_POST_CODE", undotCodes);
			List<Port> ports = queryByCondition(paraMap);
			if(ports.size() > 0)
			{
				result.addAll(ports);
			}
		}
		
		return (List<T>) result;
	}
	
	/**
	 * 查询子组合
	 * @param protPCode
	 * @return
	 */
	public List<BasePojo> getUnitLayerPort(String[] protPCode) {
		// TODO Auto-generated method stub
		return svcDao.getUnitLayerPort(protPCode);
	}

	/**
	 * 根据组合代码查询组合
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getPortListByCodes(String[] codes) throws Exception {
		return (List<T>) svcDao.getPortListByCodes(codes);
	}

	/**
	 * 查询关联了流程的组合
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public List<String> getFlowPort(String[] portCodes) {
		IFastAutomaticService fastAutomaticService = YssServiceFactory.getInstance().createService(IFastAutomaticService.class);
		return fastAutomaticService.getFlowPort(Arrays.asList(portCodes));
	}
	
	/**
	   * 不要权限查组合PojoBy组合Code
	   * @param conds
	   * @return
	   * @throws Exception
	   */
	public List<Port> queryPortByPortCode(String[] conds)
	   throws Exception {
	  // 获取数据
	  return svcDao.queryPortByPortCode(conds);
	 }
	  /**
		 * 在应用中实现组合的筛选
		 * 
		 * 实现步骤如下： 1、从数据库中获取所有组合并且存放到HashMap中 2、根据参数筛选Map中的数据 3、将Map中的数据转为列表返回
		 * 
		 * @throws Exception
		 * 
		 */
		public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
				String datClass, String dvPortCode, String trCode,String userCode) throws Exception {
			List<T> dataList = new ArrayList<T>();
			List<String> dataRights = new ArrayList<String>();
			if(isDataRight) {
				if(null!=userCode && !"".equalsIgnoreCase(userCode)){
					 dataRights = getDataRightList(userCode,"");
					
				}
			}
			// 获取组合树形结构数据
			HashMap<String, Port> dataMap =  getDataMapFromCache(trCode);

			// 对返回结果集作空值处理
			if (dataMap == null) {
				dataMap = new HashMap<String, Port>();
			}

			HashMap<String, Port> rsMap = new HashMap<String, Port>();

			Iterator<Port> dataIt = null;

			Port port = null;
			// 如果查询集合不为空，则执行筛选操作
			if (!dataMap.isEmpty()) {
				// 数据权限筛选
				dataIt = dataMap.values().iterator();

				while (dataIt.hasNext()) {
					port = dataIt.next();
					if(null!=userCode){
						if (!dataRightCheck(isDataRight, port, dataRights)) {
							continue;
						}
					}
					if (!checkDatClass(datClass, port)) {
						continue;
					}

					if (!checkDvPortCode(dvPortCode, port)) {
						continue;
					}

					if (!isLeafPort(port)) {
						continue;
					}

					setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
				}

				// 将最终结果转为列表
				rsMapToList(rsMap, dataList);
			}

			return dataList;
		}
		
		/**
		 * 获取组合数据权限
		 * 
		 * @param rights
		 */
		public List<String> getDataRightList(String userCode,String postCode) {

			// FAST权限体系
			if (RightConstants.isFastRight) {
				// 此方法走FAST权限体系
				return getDataRightList(portType);
			}
			
			Logger logger = LogManager.getLogger(getClass());
			List<String> list = new ArrayList<String>();
			try {
				IRightManageDataService rms = HttpServiceFactory.getInstance()
						.createService(IRightManageDataService.class);
				list = rms.getUserDataRight(userCode, postCode);
			} catch (Exception e) {
				logger.log("查询失败", e);
				// e.printStackTrace();
			}
			return list;
		}
		/**
		 * STORY #69369 易方达：【自动化】关于流程管理组合维护的需求  baoqiaolin 20190910
		 * 根据树形结构代码查询已分配的组合
		 * @param isDataRight
		 * @param datClass
		 * @param dvPortCode
		 * @param trCode
		 * @return
		 * @throws Exception
		 */
		public List<Port> PortFilter_ByTrCode_YFP(boolean isDataRight,
				String datClass, String dvPortCode, String trCode) throws Exception {
			List<Port> dataList = new ArrayList<Port>();
			HashMap<String, Port> dataMap = null;
			List<String> dataRights = new ArrayList<String>();
			if(isDataRight){
				dataRights=getDataRightListForReportCenter();
			}
			dataMap = this.getPortDataMapByTrCode_YFP(trCode);
			HashMap<String, Port> rsMap = new HashMap<String, Port>();
			Iterator<Port> dataIt = null;
			Port port = null;
			if (!dataMap.isEmpty()) {
				dataIt = dataMap.values().iterator();
				while (dataIt.hasNext()) {
					port = dataIt.next();
					if (!dataRightCheck(isDataRight, port, dataRights)) {
						continue;
					}
					if (!checkDatClass(datClass, port)) {
						continue;
					}
					if (!checkDvPortCode(dvPortCode, port)) {
						continue;
					}
					if (!isLeafPort(port)) {
						continue;
					}
					setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
				}
				Iterator<Port> rsIt = rsMap.values().iterator();
				while (rsIt.hasNext()) {
					dataList.add(rsIt.next());
				}
			}
			return dataList;
		}
		/**
		 * STORY #69369 易方达：【自动化】关于流程管理组合维护的需求  baoqiaolin 20190910
		 * @param trCode
		 * @return
		 * @throws Exception
		 */
		public HashMap<String, Port> getPortDataMapByTrCode_YFP(String trCode) throws Exception{
			HashMap<String, Port> dataMap = null;		
			if (trCode == null || trCode.trim().length() == 0) {
				String key = "NULL_TRCODE";
				dataMap = PortCache.getCachedTrCodeMap().get(key);
				if(dataMap == null){
					dataMap = svcDao.getPortDataMap();
					//map 已经改成线程安排的map
					//synchronized(PortCache.getCachedTrCodeMap()){
						PortCache.getCachedTrCodeMap().clear();
						PortCache.getCachedTrCodeMap().put(key, dataMap);
					//}
				}else{
					//查找资产类型的数据，资产类型数据,这里资产类型数量很少，故查询不影响性能
					HashMap<String,Port> tssTypeMap = svcDao.getPortDataMapOnlyWithTssType();
					if(tssTypeMap != null){
						//synchronized(PortCache.getCachedTrCodeMap()){
							PortCache.getCachedTrCodeMap().get(key).putAll(tssTypeMap);
						//}
					}
				}
			} else if (trCode .equals("ZCTG")) {
				dataMap = svcDao.getPortDataForZCTGMap();			
			} else if (trCode .equals("TGZC")) {
				dataMap = svcDao.getPortDataForTGZCMap();
			} else if (trCode .equals("GLZC")) { //add by chenyoucai 20180120 STORY #51993 财务报表优化-区分不同管理人出具纳税报表
				dataMap = svcDao.getPortDataForGLZCMap();
			}else if (trCode .equals("ZCGL")) { //add by xyh 20180122
				dataMap = svcDao.getPortDataForZCGLMap();
			}else if (trCode .equals("NSPL")) { //add by xyh 20180122  
				dataMap = svcDao.getPortDataForNSPLMap();
			}else if (trCode.equals("ZCZL")) { //add by yz 20181219 STORY #61285 【中金公司】资产类型调整
				dataMap = svcDao.getPortDataForZCZLMap();
			}else if (trCode.equals("ZCWT")) { //add by yz 20190711 STORY #67195 人保资产-4.5测试系统中产品建账环节：产品树信息建议设置为账户基本信息【3其它需求-079】
				dataMap = svcDao.getPortDataForZCWTMap();
			}else if (trCode.equals("CPZT")) { 
				dataMap = svcDao.getPortDataForCPZTMap();
			}else if (trCode.equals("ZCMXLX")) {
				dataMap = svcDao.getPortDataForZCMXLXMap();
			}
			//解決当前A区由于清算系统引起的A区加载问题
			else if (trCode.equals("TAQS")) {
				dataMap = svcDao.getPortNoClsDataMap();
			}
			else {
				dataMap = getDefaultAllPortMap_YFP(trCode);
			}
			
			return dataMap;
		}
		/**
		 * STORY #69369 易方达：【自动化】关于流程管理组合维护的需求  baoqiaolin 20190910
		 * @param trCode
		 * @return
		 * @throws Exception
		 */
		private HashMap<String, Port> getDefaultAllPortMap_YFP(
				String trCode) throws Exception {
			HashMap<String, Port> defaultPortMap = new HashMap<String, Port>();
			if (trCode == null || trCode.trim().equals("")) {
				return defaultPortMap;
			}
			List<BasePojo> list = svcDao.getDefaultAllPort_YFP(trCode);
			for (BasePojo pojo : list) {
				Port port = (Port) pojo;
				defaultPortMap.put(port.getC_PORT_CODE(), port);
			}
			return defaultPortMap;
		}
		
		/**
	     * STORY #73984 增值税纳税报表辅助部分优化需求
		 * @return
		 * @throws Exception
		 */
		public HashMap<String, String> portDeduction(List<String> portList){
			return svcDao.portDeduction(portList);
		}
		
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 * @return
	 * @throws Exception
	 */
	public List<AssetsTree_A> queryAssetTreeWithLeafNode() throws Exception {
		return svcDao.queryAssetTreeWithLeafNode();
	}
	
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 * @return
	 * @throws Exception
	 */
	public List<Port> getTreePortByCode(boolean isDataRight, String trCode, String trCodeR) throws Exception {
		List<Port> portList = new ArrayList<Port>();
		List<Port> list = svcDao.getTreePortByCode(trCode, trCodeR);
		if (isDataRight) {
			List<String> dataRights = getDataRightListForReportCenter();
			for (Port port : list) {
				if (!dataRightCheck(isDataRight, port, dataRights)) {
					continue;
				}
				portList.add(port);
			}
		}
		else {
			portList.addAll(list);
		}
		return portList;
	}
	
	/**
	 * STORY #75531 【广发基金】支持美元本位币记账组合的人民币转换和核对的需求 (#2 #1 ) 
	 * 查询并行组合
	 * @author zengguowei
	 * @since 2019-07-18
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getSourcesBxzhViewDataPojos(HashMap<String, Object> paraMap){
		if(RightConstants.isFastRight){
			IFASTDataAuthorityService authorityService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
			List<String> portCodes = null;
				try {
					portCodes = authorityService.queryByUser("1",String.valueOf(paraMap.get("APPY_USER_REAL")));
				} catch (Exception e) {
					//e.printStackTrace();
				}
				paraMap.remove("APPY_USER_REAL");
				StringBuffer portCodesStr = new StringBuffer();
				if(portCodes != null){
					for (String string : portCodes) {
						portCodesStr.append(string).append(",");
					}
				}
				paraMap.put("ARRAY_C_PORT_CODE", portCodesStr);
//				paraMap.put("C_DV_PORT_CODE", "UNIT_LAYER");
				return svcDao.querySourcesBxzhViewDataPojos(paraMap);
		}else{
			return  null;
		}
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
    public List<Port> queryPortByTypeCondition(String type, List<String> codes, boolean isDataRight) throws Exception {
    	List<Port> portList = new ArrayList<Port>();
    	try {
    		List<String> dataRights = null;
    		if (isDataRight) {
				dataRights = getDataRightListForReportCenter();
				if (dataRights == null) {
					dataRights = new ArrayList<String>();
				}
			}
    		
			if ("C_DAT_CODE".equals(type)) {
				PortCache portCache =CacheManager.getInstance().getCache(CacheGroup.PORT);
				List<Port> portCacheList = portCache.getCacheList();
				for (Port port : portCacheList) {
					if (port.getAuditState() == 1 && codes.contains(port.getC_DAT_CODE())) {
						if (dataRightCheck(isDataRight, port, dataRights)) {
							portList.add(port);
						}
					}
				}
			}
			else if ("C_DAT_CLS".equals(type)) {
				PortCache portCache =CacheManager.getInstance().getCache(CacheGroup.PORT);
				List<Port> portCacheList = portCache.getCacheList();
				for (Port port : portCacheList) {
					if (port.getAuditState() == 1 && codes.contains(port.getC_DAT_CLS())) {
						if (dataRightCheck(isDataRight, port, dataRights)) {
							portList.add(port);
						}
					}
				}
			}
			else if ("GROUP".equals(type)) {
				Map<String, List<Port>> groupMap = getAllPortByGroups(codes);
				for (Map.Entry<String, List<Port>> entry : groupMap.entrySet()) {
					for (Port port : entry.getValue()) {
						if (dataRightCheck(isDataRight, port, dataRights)) {
							portList.add(port);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
    	return portList;
    }
}
