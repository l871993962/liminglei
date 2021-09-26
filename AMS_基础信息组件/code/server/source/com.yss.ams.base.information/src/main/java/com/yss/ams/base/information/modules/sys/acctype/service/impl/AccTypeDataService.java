package com.yss.ams.base.information.modules.sys.acctype.service.impl;

import java.util.HashMap;
import java.util.List;









//import com.yss.bundle.activator.UcoActivator;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;
//import com.yss.para.bi.acctype.admin.AccTypeDataAdmin;
//import com.yss.para.bi.acctype.dao.AccTypeSqlBuilder;
//
//import dataservice.comm.pojo.AccType;
//import dataservice.service.IAccTypeDataService;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.acctype.admin.AccTypeDataAdmin;
import com.yss.ams.base.information.modules.sys.acctype.dao.AccTypeSqlBuilder;
import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeDataService;

public class AccTypeDataService implements IAccTypeDataService {

	private AccTypeDataAdmin accTypeAdmin = null;
	
	public AccTypeDataService() {
		accTypeAdmin = new AccTypeDataAdmin(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class),
				new AccTypeSqlBuilder());
	}
	
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @param code ：C_DAT_CODE的值
	 * @return 资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public AccType getDataByCode(String code) throws ServiceException {
		try {
			return accTypeAdmin.getDataByCode(code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有记录
	 * @return	资产类型字典V_S_DAT_ASS_TYPE的所有记录的AccType对象集合
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<AccType> getDataList() throws ServiceException {
		try {
			return accTypeAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 用所有资产类型字典表数据初始化QueryRes对象
	 * @return 已经初始化的QueryRes类型对象
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = accTypeAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_accType");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_accType",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据类型获取相应资产类型数据
	 * @param types:String类型数组
	 * @return 相应资产类型数据集合
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return accTypeAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据指定类型的资产类型字典表数据初始化QueryRes对象
	 * @return 初始化了的QueryRes对象
	 * @throws ServiceException
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = accTypeAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_accType");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_accType",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有 
	 * 资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @return hashMap类型的资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return accTypeAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @param keys String[]类型数组
	 * @return	符合条件的AccType对象集合
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return accTypeAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据，并用此集合初始化QueryRes对象
	 * @param keys String[]类型数组
	 * @return	初始化后的QueryRes对象
	 * @throws ServiceException
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = accTypeAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_accType");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_accType",InformationActivator.class));
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
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @param pojoCode ：C_DAT_CODE的值
	 * @return 资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的一条记录
	 * @throws ServiceException
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return accTypeAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @param listKey List类型集合
	 * @return hashMap类型的资产类型代码C_DAT_CODE和资产类型名称C_DAT_NAME的集合
	 * @throws ServiceException
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return accTypeAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
