package com.yss.ams.sec.information.modules.plateset.plate.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.plateset.plate.admin.PlateDataAdmin;
import com.yss.ams.sec.information.modules.plateset.plate.dao.PlateSqlBuilder;
import com.yss.ams.sec.information.modules.plateset.platesub.dao.PlateSubDao;
import com.yss.ams.sec.information.modules.plateset.platesub.dao.PlateSubSqlBuilder;
import com.yss.ams.sec.information.modules.plateset.platesub.service.impl.PlateSubService;
import com.yss.ams.sec.information.support.modules.plateset.plate.service.IPlateDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 板块数据服务类
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class PlateDataService implements IPlateDataService {
	private PlateSubDao serviceDao = null;
	private PlateDataAdmin plateDataAdmin = null;
	private PlateSubService plateSubService = null;
	public PlateDataService() throws Exception {
		plateDataAdmin = new PlateDataAdmin(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new PlateSqlBuilder());
		plateSubService = new PlateSubService();
		serviceDao = new PlateSubDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new PlateSubSqlBuilder());
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return plateDataAdmin.getDataList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return null;
	}

	@Override
	public String getMenuId() {
		return null;
	}

	@Override
	public void setMenuId(String menuId) {

	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return plateDataAdmin.getConvertKeyMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return plateDataAdmin.getPojoByCode(pojoCode);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return plateDataAdmin.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String updatePlateById(List<BasePojo> pojoList) {
		return plateSubService.updatePlateById(pojoList);
	}

	@Override
	public void insert(List<BasePojo> list) {
		serviceDao.insert(list);
	}

}
