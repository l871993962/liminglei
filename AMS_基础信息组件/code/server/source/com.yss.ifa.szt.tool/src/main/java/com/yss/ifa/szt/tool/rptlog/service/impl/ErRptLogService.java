package com.yss.ifa.szt.tool.rptlog.service.impl;
import java.util.Date;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.ifa.szt.tool.rptlog.dao.ErRptLogDao;
import com.yss.ifa.szt.tool.rptlog.dao.ErRptLogSqlBuilder;
import com.yss.ifa.szt.tool.rptlog.service.IErRptLogService;

public class ErRptLogService extends ServiceBus<ErRptLogService> implements IErRptLogService {

	private ErRptLogDao serviceDao = null;
	public ErRptLogService() throws Exception {
		serviceDao = new ErRptLogDao(DbPoolFactory.getInstance().getPool(),new ErRptLogSqlBuilder());
		dao = serviceDao;
	}
	
	@Override
	public void deleteRptLog(int day) {
		String date = DateUtil.dateToString(DateUtil.nextDay(new Date(), -day), "yyyyMMdd");
		serviceDao.deleteRptLog(date);
	}

	@Override
	public String queryLogById(String id) {
		return serviceDao.queryLogById(id);
	}

	@Override
	public void updateSN(String oldSn, String newSn) {
		serviceDao.updateSN(oldSn, newSn);
	}

}