package com.yss.ams.product.information.modules.cp.pubacc.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.cp.pubacc.dao.PubAccDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;


public class PubAccAdmin extends BaseAdmin {
	private PubAccDao dao = null;

	public PubAccAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		dao = new PubAccDao(pool, sqlBuilder);

	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) dao.getAllDataList();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) dao.getDataByCode(code);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) dao.getDataListByTypes(types);
	}

	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return dao.getKeyConvertMap();
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return dao.getKeyConvertMap(listKey);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) dao.getDataListByTypes(types);
	}
}
