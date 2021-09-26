package com.yss.ams.base.information.modules.sys.dvaitem.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.dvaitem.dao.DavItemDao;
import com.yss.ams.base.information.support.sys.dvaitem.pojo.DvaItem;
//import com.yss.dict.dvaitem.dao.DavItemDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

//import dataservice.comm.pojo.DvaItem;

/**
 * 核算业务项字典T_S_DVA_ITEM Admin
 *
 */
public class DvaItemDataAdmin extends BaseAdmin {
	DavItemDao svcDao = null;

	public DvaItemDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new DavItemDao(pool, sqlBuilder);
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据核算项目代码C_DVA_ITEM_CODE获取核算业务项字典T_S_DVA_ITEM的一条数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据（只包含核算项目代码，核算项目名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 *  获取核算业务项字典T_S_DVA_ITEM的所有数据树形结构
	 * @return
	 */
	public List<DvaItem> getTreeViewForDayfItems() throws Exception {
		return svcDao.getTreeViewForDayfItems();
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	public List<?> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * 根据IDs查询数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:47:51
	 * @param ids
	 * @param clazz
	 * @return
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
}
