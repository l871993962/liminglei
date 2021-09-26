package com.yss.ams.base.information.modules.bi.ie.service.impl;

import com.yss.ams.base.information.modules.bi.ie.dao.IeDao;
import com.yss.ams.base.information.modules.bi.ie.dao.IeSqlBuilder;
import com.yss.ams.base.information.support.bi.ie.service.IIeService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * @classDesc
 * @version 1.0 2012-11-29
 * @author yh
 */

/**
 * 收支代码设置服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@DefaultCacheRefresh(group = CacheGroup.IE)
public class IeService extends ServiceBus<IeService> implements IIeService {
	private IeDao serviceDao = null;

	public IeService() throws Exception {
		serviceDao = new IeDao(DbPoolFactory.getInstance().getPool(),
				new IeSqlBuilder());
		dao = serviceDao;
	}

}
