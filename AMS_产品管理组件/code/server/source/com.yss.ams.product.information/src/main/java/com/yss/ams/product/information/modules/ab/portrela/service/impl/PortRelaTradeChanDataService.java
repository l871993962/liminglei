package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.admin.PortRelaTradeChanDataAdmin;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeChanDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaTradeChanDataService implements
		IPortRelaTradeChanDataService {
	private PortRelaTradeChanDataAdmin relaTradeChanDataAdmin = null;

	public PortRelaTradeChanDataService() {
		relaTradeChanDataAdmin = new PortRelaTradeChanDataAdmin(DbPoolFactory
				.getInstance().getPool(),
				new PortRelaSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return relaTradeChanDataAdmin.getAllDataList();
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = relaTradeChanDataAdmin.getAllDataList();
		res.setDataList(pojoList);
		res.setMenuId("portTradeSeat");
		res.setHeadKeyList(ServiceAssistance.getListHead("portTradeSeat",ProductInfoActivator.class));
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return relaTradeChanDataAdmin.getDataByCode(dataCode);
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		return relaTradeChanDataAdmin.getDataListByTypes(types);
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = relaTradeChanDataAdmin
				.getDataListByTypes(types);
		res.setDataList(pojoList);
		res.setMenuId("portTradeSeat");
		res.setHeadKeyList(ServiceAssistance.getListHead("portTradeSeat",ProductInfoActivator.class));
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException{
		return relaTradeChanDataAdmin.getKeyConvertMap();
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		return relaTradeChanDataAdmin.getDataListByKeys(keys);
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = relaTradeChanDataAdmin
				.getDataListByKeys(keys);
		res.setDataList(pojoList);
		res.setMenuId("portTradeSeat");
		res.setHeadKeyList(ServiceAssistance.getListHead("portTradeSeat",ProductInfoActivator.class));
		return res;
	}

	private String menuId = "";
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
//		TdChanCache tdChanCache = CacheManager.getInstance().getCache(CacheGroup.TDCHAN);
//		return (K)tdChanCache.getCacheByKey(pojoCode);
	    //TODO 应当增加修改为 BASETDCHAN 需要在FAST增加CacheGroup.BASETDCHAN
	    return (K)CacheManager.getInstance().getCache(CacheGroup.TDCHAN).getCacheByKey(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		//TdChanCache tdChanCache = CacheManager.getInstance().getCache(CacheGroup.TDCHAN);
		//return tdChanCache.getKeyConvertMap(listKey);
	    //TODO 应当增加修改为 BASETDCHAN 需要在FAST增加CacheGroup.BASETDCHAN
	    return CacheManager.getInstance().getCache(CacheGroup.TDCHAN).getKeyConvertMap(listKey);
	}

}
