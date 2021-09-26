package com.yss.ams.sec.information.modules.sv.secSoldBack.service.impl;


import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseService;
import com.yss.ams.sec.information.modules.sv.secSoldBack.dao.SecSoldBackBuilder;
import com.yss.ams.sec.information.modules.sv.secSoldBack.dao.SecSoldBackDao;
import com.yss.ams.sec.information.support.modules.sv.secSoldBack.service.ISecSoldBackService;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecSoldBackService extends ServiceBus<SecBaseService> implements ISecSoldBackService {

	private SecSoldBackDao serviceDao = null;
	public SecSoldBackService() throws Exception{
		serviceDao = new SecSoldBackDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecSoldBackBuilder());
		dao = serviceDao;
		}
	
}
