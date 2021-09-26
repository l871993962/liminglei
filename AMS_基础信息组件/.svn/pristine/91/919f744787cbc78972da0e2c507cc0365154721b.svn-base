package com.yss.ams.base.information.modules.sys.deexp.service.impl;

//import com.yss.dict.deexp.dao.DeExpDao;
//import com.yss.dict.deexp.dao.DeExpSqlBuilder;
//import com.yss.dict.deexp.service.IDeExpService;
import com.yss.ams.base.information.modules.sys.deexp.dao.DeExpDao;
import com.yss.ams.base.information.modules.sys.deexp.dao.DeExpSqlBuilder;
import com.yss.ams.base.information.support.sys.deexp.service.IDeExpService;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.ams.base.information.support.sys.deexp.service.IDeExpService;
import com.yss.framework.db.DbPoolFactory;

/**
 * 表达式字典表T_S_DE_EXP service
 *
 */
public class DeExpService extends ServiceBus<DeExpService> implements IDeExpService{
	private DeExpDao serviceDao = null;
	public DeExpService(){
		serviceDao = new DeExpDao(DbPoolFactory.getInstance().getPool(), new DeExpSqlBuilder());
		dao = serviceDao;
	}
}
