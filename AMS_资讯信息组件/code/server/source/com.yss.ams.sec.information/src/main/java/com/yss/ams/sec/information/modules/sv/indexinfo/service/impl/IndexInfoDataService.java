package com.yss.ams.sec.information.modules.sv.indexinfo.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ams.sec.information.modules.sv.indexinfo.admin.IndexInfoDataAdmin;
import com.yss.ams.sec.information.modules.sv.indexinfo.dao.IndexInfoSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.service.IIndexInfoDataService;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexInfoDataService implements IIndexInfoDataService {

	private IndexInfoDataAdmin indexInfoAdmin = null;

	public IndexInfoDataService() {
		indexInfoAdmin = new IndexInfoDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new IndexInfoSqlBuilder());
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
		try {
			return indexInfoAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return indexInfoAdmin.getDataByCode(dataCode);
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
		return indexInfoAdmin.getKeyConvertMap();
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return indexInfoAdmin.getDataByCode(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return indexInfoAdmin.getKeyConvertMap(listKey);
	}

}
