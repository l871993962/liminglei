package com.yss.ams.base.information.modules.bi.mkt.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.mkt.admin.MktVarDataAdmin;
import com.yss.ams.base.information.modules.bi.mkt.dao.MktSqlBuilder;
import com.yss.ams.base.information.support.bi.mkt.service.IMktVarDataService;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * 
 * 市场字典数据服务<br>
 * 用于市场代码中文转换<br>
 */

/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
public class MktVarDataService implements IMktVarDataService {
	private MktVarDataAdmin mktVarDataAdmin = null;

	public MktVarDataService() {
		mktVarDataAdmin = new MktVarDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new MktSqlBuilder());
	}

	/**
	 * 获取市场代码代码对应中文转换
	 * @param 
	 * @return HashMap
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return mktVarDataAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String arg0)
			throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	/**
	 * 获取多个市场代码代码对应中文转换
	 * @param listKey
	 * @return HashMap
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return mktVarDataAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 重新加载缓存
	 */
	@Override
	public void reloadCache() {
		MktCache cache = CacheManager.getInstance().getCache(CacheGroup.MKT);
		cache.reloadData();
	}

}
