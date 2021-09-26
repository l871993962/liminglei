package com.yss.ams.base.information.modules.bi.hdaygroup.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.hdaygroup.dao.HdayGroupDao;
import com.yss.ams.base.information.support.cache.HDayCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 节假日群类型业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class HDayDataAdmin extends BaseAdmin {
	HdayGroupDao svcDao = null;

	private HDayCache hDayCache = null;
	
	public HDayDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new HdayGroupDao(pool, sqlBuilder);
	}
	
	/**
	 * 查询所有节假日群
	 * 
	 * @return 节假日群列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}
	
	/**
	 * 根据节假日群代码获取节假日群实体
	 * 
	 * @param code
	 *            节假日群代码
	 * @return 节假日群实体
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 根据节假日群类型代码获取节假日缓存
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getPojoByCode(String code) throws Exception {
		hDayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
		return (T) hDayCache.getCacheByKey(code);
	}
	
	/**
	 * 根据多个节假日群代码获取节假日群实体
	 * 
	 * @param types
	 *            节假日群代码
	 * @return 节假日群实体列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取代码转换MAP
	 * 
	 * @return 代码名称转换MAP<"hdayCode",hdayName>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		hDayCache = CacheManager.getInstance().getCache(CacheGroup.HDAY);
		return hDayCache.getKeyConvertMap(listKey);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
	
	/**
	 * 根据时间戳获取数据，用于缓存更新
	 * 
	 * @param timestamp
	 *            时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
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
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
}
