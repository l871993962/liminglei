package com.yss.uco.elecreco.er.repcolcfg.service.impl;
import java.util.List;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.repcolcfg.dao.DzRepColCfgDao;
import com.yss.uco.elecreco.er.repcolcfg.dao.DzRepColCfgSqlBuilder;
import com.yss.uco.elecreco.er.repcolcfg.pojo.DzRepColCfg;
import com.yss.uco.elecreco.er.repcolcfg.service.IDzRepColCfgService;

public class DzRepColCfgService extends ServiceBus<DzRepColCfgService> implements IDzRepColCfgService {

	private DzRepColCfgDao serviceDao = null;
	public DzRepColCfgService() throws Exception {
		serviceDao = new DzRepColCfgDao(DbPoolFactory.getInstance().getPool(),new DzRepColCfgSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public List<DzRepColCfg> getDzRepColCfgs(String dzType, String reportCode) {
		return serviceDao.getDzRepColCfgs(dzType, reportCode);
	}
//	@Override
//	public boolean isHaveCfg(String reportCode) {
//		return serviceDao.isHaveCfg(reportCode);
//	}

}