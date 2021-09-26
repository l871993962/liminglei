package com.yss.uco.elecreco.er.repcfg.service.impl;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.reportdesign.template.service.IReportTemplateService;
import com.yss.uco.elecreco.er.repcfg.dao.DzRepCfgDao;
import com.yss.uco.elecreco.er.repcfg.dao.DzRepCfgSqlBuilder;
import com.yss.uco.elecreco.support.bean.DzRepCfgInsert;
import com.yss.uco.elecreco.support.service.IDzRepCfgService;

public class DzRepCfgService extends ServiceBus<DzRepCfgService> 
		implements IDzRepCfgService {
	private DzRepCfgDao serviceDao = null;
	
	private IReportTemplateService reportTemplateService = null;

	public DzRepCfgService() throws Exception {
		serviceDao = new DzRepCfgDao(DbPoolFactory.getInstance().getPool(),
				new DzRepCfgSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public <K extends BasePojo> List<K> queryByIds(String[] ids) {
		String idsStr = "";
		List<K> result = null;
		for (String id : ids) {
			idsStr += id + ",";
		}
		try {
			result = dao.queryByIds(idsStr, DzRepCfgInsert.class);
		} catch (Exception ex) {
			throw (ServiceException)ex;
		}
		return result;
	}

	@Override
	public QueryRes getReportTemplateTreeView(String ports) {
		if(this.reportTemplateService == null)
		{
			this.reportTemplateService = YssServiceFactory.getInstance().createService(IReportTemplateService.class);
		}
		return this.reportTemplateService.getReportTemplateTreeView(ports);
	}
}
