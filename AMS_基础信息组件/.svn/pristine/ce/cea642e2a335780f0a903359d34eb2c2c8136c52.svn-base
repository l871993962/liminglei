package com.yss.ams.base.information.modules.sys.acctype.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.acctype.dao.AccTypeDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
//import com.yss.para.bi.acctype.dao.AccTypeDao;

public class AccTypeDataAdmin extends BaseAdmin{
	AccTypeDao svcDao = null;

	public AccTypeDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new AccTypeDao(pool, sqlBuilder);
	}
	
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有 
	 * 资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @return hashMap类型的资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @param listKey List类型集合
	 * @return hashMap类型的资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}
	
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有记录
	 * @return	资产类型字典V_S_DAT_ASS_TYPE的所有记录的AccType对象集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}
	
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @param code ：C_DAT_CODE的值
	 * @return 资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}
	
	/**
	 * 根据类型获取相应资产类型数据
	 * @param types:String类型数组
	 * @return 相应资产类型数据集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}
	
	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @param types String[]类型数组
	 * @return	符合条件的AccType对象集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
	
}
