package com.yss.ifa.szt.tool.para.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.para.dao.ErVocDao;
import com.yss.ifa.szt.tool.para.dao.ErVocSqlBuilder;

public class ErVocAdmin extends BaseAdmin {
	private ErVocDao svcDao = null;

	public ErVocAdmin() {
		svcDao = new ErVocDao(DbPoolFactory.getInstance().getPool(),
				new ErVocSqlBuilder());
	}
	
	/**
	 * 获取电子对账词汇表所有数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllData() throws Exception {
		return (List<T>) svcDao.getAllData();
	}
	
	/**
	 * 根据词汇代码查询对应pojo的list
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataByKeys(String[] keys)
			throws Exception {
		return (List<T>) svcDao.getDataByKeys(keys);
	}
	
	/**
	 * 根据词汇类型类型代码查询所有对应pojo的list
	 * @param types
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataByTypes(types);
	}
	
	/**
	 * 根据词汇代码查询所有对账类型字典表数据对应pojo
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 查询所有词汇字典代码和词汇字典名称转Map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	/**
	 * 根据词汇代码获取所有词汇代码和词汇名称转Map
	 * @param listKey
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}
}
