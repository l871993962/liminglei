package com.yss.ams.base.information.modules.sys.dai.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.dai.admin.AccProAdmin;
//import com.yss.ams.base.information.modules.sys.dai.cache.DaiCache;
import com.yss.ams.base.information.modules.sys.dai.dao.AccProSqlBuilder;
import com.yss.ams.base.information.support.cache.DaiCache;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IAccProDataService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.cache.DaiCache;
//import com.yss.dict.dai.admin.AccProAdmin;
//import com.yss.dict.dai.dao.AccProSqlBuilder;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

//import dataservice.service.IAccProDataService;
//import dataservice.comm.pojo.Dai;

/**
 * 核算项目字典表T_S_DAI_ITEM service类 
 *
 */
public class AccProDataService extends ServiceBus<AccProDataService> implements
		IAccProDataService {
	private AccProAdmin accProAdmin = null;

	public AccProDataService() {
		accProAdmin = new AccProAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new AccProSqlBuilder());
	}

	/**
	 * 先从核算项目缓存DaiCache中通过键值dataCode取数据，若缓存中无数据，则从数据库中
	 * 查询指定 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dataCode 核算项目代码C_DAI_CODE值
	 * @return
	 */
/*	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			DaiCache daiCache = CacheManager.getInstance().getCache(CacheGroup.BASEDAI);
			Dai actItemBean = daiCache.getCacheByKey(dataCode);
			if(null == actItemBean){
				actItemBean = accProAdmin.getDataByCode(dataCode);
			}
			return (K) actItemBean;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/
	
	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return accProAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据,并对结果按 C_DV_KM_CLS,N_ORDER进行排序
	 * @return
	 */
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return accProAdmin.getDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataList(String types,
			HashMap<String, String> paraMap) throws ServiceException {
		return null;
	}

	/**
	 * 查询 符合条件sqlCond的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param sqlCond 查询条件
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListBySqlCond(String sqlCond)
			throws ServiceException {
		try {
			return accProAdmin.getDataListBySqlConds(sqlCond);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param types 科目类别C_DV_KM_CLS值组成的数组
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String types)
			throws ServiceException {
		try {
			return accProAdmin.getDataListByTypes(types.split(","));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryRes(String types, HashMap<String, String> paraMap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getQueryResBySqlCond(String sqlCond) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据，并初始化QueryRes对象
	 * @param types 科目类别C_DV_KM_CLS值组成的数组
	 * @return
	 */
	public QueryRes getQueryResByTypes(String types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList;
		try {
			dataList = accProAdmin.getDataListByTypes(types.split(","));
			res.setDataList(dataList);
			res.setHeadKeyList(ServiceAssistance.getListHead("base_daidictquy",InformationActivator.class));
			res.setMenuId("accProDataService");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public QueryRes getTreeViewListRes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param types 科目类别C_DV_KM_CLS值组成的数组
	 * @return 指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据集合
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return accProAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据 并用此初始化QueryRes对象
	 * @param types 科目类别C_DV_KM_CLS值组成的数组
	 * @return 指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据 初始化的QueryRes对象
	 */
	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList;
		try {
			dataList = accProAdmin.getDataListByTypes(types);
			res.setDataList(dataList);
			res.setHeadKeyList(ServiceAssistance.getListHead("base_daidictquy",InformationActivator.class));
			res.setMenuId("accProDataService");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据,并对结果按 C_DV_KM_CLS,N_ORDER进行排序 并用此初始化QueryRes对象
	 * @return 所有的核算项目字典表T_S_DAI_ITEM数据 初始化的QueryRes对象
	 */
	@Override
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		try {
			List<BasePojo> dataList;
			dataList = accProAdmin.getDataList();
			res.setDataList(dataList);
			res.setHeadKeyList(ServiceAssistance.getListHead("base_daidictquy",InformationActivator.class));
			res.setMenuId("accProDataService");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 查询某些 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param keys 核算项目代码C_DAI_CODE值组成的数组
	 * @return
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return accProAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询某些 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据 并用此初始化QueryRes对象
	 * @param keys 核算项目代码C_DAI_CODE值组成的数组
	 * @return 某些 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据 初始化的QueryRes对象
	 */
	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList;
		try {
			dataList = accProAdmin.getDataListByKeys(keys);
			res.setDataList(dataList);
			res.setHeadKeyList(ServiceAssistance.getListHead("base_daidictquy",InformationActivator.class));
			res.setMenuId("accProDataService");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM的核算项目代码C_DAI_CODE和核算项目名称C_DAI_NAME组成的集合
	 * @return
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return accProAdmin.getKeyConvertMap();
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param kmCls 科目类别C_DV_KM_CLS的值数组
	 * @return
	 */
	@Override
	public List<BasePojo> getAccProDataByKmCls(String[] kmCls) throws ServiceException {
		return accProAdmin.getAccProDataByKmCls(kmCls);
	}
	
	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 在核算项目缓存DaiCache中，根据提供的键值，获取对应键的Dai对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		DaiCache daiCache = CacheManager.getInstance().getCache(CacheGroup.DAI);
		return (K)daiCache.getCacheByKey(pojoCode);
	}

	/**
	 * 根据键获取对应名称字典
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		DaiCache daiCache = CacheManager.getInstance().getCache(CacheGroup.DAI);
		return daiCache.getKeyConvertMap(listKey);
	}
	
	/**
	 * 更新时间戳
	 * @return 更新时间戳之后的Data对象
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//把时间戳带进去
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = accProAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	
	/**
	 * 根据IDs查询数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:46:24
	 * @param ids
	 * @param clazz
	 * @return
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return accProAdmin.queryByIds(ids, Dai.class);
	}
}
