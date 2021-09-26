package com.yss.ams.product.information.modules.ab.portrela.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaTradeAccNoDataServiceDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class PortRelaTradeAccNoDataAdmin extends BaseAdmin {
	PortRelaTradeAccNoDataServiceDao svcDao = null;

	public PortRelaTradeAccNoDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new PortRelaTradeAccNoDataServiceDao(pool, sqlBuilder);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getPojoByCode(String code) throws Exception {
		return (T) svcDao.getPojoByCode(code);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
}
