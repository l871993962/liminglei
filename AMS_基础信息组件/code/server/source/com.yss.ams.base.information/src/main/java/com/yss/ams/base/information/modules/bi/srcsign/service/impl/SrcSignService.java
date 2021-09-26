package com.yss.ams.base.information.modules.bi.srcsign.service.impl;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.srcsign.dao.SrcSignDao;
import com.yss.ams.base.information.modules.bi.srcsign.dao.SrcSignSqlBuilder;
import com.yss.ams.base.information.support.bi.srcsign.service.ISrcSignService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 来源标识服务
 * @author 马向峰 拆分
 * @Date 20170531
 */
public class SrcSignService extends ServiceBus<SrcSignService> implements ISrcSignService {

	private SrcSignDao serviceDao = null;
	public SrcSignService() throws Exception{
		serviceDao = new SrcSignDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SrcSignSqlBuilder());
		dao = serviceDao;
		}

}
