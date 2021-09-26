package com.yss.ams.base.information.modules.sys.secvar.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.secvar.dao.SecVarDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
//import com.yss.para.bi.secvar.dao.SecVarDao;

/**
 * 证券品种字典T_S_DA_SEC_VAR  Admin
 *
 */
public class SecVarDataAdmin extends BaseAdmin {
	SecVarDao svcDao = null;

	public SecVarDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new SecVarDao(pool, sqlBuilder);
	}

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList()
			throws ServiceException {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 一条证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code)
			throws ServiceException {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据 证券属性代码 C_DA_CODE数组 获取 符合条件的证券品种字典V_S_DA_SEC_VAR的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据（只包含证券品种代码 和证券品种名称）
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 所有证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

    /**
     * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
     * @return
     */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017Äê3ÔÂ29ÈÕÏÂÎç6:55:31
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
}
