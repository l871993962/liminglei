package com.yss.uco.elecreco.er.template.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.template.dao.DzTemplateDao;
import com.yss.uco.elecreco.er.template.dao.DzTemplateSqlBuilder;
import com.yss.uco.elecreco.er.template.service.IDzTemplateDataService;

/**
 * @author liuxiang 2015年2月15日
 */
public class DzTemplateDataService implements IDzTemplateDataService {

	private String menuId = "dzTemplate";

	private DzTemplateDao dao =null;
	public DzTemplateDataService() {
		dao = new DzTemplateDao(DbPoolFactory.getInstance().getPool(),
				new DzTemplateSqlBuilder());
	}
	
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return dao.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return dao.getKeyConvertMap(listKey);
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return null;
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
		return this.menuId;
	}

	@Override
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
