package com.yss.ams.base.information.modules.bi.mkt.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.mkt.dao.MktHDayDataServiceDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * 交易市场业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktHDayDataAdmin extends BaseAdmin {
	MktHDayDataServiceDao svcDao = null;

	public MktHDayDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new MktHDayDataServiceDao(pool, sqlBuilder);
	}

	/**
	 * 查询所有交易市场
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList()
			throws ServiceException {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据市场代码查询交易市场
	 * 
	 * @param code
	 *            市场代码
	 * @return 交易市场实体
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code)
			throws ServiceException {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据市场代码查询多个交易市场
	 * 
	 * @param types
	 *            市场代码
	 * @return 交易市场列表
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取交易市场代码转换MAP
	 * 
	 * @return 代码转换MAP
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 根据节假日群代码查询对应节假日信息
	 * 
	 * @param code
	 *            节假日群代码
	 * @return MAP<"节假日群代码",<"年份","节假日期">>
	 * @throws ServiceException
	 */
	public HashMap<String, HashMap<Integer, List<Date>>> getHolidays(String code)
			throws ServiceException {
		return svcDao.getHolidaysByCode(code);
	}
}
