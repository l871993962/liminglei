package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaMemberService;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 
 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
 * 
 */
public class PortRelaMemberService extends ServiceBus<PortRelaMemberService>
		implements IPortRelaMemberService {

	private IPortRelaService baseService = null;
	private PortRelaDao serviceDao = null;

	public PortRelaMemberService() throws Exception {
		baseService = new PortRelaService();
		serviceDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(
				ProductInfoActivator.class), new PortRelaSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public QueryRes queryPortRelaMember(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaMember(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,
				ProductInfoActivator.class));
		return res;
	}

	@Override
	public QueryRes queryPortRelaMemberPage(HashMap<String, Object> paraMap,
			PageInation page) throws Exception {
		return baseService.queryPortRelaMemberPage(paraMap, page);
	}

}
