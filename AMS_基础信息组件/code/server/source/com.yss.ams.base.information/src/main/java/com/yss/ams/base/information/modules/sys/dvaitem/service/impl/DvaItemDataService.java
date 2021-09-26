package com.yss.ams.base.information.modules.sys.dvaitem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.dvaitem.admin.DvaItemDataAdmin;
import com.yss.ams.base.information.support.cache.DvaItemCache;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.platform.support.dataservice.sqlbuilder.DvaItemSqlBuilder;

//import dataservice.comm.pojo.DvaItem;
//import dataservice.service.IDvaItemDataService;

/**
 * 核算业务项字典T_S_DVA_ITEM DataService
 *
 */
public class DvaItemDataService implements IDvaItemDataService {
	private DvaItemDataAdmin dvaItemAdmin = null;

	public DvaItemDataService() {
		dvaItemAdmin = new DvaItemDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new DvaItemSqlBuilder());
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	public List<DvaItem> getDataList() throws ServiceException {
		try {
			return dvaItemAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据 并初始化QueryRes对象
	 * @return 初始化的QueryRes对象
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dvaItemAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_dvaitem");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dvaitem",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 先从核算业务项缓存中根据ID取数据，缓存为空则
	 * 根据核算项目代码C_DVA_ITEM_CODE获取核算业务项字典T_S_DVA_ITEM的一条数据
	 * @return
	 */
/*	@SuppressWarnings("unchecked")
	public DvaItem getDataByCode(String dataCode)
			throws ServiceException {
		try {
			DvaItemCache dvaitemCache = CacheManager.getInstance().getCache(CacheGroup.BASEDVAITEM);
			DvaItem dvaItem = dvaitemCache.getCacheByKey(dataCode);
			if(null == dvaItem){
				dvaItem = dvaItemAdmin.getDataByCode(dataCode);
			}
			return dvaItem;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public DvaItem getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return dvaItemAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 更新缓存
	 * add by qkw 2017/5/24
	 */
	public void doUpdate(CacheRefreshInfo cacheRefreshInfo)
			throws ServiceException {
		try {
			DvaItemCache dvaitemCache = CacheManager.getInstance().getCache(CacheGroup.DVAITEM);
			dvaitemCache.update(cacheRefreshInfo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return dvaItemAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据，并初始化QueryRes对象
	 * @return
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dvaItemAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_dvaitem");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dvaitem",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据（只包含核算项目代码，核算项目名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return dvaItemAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return dvaItemAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据，并初始化QueryRes对象
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dvaItemAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_dvaitem");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dvaitem",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 *  获取核算业务项字典T_S_DVA_ITEM的所有数据树形结构
	 * @return
	 */
	public List<DvaItem> getTreeViewForDayfItems() throws ServiceException {
		List<DvaItem> dvaItemList = new ArrayList<DvaItem>();
		try {
			dvaItemList = dvaItemAdmin.getTreeViewForDayfItems();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return dvaItemList;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 在核算业务项缓存中根据键值获取单个值信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		DvaItemCache dvaItemCache = CacheManager.getInstance().getCache(CacheGroup.DVAITEM);
		return (K)dvaItemCache.getCacheByKey(pojoCode);
	}

	/**
	 * 在核算业务项缓存中根据键值获取单个值信息，（只包含核算项目代码，核算项目名称）
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		DvaItemCache dvaItemCache = CacheManager.getInstance().getCache(CacheGroup.DVAITEM);
		return dvaItemCache.getKeyConvertMap(listKey);
	}

	/**
	 * 根据时间戳获取更新核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<?> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//带上时间戳，
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = dvaItemAdmin.getDataListByTimestamp(timestamp);
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
	 * @date 2017年3月29日下午6:47:51
	 * @param ids
	 * @param clazz
	 * @return
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return dvaItemAdmin.queryByIds(ids, DvaItem.class);
	}
}
