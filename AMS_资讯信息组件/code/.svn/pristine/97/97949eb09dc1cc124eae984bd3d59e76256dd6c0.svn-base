package com.yss.ams.sec.information.modules.sv.secbasejf.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseService;
import com.yss.ams.sec.information.modules.sv.secbasejf.dao.SecBaseJfDao;
import com.yss.ams.sec.information.modules.sv.secbasejf.dao.SecBaseJfSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.secbasejf.service.ISecBaseJfService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 计费证券信息实现类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseJfService extends ServiceBus<SecBaseService> implements
		ISecBaseJfService {
	
	private SecBaseJfDao secBaseJfDao = null;

	public SecBaseJfService() throws Exception {
		secBaseJfDao = new SecBaseJfDao(YssDbPoolFactory.getInstance().getDbPool(
				SecInfoActivator.class), new SecBaseJfSqlBuilder());
		dao = secBaseJfDao;
	}
	
	
	/**
	 * 嵌套窗体组合关联计费证券信息SET界面查询（废弃）
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @return
	 */
//	public QueryRes getPortRelaChargingSec(HashMap<String, Object> paraMap) {
//		QueryRes queryRes = new QueryRes();
//		String classString = "";
//		Class<?> clazz;
//		try {
//			classString = String.valueOf(paraMap.get("dataClass"));
//			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);
//
//			List<BasePojo> dataList = (List<BasePojo>) secBaseJfDao.getPortRelaChargingSec(
//					paraMap, clazz);
//			queryRes.setDataList(dataList);
//
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,UcoActivator.class));
//
//			setShowConvertAssemble(queryRes);
//			queryRes.setOperRes(MvcConstant._Success);
//			queryRes.setMenuId(menuId);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			queryRes.setOperRes(MvcConstant._Fault);
//		}
//		return queryRes;
//	}
	
	/**
	 * 嵌套窗体LIST界面查询计费证券信息
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @param pageIns
	 * @return
	 */
	public QueryRes queryRelaChargingSec(HashMap<String, Object> paraMap, PageInation pageIns){
		QueryRes queryRes = new QueryRes(); // LIST窗体查询
		try {
			
			List<BasePojo> dataList = (List<BasePojo>) secBaseJfDao.queryRelaChargingSec(
					paraMap, pageIns);
			if (pageIns == null) {
				pageIns = getDefaultPageInation();
			}
			
			int recCount = 0;
			recCount = queryRelaChargingSecCount(paraMap);
			
			fillResultObject(queryRes, dataList, recCount, pageIns, "portRelaChargingSec");
//			queryRes.setMenuId(menuId);
//			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,UcoActivator.class));
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("嵌套窗体LIST界面查询计费证券信息出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 嵌套窗体LIST界面查询计费证券信息的数量
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @return
	 */
	private int queryRelaChargingSecCount(HashMap<String, Object> paraMap) {
		return secBaseJfDao.queryRelaChargingSecCount(paraMap);
	}
	
	/**
	 * 嵌套窗体【选择】窗体查询计费证券信息
	 * 
	 * @param paraMap
	 * @param pageIns
	 * @return
	 */
	public QueryRes queryPortRelaChargingSec(HashMap<String, Object> paraMap, PageInation pageIns)
			throws Exception {
		QueryRes res = new QueryRes(); // 【选择】窗体查询
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			if (this.bundleContext == null) {
				this.bundleContext = YssContextFactory.getInstance()
						.getBundleContext(SecInfoActivator.class);
			}
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader
					.getPojoClassById(classString, this.bundleContext);
			if (pageIns == null) {
				pageIns = getDefaultPageInation();
			}
			
			List<BasePojo> dataList = (List<BasePojo>) secBaseJfDao
					.queryPortRelaChargingSec(paraMap, pageIns, clazz);
			res.setDataList(dataList);

			recCount = secBaseJfDao.queryPortRelaChargingSecCount(paraMap);
			pageIns.setTotalNum(recCount);
			res.setPage(pageIns);
			res.setHeadKeyList(ServiceAssistance.getListHead(menuId,SecInfoActivator.class));
			setShowConvertAssemble(res);
			res.setOperRes(MvcConstant._Success);
			res.setMenuId(menuId);
			
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("嵌套窗体LIST界面查询计费证券信息出错", ex);
			res.setOperRes(MvcConstant._Fault);
		}
		return res;
	}

}
