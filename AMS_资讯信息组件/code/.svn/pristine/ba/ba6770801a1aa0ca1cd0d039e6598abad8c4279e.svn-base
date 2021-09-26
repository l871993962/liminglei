package com.yss.ams.sec.information.modules.sv.indexinfo.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.ams.sec.information.modules.sv.indexinfo.dao.IndexInfoDao;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexInfoDataAdmin extends BaseAdmin{
	
	private IndexInfoDao indexInfoDao = null;
	
	public IndexInfoDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		indexInfoDao = new IndexInfoDao(pool, sqlBuilder);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) indexInfoDao.getAllDataList();
	}

	@SuppressWarnings("unchecked")
	public  <T extends BasePojo> T getDataByCode(String dataCode) {
		return  (T) indexInfoDao.getDataByCode(dataCode);
	}

	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return indexInfoDao.getKeyConvertMap(listKey);
	}

	/**
	 * 
	 * @author liuxiang
	 * @date 2015年11月4日 STORY #22070 重复清算行情文件时，能提示系统中已经有自动转手工的数据
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() {
		return indexInfoDao.getKeyConvertMap();
	}
}
