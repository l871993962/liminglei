package com.yss.ams.base.information.modules.sys.ieItem.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.ieItem.admin.IeItemAdmin;
//import com.yss.ams.base.information.modules.sys.ieItem.cache.IeItemCache;
import com.yss.ams.base.information.modules.sys.ieItem.dao.IeItemSqlBuilder;
import com.yss.ams.base.information.support.cache.IeItemCache;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemDataService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.cache.IeItemCache;
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
//import com.yss.para.bi.ieItem.admin.IeItemAdmin;
//import com.yss.para.bi.ieItem.dao.IeItemSqlBuilder;

//import dataservice.comm.pojo.IeItem;
//import dataservice.service.IIeItemDataService;

/**
 * 收支结转数据字典，不是用来转换费用的，转换费用请参见FeeDataService
 * 
 * @author leeyu
 * @date 2013-5-24
 * @version V4.5.0.1
 */
public class IeItemDataService implements IIeItemDataService {
	private final String IE_FUN = "base_ieItem";
	private IeItemAdmin ieAdmin = null;

	public IeItemDataService() {
		ieAdmin = new IeItemAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new IeItemSqlBuilder());
	}

	/**
	 * 获取收支项目字典表T_S_IE_ITEM 所有数据
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return ieAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取收支项目字典表T_S_IE_ITEM 所有数据 并用此初始化QueryRes对象
	 * @return
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId(IE_FUN);
			res.setHeadKeyList(ServiceAssistance.getListHead(IE_FUN,InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 先根据收支项目代码C_IE_CODE 从 收支项目缓存IeItemCache中获取缓存数据，若缓存为空则
	 * 根据收支项目代码C_IE_CODE 获取对应一条收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
/*	@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			IeItemCache ieItemCache = CacheManager.getInstance().getCache(CacheGroup.BASEIEITEM);
			IeItem ieItemBean = ieItemCache.getCacheByKey(dataCode);
			if(null == ieItemBean){
				ieItemBean = ieAdmin.getDataByCode(dataCode);
			}
			return (K) ieItemBean;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/
	
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return ieAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
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
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据，并用此进行初始化QueryRes对象
	 * @return 包含收支项目字典表T_S_IE_ITEM 数据 的QueryRes对象
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_ie");
			res.setHeadKeyList(ServiceAssistance.getListHead(IE_FUN,InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取收支项目字典表T_S_IE_ITEM 所有数据（只包含收支项目代码和收支项目名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return ieAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return ieAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据 并封装在QueryRes对象中
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId(IE_FUN);
			res.setHeadKeyList(ServiceAssistance.getListHead(IE_FUN,InformationActivator.class));
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
	 * 根据pojoCode获取 收支项目缓存IeItemCache中数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		IeItemCache ieCache = CacheManager.getInstance().getCache(CacheGroup.IEITEM);
		return (K)ieCache.getCacheByKey(pojoCode);
	}

	/**
	 * 根据pojoCode获取 收支项目缓存IeItemCache中数据（只包含收支项目代码和收支项目名称）
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		IeItemCache ieCache = CacheManager.getInstance().getCache(CacheGroup.IEITEM);
		return ieCache.getKeyConvertMap(listKey);
	}

	/**
	 * 根据时间戳获取所有收支项目字典表T_S_IE_ITEM数据
	 * @return
	 */
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

	/**
	 * 根据收支项目类型获取收支项目
	 * @param types 收支项目类型
	 * @return
	 * @throws ServiceException
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByIeTypes(String[] types)
			throws ServiceException {
		try {
			return ieAdmin.getDataListByIeTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据IDs获取时间
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:54:58
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return ieAdmin.queryByIds(ids, IeItem.class);
	}
}
