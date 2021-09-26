package com.yss.ams.base.information.modules.bi.hdaygroup.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.hdaygroup.admin.HDayDataAdmin;
import com.yss.ams.base.information.modules.bi.hdaygroup.dao.HdayGroupSqlBuilder;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHDayDataService;
import com.yss.ams.base.information.support.cache.HDayCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 节假日群类型数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class HDayDataService implements IHDayDataService {
	private HDayDataAdmin hDayPairAdmin = null;

	public HDayDataService() {
		hDayPairAdmin = new HDayDataAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new HdayGroupSqlBuilder());
	}
	
	/**
	 * 查询所有节假日群
	 * @return 节假日群列表
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return hDayPairAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询节假日群类型关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = hDayPairAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_holidays_A");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_holidays_A",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据节假日群代码获取节假日群实体
	 * 
	 * @param code
	 *            节假日群代码
	 * @return 节假日群实体
	 * @throws Exception
	 */
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return hDayPairAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据多个节假日群代码获取节假日群实体
	 * 
	 * @param types
	 *            节假日群代码
	 * @return 节假日群实体列表
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return hDayPairAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询节假日群类型关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = hDayPairAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_holidays_A");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_holidays_A",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取代码转换MAP
	 * 
	 * @return 代码名称转换MAP<"hdayCode",hdayName>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return hDayPairAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return hDayPairAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询节假日群类型关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = hDayPairAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_holidays_A");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_holidays_A",InformationActivator.class));
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
	 * 根据节假日群类型代码获取节假日缓存
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return hDayPairAdmin.getPojoByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return hDayPairAdmin.getKeyConvertMap(listKey);
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
			list = hDayPairAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	
	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:52:01
	 * @param ids
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return hDayPairAdmin.queryByIds(ids, HdayGroup.class);
	}

	/**
	 * 获取节假日群下的全部节假日
	 * 
	 * @param hdayCode
	 *            节假日代码
	 * @return
	 */
	@Override
	public HashMap<Integer, List<Date>> getHDayGroupAllDate(String holidaysCode) {
		HDayCache hDayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
		return hDayCache.getHDayGroupAllDate(holidaysCode);
	}
}
