package com.yss.ams.base.information.modules.sys.dztype.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.dztype.admin.DzTypeAdmin;
import com.yss.ams.base.information.support.sys.dztype.pojo.DzType;
import com.yss.ams.base.information.support.sys.dztype.service.IDzTypeDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 对账类型字典服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class DzTypeDataService implements IDzTypeDataService {
	protected String menuId = "";
	private DzTypeAdmin dzTypeAdmin = null;
	public DzTypeDataService() {
		dzTypeAdmin = new DzTypeAdmin();
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param code 对账类型代码
	 * @return DzType
	 */
	@SuppressWarnings("unchecked")
	public DzType getDataByCode(String code) throws ServiceException {
		DzType pojo = null;
		try{
			pojo = dzTypeAdmin.getDataByCode(code);
		}catch(Exception ex){
			throw new ServiceException(ex);
		}
		
		return pojo;
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典表数据对应pojo的list
	 * @param
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<DzType> getDataList() throws ServiceException {
		List<DzType> pojos = null;
		try {
			pojos = dzTypeAdmin.getAllData();
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return pojos;
	}
	
	/**
	 * 获取包含menuId、DataList、HeadKeyList属性的查询结果pojo
	 * @param
	 * @return QueryRes
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = dzTypeAdmin.getAllData();
			res.setDataList(dataList);
			this.menuId = "dzcode";
			Context context = YssContextFactory.getInstance().getAppContext(InformationActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
			res.setMenuId(menuId);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
	 * @return List
	 * @param keys
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<K> pojos = null;
		try {
			pojos = dzTypeAdmin.getDataByKeys(keys);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return pojos;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
	 * @return List
	 * @param types
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		List<K> pojos = null;
		try {
			pojos = dzTypeAdmin.getDataByTypes(types);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return pojos;
	}
	
	/**
	 * 获取包含DataList、HeadKeyList属性的查询结果pojo
	 * @return QueryRes
	 * @param keys
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = dzTypeAdmin.getDataByKeys(keys);
			res.setDataList(dataList);
			Context context = YssContextFactory.getInstance().getAppContext(InformationActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}
	
	/**
	 * 获取包含DataList、HeadKeyList属性的查询结果pojo
	 * @return QueryRes
	 * @param keys
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = dzTypeAdmin.getDataByTypes(types);
			res.setDataList(dataList);
			Context context = YssContextFactory.getInstance().getAppContext(InformationActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典代码和对账类型字典名称转Map
	 * @return HashMap
	 * @param
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> map = null;
		try {
			map = dzTypeAdmin.getKeyConvertMap();
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return map;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param pojoCode
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return (K) dzTypeAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据对账类型代码获取所有对账类型字典代码和对账类型字典名称转Map
	 * @param listKey
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return dzTypeAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
