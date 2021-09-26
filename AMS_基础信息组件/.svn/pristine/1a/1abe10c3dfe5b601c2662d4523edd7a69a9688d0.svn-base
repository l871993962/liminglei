package com.yss.ams.base.information.modules.sys.indexdi.service.impl;

import com.yss.ams.base.information.modules.sys.indexdi.dao.IndexdiDao;
import com.yss.ams.base.information.modules.sys.indexdi.dao.IndexdiSqlBuilder;
import com.yss.ams.base.information.support.sys.indexdi.service.IIndexdiService;
//import com.yss.ams.base.information.support.sys.indexdi.service.IIndexdiService;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.para.bi.indexdi.dao.IndexdiDao;
//import com.yss.para.bi.indexdi.dao.IndexdiSqlBuilder;
//import com.yss.para.bi.indexdi.service.IIndexdiService;
import com.yss.framework.db.DbPoolFactory;

/**
 * 合规指标类型字典T_S_INDEX Service
 *
 */
public class IndexdiService extends ServiceBus<IndexdiService> implements IIndexdiService{
	public IndexdiService(){
		dao = new IndexdiDao(DbPoolFactory.getInstance().getPool(),new IndexdiSqlBuilder());
	}
}
