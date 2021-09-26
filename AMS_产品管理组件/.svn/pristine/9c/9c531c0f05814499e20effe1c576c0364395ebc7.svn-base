package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaIndexService;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaIndexService extends ServiceBus<PortRelaIndexService>
		implements IPortRelaIndexService {

	private IPortRelaService baseService = null;
	private PortRelaDao serviceDao = null;

	public PortRelaIndexService() throws Exception {
		baseService = new PortRelaService();
		serviceDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortRelaSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public QueryRes queryPortRelaIndexPage(HashMap<String, Object> paraMap,
			PageInation page) {
		return baseService.queryPortRelaIndexPage(paraMap, page);

	}

	@Override
	public QueryRes queryPortRelaIndex(HashMap<String, Object> paraMap) {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaIndex(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}
}
