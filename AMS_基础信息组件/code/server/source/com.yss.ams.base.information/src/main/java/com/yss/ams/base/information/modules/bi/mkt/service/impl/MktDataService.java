package com.yss.ams.base.information.modules.bi.mkt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.mkt.admin.MktDataAdmin;
import com.yss.ams.base.information.modules.bi.mkt.dao.MktSqlBuilder;
import com.yss.framework.api.common.co.Mkt;
import com.yss.ams.base.information.support.bi.mkt.service.IMktDataService;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 交易市场数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktDataService implements IMktDataService {
	private MktDataAdmin mktDataAdmin = null;
	MktSqlBuilder mktSqlBuilder = null;
	public MktDataService() {
		mktSqlBuilder = new MktSqlBuilder();
		mktDataAdmin = new MktDataAdmin(DbPoolFactory.getInstance().getPool(),
				mktSqlBuilder);
	}

	/**
	 * 获取所有交易市场设置数据
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return mktDataAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询交易市场关联数据
	 * @param
	 * @return
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = mktDataAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_exchange");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_exchange",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据交易市场代码获取交易市场缓存信息
	 * @return pojo
	 * @param datacode
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			MktCache cache = CacheManager.getInstance().getCache(CacheGroup.MKT);
			Mkt mkt = cache.getCacheByKey(dataCode);
			if (null == mkt){
				mkt = mktDataAdmin.getDataByCode(dataCode);
			}
			return (K) mkt;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据市场类型获取市场列表
	 * @return List
	 * @param types
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return mktDataAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询交易市场关联数据
	 * @param types
	 * @return
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = mktDataAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_exchange");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_exchange"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取交易市场代码转换MAP
	 * 
	 * @return 交易市场代码转换MAP
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return mktDataAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据多个交易市场类型获取交易市场设置
	 * 
	 * @param keys
	 *            市场类型
	 * @return 市场列表
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return mktDataAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，根据多个交易市场类型查询交易市场关联数据
	 * @param types
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = mktDataAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_exchange");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_exchange"));
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

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return mktDataAdmin.getPojoByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return mktDataAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = mktDataAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	
	/**
	 * 取得所有数据（只包含代码和名称
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException{
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			MktCache cache =  CacheManager.getInstance().getCache(CacheGroup.MKT);
			for (Mkt mkt : cache.getCacheList()) {
				if(!map.containsKey(mkt.getC_MKT_CODE())){
					map.put(mkt.getC_MKT_CODE(), mkt.getC_MKT_NAME());
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return map;
	}
	
	/**
	 * 根据市场代码取值
	 * @return
	 */
	public <K extends BasePojo> List<K> getAllDataSqlByKeys(String[] keys)
			throws ServiceException {
		try {
			return mktDataAdmin.getAllDataSqlByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 获取全部市场数据
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByAux() throws ServiceException {
		try {
			return mktDataAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 获取清算机构
	 * @author guohui
	 * STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息
	 * @date 2016-12-09 
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListAux() throws ServiceException {
		try {
			return mktDataAdmin.getAllDataListAux();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:55:31
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return mktDataAdmin.queryByIds(ids, Mkt.class);
	}

	@Override
	public String getAllDataSql() {
		
		return mktSqlBuilder.getAllDataSql();
	}

}
