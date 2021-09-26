package com.yss.ams.sec.information.modules.ac.etfskep.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.ac.etfskep.dao.EtfSkepDao;
import com.yss.ams.sec.information.modules.ac.etfskep.dao.EtfSkepSqlBuilder;
import com.yss.ams.sec.information.support.modules.ac.etfskep.service.IEtfSkepService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
/**
 * ETF股票篮子 普通服务类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class EtfSkepService extends ServiceBus<EtfSkepService> implements
		IEtfSkepService {

	private EtfSkepDao serviceDao = null;

	public EtfSkepService() throws Exception {
		serviceDao = new EtfSkepDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new EtfSkepSqlBuilder());
		dao = serviceDao;

	}

}
