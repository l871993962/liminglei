package com.yss.ams.product.information.modules.ab.assetsTree_a.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.assetsTree_a.dao.AssetsTree_ADao;
import com.yss.ams.product.information.modules.ab.assetsTree_a.dao.AssetsTree_ASqlBuilder;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetTrees_ADataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * <A区资产树型结构>数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_ADataService implements IAssetTrees_ADataService {

	private AssetsTree_ADao serviceDao = null;

	/**
	 * 树形结构A区数据服务
	 * @throws Exception
	 */
	public AssetsTree_ADataService() throws Exception {
		serviceDao = new AssetsTree_ADao(DbPoolFactory.getInstance().getPool(), new AssetsTree_ASqlBuilder());
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
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return serviceDao.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return serviceDao.getKeyConvertMap();
	}

}
