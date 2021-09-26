package com.yss.ams.visaval.service.impl;

import com.yss.ams.visaval.activator.AvalActivator;
import com.yss.ams.visaval.dao.FunParamCNDao;
import com.yss.ams.visaval.support.service.IVisFunParamCNService;
import com.yss.framework.api.database.YssDbPoolFactory;

public class FunParamCNService implements IVisFunParamCNService{

	@Override
	public String getCode(String sql, String name) {
		FunParamCNDao dao = new FunParamCNDao(YssDbPoolFactory.getInstance().getDbPool(AvalActivator.class), null);
		return dao.getCode(sql, name);
	}
}
