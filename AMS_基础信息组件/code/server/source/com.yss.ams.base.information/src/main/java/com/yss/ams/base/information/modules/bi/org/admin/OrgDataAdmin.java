package com.yss.ams.base.information.modules.bi.org.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.org.dao.OrgDao;
import com.yss.ams.base.information.support.cache.OrgCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 机构业务管理
 * @author 马向峰 拆分  2017.0527
 *
 */
public class OrgDataAdmin extends BaseAdmin{
	private OrgDao svcDao = null;
	private OrgCache orgCache = null;
	
	public OrgDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new OrgDao(pool, sqlBuilder);
	}
	
	/**
	 * 获取列表所有数据
	 * @return	所有机构数据
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 根据机构类型组获得机构信息
	 * @param types	机构类型 组
	 * @return 机构列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}
	
	public <T extends BasePojo> List<T> getDataListByZtzz(String[] types) {
		return (List<T>) svcDao.getDataListByZtzz(types);
	}
	
	/**
	 * 将机构代码和机构名称转化为map
	 * @return HashMap<机构代码, 机构名称>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		if (orgCache == null) {
			orgCache = CacheManager.getInstance().getCache(CacheGroup.ORG);
		}
		return orgCache.getKeyConvertMap();
	}
	
	/**
	 * 根据机构代码集合查询机构信息，并将机构代码和机构名称存到map中
	 * @param listKey	机构代码列表
	 * @return HashMap<机构代码, 机构名称>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		if (orgCache == null) {
			orgCache = CacheManager.getInstance().getCache(CacheGroup.ORG);
		}
		return orgCache.getKeyConvertMap(listKey);
	}
	
	/**
	 * 根据机构类型组查询机构信息
	 * @param types 机构类型组
	 * @return 机构列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
	
	/**
	 * 根据付款账号获得所有的父级 机构代码
	 * @return 机构代码列表
	 */
	public List<String> getOrgCodebyAccNo(String AccNo) {
		return svcDao.getOrgCodebyAccNo(AccNo);
	}

	/**
	 * 更新缓存
	 * 
	 * @author liuxiang
	 * @date 2016年2月26日 STORY #28246 【自动化款指令】及【划款指令管理】模块界面问题优化
	 * @param timestamp
	 *            时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllBankHead() {
		// TODO Auto-generated method stub
		return (List<T>)svcDao.getAllBankHead();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getBankBranchByHead(String[] param) {
		// TODO Auto-generated method stub
		return (List<T>)svcDao.getBankBranchByHead(param);
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
	
	
	public String getDataListCount() {
		return Integer.toString(svcDao.getDataListCount());
	}

	public String getDataListByTimestampCount(String timestamp) {
		return Integer.toString(svcDao.getUpdateByTimestampCount(timestamp));
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListPageFromDb(PageInation page) {
		return (List<T>) svcDao.getDataListPage(page);
	}

	public List<BasePojo> getDataListByTimestampPage(String timestamp,
			PageInation page) {
		return svcDao.getDataListByTimestampPage(timestamp, page);
	}
}
