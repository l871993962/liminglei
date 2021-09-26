package com.yss.ams.base.information.modules.sys.dvaitem.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.dvaitem.dao.DavItemDao;
import com.yss.ams.base.information.modules.sys.dvaitem.dao.DavItemSqlBuilder;
import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemService;
//import com.yss.ams.base.information.support.sys.dvaitem.service.IDvaItemService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.dict.dvaitem.dao.DavItemDao;
//import com.yss.dict.dvaitem.dao.DavItemSqlBuilder;
//import com.yss.dict.dvaitem.service.IDvaItemService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;

/**
 * 核算业务项字典T_S_DVA_ITEM Service
 *
 */
@DefaultCacheRefresh(group = CacheGroup.DVAITEM)
public class DvaItemService extends ServiceBus<DvaItemService> implements IDvaItemService{
	String CorpOrgMemuId = "base_dvaitemquy";
	private DavItemDao serviceDao;
	public DvaItemService(){
		serviceDao = new DavItemDao(DbPoolFactory.getInstance().getPool(),new DavItemSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 查询组织架构信息具体实现方法
	 */
	public QueryRes getDvaItemsTreeData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryTreeViewData(paraMap,this.bundleContext);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(InformationActivator.class).getListHeadMap(CorpOrgMemuId);
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("zzywgl_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询组织架构信息时失败!", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
}
