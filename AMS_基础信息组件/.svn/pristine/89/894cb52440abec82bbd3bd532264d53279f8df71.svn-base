package com.yss.ams.base.information.modules.bi.region.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.region.dao.AreaDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * 地区信息业务管理类
 * @author 马向峰 拆分
 *@Date 20170601
 */
public class AreaDataAdmin extends BaseAdmin {
	AreaDao aDao = null;

	public AreaDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		aDao = new AreaDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有的地区信息
	 * @return 地区信息集合
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllAreas() throws Exception {
		return (List<T>) aDao.getAllAreas();
	}

	/**
	 * 获取所有地区最上层所属地区
	 * @return List<Area>
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllTopAreas() throws Exception {
		return (List<T>) aDao.getAllTopAreas();
	}
	
	/**
	 * 获取所有地区非最上层所属地区
	 * @return List<Area>
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllNotTopAreas() throws Exception {
		return (List<T>) aDao.getAllNotTopAreas();
	}

	/**
	 * 将所有地区信息的编号和名称转化为map
	 * HashMap<编号, 名称>
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return aDao.getKeyConvertMap();
	}
	
	/**
	 * 根据地区编号获取所有地区信息，并将编号和名称转化为map
	 * HashMap<编号, 名称>
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return aDao.getKeyConvertMap(listKey);
	}
	
	/**
	 * 根据地区编号获取指定的地区信息
	 * @param code	地区编号
	 * @return	地区信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) aDao.getDataByCode(code);
	}
}
