package com.yss.ams.base.information.modules.bi.mkt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.mkt.admin.MktHDayDataAdmin;
import com.yss.ams.base.information.modules.bi.mkt.dao.MktHDayDataSqlBuilder;
import com.yss.ams.base.information.support.bi.mkt.service.IMktHDayDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 交易市场服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktHDayDataService implements IMktHDayDataService {
	private MktHDayDataAdmin mktDataAdmin = null;
	
	private Logger logger = LogManager.getLogger(this.getClass());

	public MktHDayDataService() {
		mktDataAdmin = new MktHDayDataAdmin(DbPoolFactory.getInstance()
				.getPool(),
				new MktHDayDataSqlBuilder());
	}
	/**
	 * 获取所有数据列表
	 * @return List
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		List<K> dataList = null;
		try {
			dataList = mktDataAdmin.getAllDataList();
		} catch (Exception e) {
			logger.log("交易市场设置：查询取所有交易市场信息出错", e);
			throw new ServiceException(e);
		}
		return dataList;
	}

	/**
	 * 获取所有数据列表，返回查询结果对象
	 * @return QueryRes
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = null;

		try {
			pojoList = mktDataAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_exchange");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_exchange",InformationActivator.class));
		} catch (Exception e) {
			logger.log("交易市场设置：查询取所有交易市场信息出错", e);
			throw new ServiceException(e);
		}

		return res;
	}

	/**
	 * 根据唯一性标识获取数据
	 * @param dataCode
	 */
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		K dataObj = null;

		try {
			dataObj = mktDataAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			logger.log("交易市场设置：根据唯一性标识获取数据出错", e);
			throw new ServiceException(e);
		}

		return dataObj;
	}

	/**
	 * 根据市场代码获取市场设置实体列表
	 * @param <K>
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws Exception {
		List<K> dataList = null;

		try {
			dataList = mktDataAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			logger.log("交易市场设置：根据市场类型获取数据出错", e);
			throw new ServiceException(e);
		}

		return dataList;
	}

	public QueryRes getQueryResByTypes(String[] types) throws Exception {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList = null;

		try {
			pojoList = getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_exchange");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_exchange",InformationActivator.class));
		} catch (Exception e) {
			logger.log("交易市场设置：根据市场类型获取数据出错", e);
			throw new ServiceException(e);
		}

		return res;
	}
	
	/**
	 * 获取代码中文转换MAP
	 * @return Map
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> cvtMap = null;

		try {
			cvtMap = mktDataAdmin.getKeyConvertMap();
		} catch (Exception e) {
			logger.log("交易市场设置：获取代码名称转换HashMap出错", e);
			throw new ServiceException(e);
		}
		return cvtMap;
	}

	/**
	 * 根据交易市场获取节假日信息
	 * @param code
	 */
	public HashMap<String, HashMap<Integer, List<Date>>> getHolidays(String code)
			throws ServiceException {
		HashMap<String, HashMap<Integer, List<Date>>> dateMap = null;

		try {
			dateMap = mktDataAdmin.getHolidays(code);
		} catch (Exception e) {
			logger.log("交易市场设置：根据市场获取节假日信息出错", e);
			throw new ServiceException(e);
		}

		return dateMap;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Override
	public HashMap<String, String> getMarketHDaysData() throws YssException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, HashMap<Integer, List<Date>>> getHolidays()
			throws YssException {
		// TODO Auto-generated method stub
		return null;
	}
}
