package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseLlSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseLlService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;


/**
 * 利率品种信息 普通服务类
 * @author shiliang
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseLlService extends ServiceBus<SecBaseService> implements ISecBaseLlService {
	private SecBaseDao serviceDao = null;

	public SecBaseLlService() throws Exception
	{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseLlSqlBuilder());
		dao = serviceDao;
	}
}
