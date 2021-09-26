package com.yss.uco.elecreco.er.erdztype.service.impl;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.activator.ErActivator;
import com.yss.uco.elecreco.er.erdztype.admin.ErDzTypeAdmin;
import com.yss.uco.elecreco.er.erdztype.dao.ErDzTypeDao;
import com.yss.uco.elecreco.er.erdztype.dao.ErDzTypeSqlBuilder;
import com.yss.uco.elecreco.support.bean.ErDzType;
import com.yss.uco.elecreco.support.service.IErDzTypeDataService;

public class ErDzTypeDataService extends ServiceBus<ErDzTypeDataService> implements IErDzTypeDataService {

	private ErDzTypeDao serviceDao = null;
	private ErDzTypeAdmin dzTypeAdmin = null;
	public ErDzTypeDataService() throws Exception {
		serviceDao = new ErDzTypeDao(DbPoolFactory.getInstance().getPool(),new ErDzTypeSqlBuilder());
		dao = serviceDao;
		dzTypeAdmin = new ErDzTypeAdmin();
	}
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param code 对账类型代码
	 * @return DzType
	 */
	@SuppressWarnings("unchecked")
	public ErDzType getDataByCode(String code) throws ServiceException {
		ErDzType pojo = null;
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
	public List<ErDzType> getDataList() throws ServiceException {
		List<ErDzType> pojos = null;
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
			this.menuId = "base_erdztype";
			Context context = YssContextFactory.getInstance().getAppContext(ErActivator.class);
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
			Context context = YssContextFactory.getInstance().getAppContext(ErActivator.class);
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
			Context context = YssContextFactory.getInstance().getAppContext(ErActivator.class);
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

	@Override
	public List<BasePojo> getGeneDzType() {
		return this.serviceDao.getGeneDzType();
	}

	@Override
	public List<BasePojo> getAllDzTypeMap() {
		return this.serviceDao.getAllDzTypeMap();
	}

}