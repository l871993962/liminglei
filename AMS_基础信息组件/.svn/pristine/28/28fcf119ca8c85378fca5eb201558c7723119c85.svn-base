package com.yss.ams.base.information.modules.bi.curypair.service.impl;

import com.yss.ams.base.information.modules.bi.curypair.dao.CuryPairDao;
import com.yss.ams.base.information.modules.bi.curypair.dao.CuryPairSqlBuilder;
import com.yss.ams.base.information.support.bi.curypair.service.ICuryPairService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 货币对服务
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class CuryPairService extends ServiceBus<CuryPairService> implements ICuryPairService {

	private CuryPairDao serviceDao = null;
	public CuryPairService() throws Exception{
		serviceDao = new CuryPairDao(DbPoolFactory.getInstance().getPool(), new CuryPairSqlBuilder());
		dao = serviceDao;
		}

}
