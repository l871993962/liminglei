package com.yss.ams.product.information.modules.cp.pubacc.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.cp.pubacc.dao.PubAccSqlBuilder;
import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.cp.pubacc.admin.PubAccAdmin;
import com.yss.ams.product.information.support.modules.cp.pubacc.service.IPubAccDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;






/**
 * 公共账户参数数据服务
 * @author leeyu
 * @date 2013-5-24
 * @version V4.5.0.1
 */
public class PubAccDataService implements IPubAccDataService{
	private PubAccAdmin admin = null;
	public PubAccDataService(){
		admin = new PubAccAdmin(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PubAccSqlBuilder());
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return admin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return admin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = admin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId(menuId);
			res.setHeadKeyList(ServiceAssistance.getListHead("ie",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return admin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return admin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = admin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId(menuId);
			res.setHeadKeyList(ServiceAssistance.getListHead("ie",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = admin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId(menuId);
			res.setHeadKeyList(ServiceAssistance.getListHead("ie",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return admin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private String menuId = "pubAccInfo";

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
			return admin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return admin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
