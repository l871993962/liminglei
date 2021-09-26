package com.yss.ams.base.information.modules.sys.dccury.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.dccury.dao.DcCuryDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
/**
 * 国际货币信息业务管理类
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class DcCuryDataAdmin extends BaseAdmin {
	DcCuryDao svcDao = null;

	public DcCuryDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new DcCuryDao(pool, sqlBuilder);
	}

	/**
	 * 查询所有国际货币
	 * @return	国际货币列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据币种代码查询货币
	 * @param code	币种代码
	 * @return	货币信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据币种代码的集合查询货币信息
	 * @param types 币种代码数组
	 * @return	货币信息List集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}
	
	/**
	 * 查询所有国际货币信息并封装为Map集合，HashMap<String, String>，key为币种代码，value为币种名称
	 * @return	封装了币种信息的Map
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 根据币种代码的集合查询货币信息
	 * @param types 币种代码数组
	 * @return	货币信息List集合
	 * @throws Exception
	 */
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
	 * @date 2017年3月29日下午6:51:18
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
	
}
