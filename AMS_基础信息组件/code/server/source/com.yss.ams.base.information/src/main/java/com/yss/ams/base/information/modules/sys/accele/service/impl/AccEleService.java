package com.yss.ams.base.information.modules.sys.accele.service.impl;

import com.yss.ams.base.information.modules.sys.accele.dao.AccEleDao;
import com.yss.ams.base.information.modules.sys.accele.dao.AccEleSqlBuilder;
import com.yss.ams.base.information.support.sys.accele.service.IAccEleService;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.para.bi.accele.dao.AccEleDao;
//import com.yss.para.bi.accele.dao.AccEleSqlBuilder;
//import com.yss.para.bi.accele.service.IAccEleService;

public class AccEleService extends ServiceBus<AccEleService> implements IAccEleService {

	private AccEleDao serviceDao = null;
	public AccEleService(){
		serviceDao = new AccEleDao(DbPoolFactory.getInstance().getPool(),new AccEleSqlBuilder());
		dao = serviceDao;
	}

}
