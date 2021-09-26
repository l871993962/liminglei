package com.yss.ams.base.information.modules.bi.account.service.impl;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.account.dao.PortRelaDao;
import com.yss.ams.base.information.modules.bi.account.dao.PortRelaSqlBuilder;
import com.yss.ams.base.information.support.bi.account.service.IPortRelaDataService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * STORY #76292 【4.5同步】同步“机构名称”、“账户明细类型”、“关联组合”至“现金账户”
 * @author lenovo
 *
 */
public class PortRelaDataServiceImpl extends ServiceBus<PortRelaDataServiceImpl> implements IPortRelaDataService {

	private PortRelaDao portRelaDao = null;
	
	public PortRelaDataServiceImpl() throws Exception {
		portRelaDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), 
				new PortRelaSqlBuilder());
	}
	
	@Override
	public String getCashPortByInfo(String relaCode) throws Exception {
		return portRelaDao.getCashPortByInfo(relaCode);
	}

	@Override
	public void deletePortRela(String relaCode, String portCodes) throws Exception {
		portRelaDao.deletePortRela(relaCode, portCodes);
	}

	@Override
	public void updatePortRela(String relaCode, String portCodes) throws Exception {
		portRelaDao.updatePortRela(relaCode, portCodes);
	}

	@Override
	public void insertPortRela(String relaCode, String portCodes) throws Exception {
		portRelaDao.insertPortRela(relaCode, portCodes);
	}
}
