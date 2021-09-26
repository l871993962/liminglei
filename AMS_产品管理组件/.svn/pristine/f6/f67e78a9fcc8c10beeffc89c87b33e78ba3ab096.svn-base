package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccountIdService;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaCashAccountIdService extends ServiceBus<PortRelaCashAccountIdService> implements
		IPortRelaCashAccountIdService {

	private IPortRelaService baseService = null;
	private PortRelaDao serviceDao = null;

	public PortRelaCashAccountIdService() throws Exception {
		baseService = new PortRelaService();
		serviceDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortRelaSqlBuilder());
		dao = serviceDao;
	}
	/**
	 * 组合关联股东账户
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public QueryRes queryPortRelaCashAccountId(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaCashAccountId(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}

	/**
	 * 组合关联股东账户
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public QueryRes queryPortRelaCashAccountIdPage(
			HashMap<String, Object> paraMap, PageInation page) throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaCashAccountIdPage(paraMap, page);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}

}
