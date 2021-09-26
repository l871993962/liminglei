package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBasePjSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBasePjService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 票据基本信息的service实现类。
 * @author tangshifeng 
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBasePjService extends ServiceBus<SecBaseService> implements ISecBasePjService {

	private SecBaseDao serviceDao = null;
	public SecBasePjService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBasePjSqlBuilder());
		dao = serviceDao;
		}
	
	/**
	 * 根据票据代码获取票据信息
	 * @param secCode
	 * @return 票据信息
	 */
	public BasePojo queryPjSecBase(String secCode) {
		return this.serviceDao.querySecBaseBySecCode(secCode);
	}

}
