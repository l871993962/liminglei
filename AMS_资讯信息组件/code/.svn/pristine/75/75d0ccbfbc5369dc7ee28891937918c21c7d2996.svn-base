package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseWhSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseWhService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 外汇交易品种普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseWhService extends ServiceBus<SecBaseService> implements ISecBaseWhService {

	private SecBaseDao serviceDao = null;
	public SecBaseWhService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseWhSqlBuilder());
		dao = serviceDao;
		}

}
