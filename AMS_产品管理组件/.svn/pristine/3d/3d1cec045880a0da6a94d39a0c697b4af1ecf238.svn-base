package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.admin.PortRelaTradeAccDataAdmin;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaTradeAccDataSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeAccountDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaTradeAccDataService implements
		IPortRelaTradeAccountDataService {
	private PortRelaTradeAccDataAdmin portAdmin = null;

	public PortRelaTradeAccDataService() {
		portAdmin = new PortRelaTradeAccDataAdmin(DbPoolFactory.getInstance()
				.getPool(),
				new PortRelaTradeAccDataSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return portAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("portRelatradeAccount");
			res.setHeadKeyList(ServiceAssistance.getListHead("portRelatradeAccount",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return portAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return portAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("portRelatradeAccount");
			res.setHeadKeyList(ServiceAssistance.getListHead("portRelatradeAccount",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return portAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return portAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("portRelatradeAccount");
			res.setHeadKeyList(ServiceAssistance.getListHead("portRelatradeAccount",ProductInfoActivator.class));
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

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return portAdmin.getPojoByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
