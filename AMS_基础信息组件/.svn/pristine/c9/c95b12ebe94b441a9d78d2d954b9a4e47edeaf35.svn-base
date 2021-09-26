package com.yss.ams.base.information.modules.sys.dtatdattr.service.impl;

//import com.yss.dict.dtatdattr.dao.DtatdAttrDao;
//import com.yss.dict.dtatdattr.dao.DtatdAttrSqlBuilder;
//import com.yss.dict.dtatdattr.service.IDtatdAttrService;
import com.yss.ams.base.information.modules.sys.dtatdattr.dao.DtatdAttrDao;
import com.yss.ams.base.information.modules.sys.dtatdattr.dao.DtatdAttrSqlBuilder;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtatdAttrService;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtatdAttrService;
import com.yss.framework.db.DbPoolFactory;


/**
 * 交易属性字典T_S_DTA_TD_ATTR service
 *
 */
public class DtatdAttrService extends ServiceBus<DtatdAttrService> implements IDtatdAttrService{
	private DtatdAttrDao serviceDao = null;

	public DtatdAttrService() throws Exception {
		serviceDao = new DtatdAttrDao(DbPoolFactory.getInstance().getPool(), new DtatdAttrSqlBuilder());
		dao = serviceDao;
	}
}
