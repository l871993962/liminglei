package com.yss.ams.base.information.modules.sys.secvar.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.secvar.admin.SecVarDataAdmin;
//import com.yss.ams.base.information.modules.sys.secvar.cache.SecVarCache;
import com.yss.ams.base.information.modules.sys.secvar.dao.SecVarSqlBuilder;
import com.yss.ams.base.information.support.cache.SecVarCache;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarDataService;
//import com.yss.bundle.activator.UcoActivator;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.mvc.service.ServiceAssistance;



//import dataservice.service.ISecVarDataService;

/**
 * 证券品种字典T_S_DA_SEC_VAR  DataService
 *
 */
public class SecVarDataService implements ISecVarDataService {
	private SecVarDataAdmin secVarAdmin = null;

	public SecVarDataService() {
		secVarAdmin = new SecVarDataAdmin(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class),
				new SecVarSqlBuilder());
	}

	/**
	 * 2016-8-22 蒋锦 南方基金性能优化 修改此方法逻辑，方法改为从缓存中获取数据
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		SecVarCache secVarCache = CacheManager.getInstance().getCache(CacheGroup.SECVAR);
		try {
			return (List<K>) secVarCache.getCacheList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 2016-8-22 蒋锦 南方基金性能优化
	 * 
	 * @return 缓存加载数据用此方法
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getAllDataList()
			throws ServiceException {
		try {
			return secVarAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据 并填充QueryRes对象
	 * @return QueryRes对象
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secVarAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_seccategory");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_seccategory",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 先从证券品种缓存SecVarCache中通过dataCode获取数据，如果缓存数据为空，则
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 一条证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
/*	@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			SecVarCache secvarCache = CacheManager.getInstance().getCache(CacheGroup.BASESECVAR);
			SecVar secVar = secvarCache.getCacheByKey(dataCode);
			if (null == secVar){
				secVar = secVarAdmin.getDataByCode(dataCode);
			}
			return (K) secVar;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/
	
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return secVarAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据 证券属性代码 C_DA_CODE数组 获取 符合条件的证券品种字典V_S_DA_SEC_VAR的数据
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return secVarAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据 证券属性代码 C_DA_CODE数组 获取 符合条件的证券品种字典V_S_DA_SEC_VAR的数据
	 * 并填充QueryRes对象
	 * @return QueryRes对象
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secVarAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_seccategory");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_seccategory",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据（只包含证券品种代码 和证券品种名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return secVarAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 所有证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return secVarAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 所有证券品种字典V_S_DA_SEC_VAR数据
	 * 并填充QueryRes对象
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secVarAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_seccategory");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_seccategory",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}
	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 一条证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return secVarAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 通过listKey集合获取缓存中的数据
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		SecVarCache secvarCache = CacheManager.getInstance().getCache(CacheGroup.SECVAR);
		return secvarCache.getKeyConvertMap(listKey);
	}
	
	/**
	 * 更新 时间戳以及获取证券品种字典V_S_DA_SEC_VAR所有的数据
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);

		List<BasePojo> list = null;
		// DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);
		if (timestamp == null || timestamp.equals("")) {
			// 2016-8-22 蒋锦 南方基金性能优化 缓存获取数据更改为采用getAllDataList；
			list = getAllDataList();
			// list = this.getDataList();
		} else {
			list = secVarAdmin.getDataListByTimestamp(timestamp);
		}

		data.setDataList(JsonUtil.toString(list));
		if (list != null && list.size() > 0) {
			data.setTimestamp(t);
		}
		return data;
	}

	/**
	 * 取得所有数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-9
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			SecVarCache cache =  CacheManager.getInstance().getCache(CacheGroup.SECVAR);
			for (SecVar secVar : cache.getCacheList()) {
				if(!hashMap.containsKey(secVar.getC_SEC_VAR_CODE())){
					hashMap.put(secVar.getC_SEC_VAR_CODE(), secVar.getC_SEC_VAR_NAME());
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return hashMap;
	}

	/**
	 * Fixed by huangsq 20160729 BUG #135169 算法公式重新导入后不启作用<br />
	 * 重新加载缓存
	 */
	@Override
	public void reloadCache() {
		SecVarCache cache = CacheManager.getInstance().getCache(CacheGroup.SECVAR);
		cache.reloadData();
	}

	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017Äê3ÔÂ29ÈÕÏÂÎç6:55:31
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return secVarAdmin.queryByIds(ids, SecVar.class);
	}
}
