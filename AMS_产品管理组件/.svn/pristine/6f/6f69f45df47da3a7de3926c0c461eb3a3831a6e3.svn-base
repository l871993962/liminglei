package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.List;

import com.yss.ams.product.information.modules.ab.port.admin.PortAdmin;
import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.assetsTree_report.service.IAssetsTreeReportService;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;

public class AssetsTreeReportService implements IAssetsTreeReportService {

	private PortAdmin portAdmin=null;
	
	public AssetsTreeReportService() {
		
		portAdmin = new PortAdmin(DbPoolFactory.getInstance().getPool(),
				new PortSqlBuilder());
	}

	
	
	@Override
	public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode, String userCode)
			throws Exception {
		return  portAdmin.portFilter(isDataRight, datClass, dvPortCode, trCode, userCode);
		
		
	}

}
