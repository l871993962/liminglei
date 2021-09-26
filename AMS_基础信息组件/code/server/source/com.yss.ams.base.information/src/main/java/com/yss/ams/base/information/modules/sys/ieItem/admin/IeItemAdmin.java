package com.yss.ams.base.information.modules.sys.ieItem.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.ieItem.dao.IeItemDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
//import com.yss.para.bi.ieItem.dao.IeItemDao;

public class IeItemAdmin extends BaseAdmin {
	IeItemDao svcDao = null;

	public IeItemAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new IeItemDao(pool, sqlBuilder);
	}

	/**
	 * 获取收支项目字典表T_S_IE_ITEM 所有数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应一条收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取收支项目字典表T_S_IE_ITEM 所有数据（只包含收支项目代码和收支项目名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * 根据时间戳获取所有收支项目字典表T_S_IE_ITEM数据
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}

	/**
	 * 根据收支项目类型获取收支项目
	 * @param types 收支项目类型
	 * @return
	 * @throws Exception
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByIeTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByIeTypes(types);
	}
	
	/**
	 * 根据IDs获取时间
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:54:58
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
}

