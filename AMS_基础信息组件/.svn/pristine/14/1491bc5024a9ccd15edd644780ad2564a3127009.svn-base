package com.yss.ams.base.information.modules.bi.salesnet.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.salesnet.dao.SalesNetDao;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * 销售网点设置业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class SalesNetDataAdmin extends BaseAdmin {
	private SalesNetDao svcDao = null;

	public SalesNetDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new SalesNetDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有销售网点设置数据
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList()
			throws ServiceException {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据销售网点代码获取销售网点设置
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code)
			throws ServiceException {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据销售网点类型获取销售网点设置
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取销售网点代码转换MAP
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return svcDao.getKeyConvertMap();
	}
	
	/**
	 * 根据销售网点代码集获取销售网点代码转换MAP
	 * @param listKey
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}

	/**
	 * 根据多个销售网点代码获取销售网点设置list
	 * @param keys
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * 获取所有销售网点代码和销售网点名称MAP
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() {
		return svcDao.getShortDataMap();
	}

	public SalesNet getSalesNetByCode(String code) {
		return svcDao.getSalesNetByCode(code);
	}

	public HashMap<String, String> getAllNetCodeAndName() {
		return svcDao.getAllNetCodeAndName();
	}

	public SalesNet getSalesNetByVendorCode(String vendorCode) {
		return svcDao.getSalesNetByVendorCode(vendorCode);
	}
}
