package com.yss.uco.elecreco.er.erresview.admin;

import java.util.List;

import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erresview.dao.ErResviewDao;
import com.yss.uco.elecreco.er.erresview.dao.ErResviewSqlBuilder;
import com.yss.uco.elecreco.er.erresview.pojo.ErResview;

public class ErResviewAdmin extends BaseAdmin {
	
	private ErResviewDao svcDao = null;
	public ErResviewAdmin() {
		svcDao = new ErResviewDao(DbPoolFactory.getInstance().getPool(),
				new ErResviewSqlBuilder());
	}
	
	public ErResview getDataByCode(String pojoCode) {
		return svcDao.getDataByCode(pojoCode);
	}
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataByTypes(String[] types) {
		return (List<T>) svcDao.getDataByTypes(types);
	}
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataByKeys(String[] keys) {
		return (List<T>) svcDao.getDataByKeys(keys);
	}
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllData() {
		return (List<T>) svcDao.getAllData();
	}
	
}
