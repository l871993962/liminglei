package com.yss.ams.base.information.modules.sys.dai.service.impl;

//import com.yss.dict.dai.dao.AccProDao;
//import com.yss.dict.dai.dao.AccProSqlBuilder;
//import com.yss.dict.dai.service.IAccProService;
import com.yss.ams.base.information.modules.sys.dai.dao.AccProDao;
import com.yss.ams.base.information.modules.sys.dai.dao.AccProSqlBuilder;
import com.yss.ams.base.information.support.sys.dai.service.IAccProService;
//import com.yss.ams.base.information.support.sys.dai.service.IAccProService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 核算项目字典表T_S_DAI_ITEM service类 
 *
 */
@DefaultCacheRefresh(group = CacheGroup.DAI)
public class AccProService extends ServiceBus<AccProService> implements
		IAccProService {
	private AccProDao serviceDao = null;

	/**
	 * 构造方法
	 * @throws Exception
	 */
	public AccProService() throws Exception {
		serviceDao = new AccProDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new AccProSqlBuilder());
		dao = serviceDao;
	}

}
