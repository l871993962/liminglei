package com.yss.ams.base.information.modules.sys.secvar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.secvar.dao.SecVarDao;
import com.yss.ams.base.information.modules.sys.secvar.dao.SecVarSqlBuilder;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarService;
//import com.yss.ams.base.information.support.sys.secvar.service.ISecVarService;
//import com.yss.bundle.activator.UcoActivator;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;


/**
 * 证券品种字典T_S_DA_SEC_VAR Service
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECVAR)
public class SecVarService extends ServiceBus<SecVarService> implements ISecVarService {

	private SecVarDao serviceDao = null;

	public SecVarService() throws Exception{
		serviceDao = new SecVarDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SecVarSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 查询证券品种字典T_S_DA_SEC_VAR 并分页显示
	 */
	public QueryRes selectWithSecAttr(HashMap<String, Object> paraMap,
			PageInation page, String queryMenuId) {
		QueryRes queryRes = new QueryRes();
		int recCount = 0;
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.querySecVarExtendDataList(paraMap, page);

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(queryMenuId,InformationActivator.class));
			
			recCount = serviceDao.querySecVarExtendDataListCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(queryMenuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("证券品种字典参数：查询证券品种字典出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * 获取证券品种代码 c_sec_var_code 为'PJ','LC','ZQ','CK','HG','CJ'  的证券品种字典视图V_S_DA_SEC_VAR的数据
	 * 以及 业务类型c_busi_type 为('HG','CJ','CK')   的交易方式字典V_S_DT_TD_MODE的数据	
	 *   的集合
	 * 
	 * @return
	 */
	public QueryRes queryIdxCtrlSec(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		List<BasePojo> dataList = new ArrayList<BasePojo>();
		try {
			dataList = serviceDao.queryIdxCtrlSec();
			fillResultObject(queryRes, dataList, 0, null, menuId);

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.log(ex.getMessage());
				throw new ServiceException(ex);
			}
//			queryRes.setOperRes(MvcConstant._Fault);
//			queryRes.setMenuId(menuId);
//			throw new ServiceException(ex, menuId, queryRes);
		}
		return queryRes;
	}

	/**
	 * 根据交易证券代码获取对应的品种类型
	 * @param seccode 交易证券代码
	 * @return
	 */
	public QueryRes queryVarcodeByCode(String seccode){
		QueryRes queryRes = new QueryRes();
		List<BasePojo> dataList = new ArrayList<BasePojo>();
		try {
			dataList = serviceDao.getVarcodeByCode(seccode);
			fillResultObject(queryRes, dataList, 0, null, menuId);

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.log(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}
	
	/**
	 * 查询证券品种有哪些(分父子)
	 * add by wangpeixu 20170427 
	 * STORY37961财税140号文件，针对资管增值税系统改造需求
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, List<SecVar>> querySecVar() throws Exception{
		return serviceDao.querySecVar();
	}
}
