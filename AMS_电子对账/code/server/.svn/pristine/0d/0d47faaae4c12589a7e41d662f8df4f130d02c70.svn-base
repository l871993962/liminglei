package com.yss.uco.elecreco.er.erdztype.admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erdztype.dao.ErDzTypeDao;
import com.yss.uco.elecreco.er.erdztype.dao.ErDzTypeSqlBuilder;
import com.yss.uco.elecreco.er.erdztype.util.LicDzTypeUtil;

/**
 * 对账类型字典业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class ErDzTypeAdmin extends BaseAdmin {
	private ErDzTypeDao svcDao = null;


	public ErDzTypeAdmin() {
		svcDao = new ErDzTypeDao(DbPoolFactory.getInstance().getPool(),
				new ErDzTypeSqlBuilder());
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典表数据对应pojo的list
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllData() throws Exception {
		return (List<T>) svcDao.getAllData(LicDzTypeUtil.getInstance().getAllDzTypes());
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
		Set<String> dzTypes = LicDzTypeUtil.getInstance().getAllDzTypes();
		dzTypes.retainAll(Arrays.asList(keys));
		return (List<T>) svcDao.getDataByKeys(dzTypes);
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
		Set<String> dzTypes = LicDzTypeUtil.getInstance().getAllDzTypes();
		dzTypes.retainAll(Arrays.asList(types));
		return (List<T>) svcDao.getDataByTypes(dzTypes);
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		Set<String> dzTypes = LicDzTypeUtil.getInstance().getAllDzTypes();
		if(!dzTypes.contains(code))
		{
			return null;
		}
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
