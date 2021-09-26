package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.port.admin.PortAdmin;
import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.modules.ab.port.service.impl.PortAlterPublisher.ProductInfoState;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortService;
import com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService;
import com.yss.datacheck.annotation.CommonDataCheck;
import com.yss.datacheck.annotation.DefaultDataCheck;
import com.yss.datacheck.enums.CheckFuncGroup;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheRefresh;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.resource.mgr.pojo.BusinessData;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;

/**
 * <产品基本信息>组合服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@DefaultCacheRefresh(group = CacheGroup.PORT)
public class PortService extends ServiceBus<PortService> implements
		IPortService, IRightPortService {

	private PortDao serviceDao = null;

	private PortAdmin portAdmin = null;

	public PortService() throws Exception {
		serviceDao = new PortDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortSqlBuilder());
		dao = serviceDao;

		portAdmin = new PortAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortSqlBuilder());
		
		serviceBindingClazz = Port.class;
	}

	public QueryRes getAssetTreeView(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getAssetTreeView(paraMap);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("pd_portAssTree");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public QueryRes getPlanRelaPortAdd(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPlanRelaPortAdd(paraMap, clazz);
			queryRes.setDataList(dataList);

//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("PlanRelaPortAdd");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public QueryRes getPlanRelaPortBrow(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPlanRelaPortBrow(paraMap, clazz);
			queryRes.setDataList(dataList);

//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("PlanRelaPortBrow");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public List<Port> getParamSetPortList(HashMap<String, Object> paraMap) {
		List<Port> portList = new ArrayList<Port>();

		try {

		} catch (Exception ex) {
//			ex.printStackTrace();
		}

		return portList;
	}

	public List<Port> getRightManagePortList(
			HashMap<String, Object> paraMap) throws Exception {
		List<Port> portList = new ArrayList<Port>();

		try {
			// 前台的状态
			// String status=paraMap.get("status").toString();
			// Object o = paraMap.get("onlyHead");
			// 判断是否第一次加载组合树，如果不为空表示加载所有的组合信息
			// if (o == null)
			// 从已有的记录中加载 不区分是否审核
			portList = ((PortDao) dao).getPortDataSql(paraMap);
			// /else {
			// / portList = ((PortDao) dao).getRightManagePortList(paraMap);
			// }

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			throw new Exception();
		}

		return portList;
	}

	public List<Port> getRightManagePortTree(
			HashMap<String, Object> paraMap) throws Exception {
		List<Port> portList = new ArrayList<Port>();
		try {
			portList = ((PortDao) dao).getPortTreeForRight(paraMap);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			throw new Exception();
		}

		return portList;
	}

	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) throws Exception {
		List<Port> portList = new ArrayList<Port>();

		try {
			portList = ((PortDao) dao).getRightManagePortListExpertAdd(paraMap);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			throw new Exception();
		}

		return portList;
	}

	public QueryRes getDspPort(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		List<BasePojo> dataList = null;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");
			dataList = serviceDao.queryDspPortDao(paraMap, clazz);

			fillResultObject(queryRes, dataList, 0, null, menuId);
			queryRes.setMenuId("PlanRelaPortAdd");

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.log(ex.getMessage());
				throw new ServiceException(ex);
			}
//			queryRes.setOperRes(MvcConstant._Fault);
//			queryRes.setMenuId(menuId);
//			throw new ServiceException(ex, menuId, queryRes);
		}

		return queryRes;
	}

	public QueryRes queryDataByBrow(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		List<BasePojo> dataList = null;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");
			dataList = serviceDao.queryDataByBrowDao(paraMap, clazz);

			fillResultObject(queryRes, dataList, 0, null, menuId);
			queryRes.setMenuId("PlanRelaPortAdd");

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.log(ex.getMessage());
				throw new ServiceException(ex);
			}
//			queryRes.setOperRes(MvcConstant._Fault);
//			queryRes.setMenuId(menuId);
//			throw new ServiceException(ex, menuId, queryRes);
		}
		return queryRes;
	}

	public List<Port> getRightManagePortList() throws Exception {
		return null;
	}

	/**
	 * 根据参数查询组合信息
	 */
	public List<Port> getPortInfoList(HashMap<String, Object> paraMap)
			throws Exception {
		// 从组合PortDao中获取
		List<Port> ports = ((PortDao) dao).getPortList(paraMap);
		return ports;
	}

	/**
	 * 投资组合数据（树形结构） 查询下面存在单元层组合的组合层组合、计划层组合信息
	 * 
	 * @author tangshifeng
	 * @since 2013-04-10
	 * @param paraMap
	 * @return
	 */
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryTreeViewData(paraMap);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("solutionSet_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * BUG #378219 【深国投信托】【0611.0729】部分界面权限优化
	 * 根据查询条件获取投资组合数据（树形结构）
	 * @param isDataRight
	 * @param paraMap
	 * @return
	 */
	public QueryRes getTreeViewDataRight(String isDataRight, HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<String> dataRights = null;
    		if ("true".equals(isDataRight)) {
				dataRights = portAdmin.getDataRightListForReportCenter();
				if (dataRights == null) {
					dataRights = new ArrayList<String>();
				}
				paraMap.put("APPY_USER_REAL", StringUtil.join(dataRights, ","));
			}
    		
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryTreeViewData(paraMap);
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId, ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("solutionSet_A");
		} catch (Exception ex) {
			logger.error("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * BUG #378219 【深国投信托】【0611.0729】部分界面权限优化
	 * 获取当前登录用户有权限的组合代码
	 * @return
	 */
	public List<String> getDataRightListForReportCenter() {
		List<String> portCodeList = new ArrayList<String>();
		try {
			portCodeList = portAdmin.getDataRightListForReportCenter();
			if (portCodeList == null) {
				portCodeList = new ArrayList<String>();
			}
		} catch (Exception e) {
			logger.error("查询失败", e);
		}
		return portCodeList;
	}

	/**
	 * 单元层投资组合数据（列表结构） 查询下面存在单元层组合的组合层组合、计划层组合信息
	 * 
	 * @author tangshifeng
	 * @since 2013-04-17
	 * @param paraMap
	 * @return
	 */
	public QueryRes getUnitPortData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryUnitPortData(paraMap);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("solutionSet_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * BUG #377903 【深国投信托】【0611.0729】汇集调尾处理问题
	 * 根据查询条件获取单元层投资组合数据（列表结构）
	 * @param isDataRight
	 * @param paraMap
	 * @return
	 */
	public QueryRes getUnitPortDataRight(String isDataRight, HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<String> dataRights = null;
    		if ("true".equals(isDataRight)) {
				dataRights = portAdmin.getDataRightListForReportCenter();
				if (dataRights == null) {
					dataRights = new ArrayList<String>();
				}
				paraMap.put("ARRAY_C_PORT_CODE", StringUtil.join(dataRights, ","));
			}
    		
    		List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryUnitPortData(paraMap);
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId, ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("solutionSet_A");
		} catch (Exception ex) {
			logger.error("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/* START 资产数列表详细信息界面获取数据列表 */
	public QueryRes getPortAssTreeList(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = portAdmin.getPortAssTreeList(paraMap);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("pd_portAssTree");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public QueryRes getPortAssTreeListAddForm(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = portAdmin.getPortAssTreeListAddForm(paraMap);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("pd_portAssTree");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/* END 资产数列表详细信息界面获取数据列表 */

	@Override
	public String operDQQR(List<Port> lstPort) throws ServiceException {
		String retInfo = "";
		try {
			portAdmin.operDQQR(lstPort);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}

		return retInfo;
	}

	@Override
	public String operDQQX(List<Port> lstPort) throws ServiceException {
		String retInfo = "";
		try {
			portAdmin.operDQQX(lstPort);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}

		return retInfo;
	}

	@Override
	public String operQSQR(List<Port> lstPort) throws ServiceException {
		String retInfo = "";
		try {
			portAdmin.operQSQR(lstPort);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}

		return retInfo;
	}

	@Override
	public String operQSQX(List<Port> lstPort) throws ServiceException {
		String retInfo = "";
		try {
			portAdmin.operQSQX(lstPort);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}

		return retInfo;
	}
	
	/**
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 */
	@Override
	public String operQSGZ(List<Port> lstPort) throws ServiceException,Exception{
		String retInfo = "";
		try {
			portAdmin.operQSGZ(lstPort);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}
		return retInfo;
	}
    
	/**
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 */
	@Override
	public String operGZCX(List<Port> lstPort) throws ServiceException {
		String retInfo = "";
		try {
			// 核算处理,删除相关核算数据，（非手动损益结转凭证，对冲凭证数据）
		    // 调整功能实现位置到估值
			//portAdmin.deleteByGZCX(lstPort);
//		    List<Map<String,Object>> listPortMap = new ArrayList<Map<String,Object>>();
//		    for (Port port : lstPort) {
//		        Map<String, Object> map = new HashMap<String, Object>();
//		        map.put("C_PORT_CODE", port.getC_PORT_CODE());
//		        map.put("D_COLSE_ACC", port.getD_COLSE_ACC());
//		        listPortMap.add(map);
//		    }
		    
			//修改产品基本信息
			portAdmin.operGZCX(lstPort);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}
		return retInfo;
	}
	
	/**
	 * 树形显示组合 
	 * @param paraMap
	 * @return
	 */
	public QueryRes getPortTreeWithNode(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.getPortTreeWithNode(paraMap);
//			ListHeadDtl listHeadInfo = AppContext.getInstance().getListHeadMap(menuId);
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance()
					.getAppContext(ProductInfoActivator.class)
					.getListHeadMap(menuId);
			
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("zzywgl_port_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 根据id删除数据（批量）
	 * 
	 * @param pojoList
	 *            数据对象列表
	 * @return 操作结果
	 */
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Delete)
	public String deleteById(List<BasePojo> pojoList) {
		String retInfo = "";

		try {
			dao.deleteById(pojoList);
			
			if (safeData != null && safeData.getN_RECYCLE() > 0) {
				retInfo = saveDelRecord(pojoList);
			}
			
//			if(pojoList.size()>0){
//				IPortGroupDataService PortGroupDataService = HttpServiceFactory
//						.getInstance().createService(IPortGroupDataService.class);
//				
//				String [] portCodes = new String[pojoList.size()];
//				for (int i = 0; i < pojoList.size(); i++) {
//					Port port = (Port) pojoList.get(i);
//					portCodes[i] = port.getC_PORT_CODE();
//				}
//				
//				PortGroupDataService.deleteByPortCodes(portCodes);
//			}
			
			retInfo = getDelInfo(retInfo);
			
			//投资组合信息变更消息发送
			new PortAlterPublisher().send(pojoList, ProductInfoState.DELETE);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}
			
			logger.log(ex.getMessage());
			throw new ErrorMessageException(ex, ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeDelErr, menuId));
			
//			retInfo = ReturnInfoGenerator.getOperErrMsg(
//					MvcConstant._CodeDelErr, menuId);
//			throw new ServiceException(ex, menuId, retInfo);
		}

		return retInfo;
	}

	@Override
	public List<Port> getTheSameAssCodeList(String  portCode, String assCode) throws ServiceException {
		return serviceDao.getTheSameAssCodeList(portCode, assCode);
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-07-26
	 * Status : Add
	 * Comment: 产品资产类别关系
	 * @param portCodes
	 * @return
	 */
	@Override
	public Map<String, String> getPortDatClsMap(String[] portCodes) {
		return serviceDao.getPortDatClsMap(portCodes);
	}
	
	@Override
	public List<Port> getPortType(){
		List<Port> ports = serviceDao.queryPortType();
		return ports;
	}

	/**
	 * 查询所有已审核的数据权限资源
	 */
	@Override
	public List<DataRight> query() {
		return serviceDao.queryPortTree();
	}
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		
		return null;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> arg0)
			throws ServiceException {
		
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String arg0)
			throws ServiceException {
		
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] arg0)
			throws ServiceException {
		
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] arg0)
			throws ServiceException {
		
		return null;
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String arg0)
			throws ServiceException {
		
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] arg0) throws ServiceException {
		
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] arg0) throws ServiceException {
		
		return null;
	}

	
	@Override
	public List<DataDimension> queryDataDimensions() {
		return null;
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, HashMap<String,String> customParam) {
		
		return null;
	}

	@Override
	public List<DataRight> query(String[] codes, HashMap<String,String> customParam) {
		List<BasePojo> ports = serviceDao.getPortTreeByCodes(codes);
		List<DataRight> dataRights = new ArrayList<DataRight>();
		String portState = customParam.get("dataParam");
		for (BasePojo basePojo : ports) {
			Port port = (Port)basePojo;
			if(port.getAuditState()==1){
				DataRight dataRight = new DataRight();
				dataRight.setC_DATA_CODE(port.getC_PORT_CODE());
				dataRight.setC_DATA_NAME(port.getC_PORT_NAME());
				//组合
				dataRight.setC_DATA_TYPE("1");
				dataRight.setC_DATA_CODE_P(port.getC_PORT_CODE_P());
				dataRight.setAuditState(1);
				//// 根节点是没有id值的
				dataRight.setId(port.getId());
				if("PORT_TYPE".equalsIgnoreCase(port.getdATA_TYPE())){
					dataRight.setAss_Port_Type(1);
				}
				
				if((portState != null && portState.contains(port.getC_DV_PROD_STATE()))
						|| "ASS_TYPE".equalsIgnoreCase(port.getdATA_TYPE())){
					dataRights.add(dataRight);
				} else if(portState == null){
					dataRights.add(dataRight);
				}
			}
		}
		return dataRights;
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, String[] codes, HashMap<String,String> customParam) {
		return null;
	}
	
	/**
	 * 产品生命周期 产品管理功能   提供控制事务的插入方法
	 * @author jiangzhichao
	 * @Date 20171229
	 * @param pojo
	 * @param conn
	 * @return
	 */
	public String connInsert(BasePojo pojo,Connection conn) throws ServiceException{
		String returnId = "";
		try {
			returnId = serviceDao.insert(pojo, conn);
		} catch (Exception e) {
			logger.log("插入失败", e);
			throw new ServiceException("插入失败", e);
		}
		return returnId;
	}
	
	/**
	 * 产品生命周期 产品管理功能   提供控制事务的删除方法
	 * @author jiangzhichao
	 * @Date 20171229
	 * @param pojo
	 * @param conn
	 * @return
	 */
	public void connDelete(List<BasePojo> list,Connection conn) throws ServiceException{
		try {
			for (BasePojo basePojo : list) {
				serviceDao.deleteById(basePojo, conn);
			}
		} catch (Exception e) {
			logger.log("删除失败", e);
			throw new ServiceException("删除失败", e);
		}
	}
	
	/**
	 * 产品生命周期 产品管理功能   提供控制事务的修改方法
	 * @author jiangzhichao
	 * @Date 20171229
	 * @param pojo
	 * @param conn
	 * @return
	 */
	public void connUpdate(BasePojo pojo,Connection conn) throws ServiceException{
		try {
			serviceDao.updateById(pojo, conn);
		} catch (Exception e) {
			logger.log("修改失败", e);
			throw new ServiceException("修改失败", e);
		}
	}
	
	 
	/**
	 * 根据id更新数据（批量）重写基类的修改方法    
	 * 保存数据的第一种情况：1.修改  
	 * @param pojoList  代更新数据列表
	 * add by yangweijie 2017-7-11 STORY #41439 华泰产品要区分集合、定向、专项           
	 * @return 操作结果
	 */
	@DefaultDataCheck
	@CacheRefresh(group = CacheGroup.PORT)
	public String updateById(List<BasePojo> pojoList) {
		String retInfo = "";
		try {
			retInfo = super.updateById(pojoList);
			for(BasePojo pojo : pojoList){
			    Port port=(Port)pojo;
				if(!StringUtil.IsNullOrEmpty(port.getC_ASSETS_CODE())){
					serviceDao.insertPortPd(port);
				}
			}
			
			//投资组合信息变更消息发送
			new PortAlterPublisher().send(pojoList, ProductInfoState.UPDATE);
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		}
		
		return retInfo;
	}
	
	/**
	 * 插入数据
	 * 保存数据的第二种情况：2.新增
	 * @param pojoList   数据列表
	 *    add by yangweijie 2017-7-11 STORY #41439 华泰产品要区分集合、定向、专项             
	 * @return 操作结果
	 * 
	 */
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Add)
	public String insert(List<BasePojo> pojoList) {
		String retInfo = "";
		try {
			retInfo = super.insert(pojoList);
			for(BasePojo pojo : pojoList){
			    Port port=(Port)pojo;
				if(!StringUtil.IsNullOrEmpty(port.getC_ASSETS_CODE())){
					serviceDao.insertPortPd(port);
				}
			}
			
			//投资组合信息变更消息发送
			new PortAlterPublisher().send(pojoList, ProductInfoState.INSERT);
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		}

		return retInfo;
	}

	@Override
	public String operDQQR(List<Port> lstPort, String date, String isUpdate)
			throws ServiceException {
		String retInfo = "";
		try {
			for(Port port : lstPort){
				if(port!=null){
					java.util.Date oldDate = DateUtil.stringtoDate(port.getD_CLOSE(),DateUtil.LONG_DATE_FORMAT);
					java.util.Date newDate = DateUtil.stringtoDate(date,DateUtil.LONG_DATE_FORMAT);
					//STORY #101658 【海富通基金 版本300.7 20201031.0203】---海富通产品到期确认到期日期取值逻辑修改
					//当用户手工输入后，关联【产品基本信息】中的产品的“到期日期”。如果输入时间晚于产品的到期时间，保存时，取产品的到期日期
					if(newDate.getTime()>oldDate.getTime()) {
						continue;
					}else {
						//如果输入时间早于或等于产品的到期日期，取输入时间
						port.setD_CLOSE(date);
					}
				}
			}
			
			portAdmin.operDQQR(lstPort);
			if("1".equals(isUpdate)){
				portAdmin.updateInveFeeDate(lstPort);
			}
			
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}

		return retInfo;
	}
	


	@Override
	public String dynamicCheck(String docTypeName, List<ResourceMgr> mgrList) {
		StringBuffer buffer = new StringBuffer();
		if (mgrList == null || mgrList.size() == 0) {
			buffer.append(docTypeName + "类型请至少上传1个附件");
			return buffer.toString();
		}
		
		for (ResourceMgr mgr : mgrList) {
			// 测试用
			buffer.append("测试显示每条记录的虚拟目录: " + mgr.getC_VIRTUAL_PATH());
		}
		return buffer.toString();
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataTime(String funCode, 
			String businessBeginDate, String businessEndDate) {
		List<BusinessData> resList = new ArrayList<BusinessData>();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("dataClass", "Port");
		if (!StringUtil.IsNullOrEmpty(businessBeginDate)) {
			paraMap.put("BEGIN_C_UPDATE_TIME", businessBeginDate);
		}
		if (!StringUtil.IsNullOrEmpty(businessEndDate)) {
			paraMap.put("END_C_UPDATE_TIME", businessEndDate);
		}
		try {
			List<BasePojo> list = queryListByCondition(paraMap);
			for (BasePojo pojo : list) {
				BusinessData data = new BusinessData();
				Port port = (Port)pojo;
				String portCode = port.getC_PORT_CODE();
				if (StringUtil.IsNullOrEmpty(portCode)) {
					portCode = "null";
				}
				data.setFunCode(funCode);
				data.setPortCode(portCode);
				data.setDataId(port.getId());
				data.setDataTime(port.getModifyDate());
				resList.add(data);
			}
		} catch (Exception e) {
			logger.log("查询失败", e);
			//e.printStackTrace();
		}
		return resList;
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataIds(String funCode, String dataIds) {
		List<BusinessData> resList = new ArrayList<BusinessData>();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("dataClass", "Port");
		if (!StringUtil.IsNullOrEmpty(dataIds)) {
			paraMap.put("ARRAY_C_IDEN", dataIds);
		}
		try {
			List<BasePojo> list = queryListByCondition(paraMap);
			for (BasePojo pojo : list) {
				BusinessData data = new BusinessData();
				Port port = (Port)pojo;
				String portCode = port.getC_PORT_CODE();
				if (StringUtil.IsNullOrEmpty(portCode)) {
					portCode = "null";
				}
				data.setFunCode(funCode);
				data.setPortCode(portCode);
				data.setDataId(port.getId());
				data.setDataTime(port.getModifyDate());
				resList.add(data);
			}
		} catch (Exception e) {
			logger.log("查询失败", e);
			//e.printStackTrace();
		}
		return resList;
	}
	@Override
	public String queryByParaCode(String paramCode) {
		return serviceDao.queryByParaCode(paramCode);
	}
	
   	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public String auditById(List<BasePojo> pojoList) {
		
		String str = super.auditById(pojoList);
		//投资组合信息变更消息发送
		new PortAlterPublisher().send(pojoList, ProductInfoState.AUDLI);
		
		return str;
	}
   	
   	/**
   	 * 清算确认
	 * 组件拆分，将估值与产品组件解耦，此处由产品组件提供服务，给估值调用
   	 */
   	public String operQSQRForGz(List<Port> lstPort)  throws ServiceException{
   		return serviceDao.operQSQR(lstPort);
   	}
   	
   	/**
   	 * 清算撤销
	 * 组件拆分，将估值与产品组件解耦，此处由产品组件提供服务，给估值调用 
   	 */
   	public String operQSQXForGz(List<Port> lstPort)  throws ServiceException{
   		return serviceDao.operQSQX(lstPort);
   	}

	/* (非 Javadoc) 
	 * <p>Title: getBusinessRelaPortAdd</p> 
	 * <p>Description: </p> 
	 * @param paraMap
	 * @return 
	 * @see com.yss.ams.product.information.support.modules.ab.port.service.IPortService#getBusinessRelaPortAdd(java.util.HashMap) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public QueryRes getBusinessRangePortAdd(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getBusinessRangePortAdd(paraMap, clazz);
			queryRes.setDataList(dataList);
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("PlanRelaPortAdd");
		} catch (Exception ex) {
			logger.error("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/* (非 Javadoc) 
	 * <p>Title: getBusinessRelaPortBrow</p> 
	 * <p>Description: </p> 
	 * @param paraMap
	 * @return 
	 * @see com.yss.ams.product.information.support.modules.ab.port.service.IPortService#getBusinessRelaPortBrow(java.util.HashMap) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public QueryRes getBusinessRangePortBrow(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getBusinessRangePortBrow(paraMap, clazz);
			queryRes.setDataList(dataList);
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("PlanRelaPortBrow");
		} catch (Exception ex) {
			logger.error("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public HashMap<String, String> queryConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRight> query(HashMap<String, String> customParam) {
		if(null ==  customParam) {
			return serviceDao.queryPortTree();
		} else {
			return serviceDao.queryPortTree(customParam);
		}
	}
	
	@Override
    public void updateDataByPortCode(Port updatePortData, String portCode, Connection conn) {
        serviceDao.updateDataByPortCode(updatePortData, portCode, conn);
    }
   	
	/**
     * 根据组合代码获取组合列表
     * @param portCode
     * @return
     */
	@Override
    public List<Port> getPortListByPortCode(String portCodes){
		return serviceDao.getPortListByPortCode(portCodes);
    }
	
	@Override
	public void delPortRela(String[] portCodes) {
		try {
			serviceDao.delPortRela(portCodes); 
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("删除失败", e);
		}
	}
	
	/**
	 * 根据总账组合代码 查找总账下面的分账组合
	 * STORY #87435 华夏基金-MOM产品需求-个性化部分 add  by sunyanlin 20200525
	 * @param paraMap
	 * @return
	 */
	public QueryRes getMomPortSub(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryMomPortSub(paraMap);
			queryRes.setDataList(dataList);
			queryRes.setMenuId(menuId);
		} catch (Exception ex) {
			logger.error("查询失败", ex);
		}
		return queryRes;
	}
	
	@Override
	public QueryRes getAutomaticSetPortAdd(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString, this.bundleContext);
			paraMap.remove("dataClass");
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.getAutomaticSetPortAdd(paraMap, clazz);
			queryRes.setDataList(dataList);
			// PortService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId, ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("PlanRelaPortAdd");
		} catch (Exception ex) {
			logger.error("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	/**
	 * STORY #75531 【广发基金】支持美元本位币记账组合的人民币转换和核对的需求 (#2 #1 ) 
	 * 查询并行组合
	 * @author zengguowei
	 * @since 2019-07-18
	 * @param paraMap
	 * @return
	 */
	public QueryRes getSourcesBxzhViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.getSourcesBxzhViewDataPojos(paraMap);
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("solutionSet_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public String getCacodeByAccountType(String portCode, String dcCode) {
		return serviceDao.getCacodeByAccountType(portCode,dcCode);
	}

	@Override
	public String getCacodeByAccountType1(String openName, String openNo, String openAddr, String dcCode) {
		return serviceDao.getCacodeByAccountType1(openName,openNo,openAddr,dcCode);
	}
}
