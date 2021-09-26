package com.yss.ams.sec.information.modules.mp.secequ.service.impl;

import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquDao;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquPdService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;



/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * 证券配对信息
 * @author liyanjun
 * 20150625
 */
public class SecEquPdService extends ServiceBus<SecEquPdService> implements
	ISecEquPdService {

	private SecEquDao serviceDao = null;

	public SecEquPdService() throws Exception {
		serviceDao = new SecEquDao(DbPoolFactory.getInstance().getPool(), new SecEquSqlBuilder());
		dao = serviceDao;
	}

}
