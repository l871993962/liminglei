package com.yss.ams.base.information.modules.sys.cashflow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.cashflow.dao.CashFlowDao;
import com.yss.ams.base.information.modules.sys.cashflow.dao.CashFlowSqlBuilder;
import com.yss.ams.base.information.support.sys.cashflow.pojo.CashFlow;
import com.yss.ams.base.information.support.sys.cashflow.service.ICashFlowService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * 现金流标记字典service层
 * @author yuankai 基础信息拆分  2017.5.31
 *
 */
public class CashFlowService implements ICashFlowService,IKeyConvertDataService {
	private CashFlowDao serviceDao = null;

	public CashFlowService() throws Exception {
		serviceDao = new CashFlowDao(DbPoolFactory.getInstance().getPool(), new CashFlowSqlBuilder());
	}
	
	/**
	 * 获取所有现金流标记代码和现金流标记名称对应pojo的list集合
	 * @param
	 * @return list
	 */
	@Override
	public ArrayList<CashFlow> getCashFlowCode() throws ServiceException {
		 return serviceDao.getCashFlowCode();
	}
	
	/**
	 * 根据现金流标记代码获取对应的现金流标记代码和现金流标记名称对应的pojo
	 * @return pojo
	 * @param
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return  (K)serviceDao.getCashFlowCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		// luxiaoying:现金流标记转换
		return serviceDao.getKeyConvertMap();
	}
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKeys)//lxy
			throws ServiceException {
		// luxiaoying:现金流标记转换
		return serviceDao.getKeyConvertMap();
	}

	
}
