package com.yss.ams.base.information.modules.bi.salesnet.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.salesnet.admin.SalesNetDataAdmin;
import com.yss.ams.base.information.modules.bi.salesnet.dao.SalesNetSqlBuilder;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.ams.base.information.support.bi.salesnet.service.INetDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 销售网点设置数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class SalesNetDataService implements INetDataService {
	private SalesNetDataAdmin netAdmin = null;

	public SalesNetDataService() {
		netAdmin = new SalesNetDataAdmin(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SalesNetSqlBuilder());
	}

	/**
	 * 获取所有销售网点设置数据
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return netAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询销售网点设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = netAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_taselnet");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_taselnet",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据销售网点代码获取销售网点设置
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return netAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据销售网点类型获取销售网点设置
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return netAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，根据销售网点类型查询销售网点设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = netAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_taselnet");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_taselnet",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取销售网点代码转换MAP
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return netAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据多个销售网点代码获取销售网点设置list
	 * @param keys
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return netAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，根据多个销售网点代码查询销售网点设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = netAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_taselnet");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_taselnet",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 根据销售网点代码获取销售网点设置
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return netAdmin.getDataByCode(pojoCode);
	}

	/**
	 * 根据销售网点代码集获取销售网点代码转换MAP
	 * @param listKey
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return netAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 获取所有销售网点代码和销售网点名称MAP
	 * @return
	 */
	@Override
	public HashMap<String, String> getShortDataMap()throws ServiceException {
		HashMap<String, String> map = null;
		try {
			map = netAdmin.getShortDataMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return map;
	}

	@Override
	public SalesNet getSalesNetByCode(String code) throws ServiceException {
		
		return netAdmin.getSalesNetByCode(code);
	}

	@Override
	public HashMap<String, String> getAllNetCodeAndName()
			throws ServiceException {
		return netAdmin.getAllNetCodeAndName();
	}

	@Override
	public SalesNet getSalesNetByVendorCode(String vendorCode) {
		return netAdmin.getSalesNetByVendorCode(vendorCode);
	}
	
}
