package com.yss.ams.base.information.modules.sys.dttdmode.service.impl;

//import com.yss.dict.dttdmode.dao.DttdModeDao;
//import com.yss.dict.dttdmode.dao.DttdModeDaoSqlBuilder;
//import com.yss.dict.dttdmode.service.IDttdModeService;
import com.yss.ams.base.information.modules.sys.dttdmode.dao.DttdModeDao;
import com.yss.ams.base.information.modules.sys.dttdmode.dao.DttdModeDaoSqlBuilder;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDttdModeService;
//import com.yss.ams.base.information.support.sys.dttdmode.service.IDttdModeService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 交易方式字典表T_S_DT_TD_MODE Service
 *
 */
@DefaultCacheRefresh(group = CacheGroup.DTTDMODE)
public class DttdModeService extends ServiceBus<DttdModeService> implements
		IDttdModeService {

	private DttdModeDao serviceDao = null;

	public DttdModeService() throws Exception {
		serviceDao = new DttdModeDao(DbPoolFactory.getInstance().getPool(),
				new DttdModeDaoSqlBuilder());
		dao = serviceDao;
	}
}
