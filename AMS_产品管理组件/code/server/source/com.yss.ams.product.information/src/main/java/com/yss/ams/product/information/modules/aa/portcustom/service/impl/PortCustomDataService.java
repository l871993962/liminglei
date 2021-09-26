package com.yss.ams.product.information.modules.aa.portcustom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.aa.portcustom.admin.PortCustomDataAdmin;
import com.yss.ams.product.information.modules.aa.portcustom.dao.PortCustomSqlBuilder;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * <用户自定义组合>数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortCustomDataService implements IPortCustomDataService {
	private PortCustomDataAdmin portCustomAdmin = null;

	public PortCustomDataService() {
		portCustomAdmin = new PortCustomDataAdmin(DbPoolFactory.getInstance()
				.getPool(),
				new PortCustomSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return null;
	}

	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return null;
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPortCode(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPortCodeByArray(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPortCodeByMenuid(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException {
		return portCustomAdmin.getShowType(codeMap);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return new HashMap<String, String>();
	}

	@Override
	public ArrayList<String> getAssetTypeOnlyCode() throws ServiceException {
		// TODO Auto-generated method stub
		return portCustomAdmin.getAssetTypeOnlyCode();
	}

}
