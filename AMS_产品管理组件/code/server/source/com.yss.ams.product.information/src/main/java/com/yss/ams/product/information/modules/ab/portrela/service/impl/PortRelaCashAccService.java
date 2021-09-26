package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaCashAccService;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 
 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
 * 
 */
public class PortRelaCashAccService extends ServiceBus<PortRelaCashAccService>
		implements IPortRelaCashAccService {

	private IPortRelaService baseService = null;
	private PortRelaDao serviceDao = null;

	public PortRelaCashAccService() throws Exception {
		baseService = new PortRelaService();
		serviceDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(
				ProductInfoActivator.class), new PortRelaSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public QueryRes queryPortRelaCashAccPage(HashMap<String, Object> paraMap,
			PageInation page) throws Exception {
		return baseService.queryPortRelaCashAccPage(paraMap, page);
	}
	
	/**
	 * 组合关联投资经理
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaCashAcc(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaCashAcc(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}
}
