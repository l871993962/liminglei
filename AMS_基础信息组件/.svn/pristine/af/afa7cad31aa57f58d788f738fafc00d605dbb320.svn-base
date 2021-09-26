package com.yss.ams.base.information.modules.bi.ieLink.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.ieLink.dao.FeeDao;
import com.yss.ams.base.information.modules.bi.ieLink.dao.FeeSqlBuilder;
import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ieLink.service.IFeeDataService;
import com.yss.ams.base.information.support.cache.IeCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * 收支费用服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class FeeDataService implements IFeeDataService {

	/**
	 * 定义dao类
	 */
	private FeeDao feeDao = null;

	public FeeDataService() {
		feeDao = new FeeDao(DbPoolFactory.getInstance().getPool(),
				new FeeSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
	 * 获取收支项目设置中所有收支代码和收支名称的集合map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return feeDao.getKeyConvertMap();
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
	}
	
	/**
	 * 根据收支连接代码获取收支连接信息
	 * @return
	 * @param pojoCode
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		IeCache ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
		return (K)ieCache.getCacheByKey(pojoCode);
	}

	/**
	 * 根据多个收支连接代码获取收支连接信息转Map
	 * @return
	 * @param pojoCode
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		IeCache ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
		return ieCache.getKeyConvertMap(listKey);
	}

	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException
	{
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			IeCache ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
			for (Ie ie : ieCache.getCacheList()) {
				if(!map.containsKey(ie.getC_FEE_CODE())){
					map.put(ie.getC_FEE_CODE(), ie.getC_FEE_NAME());
				}
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
		return map;
	}
}
