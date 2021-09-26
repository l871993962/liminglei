package com.yss.ams.base.information.modules.bi.tdchan.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.tdchan.dao.TdChanDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class SecSeatDataAdmin extends BaseAdmin{
	private TdChanDao svcDao = null;
	
	public SecSeatDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new TdChanDao(pool, sqlBuilder);
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
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws Exception {
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

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * By Jinghehe 2015-9-29 
	 * 获取所有渠道数据，包括ALL 构造的数据
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public <T extends BasePojo> List<T> getDataListByComm(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByComm(types);
	}
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByPort(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByPort(types);
	}
}
