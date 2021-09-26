package com.yss.ams.base.information.modules.bi.mkt.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.mkt.dao.MktDao;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * 交易市场业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktDataAdmin extends BaseAdmin {
	private MktDao svcDao = null;
	private MktCache mktCache = null;

	public MktDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new MktDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有交易市场设置数据
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据市场代码获取交易市场设置
	 * 
	 * @param code
	 *            市场代码
	 * @return MKt
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 根据交易市场代码获取交易市场缓存
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getPojoByCode(String code) throws Exception {
		mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		return (T) mktCache.getCacheByKey(code);
	}

	/**
	 * 根据交易市场类型获取交易市场设置
	 * 
	 * @param types
	 *            市场类型
	 * @return 交易市场列表
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取交易市场代码转换MAP
	 * 
	 * @return 交易市场代码转换MAP
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		return mktCache.getKeyConvertMap(listKey);
	}

	/**
	 * 根据多个交易市场类型获取交易市场设置
	 * 
	 * @param keys
	 *            市场类型
	 * @return 市场列表
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * 根据时间戳获取市场，用于缓存刷新
	 * 
	 * @param timestamp
	 *            时间戳
	 * @return 市场列表
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * 根据市场代码取值 STORY #19553 最低备付金调整
	 * 
	 * @author dingshalu
	 * @date 2015-8-5
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataSqlByKeys(String[] types) throws Exception {
		return (List<T>)svcDao.getAllDataSqlByKeys(types);
	}
	
	/**
	 * STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息<br>
	 * 关联结算机构查询交易市场
	 * 
	 * @return 交易市场列表
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataListAux() throws Exception {
		return (List<T>) svcDao.getAllDataListAux();
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
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
}
