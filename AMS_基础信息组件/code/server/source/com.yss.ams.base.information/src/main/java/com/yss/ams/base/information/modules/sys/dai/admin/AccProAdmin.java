package com.yss.ams.base.information.modules.sys.dai.admin;

import java.util.HashMap;
import java.util.List;


import com.yss.ams.base.information.modules.sys.dai.dao.AccProDao;
//import com.yss.dict.dai.dao.AccProDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

public class AccProAdmin extends BaseAdmin {
	AccProDao svcDao = null;

	/**
	 * 构造方法
	 * @param pool
	 * @param sqlBuilder
	 */
	public AccProAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new AccProDao(pool, sqlBuilder);
	}

	/**
	 * 查询指定 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dataCode 核算项目代码C_DAI_CODE值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws Exception {
		return (K) svcDao.getDataByCode(dataCode);
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据,并对结果按 C_DV_KM_CLS,N_ORDER进行排序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataList() throws Exception {
		return (List<K>) svcDao.getDataList();
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param types 科目类别C_DV_KM_CLS值组成的数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws Exception {
		return (List<K>) svcDao.getDataListByTypes(types);
	}
	
	/**
	 * 查询 符合条件Conds的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param Conds 查询条件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataListBySqlConds(String conds)
			throws Exception {
		return (List<K>) svcDao.getDataListBySqlCond(conds);
	}

	/**
	 * 查询某些 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param keys 核算项目代码C_DAI_CODE值组成的数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws Exception {
		return (List<K>) svcDao.getDataListByKeys(keys);
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM的核算项目代码C_DAI_CODE和核算项目名称C_DAI_NAME组成的集合
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param kmCls 科目类别C_DV_KM_CLS的值数组
	 * @return
	 */
	public  List<BasePojo> getAccProDataByKmCls(String[] kmCls) throws ServiceException {
		return svcDao.getAccProDataByKmCls(kmCls);
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据
	 * @param timestamp 时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * 根据IDs查询数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:46:24
	 * @param ids
	 * @param clazz
	 * @return
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
}
