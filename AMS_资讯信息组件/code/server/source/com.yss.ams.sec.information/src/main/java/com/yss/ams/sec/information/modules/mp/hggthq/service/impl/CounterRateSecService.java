package com.yss.ams.sec.information.modules.mp.hggthq.service.impl;

import com.yss.ams.sec.information.modules.mp.hggthq.dao.CounterRateDao;
import com.yss.ams.sec.information.modules.mp.hggthq.dao.CounterRateSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateSecService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
/**
 * 证券回购收益行情 普通服务类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class CounterRateSecService extends ServiceBus<CounterRateSecService>
		implements ICounterRateSecService {
	private CounterRateDao serviceDao = null;

	public CounterRateSecService() {
		serviceDao = new CounterRateDao(DbPoolFactory.getInstance().getPool(),
				new CounterRateSqlBuilder());
		dao = serviceDao;
	}
}
