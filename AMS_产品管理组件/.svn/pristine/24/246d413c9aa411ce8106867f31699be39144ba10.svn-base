package com.yss.ams.product.information.modules.cp.fax.service.imp;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.cp.fax.service.IElecSealRelaService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;

/**
 * 签章关联业务服务实现类型
 * @ClassName ElecSealRelaService
 * @Description 
 * @author leijianhua@ysstech.com
 * @CreateDate 2017年8月17日下午4:21:38
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class ElecSealRelaService extends ServiceBus<ElecSealRelaService> implements IElecSealRelaService {
	
	private static final String EXT_LISTHEADLIST_KEY = "EXT_LISTHEADLIST";
	/**
	 * 签章关联业务dao
	 */
//	private ElecSealRelaDao svcDao = null;
	private com.yss.platform.support.seal.service.IElecSealRelaService service = null;
	
	/**
	 * @Description 构造函数
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月17日下午4:25:21
	 */
	public ElecSealRelaService() {
//		svcDao = new ElecSealRelaDao(DbPoolFactory.getInstance().getPool(), new ElecSealRelaSqlBuilder());
//		this.dao = svcDao;
		service = YssServiceFactory.getInstance().createService(com.yss.platform.support.seal.service.IElecSealRelaService.class);
	}
	
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap, PageInation page) {
//		List<HeadKey> extHeadKeyList = null;
//		if(paraMap.containsKey(EXT_LISTHEADLIST_KEY)){
//			String listHeadListJson = (String) paraMap.get(EXT_LISTHEADLIST_KEY);
//			if(!StringUtil.IsNullOrEmpty(listHeadListJson)){
//			  extHeadKeyList = JsonUtil.toList(listHeadListJson, HeadKey.class);
//			}
			
//			paraMap.remove(EXT_LISTHEADLIST_KEY);
//		}
		
//		QueryRes queryRes = new QueryRes();
//		String classString = "";
//		Class<?> clazz;
//		int recCount = 0;
//		List<BasePojo> dataList = null;
//		try {
//			classString = String.valueOf(paraMap.get("dataClass"));
//			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

//			if (page == null) {
//				page = getDefaultPageInation();
//			}
			
//			dataList = query(paraMap, page, clazz);
//			fillResultObject(queryRes, dataList, recCount, page, menuId, extHeadKeyList);
//			queryRes.setMenuId(menuId);
//		} catch (Exception ex) {
//			if(ex instanceof YssRuntimeException){
//				throw (ServiceException)ex;
//			}else{
//				logger.log(ex.getMessage());
//				throw new ServiceException(ex);
//			}
//		}
//		return queryRes;
		return service.queryByCondition(paraMap, page);
	}
	
	@Override
	public String queryDataTotal(HashMap<String, Object> paraMap) {
//		paraMap.remove(EXT_LISTHEADLIST_KEY);
//		return super.queryDataTotal(paraMap);
		return service.queryDataTotal(paraMap);
	}
	
	/**
	 * 封装结果集
	 * @Title fillResultObject 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2016年12月26日上午10:23:29
	 * @param queryRes
	 * @param dataList
	 * @param countAll
	 * @param page
	 * @param menuId
	 * @param extHeadKeyList
	 * @throws Exception
	 * @return void
	 */
	protected void fillResultObject(QueryRes queryRes, List<BasePojo> dataList, int countAll, PageInation page,
			String menuId, List<HeadKey> extHeadKeyList) throws Exception {
		ListHeadDtl listHeadInfo = null;
		boolean isOSGI = YssContextFactory.getInstance().getOSGI();
		if(isOSGI){
			Context context = YssContextFactory.getInstance().getAppContext(bundleContext.getAppCode());
			listHeadInfo = context.getListHeadMap(menuId);
		}else{
			listHeadInfo = AppContext.getInstance().getListHeadMap(menuId);
		}
		List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
		
		////增加扩展的列头
		if(extHeadKeyList != null && extHeadKeyList.size()>0){
			extHeadKeyList.addAll(headKeyList);
			queryRes.setHeadKeyList(extHeadKeyList);
		}else {
			queryRes.setHeadKeyList(headKeyList);
		}
		
		queryRes.setDataList(dataList);
		
		if (page == null) {
			page = new PageInation();
		} else {
			page.setTotalNum(countAll);
		}
		
		queryRes.setPage(page);
		setShowConvertAssemble(queryRes);
		queryRes.setOperRes(MvcConstant._Success);
		queryRes.setMenuId(menuId);
		queryRes.setHeadKeyList(headKeyList);
	}
}
