package com.yss.ams.base.information.modules.sys.convertdict.zdorg.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.convertdict.zdorg.dao.ZdOrgDao;
import com.yss.ams.base.information.modules.sys.convertdict.zdorg.dao.ZdOrgSqlBuilder;
import com.yss.ams.base.information.support.sys.convertdict.zdorg.service.IZdOrgService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

public class ZdOrgService extends ServiceBus<ZdOrgService> implements IZdOrgService {
	
	private ZdOrgDao serviceDao = null;
	
	String CorpOrgMemuId = "base_zdorg";
	
	public ZdOrgService() throws Exception {
		serviceDao = new ZdOrgDao(DbPoolFactory.getInstance().getPool(), new ZdOrgSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 查询组织架构信息具体实现方法
	 */
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryTreeViewData(paraMap,this.bundleContext);
//			ListHeadDtl listHeadInfo = AppContext.getInstance().getListHeadMap(CorpOrgMemuId);
//			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			//queryRes.setHeadKeyList(headKeyList);

			queryRes.setHeadKeyList(ServiceAssistance.getListHead(CorpOrgMemuId,
					InformationActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(CorpOrgMemuId);
		} catch (Exception ex) {
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 是否有根节点
	 */
	public String checkHasRootNode() {
		return ((ZdOrgDao)dao).getCheckHasRootNode();
	}
}
