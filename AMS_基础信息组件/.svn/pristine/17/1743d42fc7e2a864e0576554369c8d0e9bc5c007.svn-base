package com.yss.ams.base.information.modules.bi.curypair.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.curypair.dao.CuryPairDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 货币对 业务管理类
 * @author 马向峰  拆分  2017.0527
 *
 */
public class CuryPairDataAdmin extends BaseAdmin {
	CuryPairDao svcDao = null;

	public CuryPairDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new CuryPairDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有的货币对
	 * @return List<CuryPair>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据货币对代码获取货币对信息
	 * @param code 货币对代码
	 * @return 货币对
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据货币对代码组查找货币对信息
	 * @param types 货币对代码组
	 * @return 货币对信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 查询所有货币对并将货币对信息封装到Map内（HashMap<货币对代码, 货币对名称>）
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	/**
	 * 根据货币对代码组查找货币对信息并封装为Map（HashMap<货币对代码, 货币对名称>）
	 * @param listKey 货币对代码集合
	 * @return 货币对信息（代码，名称）
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}

	/**
	 * 根据货币对代码组查找货币对
	 * @param keys 代码组
	 * @return 货币对信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
}
