package com.yss.uco.elecreco.er.erresview.service.impl;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.activator.ErActivator;
import com.yss.uco.elecreco.er.erresview.admin.ErResviewAdmin;
import com.yss.uco.elecreco.er.erresview.dao.ErResviewDao;
import com.yss.uco.elecreco.er.erresview.dao.ErResviewSqlBuilder;
import com.yss.uco.elecreco.er.erresview.pojo.ErResview;
import com.yss.uco.elecreco.er.erresview.service.IErResviewService;

public class ErResviewService extends ServiceBus<ErResviewService> implements IErResviewService{
	
	private ErResviewAdmin erResviewAdmin = null;
	private ErResviewDao serviceDao = null;
	public ErResviewService() {
		erResviewAdmin = new ErResviewAdmin();
		serviceDao = new ErResviewDao(DbPoolFactory.getInstance().getPool(), new ErResviewSqlBuilder());
		dao = serviceDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ErResview getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return  erResviewAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErResview> getDataListByTypes(String[] types)
			throws ServiceException {
		List<ErResview> pojos = null;
		try {
			pojos = erResviewAdmin.getDataByTypes(types);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return pojos;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = erResviewAdmin.getDataByTypes(types);
			res.setDataList(dataList);
			Context context = YssContextFactory.getInstance().getAppContext(ErActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErResview> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<ErResview> pojos = null;
		try {
			pojos = erResviewAdmin.getDataByKeys(keys);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return pojos;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = erResviewAdmin.getDataByKeys(keys);
			res.setDataList(dataList);
			Context context = YssContextFactory.getInstance().getAppContext(ErActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErResview> getDataList() throws ServiceException {
		List<ErResview> pojos = null;
		try {
			pojos = erResviewAdmin.getAllData();
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return pojos;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = erResviewAdmin.getAllData();
			res.setDataList(dataList);
			this.menuId = "base_erzyzb";
			
			Context context = YssContextFactory.getInstance().getAppContext(ErActivator.class);
			res.setHeadKeyList(context.getListHeadMap(menuId).getHeadKeyList());
			res.setMenuId(menuId);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ErResview getDataByCode(String dataCode)
			throws ServiceException {
		ErResview pojo = null;
		try{
			pojo = erResviewAdmin.getDataByCode(dataCode);
		}catch(Exception ex){
			throw new ServiceException(ex);
		}
		
		return pojo;
	}

	public List<String> queryItemCodesByPlanCode(String code)
	{
		List<String> codes = null;
		try{
			codes = serviceDao.queryItemCodesByPlanCode(code);
		}catch(Exception ex){
			throw new ServiceException(ex);
		}
		return codes;
	}
	
	public void deleteByPlanCode(String code)
	{
		try{
			serviceDao.deleteByPlanCode(code);
		}catch(Exception ex){
			throw new ServiceException(ex);
		}
	}

}
