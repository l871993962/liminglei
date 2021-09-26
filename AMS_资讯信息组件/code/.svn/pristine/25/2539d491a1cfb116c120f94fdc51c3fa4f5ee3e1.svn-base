package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseGpSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseGpService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 股票基本信息 普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseGpService extends ServiceBus<SecBaseService> implements ISecBaseGpService {

	private SecBaseDao serviceDao = null;
	public SecBaseGpService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseGpSqlBuilder());
		dao = serviceDao;
		}

}
