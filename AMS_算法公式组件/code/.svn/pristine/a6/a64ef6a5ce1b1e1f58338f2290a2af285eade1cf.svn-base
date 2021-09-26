package com.yss.ams.visaval.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.dao.AdvAlgoDao;
import com.yss.ams.visaval.support.api.pojo.ParamFromSql;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class AdvAlgoDataAdmin extends BaseAdmin{
	AdvAlgoDao svcDao = null;
	public AdvAlgoDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new AdvAlgoDao(pool, sqlBuilder);
	}
	
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
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
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:38:55
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz){
		return svcDao.queryByIds(ids, clazz);
	}

	public List<ParamFromSql> getParamFromSql(String sql) {
		return svcDao.getParamFromSql(sql);
	}
	
	public BasePojo getAlgoByCode(String code){
		return svcDao.getAlgoByCode(code);
	}
}
