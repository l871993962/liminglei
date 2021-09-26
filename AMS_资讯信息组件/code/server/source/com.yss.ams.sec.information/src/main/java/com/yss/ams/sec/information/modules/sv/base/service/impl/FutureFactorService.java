package com.yss.ams.sec.information.modules.sv.base.service.impl;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.ams.sec.information.modules.sv.base.dao.FutureFactorDao;
import com.yss.ams.sec.information.modules.sv.base.dao.FutureFactorSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.service.IFutureFactorService;

/**
 * 期货结算债券转换信息 普通服务类
 * @author gongyue
 * 资讯信息拆分    STORY #42948 资讯信息管理组件化拆分
 */
public class FutureFactorService extends ServiceBus<FutureFactorService>
		implements IFutureFactorService {

	private FutureFactorDao serviceDao = null;

	public FutureFactorService() {
		serviceDao = new FutureFactorDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new FutureFactorSqlBuilder());
		dao = serviceDao;
	}
}
