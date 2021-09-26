package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.assetsTree_a.service.impl.AssetsTree_AService;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.pojo.Port_A;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortADataService;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.context.ContextFactory;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.platform.support.dataservice.service.IRightManageDataService;
import com.yss.platform.support.portopervalid.service.IPortOperValidService;

/**
 * <产品基本信息>A区组合数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortADataService implements IPortADataService {

	private final String _defaultMenuId = "pd_portfolio_A";
	protected String _rootMark = "[root]";
	protected String _Port = "PORT_TYPE";
	protected String _Asset = "ASS_TYPE";

	private AssetsTree_AService oftenUsePortService = null;
	
	private Logger logger = LogManager.getLogger(PortADataService.class);

	public PortADataService() {
	}

	/**
	 * Author : ChenLong
	 * Date   : 2015-01-28
	 * Status : Add
	 * Comment: 组合权限过滤
	 * @param isDataRight
	 * @param datClass
	 * @param dvPortCode
	 * @param trCode
	 * @param portCodeMap
	 * @return
	 * @throws Exception
	 */
	private <T extends BasePojo> List<T> filterPortDataForOperRight(boolean isDataRight,
			String datClass, String dvPortCode, String trCode,Map<String,String> portCodeMap) throws Exception {
		List<T> dataList = new ArrayList<T>();
		List<String> dataRights = getDataRightList();
		HashMap<String, Port_A> dataMap = null;
		// 获取数据
		dataMap = getDefaultPortMap(dataRights, trCode);
		HashMap<String, Port_A> rsMap = new HashMap<String, Port_A>();
		Iterator<Port_A> dataIt = null;
		Port_A port = null;
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
				
				if(!portCodeMap.containsKey(port.getC_PORT_CODE())){
					continue;
				}

				setRsMap(port.getC_PORT_CODE(), dataMap, rsMap);
			}
			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}
	
	public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode) throws Exception {
		List<T> dataList = new ArrayList<T>();
		List<String> dataRights = getDataRightList();
		HashMap<String, Port_A> dataMap = null;
		// 获取数据
		dataMap = getDefaultPortMap(dataRights, trCode);
		HashMap<String, Port_A> rsMap = new HashMap<String, Port_A>();
		Iterator<Port_A> dataIt = null;
		Port_A port = null;
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
			// 将最终结果转为列表
			rsMapToList(rsMap, dataList);
		}

		return dataList;
	}

	@SuppressWarnings("unchecked")
	private <T extends BasePojo> void rsMapToList(
			HashMap<String, Port_A> rsMap, List<T> dataList) {
		Iterator<Port_A> rsIt = rsMap.values().iterator();
		while (rsIt.hasNext()) {
			dataList.add((T) rsIt.next());
		}
	}

	private void setRsMap(String portCode, HashMap<String, Port_A> dataMap,
			HashMap<String, Port_A> rsMap) {
		Port_A port = dataMap.get(portCode);

		if (port != null) {
			if (!rsMap.containsKey(port.getC_PORT_CODE())) {
				rsMap.put(port.getC_PORT_CODE(), port);
			}

			if (!_rootMark.equals(port.getC_PORT_CODE_P())) {
				setRsMap(port.getC_PORT_CODE_P(), dataMap, rsMap);
			}
		}

	}

	private boolean isLeafPort(Port_A port) {
		boolean isLeaf = false;

		if (_Port.equals(port.getdATA_TYPE())) {
			isLeaf = true;
		}

		return isLeaf;
	}

	private boolean checkDvPortCode(String dvPortCode, Port_A port) {
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

	private boolean checkDatClass(String datClass, Port_A port) {
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

	private boolean dataRightCheck(boolean isDataRight, Port_A port,
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

	@Override
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trCode) {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portFilter(true, datClass, dvPortCode, trCode);
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}
	
	
	/**
	 * Author : ChenLong
	 * Date   : 2015-01-28
	 * Status : Add
	 * Comment: 组合权限过滤
	 */
	@Override
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trCode,String menuId) {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			IPortOperValidService portOperValidService = YssServiceFactory.getInstance().createService(IPortOperValidService.class);
			Map<String,String> portCodeMap = portOperValidService.getRightPortCodeMap(menuId);
			pojoList = filterPortDataForOperRight(true, datClass, dvPortCode, trCode, portCodeMap);
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 转换组合类型
	 * 
	 * @param list
	 * @return
	 */
	private Port_A port2portA(Port port) {
		Port_A portA = new Port_A();
		portA.setC_DAT_CLS(port.getC_DAT_CLS());
		portA.setC_ASS_CODE(port.getC_ASS_CODE());
		portA.setC_DAT_CODE(port.getC_DAT_CODE());
		portA.setC_DV_PORT_CODE(port.getC_DV_PORT_CODE());
		portA.setC_PORT_CODE(port.getC_PORT_CODE());
		portA.setC_PORT_CODE_P(port.getC_PORT_CODE_P());
		portA.setC_PORT_NAME(port.getC_PORT_NAME());
		portA.setC_PORT_NAME_ST(port.getC_PORT_NAME_ST());
		portA.setdATA_TYPE(port.getdATA_TYPE());
		return portA;
	}

	private HashMap<String, Port_A> getDefaultPortMap(List<String> rights,
			String trCode) throws Exception {
		HashMap<String, Port_A> defaultPortMap = new HashMap<String, Port_A>();
		IPortDataService portService = YssServiceFactory.getInstance()
				.createService(IPortDataService.class);
		PortCache portCache = CacheManager.getInstance().getCache(
				CacheGroup.PORT);
		HashMap<String, Port> map = portCache.getPortByRight(rights);
		List<Port> list = portService.getAssPort(trCode);
		
		if(list == null) {
			return defaultPortMap;
		}
		
		for (int i = 0; i < list.size(); i++) {
//			if (pojo.getdATA_TYPE().equals(_Port) && trCode != null/*	&& !trCode.isEmpty()*/) {
//				Port pojo1 = map.get(pojo.getC_PORT_CODE());
//				if (pojo1 != null) {
//					pojo1.setC_PORT_CODE_P(pojo.getC_PORT_CODE_P());
//					pojo = pojo1;
//					//if (pojo != null) {
//						Port_A port = port2portA(pojo);
//						defaultPortMap.put(port.getC_PORT_CODE(), port);
//					//}
//				}
//			}
			
			Port pojo = list.get(i);
			if (pojo.getdATA_TYPE().equals(_Port) && trCode != null	/*&& !trCode.isEmpty()*/) {
				Port pojo1 = map.get(pojo.getC_PORT_CODE());
				if (pojo1 != null) {
					pojo1.setC_PORT_CODE_P(pojo.getC_PORT_CODE_P());
					pojo = pojo1;
				}
			}
			
			if (pojo != null) {
				Port_A port = port2portA(pojo);
				defaultPortMap.put(port.getC_PORT_CODE(), port);
			}
		}

		return defaultPortMap;
	}

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
			if(postCode == null || postCode.equals("")){
				Logger logger = LogManager.getLogger(getClass());
				logger.error("岗代码为空。");
			}
		}
		try {
			IRightManageDataService rms = YssServiceFactory.getInstance()
					.createService(IRightManageDataService.class);
			list = rms.getUserDataRight(userCode, postCode);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("A区组合模块：根据权限查询组合失败", e);
		}
		return list;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Port_A> getAssList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveToOftenUsePort(List<BasePojo> basePojoList) {
		String returnInfo = "";
		try {
			if (null != basePojoList && !basePojoList.isEmpty()) {
				// 将常用组合保存到资产树形结构表中T_P_AB_ASS_TR
				getOftenUsePortService();
				oftenUsePortService.deleteAndInsert(changePojoList(basePojoList));
				returnInfo = "sucess";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("A区组合模块：将常用组合保存到资产树形结构表中失败", e);
		}
		return returnInfo;
	}

	@Override
	public List<String> getOftenUsePortList() {
		getOftenUsePortService();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_DV_TR", "PORTGROUP");
		List<String> portCodeList = oftenUsePortService
				.getOftenUsePortList(paraMap);
		// QueryRes query = doPortFilterRes("true", "", "","");
		// List<BasePojo> OftenUsePortList = new ArrayList<BasePojo>();
		// for(BasePojo pojo : leftBasePojo){
		// Port_A portA = (Port_A) pojo;
		// if(portCodeList.contains(portA.getC_PORT_CODE())){
		// OftenUsePortList.add(portA);
		// }
		// }
		// leftBasePojo.setDataList(OftenUsePortList);
		return portCodeList;
	}

	@Override
	public String deleteOftenUsePort(List<BasePojo> basePojoList) {
		String returnInfo = "";
		if (null != basePojoList && !basePojoList.isEmpty()) {
			// 将产品从常用产品中删除
			getOftenUsePortService();
			oftenUsePortService
					.deleteOftenUsePortByCode(changePojoList(basePojoList));
			returnInfo = "sucess";
		}
		return returnInfo;
	}

	private void getOftenUsePortService() {
		try {
			if (null == oftenUsePortService) {
				oftenUsePortService = new AssetsTree_AService();
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("A区组合模块：查询常用组合失败", e);
		}

	}

	private List<BasePojo> changePojoList(List<BasePojo> portAList) {
		List<BasePojo> assetList = new ArrayList<BasePojo>();
		for (BasePojo basePojo : portAList) {
			Port_A portA = (Port_A) basePojo;
			AssetsTree_A oftenUsePort = new AssetsTree_A();
			oftenUsePort.setC_TR_CODE(portA.getC_PORT_CODE()); // 结构代码中存放组合代码
			oftenUsePort.setC_DV_TR("PORTGROUP"); // 分类规则 保存为群组类型
			if (portA.getC_PORT_NAME() == null
					|| portA.getC_PORT_NAME().trim().length() == 0) {
				oftenUsePort.setC_TR_NAME(" "); // 结构名称中保存组合名称
			}else{
				oftenUsePort.setC_TR_NAME(portA.getC_PORT_NAME_ST()); // 结构名称中保存组合名称
			}
			oftenUsePort.setC_TR_CODE_P(portA.getC_PORT_CODE_P()); // 上级结构代码中存放组合上级代码
			oftenUsePort.setC_TR_CODE_R(" ");
			oftenUsePort.setAuditState(1);
			oftenUsePort.setModifier(" ");
			oftenUsePort.setModifyDate(" ");
			assetList.add(oftenUsePort);
		}
		return assetList;
	}
	
	// @Override
	// public QueryRes doPortFilterRes(String isDataRight, String datClass,
	// String dvPortCode, String trCode) {
	// QueryRes queryRes = new QueryRes();
	// List<String> lstAccType = new ArrayList<String>();
	// try {
	// List<String> listPortP = new ArrayList<String>();
	// List<BasePojo> dataList = new ArrayList<BasePojo>();
	// HashMap<String, BasePojo> hashmap = new HashMap<String, BasePojo>();
	//
	// List<String> lstPortright = getDataRightList();
	// // //根据资产结构树过滤add by weijj 20140404
	// // lstPortright = filter(trCode,lstPortright);
	//
	// PortCache portCache = CacheManager.getInstance().getCache(
	// CacheGroup.PDPORT);
	// for (String sPort : lstPortright) {
	// if (!hashmap.containsKey(sPort)) {
	// Port port = portCache.getCacheByKey(sPort);
	// if (port != null && check(port, datClass)) {
	// Port_A portA = new Port_A();
	// portA.setC_DAT_CLS(port.getC_DAT_CLS());
	// portA.setC_ASS_CODE(port.getC_ASS_CODE());
	// portA.setC_DAT_CODE(port.getC_DAT_CODE());
	// portA.setC_DV_PORT_CODE(port.getC_DV_PORT_CODE());
	// portA.setC_PORT_CODE(port.getC_PORT_CODE());
	// portA.setC_PORT_CODE_P(port.getC_PORT_CODE_P());
	// portA.setC_PORT_NAME(port.getC_PORT_NAME());
	// portA.setC_PORT_NAME_ST(port.getC_PORT_NAME_ST());
	// portA.setdATA_TYPE(port.getdATA_TYPE());
	// hashmap.put(portA.getC_PORT_CODE(), portA);
	// if (portA.getC_PORT_CODE_P() == null
	// || "".equals(portA.getC_PORT_CODE_P().trim())) {
	// portA.setC_PORT_CODE_P(portA.getC_DAT_CODE());
	// } else {
	// if (!listPortP.contains(portA.getC_PORT_CODE_P())) {
	// listPortP.add(portA.getC_PORT_CODE_P());
	// }
	// }
	//
	// if (!lstAccType.contains(portA.getC_DAT_CODE())) {
	// lstAccType.add(portA.getC_DAT_CODE());
	// }
	// }
	// }
	// }
	// // 加载组合父级信息
	// for (String portP : listPortP) {
	// if (!hashmap.containsKey(portP)) {
	// Port port = portCache.getCacheByKey(portP);
	// if (port != null && check(port, datClass)) {
	// Port_A portA = new Port_A();
	// portA.setC_DAT_CLS(port.getC_DAT_CLS());
	// portA.setC_ASS_CODE(port.getC_ASS_CODE());
	// portA.setC_DAT_CODE(port.getC_DAT_CODE());
	// portA.setC_DV_PORT_CODE(port.getC_DV_PORT_CODE());
	// portA.setC_PORT_CODE(port.getC_PORT_CODE());
	// portA.setC_PORT_CODE_P(port.getC_PORT_CODE_P());
	// portA.setC_PORT_NAME(port.getC_PORT_NAME());
	// portA.setC_PORT_NAME_ST(port.getC_PORT_NAME_ST());
	// portA.setdATA_TYPE(port.getdATA_TYPE());
	// if (portA.getC_PORT_CODE_P() == null
	// || "".equals(portA.getC_PORT_CODE_P().trim())) {
	// portA.setC_PORT_CODE_P(portA.getC_DAT_CODE());
	// hashmap.put(portA.getC_PORT_CODE(), portA);
	// }
	//
	// if (!lstAccType.contains(portA.getC_DAT_CODE())) {
	// lstAccType.add(portA.getC_DAT_CODE());
	// }
	// }
	// }
	// }
	// // 加载资产树形结构信息
	// List<AccType> listAccType = portCache.getAccType(lstAccType);
	// List<BasePojo> accTypeList = new ArrayList<BasePojo>();
	// for (AccType acc : listAccType) {
	// Port_A portA = new Port_A();
	// portA.setC_PORT_CODE(acc.getC_DAT_CODE());
	// portA.setC_DAT_CODE(acc.getC_DAT_CODE());
	// portA.setC_DV_PORT_CODE(" ");
	// portA.setC_PORT_CODE_P(acc.getC_DAT_CODE_P());
	// portA.setC_PORT_NAME(acc.getC_DAT_NAME());
	// portA.setC_PORT_NAME_ST(acc.getC_DAT_NAME());
	// portA.setdATA_TYPE("ASS_TYPE");
	// portA.setC_DAT_CLS(acc.getC_DAT_CODE());
	// accTypeList.add(portA);
	// }
	// dataList.addAll(0, accTypeList);
	// dataList.addAll(hashmap.values());
	// queryRes.setDataList(dataList);
	// queryRes.setHeadKeyList(ServiceAssistance
	// .getListHead(_defaultMenuId));
	// queryRes.setOperRes(MvcConstant._Success);
	// queryRes.setMenuId(_defaultMenuId);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// queryRes.setOperRes(MvcConstant._Fault);
	// }
	// return queryRes;
	// }
}
