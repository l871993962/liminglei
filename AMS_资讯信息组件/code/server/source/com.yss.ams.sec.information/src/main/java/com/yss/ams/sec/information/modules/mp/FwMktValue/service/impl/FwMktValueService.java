package com.yss.ams.sec.information.modules.mp.FwMktValue.service.impl;


import com.yss.ams.sec.information.modules.mp.FwMktValue.dao.FwMktValueDao;
import com.yss.ams.sec.information.modules.mp.FwMktValue.dao.FwMktValueSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.FwMktValue.service.IFwMktValueService;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 远期外汇行情 普通服务类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class FwMktValueService extends ServiceBus<FwMktValueService> implements IFwMktValueService {

	private FwMktValueDao serviceDao = null;
	public FwMktValueService() throws Exception{
		serviceDao = new FwMktValueDao(DbPoolFactory.getInstance().getPool(), new FwMktValueSqlBuilder());
		dao = serviceDao;
		}

}
