package com.yss.ams.sec.information.modules.plateset.plate.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.modules.plateset.plate.dao.PlateDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * 板块业务管理类
 * @author 马向峰拆分
 *@Date 20170531
 */
public class PlateDataAdmin extends BaseAdmin {

	private PlateDao plateDao = null;

	public PlateDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		plateDao = new PlateDao(pool, sqlBuilder);
	}
	
	/**
	 * 查询板块分类代码和名称
	 * @return	HashMap<板块分类代码, 板块名称>
	 */
	public HashMap<String, String> getConvertKeyMap() {
		return plateDao.getSubConvertMap();
	}

	/**
	 * 根据板块分类代码查找板块信息
	 * @param pojoCode	板块分类代码
	 * @return 板块信息
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return (K) plateDao.getPojoByCode(pojoCode);
	}

	/**
	 * 根据板块分类代码组查找板块信息
	 * @param listKey	板块分类代码组
	 * @return HashMap<板块分类代码, 板块名称>
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws Exception {
		return plateDao.getConvertMap(listKey);
	}

	public <T extends BasePojo> List<T>  getDataList() {
		return  (List<T>)plateDao.getPlateCategory(true);
	}

}
