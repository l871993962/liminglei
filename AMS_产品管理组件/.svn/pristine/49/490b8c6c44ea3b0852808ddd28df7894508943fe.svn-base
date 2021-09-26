package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.List;

import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDocDynamicCheckServiceOne;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.resource.mgr.pojo.BusinessData;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;

public class PortDocDynamicCheckServiceOne implements IPortDocDynamicCheckServiceOne {

	private PortDao serviceDao = null;

	public PortDocDynamicCheckServiceOne() throws Exception {
		serviceDao = new PortDao(DbPoolFactory.getInstance().getPool(YssConstant.DBSERVICE_NAME), new PortSqlBuilder());
	}
	
	@Override
	public String dynamicCheck(String docTypeName, List<ResourceMgr> mgrList) {
		StringBuffer buffer = new StringBuffer();
		if (mgrList == null || mgrList.size() == 0) {
			buffer.append(docTypeName + "类型请至少上传1个附件(ONE)");
			return buffer.toString();
		}
		
		for (ResourceMgr mgr : mgrList) {
			// 测试用
			buffer.append("测试显示每条记录的虚拟目录: " + mgr.getC_VIRTUAL_PATH() + "(ONE)");
		}
		return buffer.toString();
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataTime(String funCode, String businessBeginDate, String businessEndDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataIds(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
