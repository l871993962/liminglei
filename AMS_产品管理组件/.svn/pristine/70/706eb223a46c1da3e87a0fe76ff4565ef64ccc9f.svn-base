package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.port.admin.PortAdmin;
import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.dataservice.util.enums.BoolEnum;
import com.yss.fast.right.support.right.pojo.UserPostData;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheRefresh;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.commonInfo.pojo.FastPortData;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.task.api.IFastAutomaticService;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.platform.support.dataservice.service.IRightManageDataService;
import com.yss.platform.support.portopervalid.service.IPortOperValidService;
import com.yss.right.constants.RightConstants;
import com.yss.right.pojo.DataRight;

/**
 * <产品基本信息>组合数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@DefaultCacheRefresh(group = CacheGroup.PORT)
public class PortDataService implements IPortDataService {

	private PortAdmin portAdmin = null;
	private final String _defaultMenuId = "pd_portfolio";

	// 日志记录
	private Logger logger = LogManager.getLogger(getClass());
	
	private PortDao serviceDao = null;

	public PortDataService() {
		serviceDao = new PortDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortSqlBuilder());
		portAdmin = new PortAdmin(DbPoolFactory.getInstance().getPool(),
				new PortSqlBuilder());
	}
	
	private List<com.yss.framework.api.common.co.Port> convert2FastListPort(List<Port> portList) {
		String portListJson = JsonUtil.toString(portList);
		if (StringUtil.IsNullOrEmpty(portListJson)) {
			return new ArrayList<com.yss.framework.api.common.co.Port>();
		} else {
			return JsonUtil.toList(portListJson, com.yss.framework.api.common.co.Port.class);
		}
	}

	public <K extends BasePojo> List<K> getAllDataList()
			throws ServiceException {
		try {
			return portAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	public HashMap<String, Port> getPortDataMapWithNullTrCode(){
		try {
			return portAdmin.getPortDataMapWithNullTrCode();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据组合级别加载组合列表，不受权限控制
	 * 
	 * @param dvPortCode
	 *            组合级别
	 * @return
	 */
	public List<BasePojo> getPortListByDvPortCode(String dvPortCode) {
		try {
			return portAdmin.portFilter(false, "", dvPortCode, "");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return portAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getPortTreeViewListCommon() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.getPortTreeView();
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public QueryRes getTreeViewListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.getPortTreeView();
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return portAdmin.portFilterByKeys(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.portFilterByKeys(types);
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.portFilter(true, "", "", "");
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return portAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return portAdmin.portFilterByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BasePojo> getPortDataByKeysNoFilter(String[] keys)
			throws ServiceException {
		try {

			return portAdmin.getPortDataByKeysNoFilter(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.portFilterByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId(_defaultMenuId);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> List<K> getTreeViewList()
			throws ServiceException {
		try {
			return portAdmin.getPortTreeView();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List<Port> getRightManagePortList(HashMap<String, String> paraMap)
			throws ServiceException {
		return null;
	}

	public List<Port> getTreeViewListByCondion(HashMap<String, Object> arg0)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getTreeViewResByCondion(HashMap<String, Object> paraMap)
			throws ServiceException {
		QueryRes res = null;
		List<BasePojo> dataList = null;
		try {
			res = new QueryRes();
			dataList = portAdmin.getPortTreeViewByCondition(paraMap);
			res.setDataList(dataList);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
			res.setMenuId(_defaultMenuId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return res;
	}

	@Override
	public QueryRes getDefaultPort(String ports, String cTrCode)
			throws ServiceException {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.getDefaultUserPort(ports,
					cTrCode);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance
//					.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public List<Port> getListByCodeAndBuildDate(String portCode, Date buildDate)
			throws ServiceException {
		return this.portAdmin.getListByCodeAndBuildDate(portCode, buildDate);
	}

	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public void updateById(Port port) throws ServiceException {
		try {
			this.portAdmin.updateById(port);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public void insert(Port port) throws ServiceException {
		try {
			this.portAdmin.insert(port);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	// public void insertList(List<Port> datalist, Connection conn) {
	// try {
	// this.portAdmin.insertList(datalist, conn);
	// } catch (BusinessException e) {
	// throw new ServiceException(e);
	// }
	// }

	@Override
	public List<Port> queryByCondition(HashMap<String, Object> map)
			throws ServiceException {
		return this.portAdmin.queryByCondition(map);
	}

	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public void antiAudit(Port pojo) throws ServiceException {
		this.portAdmin.antiAudit(pojo);
	}

	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public void audit(Port pojo) throws ServiceException {
		this.portAdmin.audit(pojo);
	}

	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public void deleteById(Port pojo) throws ServiceException {
		try {
			this.portAdmin.deleteById(pojo);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BasePojo> doPortFilter() throws ServiceException {

		try {
			return portAdmin.portFilter(true, "", "", "");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BasePojo> doPortFilter(String isDataRight, String datClass)
			throws ServiceException {
		try {
			return portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					"", "");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException {
		try {
			return portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					dvPortCode, "");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes doPortFilterRes() throws ServiceException {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.portFilter(true, "", "", "");
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance
//					.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public Map<String, String> getBindPortAndPost(String userCode,
			String portCodes) {
		IRightManageDataService rightMangeDataService = HttpServiceFactory
				.getInstance().createService(IRightManageDataService.class);
		return rightMangeDataService.getBindPortAndPost(userCode, portCodes);
	}

	@Override
	public QueryRes doPortFilterRes(String isDataRight, String datClass)
			throws ServiceException {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					"", "");
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance
//					.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					dvPortCode, "");
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance
//					.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode, String trcode) throws ServiceException {
		try {
			return portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					dvPortCode, trcode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trcode) throws ServiceException {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					dvPortCode, trcode);
			queryRes.setDataList(dataList);
//			queryRes.setHeadKeyList(ServiceAssistance
//					.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public List<BasePojo> getPortTreeList(String portTreeType) throws ServiceException{
		try {
			//ArrayList<String> types = getAssetType();//测试使用
			//List<BasePojo> list = getPortTreeList("ht","","04");
			//System.out.println();
			return portAdmin.getPortTreeList(portTreeType);
		} catch (Exception e) {
			logger.log("查询失败", e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<BasePojo> getPortTreeList(String userCode,String postCode, 
			String portTreeType) throws ServiceException{
		try {
			return portAdmin.getPortTreeList(userCode,postCode, portTreeType);
		} catch (Exception e) {
			logger.log("查询失败", e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 获取资产类型--组合树类型
	 * STORY #45114 估值系统支持查询“组合树类型”列表的接口
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ArrayList<String> getAssetType() throws ServiceException {
		return portAdmin.getAssetType();
	}
	
	/**
	 * 获取资产类型--组合树类型
	 * STORY #45114 估值系统，获取公用固定的组合树的类型
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ArrayList<String> getCommonAssetType() throws ServiceException {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("ASS\t资产类型");
//		list.add("ZCTG\t资产类型-托管行");
//		list.add("TGZC\t托管行-资产类型");
		
		return list;
	}

	/**
	 * 根据资产类别获取组合列表（下拉框控件数据源用、带数据权限）
	 * 
	 * Add By Huxingtao 2013-6-3
	 */
	@Override
	public List<BasePojo> getPortListByDatClass(String datClass)
			throws ServiceException {
		List<BasePojo> dataList = null;
		try {
			dataList = portAdmin.portFilter(true, datClass, "", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.log("查询失败", e);
		}

		return dataList;
	}

	/**
	 * 根据用户代码和岗位代码获得组合树 这个方法会根据产品状态过滤组合， 如果productState为null会从数据库中查询产品状态过滤
	 * 不想根据产品状态过滤请调用两个参数的方法
	 * 
	 * @param userCode
	 *            用户代码
	 * @param postCode
	 *            岗位代码 增加字段是否根据产品状态过滤
	 * @return
	 *//*
	public List<BasePojo> getPortListByUserAndPost(String userCode,
			String postCodes, List<String> productState) {
		List<BasePojo> dataList = null;
		try {
			if (userCode == null || userCode.isEmpty()) {
				return null;
			}
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("C_USER_CODE", userCode);
			if (!"".equals(postCodes)) {
				paraMap.put("C_POST_CODE", postCodes);
			}

			if (productState == null || productState.size() == 0) {
//				productState = new SwitchProductStateService().getProdState(
//						userCode, postCodes);
				// modified by HeLiang 2017-06-11 产品信息组件拆分
				// SwitchProductStateService这个实现服务和对应接口还在com.yss.platform项目中
				// 故在com.yss.platform.support新建了ISwitchProductStateDataService接口，供这里实例化使用
				productState = YssServiceFactory.getInstance()
						.createService(ISwitchProductStateDataService.class)
						.getProdState(userCode, postCodes);
			}
			StringBuffer sb = new StringBuffer();
			for (String prodState : productState) {
				sb.append(prodState).append(",");
			}
			StringUtil.delLastSplitMark(sb, ",");
			paraMap.put("C_DV_PROD_STATE", sb.toString());
			dataList = portAdmin.getPortTreeByUserAndPostAndProdState(paraMap);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
		}

		return dataList;
	}*/

	/**
	 * 获取证券品种树 //added by wangzhiye 2014-03-18
	 * 
	 * @param userCode
	 *            用户代码
	 * @return
	 */
	public List<BasePojo> getStockTypeList(String userCode) {
		List<BasePojo> dataList = null;
		try {
			/*
			 * if (userCode == null || userCode.isEmpty()) { return null; }
			 */

			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			// paraMap.put("C_USER_CODE", userCode);

			dataList = portAdmin.getStockTypeTree(paraMap);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
		}

		return dataList;
	}

	/**
	 * 根据用户代码和岗位代码获得组合树
	 * 
	 * @param userCode
	 *            用户代码
	 * @param postCode
	 *            岗位代码
	 * @return
	 */
	public List<BasePojo> getPortListByUserAndPost(String userCode,
			String postCodes) {
		List<BasePojo> dataList = null;
		try {
			if (userCode == null || userCode.isEmpty()) {
				return null;
			}
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("C_USER_CODE", userCode);
			if (postCodes != null && !"".equals(postCodes)) {
				paraMap.put("C_POST_CODE", postCodes);
			}

			dataList = portAdmin.getPortTreeByUserAndPost(paraMap);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
		}

		return dataList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Port> getPortByUserAndPost(String userCode, String postCodes)
			throws ServiceException {
		List<Port> dataList = null;
		try {
			if (userCode == null || userCode.isEmpty()) {
				return null;
			}
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("C_USER_CODE", userCode);
			if (!"".equals(postCodes)) {
				paraMap.put("C_POST_CODE", postCodes);
			}

			dataList = (List) portAdmin.getPortTreeByUserAndPost(paraMap);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
		}

		return dataList;
	}

	/**
	 * 根据用户代码和岗位代码获得组合树
	 * 
	 * @param userCode
	 *            用户代码
	 * @param postCode
	 *            岗位代码
	 * @return 指定用户的指定岗位的组合树
	 */
	public QueryRes getPortResByUserAndPost(String userCode, String postCodes) {
		QueryRes queryRes = new QueryRes();
		try {
			queryRes.setDataList(getPortListByUserAndPost(userCode, postCodes));
//			queryRes.setHeadKeyList(ServiceAssistance
//					.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
		}
		return queryRes;
	}

	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getPortAssTreeListAddForm(
			HashMap<String, Object> paraMap) {
		return (List<K>) portAdmin.getPortAssTreeListAddForm(paraMap);
	}

	public List<Port> getPortInfoList(HashMap<String, Object> paraMap)
			throws ServiceException {
		try {
			return portAdmin.getPortInfoList(paraMap);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Port getPortInfo(String cPortCode) throws ServiceException {
		return portAdmin.getPortInfo(cPortCode);
	}

	@Override
	public Port getPortByPortName(String portName) throws ServiceException {
		return portAdmin.getPortInfoByPortName(portName);
	}

	@Override
	public String getPortCodePSubCount(String portCodeP) {
		return portAdmin.getPortSubCount(portCodeP);
	}

	@Override
	public String insertWithRetInfo(Port port) throws ServiceException {
		try {
			return portAdmin.insertWhithRetInfo(port);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return portAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

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

	/*@Override
	public String operQSQR(List<Port> lstPort) throws ServiceException {
		String retInfo = "";
		try {
//			portAdmin.operQSQR(lstPort);
//			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
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
//			portAdmin.operQSQX(lstPort);
//			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception e) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);
		}

		return retInfo;
	}*/

	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);

		List<BasePojo> list = null;
		// DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//带上时间戳，否则未查到数据时间戳被置空
		if (timestamp == null || timestamp.equals("")) {
			list = this.getAllDataList();
		} else {
			list = portAdmin.getDataListByTimestamp(timestamp);
		}

		data.setDataList(JsonUtil.toString(list));
		if (list != null && list.size() > 0) {
			data.setTimestamp(t);
		}
		return data;
	}

	public List<Port> getAssPort(String trCode) throws Exception {
		return portAdmin.getAssPort(trCode);
	}

	/**
	 * 查询A区群组数据，以树形展示 STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * 
	 * @param trCode
	 * @return
	 */
	public List<Port> getGroupDataTree(String trCode) throws Exception {
		return portAdmin.getGroupDataTree(trCode);
	}
	public List<Port> getGroupDataTree(String trCode, List<String> portList) throws Exception {
		return portAdmin.getGroupDataTree(trCode, portList);
	}

	/**
	 * 查询A区群组数据，以树形展示，无用户和岗位权限
	 * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
	 * @param trCode
	 * @return
	 */
	public List<Port> getGroupDataTreeWithoutRight(String trCode) throws Exception {
		return portAdmin.getGroupDataTreeWithoutRight(trCode);
	}

	/**
	 * 查询A区资产类型数据，以树形展示
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * neil 2019-07-26
	 * @return
	 */
	public List<Port> getAssGroupDataTree(String trCode) throws Exception {
		return portAdmin.getAssGroupDataTree(trCode);
	}
	/**
	 * 检查组合代码是否在群组中已经存在true:已经存在；false:不存在 STORY #16818 产品群组需求 add by chenwenhai
	 * 20140603
	 * 
	 * @param groupCode
	 * @return
	 */
	public String checkPortCode(String portCode) {
		return portAdmin.checkPortCode(portCode);
	}

	@Override
	public Port getPortByAssCode(String assCode) throws ServiceException {
		return portAdmin.getPortByAssCode(assCode);
	}

	/**
	 * Fixed by huangsq 20160811 STORY #26296 [招商证券]估值表发布及获取接口需求
	 */
	public List<Port> getPortByAssCodes(List<String> assCodes) {
		return portAdmin.getPortByAssCodes(assCodes);
	}

	/**
	 * 询下面存在子组合的组合信息 By Jinghehe 2014-7-28 根据用户和用户岗位来加载数据
	 * 
	 * @param userCode
	 * @param postCode
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public QueryRes queryParentPortTreeViewData(String userCode, String postCode)
			throws ServiceException {
		QueryRes res = null;
		List<BasePojo> dataList = null;
		try {
			res = new QueryRes();
			dataList = portAdmin
					.queryParentPortTreeViewData(userCode, postCode);
			res.setDataList(dataList);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId,
					ProductInfoActivator.class));
			res.setMenuId(_defaultMenuId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return res;
	}

	/**
	 * Author : ChenLong Date : 2015-01-28 Status : Add Comment: 获取有权限的组合
	 * 
	 * @param userCode
	 * @param postCode
	 * @param menuId
	 * @return
	 */
	@Override
	public QueryRes queryParentPortTreeViewData(String userCode,
			String postCode, String menuId) {
		QueryRes res = new QueryRes();
		;
		try {
			List<BasePojo> list = portAdmin.queryParentPortTreeViewData(
					userCode, postCode);
			List<BasePojo> dataList = new ArrayList<BasePojo>();
			if (list != null && list.size() > 0) {
				Map<String, String> portCodeMap = getRightPortCodeMap(menuId);
				for (BasePojo basePojo : list) {
					Port port = (Port) basePojo;
					if ("PORT_TYPE".equals(port.getdATA_TYPE())) {
						if (portCodeMap.containsKey(port.getC_PORT_CODE())) {
							dataList.add(basePojo);
						}
					} else {
						dataList.add(basePojo);
					}
				}
			}

			res.setDataList(dataList);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			res.setMenuId(_defaultMenuId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return res;
	}

	@Override
	public List<Port> dueClearedPorts(HashMap<String, String> arg0) {
		return portAdmin.dueClearedPorts(arg0);
	}

	/**
	 * 询产品和组合信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public QueryRes queryProductORPort() throws ServiceException {
		QueryRes res = null;
		List<BasePojo> dataList = null;
		try {
			res = new QueryRes();
			dataList = portAdmin.queryProductORPort();
			res.setDataList(dataList);
//			res.setHeadKeyList(ServiceAssistance.getListHead(_defaultMenuId));
			
			// modified by HeLiang 2017-06-21 STORY #42921 产品信息组件拆分开发
			// PortDataService原来是在Platform平台下面的，所以使用AppContext直接获取的方法是可行的的
			// 但是现在迁移到OSGI组件中，需要使用如下方法，从而通过YssContextFactory获取对应menuId的ListHead
			res.setHeadKeyList(ServiceAssistance.getListHead(
					_defaultMenuId, ProductInfoActivator.class));
			res.setMenuId(_defaultMenuId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return res;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return portAdmin.portFilter(true, "", "", "");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Author : ChenLong Date : 2015-01-28 Status : Add Comment: 过滤获得有权限的组合
	 */
	@Override
	public <K extends BasePojo> List<K> getFilterPortDataForOperRight(
			String menuId) throws YssException {
		Map<String, String> portCodeMap = getRightPortCodeMap(menuId);
		List<K> dataList = portAdmin.portFilter(portCodeMap);
		return dataList;
	}

	public List<BasePojo> getTreePortDataByCodes(String[] keys)
			throws ServiceException {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		pojoList = portAdmin.getTreePortDataByCodes(keys);
		IPortClsService portClsService = HttpServiceFactory.getInstance()
				.createService(IPortClsService.class);
		for (String portCode : keys) {
			List<PortCls> portClsList = portClsService
					.getPortClsByPortCode(portCode);
			for (PortCls portCls : portClsList) {
				Port port = new Port();
				port.setC_PORT_CODE(portCls.getC_PORT_CLS_CODE());
				port.setC_PORT_CODE_P(portCode);
				port.setC_PORT_NAME(portCls.getC_PORT_CLS_NAME());
				port.setC_PORT_NAME_ST(portCls.getC_PORT_CLS_NAME());
				// port.setId(portCls.getId());
				// port.setC_ASS_CODE(portCls.get);
				// port.setC_DAT_CLS(cDATCLS);
				port.setD_CLOSE("9998-12-31");
				port.setD_CLEAR(new Date(0));
				pojoList.add(port);
			}
		}

		return pojoList;
	}

	public List<BasePojo> getAllGroupAndPort() throws Exception {
		try {
			return portAdmin.getAllGroupAndPort();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public List<BasePojo> getAllGroupAndPortNoRight() throws Exception {
		try {
			return portAdmin.getAllGroupAndPortNoRight();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 查询资产类型和资产组合数据
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * neil 2019-07-26
	 * @return
	 */
	public List<BasePojo> getAllAssGroupAndPort() throws Exception {
		try {
			return portAdmin.getAllAssGroupAndPort();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * STORY #47085 【数据权限分配】增加“产品群组”
	 * 获取群组下的所有组合（无权限控制）
	 * @param groupCodes
	 * @return
	 * @throws Exception
	 */
	public FastPortData getAllPortByGroups(List<String> groupCodes) throws Exception {
		FastPortData fastPort = new FastPortData();
		try {
			Map<String, List<com.yss.framework.api.common.co.Port>> fastPortMap = new HashMap<String, List<com.yss.framework.api.common.co.Port>>();
			Map<String,List<Port>> portMap = portAdmin.getAllPortByGroups(groupCodes);
			for (String key : portMap.keySet()) {
				fastPortMap.put(key, convert2FastListPort(portMap.get(key)));
			}
			fastPort.setFastPort(fastPortMap);
			return fastPort;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
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
	public Map<String,List<Port>> getAllAssPortByGroups(List<String> groupCodes) throws Exception {
		try {
			return portAdmin.getAllAssPortByGroups(groupCodes);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据权限或者当前用户所有的总账数据，针对于人民币理财，根据权限查询产品总账 chenyoulong 20141225
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getZzDataList() throws ServiceException {
		try {
			return portAdmin.ZzPortFilter(true, "", "", "");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Author : ChenLong Date : 2015-01-28 Status : Add Comment: 获取有权限的组合集合
	 * 
	 * @param menuId
	 * @return
	 */
	private Map<String, String> getRightPortCodeMap(String menuId) {
		if (menuId == null || "".equals(menuId)) {
			throw new ServiceException("组合控件过滤权限组合时功能代码不能空。");
		}
		/* 用户代码 */
		String userCode = YssContextFactory.getInstance().getLogInfo()
				.getLoggingUserCode();
		/* 岗位代码集合 */
		String postCodes = YssContextFactory.getInstance().getLogInfo()
				.getLoggingPostCode();
		if (postCodes == null || postCodes.equals("")) {
			Logger logger = LogManager.getLogger(getClass());
			logger.error("岗代码为空。");
		}
		/* 绑定岗位与岗位下的组合集合 */
//		IRightManageDataService rightManageDataService = new RightManageDataService();
		
		// modified by HeLiang 2017-06-11 产品信息组件拆分
		// RightManageDataService这个实现服务还在com.yss.platform项目中，故改为用工厂方法实例化
		IRightManageDataService rightManageDataService = YssServiceFactory
				.getInstance().createService(IRightManageDataService.class);
		Map<String, Map<String, String>> postsAndPortsMap = rightManageDataService
				.getBindPostsAndPorts(userCode, postCodes);

		/* 有增删改权限的岗位下的组合集合 */
		Map<String, String> portCodeMap = new HashMap<String, String>();

//		IPortOperValidService portOperValidService = new PortOperValidService();
		
		// modified by HeLiang 2017-06-11 产品信息组件拆分
		// PortOperValidService这个实现服务还在com.yss.platform项目中，故改为用工厂方法实例化
		IPortOperValidService portOperValidService = YssServiceFactory
				.getInstance().createService(IPortOperValidService.class);
		for (String postCode : postsAndPortsMap.keySet()) {
			/* 验证岗位是否有增删改权限 若有收集岗位下的组合集合 */
			if (portOperValidService.isAllowedPost(postCode, menuId)) {
				portCodeMap.putAll(postsAndPortsMap.get(postCode));
			}
		}

		return portCodeMap;
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
		return portAdmin.PortFilter_ReportCenter(isDataRight, "", "", trCode);
	}

	/**
	 * 查询权限数据
	 */
	@Override
	public List<DataRight> queryDataRight(String trCode)
			throws ServiceException {
		List<DataRight> dataRights = new ArrayList<DataRight>();
		try {
			List<BasePojo> basePojos = portAdmin.queryPort(trCode);
			for (BasePojo basePojo : basePojos) {
				DataRight dataRight = new DataRight();
				Port port = (Port) basePojo;
				dataRight.setC_DATA_CODE(port.getC_PORT_CODE());
				dataRight.setC_DATA_NAME(port.getC_PORT_NAME());
				if (dataRight.getC_DATA_NAME() == null
						|| dataRight.getC_DATA_NAME().equals("")) {
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME_ST());
				}
				if (dataRight.getC_DATA_NAME() == null
						|| dataRight.getC_DATA_NAME().equals("")) {
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME_EN());
				}
				dataRight.setC_DATA_TYPE("1");
				dataRight.setC_DATA_CODE_P(port.getC_PORT_CODE_P());
				dataRights.add(dataRight);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataRights;
	}

	@Override
	public List<DataRight> queryDataRight(String trCode, String[] portCodes) {
		List<DataRight> dataRights = new ArrayList<DataRight>();
		try {
			//// 原来的逻辑查不到数据  houjiaqi 20190319
//			List<BasePojo> basePojos = portAdmin.queryPort(trCode, portCodes);
			List<BasePojo> basePojos = serviceDao.getDefaultPort(trCode, portCodes);
			for (BasePojo basePojo : basePojos) {
				DataRight dataRight = new DataRight();
				Port port = (Port) basePojo;
				dataRight.setC_DATA_CODE(port.getC_PORT_CODE());
				dataRight.setC_DATA_NAME(port.getC_PORT_NAME());
				if (dataRight.getC_DATA_NAME() == null
						|| dataRight.getC_DATA_NAME().equals("")) {
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME_ST());
				}
				if (dataRight.getC_DATA_NAME() == null
						|| dataRight.getC_DATA_NAME().equals("")) {
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME_EN());
				}
				dataRight.setC_DATA_TYPE("1");
				dataRight.setAuditState(1);// 所有查询出来的组合的状态都为已审核
				dataRight.setC_DATA_CODE_P(port.getC_PORT_CODE_P());
				dataRights.add(dataRight);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataRights;
	}
	
	/**
	 * 根据参数条件过滤
	 * @param trCode
	 * @param portCodes
	 * @param customParam
	 * @return
	 */
	public List<DataRight> queryDataRight(String trCode, String[] portCodes, HashMap<String,String> customParam) {
		List<DataRight> dataRights = new ArrayList<DataRight>();
		try {
			List<BasePojo> basePojos = serviceDao.getDefaultPort(trCode, portCodes);
			for (BasePojo basePojo : basePojos) {
				DataRight dataRight = new DataRight();
				Port port = (Port) basePojo;
				dataRight.setC_DATA_CODE(port.getC_PORT_CODE());
				dataRight.setC_DATA_NAME(port.getC_PORT_NAME());
				if (dataRight.getC_DATA_NAME() == null
						|| dataRight.getC_DATA_NAME().equals("")) {
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME_ST());
				}
				if (dataRight.getC_DATA_NAME() == null
						|| dataRight.getC_DATA_NAME().equals("")) {
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME_EN());
				}
				dataRight.setC_DATA_TYPE("1");
				dataRight.setAuditState(1);// 所有查询出来的组合的状态都为已审核
				dataRight.setC_DATA_CODE_P(port.getC_PORT_CODE_P());
				dataRights.add(dataRight);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataRights;
	}

	/**
	 * 根据编号和状态查询
	 * 
	 * @Title queryPort
	 * @Description 根据编号和状态查询
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午6:50:24
	 * @param portCodes
	 * @param productState
	 * @return
	 * @return List<Port>
	 */
	@Override
	public List<Port> queryPort(List<String> portCodes, List<String> productState) {
		return portAdmin.queryPort(portCodes, productState);
	}
	
	/**
	 * 根据编号和状态查询
	 * 
	 * @Title queryPort
	 * @Description 根据编号和状态查询
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午6:50:24
	 * @param portCodes
	 * @param productState
	 * @return
	 * @return List<Port>
	 */
	@Override
	public HashMap<String, String> queryPortMap(List<String> portCodes, List<String> productState) {
		return portAdmin.queryPortMap(portCodes, productState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Port> queryByIds(String ids) {
		return portAdmin.querybyIds(ids, Port.class);
	}

	/**
	 * 根据属性标识 获取数据对象列表中所有该属性的值
	 * <产品信息组件拆分>
	 * 
	 * @Title getDataPropertiesByCode
	 * @Description 根据属性标识 获取数据对象列表中所有该属性的值
	 * @author HeLiang@ysstech.com
	 * @date 2017年6月12日下午3:47:24
	 * @param propertyCode
	 * @return
	 * @return List<K>
	 */
	@Override
	public List<Object> getDataPropertiesListByCode(String propertyCode)
			throws ServiceException {
		try {
			return portAdmin.getFilteredPortPropertyByCode(propertyCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	

	 /*从框架移过来的接口*/
	 /**
	  * 获得组合树
	  * @param userCode 用户代码
	  * @param postCode 岗位代码
	  * @return
	  */
	@Override
	 public List<BasePojo> getPortTreeList() {
	  List<BasePojo> dataList = null;
	  try {
	   HashMap<String, Object> paraMap = new HashMap<String, Object>();
	   dataList = portAdmin.getPortTreeByUserAndPost(paraMap);
	  } catch (Exception e) {
	   logger.log("查询失败", e);
	  }
	  return dataList;
	 }
	 
	 /*从框架移过来的接口*/
	 /**
	  * 获取证券品种树
	  * @return
	  */
	@Override
	 public List<BasePojo> getStockTypeList() {
	  List<BasePojo> dataList = null;
	  try {
	   HashMap<String, Object> paraMap = new HashMap<String, Object>();
	   dataList = portAdmin.getStockTypeTree(paraMap);
	  } catch (Exception e) {
	   logger.log("查询失败", e);
	  }
	  return dataList;
	 }


	@Override
	public List<Port> doPortFilterPort() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	 
	@Override
	public <K extends BasePojo> List<K> getDataListByCodes(String codes)
			throws ServiceException {
		try {
			return portAdmin.getDataListByCodes(codes);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 查询子组合
	 */
	@Override
	public List<BasePojo> getUnitLayerPort(String[] protPCode)
			throws ServiceException {
		List<BasePojo> dataList = null;
		try {
			  dataList = portAdmin.getUnitLayerPort(protPCode);
		 } catch (Exception e) {
		  logger.log("查询失败", e);
		 }
		 return dataList;
	}
	
	/**
	 * 获取当前已关联流程的组合
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	@Override
	public <K extends BasePojo> List<K> getRelevancePort() {
		List<String> list = new ArrayList<String>();
		List<BasePojo> PortList = new ArrayList<BasePojo>();
		Set<String> set = new HashSet<String>();
		try {
			IFASTDataAuthorityService service = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
			String userCode = ContextFactory.getContext().getUserCode();
			List<UserPostData> userPostDatas =service.getUserDataRightByUserAndType(userCode,RightConstants.portType);
			if(userPostDatas !=null && !userPostDatas.isEmpty()){
				for (UserPostData upd : userPostDatas) {
					set.add(upd.getC_DATA_CODE());
				}
			}
			IFastAutomaticService automaticService = YssServiceFactory.getInstance().createService(IFastAutomaticService.class);
			list = automaticService.getFlowPort(new ArrayList<String>(set));
			//list = portAdmin.getFlowPort(set.toArray(new String[set.size()]));
			if(list != null && !list.isEmpty()){
				PortList = getPortListByCodes(list.toArray(new String[list.size()]));
			}
		} catch (Exception e) {
			logger.log("获取关联流程的组合失败", e);
			//e.printStackTrace();
		}
		return (List<K>)PortList;
	}
	
	/**
	 * 根据组合代码查询组合
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public <K extends BasePojo> List<K> getPortListByCodes(String[] codes)
			throws ServiceException {
		try {
			return portAdmin.getPortListByCodes(codes);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trcode, List<String> dataRights) throws ServiceException {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = portAdmin.portFilter(
					isDataRight.equals(BoolEnum._true.toString()), datClass,
					dvPortCode, trcode, dataRights);
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance
					.getListHead(_defaultMenuId));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(_defaultMenuId);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("查询失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 根据资产树节点获取组合树MAP（没有数据权限过滤）
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public PortCacheData getPortDataMapByTrCode(String trCode) throws ServiceException{
		try {
			HashMap<String, Port> map =  portAdmin.getPortDataMapByTrCode(trCode);
			PortCacheData portCacheData = new PortCacheData();
			portCacheData.setPortData(map);
			return portCacheData;
		} catch (Exception ex) {
			logger.log("查询失败", ex);
			throw new ServiceException(ex);
		}
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
		return serviceDao.getAllPortData();
	}
	
	@Override
	public List<Port> queryPortByPortCode(String[] keys)  throws ServiceException {
		try {
			return portAdmin.queryPortByPortCode(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * STORY #69369 易方达：【自动化】关于流程管理组合维护的需求  baoqiaolin 20190910
	 */
	public List<Port> PortFilter_ByTrCode_YFP(boolean isDataRight,
			String datClass, String dvPortCode, String trCode) throws Exception {
		return portAdmin.PortFilter_ByTrCode_YFP(isDataRight, "", "", trCode);
	}
	
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 */
	@Override
	public List<AssetsTree_A> queryAssetTreeWithLeafNode() throws Exception {
		return portAdmin.queryAssetTreeWithLeafNode();
	}
	
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 */
	@Override
	public List<Port> getTreePortByCode(boolean isDataRight, String trCode, String trCodeR) throws Exception {
		return portAdmin.getTreePortByCode(isDataRight, trCode, trCodeR);
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
    	return portAdmin.queryPortByTypeCondition(type, codes, isDataRight);
    }
}
