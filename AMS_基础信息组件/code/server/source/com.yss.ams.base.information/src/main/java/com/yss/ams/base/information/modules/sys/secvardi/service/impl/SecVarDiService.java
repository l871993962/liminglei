package com.yss.ams.base.information.modules.sys.secvardi.service.impl;

import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.secvardi.dao.SecVarDiDao;
import com.yss.ams.base.information.modules.sys.secvardi.dao.SecVarDiSqlBuilder;
import com.yss.ams.base.information.support.sys.secvardi.service.ISecVarDiService;
//import com.yss.bundle.activator.UcoActivator;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 证券品种字典T_S_DA_SEC_VAR DiService
 *
 */
public class SecVarDiService extends ServiceBus<SecVarDiService> implements ISecVarDiService {

	private SecVarDiDao serviceDao = null;
	public SecVarDiService() throws Exception{
		serviceDao = new SecVarDiDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SecVarDiSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 覆盖父类ServiceBus的fillResultObject方法
	 */
	protected void fillResultObject(QueryRes queryRes, List<BasePojo> dataList,
			int countAll, PageInation page, String menuId) throws Exception {

		queryRes.setDataList(dataList);
		queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,InformationActivator.class));

		if (page == null) {
			page = new PageInation();
		} else {
			page.setTotalNum(countAll);
		}
		queryRes.setPage(page);

		setShowConvertAssemble(queryRes);
		queryRes.setOperRes(MvcConstant._Success);
		queryRes.setMenuId(menuId);
	}
}
