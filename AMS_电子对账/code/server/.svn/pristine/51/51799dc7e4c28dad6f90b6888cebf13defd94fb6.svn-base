package com.yss.uco.elecreco.er.transrepcfg;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.repcfg.dao.DzRepCfgDao;
import com.yss.uco.elecreco.er.transrepcfg.dao.DzTransRepCfgDao;
import com.yss.uco.elecreco.er.transrepcfg.dao.DzTransRepCfgSqlBuilder;
import com.yss.uco.elecreco.support.transrepcfg.IDzTransRepCfgService;

public class DzTransRepCfgService extends ServiceBus<DzTransRepCfgService> implements IDzTransRepCfgService {

	private DzTransRepCfgDao serviceDao = null;
	
	public DzTransRepCfgService() throws Exception {
		serviceDao = new DzTransRepCfgDao(DbPoolFactory.getInstance().getPool(),
				new DzTransRepCfgSqlBuilder());
		dao = serviceDao;
	}

}
