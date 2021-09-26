package com.yss.fast.systemmanager.port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.port.admin.PortAdmin;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.platform.support.dataservice.service.IPortDeductionService;

/**
 * 
 * @author huyingzhao
 * STORY #73984 增值税纳税报表辅助部分优化需求
 */
@DefaultCacheRefresh(group = CacheGroup.PORT)
public class PortDeductionService implements IPortDeductionService {
	private PortAdmin portAdmin = null;
	
	public PortDeductionService(){
		portAdmin = new PortAdmin(DbPoolFactory.getInstance().getPool(),
			new PortSqlBuilder());
	}
	
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		return null;
	}

	@Override
	public String getMenuId() {
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		// 优先调用缓存中的数据
		IDataService dataService = null;
		if (YssContextFactory.getInstance().getOSGI()) {
			dataService = YssServiceFactory.getInstance().createService("IPortDataService");
		} else {
			dataService = HttpServiceFactory.getInstance().createService("IPortDataService");
		}

		List<Port> lstPort = dataService.getDataList();
		List<String> portList = new ArrayList<String>();
		for (Port port : lstPort) {
			if(port.getdATA_TYPE().equals("PORT_TYPE")
					//基本养老保险保险基金
					&&!port.getC_PORT_CODE_P().equals("ASS_JBYLBXBXJJ")
					//企业年金基金
					&&!port.getC_PORT_CODE_P().equals("ASS_QYNJJJ")
					//社会保障基金
					&&!port.getC_PORT_CODE_P().equals("ASS_SBJJ")
					//职业年金基金
					&&!port.getC_PORT_CODE_P().equals("ASS_ZYNJJJ"))
				portList.add(port.getC_PORT_CODE());
		}
		
		return portAdmin.portDeduction(portList);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return null;
	}

}
