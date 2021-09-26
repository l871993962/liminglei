package com.yss.uco.elecreco.er.reverse.manager.result.resrela.service.impl;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.dao.ResRelaDao;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.dao.ResRelaSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.service.IResRelaService;

public class ResRelaService extends ServiceBus<ResRelaService> implements IResRelaService {

	private ResRelaDao serviceDao = null;
	public ResRelaService() throws Exception {
		serviceDao = new ResRelaDao(DbPoolFactory.getInstance().getPool(),new ResRelaSqlBuilder());
		dao = serviceDao;
	}

}