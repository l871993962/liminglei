package com.yss.uco.elecreco.er.tmplrela.service.impl;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.tmplrela.dao.DzTmplRelaDao;
import com.yss.uco.elecreco.er.tmplrela.dao.DzTmplRelaSqlBuilder;
import com.yss.uco.elecreco.er.tmplrela.service.IDzTmplRelaService;

/**
 * @author liuxiang 2015年2月13日
 */
public class DzTmplRelaService extends ServiceBus<DzTmplRelaService> implements
		IDzTmplRelaService {

	private DzTmplRelaDao serviceDao = null;

	public DzTmplRelaService() {
		serviceDao = new DzTmplRelaDao(DbPoolFactory.getInstance().getPool(),
				new DzTmplRelaSqlBuilder());
		dao = serviceDao;
	}
}
