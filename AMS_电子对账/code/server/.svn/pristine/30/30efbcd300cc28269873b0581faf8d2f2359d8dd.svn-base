package com.yss.uco.elecreco.er.ergzb.service.impl;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.ergzb.dao.ErXmlGzbDao;
import com.yss.uco.elecreco.er.ergzb.dao.ErXmlGzbSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.gz.IErXmlGzbService;

public class ErXmlGzbService extends ServiceBus<ErXmlGzbService> implements IErXmlGzbService {

	private ErXmlGzbDao serviceDao = null;
	public ErXmlGzbService() throws Exception{
		serviceDao = new ErXmlGzbDao(DbPoolFactory.getInstance().getPool(), new ErXmlGzbSqlBuilder());
		dao = serviceDao;
	}
	
	public String deleteBySn(String sn){
		return serviceDao.deleteBySn(sn);
	}
}
