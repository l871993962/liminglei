package com.yss.ams.product.information.modules.aa.portcustom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.aa.portcustom.admin.PortCustomDataAdmin;
import com.yss.ams.product.information.modules.aa.portcustom.dao.PortCustomSqlBuilder;
import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomService;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * <用户自定义组合>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortCustomService extends ServiceBus<PortCustomService> implements
		IPortCustomService {

	private PortCustomDataAdmin admin = null;

	public PortCustomService() throws Exception {
		admin = new PortCustomDataAdmin(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortCustomSqlBuilder());
	}

	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException {
		return admin.getShowType(codeMap);
	}

	public ArrayList<String> getAssetType() throws ServiceException {
		return admin.getAssetType();
	}

	public String deleteCustomPort(HashMap<String, String> param)
			throws ServiceException {
		return admin.deleteCustomPort(param);
	}

	public ArrayList<String> getUserDefaultPort(HashMap<String, String> paradict) throws ServiceException {
		return admin.getUserDefaultPort(paradict);
	}

	public String insertCustomPort(List<PortCustom> list, String type)
			throws ServiceException {
		return admin.insertCustomPort(list, type);
	}

}
