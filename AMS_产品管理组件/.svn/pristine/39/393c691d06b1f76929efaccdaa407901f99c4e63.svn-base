package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeOrgService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaTradeOrgService extends
		ServiceBus<PortRelaTradeOrgService> implements
		IPortRelaTradeOrgService {

	private IPortRelaService baseService = null;
	private PortRelaDao serviceDao = null;

	public PortRelaTradeOrgService() throws Exception {
		baseService =new PortRelaService();
		serviceDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortRelaSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 组合关联期货公司
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaTradeOrg(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaTradeOrg(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}
	
	/**
	 * 组合关联期货公司SET界面使用
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaTradeOrgSet(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaTradeOrgSet(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}

	/**
	 * 组合关联期货公司
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public QueryRes queryPortRelaTradeOrgPage(HashMap<String, Object> paraMap,
			PageInation page) throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaTradeOrgPage(paraMap, page);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}

}
