package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.admin.PortRelaTradeAccNoDataAdmin;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaTradeAccNoDataSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeAccountNoDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaTradeAccNoDataService implements IPortRelaTradeAccountNoDataService {
	private PortRelaTradeAccNoDataAdmin portNoAdmin = null;

	public PortRelaTradeAccNoDataService() {
		portNoAdmin = new PortRelaTradeAccNoDataAdmin(DbPoolFactory
				.getInstance().getPool(),
				new PortRelaTradeAccNoDataSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return portNoAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portNoAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("portRelatradeAccountNo");
			res.setHeadKeyList(ServiceAssistance.getListHead("portRelatradeAccountNo",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return portNoAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return portNoAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = portNoAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("portRelatradeAccountNo");
			res.setHeadKeyList(ServiceAssistance.getListHead("portRelatradeAccountNo",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return portNoAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return portNoAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		try {
			List<BasePojo> pojoList;
			pojoList = portNoAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("portRelatradeAccountNo");
			res.setHeadKeyList(ServiceAssistance.getListHead("portRelatradeAccountNo",ProductInfoActivator.class));
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
	 * add by liyanjun 2016-4-15 BUG #129496 上海黄金交易所贵金属交易问题汇总
	 * 解决“组合关联客户编号”set界面控件不显示值
	 * @param code 客户编号
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return portNoAdmin.getPojoByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
