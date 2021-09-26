package com.yss.ams.base.information.modules.bi.ie.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.ie.dao.IeDao;
import com.yss.ams.base.information.support.cache.IeCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 收支代码设置业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeDataAdmin extends BaseAdmin {
	IeDao svcDao = null;
	private IeCache ieCache = null;

	public IeDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new IeDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有收支项目设置所有数据
	 * @return	收支项目设置数据集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据收支项目代码获取收支项目设置
	 * @param code  收支代码
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 根据收支代码获取收支项目缓存
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getPojoByCode(String code) throws Exception {
		ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
		return (T) ieCache.getCacheByKey(code);
	}

	/**
	 * 根据收支项目类型获取收支项目设置
	 * @param types 收支类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取收支项目设置转换map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
		return ieCache.getKeyConvertMap(listKey);
	}

	/**
	 * 根据多个收支项目类型获取收支项目设置
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByFeeCodes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByFeeCodes(types);
	}
	/**
	 * 根据时间戳获取收支设置，用于缓存刷新
	 * @param timestamp
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
	 * @date 2017年3月29日下午6:54:28
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByType(String[] types)
				throws Exception {
		return (List<T>) svcDao.getDataListByType(types);
	}
}
