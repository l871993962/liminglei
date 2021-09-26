package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 证券基本信息普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseService extends ServiceBus<SecBaseService> implements ISecBaseService {

	private SecBaseDao serviceDao = null;
	public SecBaseService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseSqlBuilder());
		dao = serviceDao;
		}

}
