package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseHgSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseHgService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 回购基本信息  普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseHgService extends ServiceBus<SecBaseService> implements ISecBaseHgService {

	private SecBaseDao serviceDao = null;
	public SecBaseHgService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseHgSqlBuilder());
		dao = serviceDao;
		}

}
