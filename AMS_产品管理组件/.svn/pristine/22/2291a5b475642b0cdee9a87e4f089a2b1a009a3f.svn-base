package com.yss.ams.product.information.modules.pg.portgrouprela.service.impl;

import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.pg.portgrouprela.dao.PortGroupRelaDao;
import com.yss.ams.product.information.modules.pg.portgrouprela.dao.PortGroupRelaSqlBuilder;
import com.yss.fast.task.support.automatic.service.IAutomaticPortGroupService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * STORY #78622 华宝：路由条件上要支持选择群组的判断
 * @author lenovo
 * 
 */
public class AutomaticPortGroupServiceImpl extends ServiceBus<AutomaticPortGroupServiceImpl> implements
		IAutomaticPortGroupService {

	private PortGroupRelaDao serviceDao = null;

	public AutomaticPortGroupServiceImpl() throws Exception {
		serviceDao = new PortGroupRelaDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortGroupRelaSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public List<String> queryPortGroupByPortCode(String portCode) {
		// TODO Auto-generated method stub
		return serviceDao.queryPortGroupByPortCode(portCode);
	}

}
