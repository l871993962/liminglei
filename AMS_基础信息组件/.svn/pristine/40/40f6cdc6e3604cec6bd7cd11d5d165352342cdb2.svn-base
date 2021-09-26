package com.yss.ams.base.information.modules.bi.curypair.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.curypair.admin.CuryPairDataAdmin;
import com.yss.ams.base.information.modules.bi.curypair.dao.CuryPairSqlBuilder;
import com.yss.ams.base.information.support.bi.curypair.service.ICuryPairDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 货币对数据服务类
 * @author 马向峰  拆分  2017.0527
 *
 */
public class CuryPairDataService implements ICuryPairDataService {
	private CuryPairDataAdmin curPairAdmin = null;

	public CuryPairDataService() {
		curPairAdmin = new CuryPairDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new CuryPairSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return curPairAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = curPairAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_currencyPair");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_currencyPair",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return curPairAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return curPairAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = curPairAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_currencyPair");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_currencyPair",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return curPairAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据货币对代码组查找货币对
	 * @param keys 代码组
	 * @return 货币对信息
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return curPairAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = curPairAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_currencyPair");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_currencyPair",InformationActivator.class));
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
			return curPairAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return curPairAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
