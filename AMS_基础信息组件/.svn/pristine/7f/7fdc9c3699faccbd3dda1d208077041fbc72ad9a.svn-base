package com.yss.ams.base.information.modules.bi.ieLink.service.impl;

import com.yss.ams.base.information.modules.bi.ieLink.dao.IeLinkDao;
import com.yss.ams.base.information.modules.bi.ieLink.dao.IeLinkSqlBuilder;
import com.yss.ams.base.information.support.bi.ieLink.service.IIeLinkService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 收支连接设置服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeLinkService extends ServiceBus<IeLinkService> implements IIeLinkService {
	private IeLinkDao serviceDao = null;
	public IeLinkService() throws Exception{
		serviceDao = new IeLinkDao(DbPoolFactory.getInstance().getPool(), new IeLinkSqlBuilder());
		dao = serviceDao;
		}

}
