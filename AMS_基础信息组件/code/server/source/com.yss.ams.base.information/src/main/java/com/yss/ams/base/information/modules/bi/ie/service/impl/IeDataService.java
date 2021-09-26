package com.yss.ams.base.information.modules.bi.ie.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.ie.admin.IeDataAdmin;
import com.yss.ams.base.information.modules.bi.ie.dao.IeSqlBuilder;
import com.yss.ams.base.information.support.bi.ie.pojo.Ie;
import com.yss.ams.base.information.support.bi.ie.service.IIeDataService;
import com.yss.ams.base.information.support.cache.IeCache;
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
 * 收支代码设置数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeDataService implements IIeDataService {
	private IeDataAdmin ieAdmin = null;

	public IeDataService() {
		ieAdmin = new IeDataAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new IeSqlBuilder());
	}

	/**
	 * 获取所有收支项目设置所有数据
	 * @return	收支项目设置数据集合
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return ieAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 查询关联信息，用于查询收支代码设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_ie");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_ie",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据收支项目代码获取收支项目设置
	 * @param code  收支代码
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return ieAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据收支项目类型获取收支项目设置
	 * @param types 收支类型
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return ieAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，根据多个收支项目代码用于查询收支代码设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_ie");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_ie",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	//收支代码下拉框只展示运营收支项
	public <K extends BasePojo> List<K> getDataListByType(String[] types)
			throws ServiceException {
		try {
			return ieAdmin.getDataListByType(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取收支项目设置转换map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return ieAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	
	/**
	 * STORY #56907 【鹏华基金】风险金管理报表
	 * @param keys
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public <K extends BasePojo> List<Ie> getDataListByFeeCodes(String[] keys)
			throws ServiceException {
		try {
			return ieAdmin.getDataListByFeeCodes(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据多个收支项目类型获取收支项目设置
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return ieAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_ie");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_ie",InformationActivator.class));
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
	 * 根据收支项目代码获取收支项目设置
	 * @param code  收支代码
	 * @return
	 * @throws Exception
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return ieAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return ieAdmin.getKeyConvertMap(listKey);
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
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = ieAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	

	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return ieAdmin.queryByIds(ids, Ie.class);
	}
	
	public Ie getCacheByIeCode(String ieCode) {
		IeCache cache = CacheManager.getInstance().getCache(CacheGroup.IE);
		return cache.getCacheByKey(ieCode);
	}
}
