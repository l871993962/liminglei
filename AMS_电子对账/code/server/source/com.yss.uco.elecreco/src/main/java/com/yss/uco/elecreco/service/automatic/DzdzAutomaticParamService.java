package com.yss.uco.elecreco.service.automatic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.service.automatic.dao.AutomaticParamDao;
import com.yss.uco.elecreco.service.automatic.dao.AutomaticParamSqlBuilder;
import com.yss.uco.elecreco.support.service.IDzdzAutomaticParamService;

public class DzdzAutomaticParamService extends ServiceBus<DzdzAutomaticParamService> implements IDzdzAutomaticParamService{

	private AutomaticParamDao serviceDao = null;
	
	public DzdzAutomaticParamService() throws Exception {
		serviceDao = new AutomaticParamDao(DbPoolFactory.getInstance().getPool(), new AutomaticParamSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * STORY #90284 【富国基金】ETF重新跑账后对应的联接基金自动重新跑
	 */
	@Override
	public Map<String, List<String>> getLinkPortbyEtfPort(List<String> portList, Date date) {
		return serviceDao.getLinkPortbyEtfPort(portList, date);
	}
}
