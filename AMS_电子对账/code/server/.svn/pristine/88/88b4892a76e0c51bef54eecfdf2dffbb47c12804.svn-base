package com.yss.uco.elecreco.er.reverse.out.erkmb.service.impl;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.reverse.out.erkmb.dao.ErKmbOutDao;
import com.yss.uco.elecreco.er.reverse.out.erkmb.dao.ErKmbOutSqlBuilder;
import com.yss.uco.elecreco.er.reverse.out.erkmb.service.IErKmbOutService;

public class ErKmbOutService extends ServiceBus<ErKmbOutService> implements IErKmbOutService {

	private ErKmbOutDao serviceDao = null;
	public ErKmbOutService() throws Exception {
		serviceDao = new ErKmbOutDao(DbPoolFactory.getInstance().getPool(),new ErKmbOutSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public String updateById(List<BasePojo> pojoList) {
		// TODO Auto-generated method stub
		return super.updateById(pojoList);
	}
	@Override
	public String deleteById(List<BasePojo> pojoList) {
		// TODO Auto-generated method stub
		return super.deleteById(pojoList);
	}

	
	
}