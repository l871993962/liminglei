package com.yss.uco.elecreco.er.reverse.out.ergzb.service.impl;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.reverse.out.ergzb.dao.ErGzbOutDao;
import com.yss.uco.elecreco.er.reverse.out.ergzb.dao.ErGzbOutSqlBuilder;
import com.yss.uco.elecreco.er.reverse.out.ergzb.service.IErGzbOutService;

public class ErGzbOutService extends ServiceBus<ErGzbOutService> implements IErGzbOutService {

	private ErGzbOutDao serviceDao = null;
	public ErGzbOutService() throws Exception {
		serviceDao = new ErGzbOutDao(DbPoolFactory.getInstance().getPool(),new ErGzbOutSqlBuilder());
		dao = serviceDao;
	}

}