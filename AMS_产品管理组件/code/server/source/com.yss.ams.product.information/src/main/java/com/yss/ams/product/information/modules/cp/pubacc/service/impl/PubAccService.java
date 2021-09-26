package com.yss.ams.product.information.modules.cp.pubacc.service.impl;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.support.modules.cp.pubacc.service.IPubAccService;
import com.yss.ams.product.information.modules.cp.pubacc.dao.PubAccDao;
import com.yss.ams.product.information.modules.cp.pubacc.dao.PubAccSqlBuilder;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;


public class PubAccService extends ServiceBus<PubAccService> implements
		IPubAccService {

	private PubAccDao serviceDao = null;

	public PubAccService() throws Exception {
		serviceDao = new PubAccDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PubAccSqlBuilder());
		dao = serviceDao;
	}

}
