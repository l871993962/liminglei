package com.yss.uco.elecreco.er.reverse.out.eryeb.service.impl;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.reverse.out.eryeb.dao.ErYebOutDao;
import com.yss.uco.elecreco.er.reverse.out.eryeb.dao.ErYebOutSqlBuilder;
import com.yss.uco.elecreco.er.reverse.out.eryeb.service.IErYebOutService;

public class ErYebOutService extends ServiceBus<ErYebOutService> implements IErYebOutService {

	private ErYebOutDao serviceDao = null;
	public ErYebOutService() throws Exception {
		serviceDao = new ErYebOutDao(DbPoolFactory.getInstance().getPool(),new ErYebOutSqlBuilder());
		dao = serviceDao;
	}

}