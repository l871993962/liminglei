package com.yss.ams.base.information.modules.sys.finitem.service.impl;

import com.yss.ams.base.information.modules.sys.finitem.dao.FinItemDao;
import com.yss.ams.base.information.modules.sys.finitem.dao.FinItemSqlBuilder;
import com.yss.ams.base.information.support.sys.finitem.service.IFinItemService;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.para.bi.finitem.dao.FinItemDao;
//import com.yss.para.bi.finitem.dao.FinItemSqlBuilder;
//import com.yss.para.bi.finitem.service.IFinItemService;
import com.yss.framework.db.DbPoolFactory;


/**
 * 财务项目字典表T_S_FIN_ITEM Service
 *
 */
public class FinItemService extends ServiceBus<FinItemService> implements IFinItemService {

	private FinItemDao serviceDao = null;
	public FinItemService() throws Exception{
		serviceDao = new FinItemDao(DbPoolFactory.getInstance().getPool(), new FinItemSqlBuilder());
		dao = serviceDao;
		}

}
