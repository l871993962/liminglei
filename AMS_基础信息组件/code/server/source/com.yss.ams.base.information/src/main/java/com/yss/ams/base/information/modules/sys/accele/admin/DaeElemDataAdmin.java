package com.yss.ams.base.information.modules.sys.accele.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.accele.dao.AccEleDao;
import com.yss.ams.base.information.support.sys.accele.pojo.AccEle;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
//import com.yss.para.bi.accele.dao.AccEleDao;
//import dataservice.comm.pojo.AccEle;


public class DaeElemDataAdmin extends BaseAdmin{
	private AccEleDao svcDao = null;
	
	public DaeElemDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new AccEleDao(pool, sqlBuilder);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws BusinessException {
		return (List<T>) svcDao.getAllDataList();
	}
	
	public AccEle getDataByCode(String code) throws BusinessException {
		return svcDao.getDataByCode(code);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getPojoByCode(String pojoCode) throws BusinessException {
		return (T) svcDao.getDataByCode(pojoCode);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws BusinessException {
		return (List<T>) svcDao.getDataListByTypes(types);
	}
	
	public HashMap<String, String> getKeyConvertMap() throws BusinessException {
		return svcDao.getKeyConvertMap();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws BusinessException {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}
}
