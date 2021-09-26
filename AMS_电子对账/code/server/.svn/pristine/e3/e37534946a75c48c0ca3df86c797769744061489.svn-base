package com.yss.uco.elecreco.er.special.zb.service.impl;
import java.util.List;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.special.zb.dao.ErSpecialZbDao;
import com.yss.uco.elecreco.er.special.zb.dao.ErSpecialZbSqlBuilder;
import com.yss.uco.elecreco.support.bean.ErSpecialZb;
import com.yss.uco.elecreco.support.service.IErSpecialZbService;

public class ErSpecialZbService extends ServiceBus<ErSpecialZbService> implements IErSpecialZbService {

	private ErSpecialZbDao serviceDao = null;
	public ErSpecialZbService() throws Exception {
		serviceDao = new ErSpecialZbDao(DbPoolFactory.getInstance().getPool(),new ErSpecialZbSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public List<ErSpecialZb> getAllData() {
		return serviceDao.getAllData();
	}

}