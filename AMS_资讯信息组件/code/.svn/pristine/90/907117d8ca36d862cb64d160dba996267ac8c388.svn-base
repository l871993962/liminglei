package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.admin.SecBaseInfoDataAdmin;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseInfoSqlBuilder;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseZQDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 债券基本信息普通服务类
 * @author 马向峰
 *
 */
public class SecBaseZQDataService implements ISecBaseZQDataService {

	private SecBaseInfoDataAdmin secBaseAdmin = null;

	public SecBaseZQDataService() {
		secBaseAdmin = new SecBaseInfoDataAdmin(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseInfoSqlBuilder());
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		ArrayList<String> list = new ArrayList<String>();
		list.add("ZQ");
		return secBaseAdmin.getKeyConvertMap(list);
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub

	}

	/**
	 * 添加代码转名称方法 
	 * 从缓存中取  weijj 性能优化   20140314
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		SecBaseCache secBaseCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		return secBaseCache.getKeyConvertMap(listKey);
	}

}
