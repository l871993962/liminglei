package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseXhSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseXhService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
/**
 * 现货品种信息服务实现类
 * 
 * Added by shiliang,资讯信息组件拆分2017-06-26
 * 资讯信息拆分   STORY #42948 资讯信息管理组件化拆分
 * */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseXhService extends ServiceBus<SecBaseService> implements
		ISecBaseXhService {
	private SecBaseDao serviceDao = null;

	public SecBaseXhService() throws Exception {
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseXhSqlBuilder());
		dao = serviceDao;
	}

}
