package com.yss.ams.sec.information.modules.plateset.plate.admin;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.plateset.plate.dao.PlateDao;
import com.yss.ams.sec.information.modules.plateset.plate.dao.PlateSqlBuilder;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.database.YssDbPoolFactory;

/**
 * 板块业务管理类
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class PlateAAdmin extends BaseAdmin {
	private PlateDao svcDao = null;

	public PlateAAdmin() {
		svcDao = new PlateDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new PlateSqlBuilder());
	}

	public String checkSubData(String[] datas) {
		return svcDao.checkSubData(datas);
	}
}
