package com.yss.ams.base.information.modules.sys.dztype.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.dztype.dao.DzTypeDao;
import com.yss.ams.base.information.modules.sys.dztype.dao.DzTypeSqlBuilder;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;

/**
 * 对账类型字典业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class DzTypeAdmin extends BaseAdmin {
	private DzTypeDao svcDao = null;

	public DzTypeAdmin() {
		svcDao = new DzTypeDao(DbPoolFactory.getInstance().getPool(),
				new DzTypeSqlBuilder());
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典表数据对应pojo的list
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllData() throws Exception {
		return (List<T>) svcDao.getAllData();
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
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
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
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
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典代码和对账类型字典名称转Map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	/**
	 * 根据对账类型代码获取所有对账类型字典代码和对账类型字典名称转Map
	 * @param listKey
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}
}
