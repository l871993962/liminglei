package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.port.dao.PortDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDocDynamicCheckDefaultService;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.resource.mgr.pojo.BusinessData;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;

public class PortDocDynamicCheckDefaultService implements IPortDocDynamicCheckDefaultService {

	private PortDao serviceDao = null;

	public PortDocDynamicCheckDefaultService() throws Exception {
		serviceDao = new PortDao(DbPoolFactory.getInstance().getPool(YssConstant.DBSERVICE_NAME), new PortSqlBuilder());
	}
	
	@Override
	public String dynamicCheck(String docTypeName, List<ResourceMgr> mgrList) {
		StringBuffer buffer = new StringBuffer();
		if (mgrList == null || mgrList.size() == 0) {
			buffer.append(docTypeName + "类型请至少上传1个附件(Default)");
			return buffer.toString();
		}
		
		for (ResourceMgr mgr : mgrList) {
			// 测试用
			buffer.append("测试显示每条记录的虚拟目录: " + mgr.getC_VIRTUAL_PATH());
		}
		return buffer.toString();
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataTime(String funCode, 
			String businessBeginDate, String businessEndDate) {
		List<BusinessData> resList = new ArrayList<BusinessData>();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		if (!StringUtil.IsNullOrEmpty(businessBeginDate)) {
			paraMap.put("BEGIN_C_UPDATE_TIME", businessBeginDate);
		}
		if (!StringUtil.IsNullOrEmpty(businessEndDate)) {
			paraMap.put("END_C_UPDATE_TIME", businessEndDate);
		}
		try {
			List<BasePojo> list = serviceDao.queryByCondition(paraMap, Port.class);
			for (BasePojo pojo : list) {
				BusinessData data = new BusinessData();
				Port port = (Port)pojo;
				String portCode = port.getC_PORT_CODE();
				if (StringUtil.IsNullOrEmpty(portCode)) {
					portCode = "null";
				}
				data.setFunCode(funCode);
				data.setPortCode(portCode);
				data.setDataId(port.getId());
				data.setDataTime(port.getModifyDate());
				resList.add(data);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.log("查询失败", e);
		}
		return resList;
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataIds(String funCode, String dataIds) {
		List<BusinessData> resList = new ArrayList<BusinessData>();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		if (!StringUtil.IsNullOrEmpty(dataIds)) {
			paraMap.put("ARRAY_C_IDEN", dataIds);
		}
		try {
			List<BasePojo> list = serviceDao.queryByCondition(paraMap, Port.class);
			for (BasePojo pojo : list) {
				BusinessData data = new BusinessData();
				Port port = (Port)pojo;
				String portCode = port.getC_PORT_CODE();
				if (StringUtil.IsNullOrEmpty(portCode)) {
					portCode = "null";
				}
				data.setFunCode(funCode);
				data.setPortCode(portCode);
				data.setDataId(port.getId());
				data.setDataTime(port.getModifyDate());
				resList.add(data);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return resList;
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

}
