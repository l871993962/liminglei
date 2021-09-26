package com.yss.ams.sec.information.modules.sv.indexstock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.datacheck.annotation.DefaultDataCheck;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.indexstock.dao.IndexStockDao;
import com.yss.ams.sec.information.modules.sv.indexstock.dao.IndexStockSqlBuilder;
import com.yss.ams.sec.information.support.activator.SecInfoSupportActivator;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.indexstock.service.IIndexStockService;
/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexStockService extends ServiceBus<IndexStockService> implements
		IIndexStockService {

	private IndexStockDao serviceDao = null;

	public IndexStockService() {
		serviceDao = new IndexStockDao(DbPoolFactory.getInstance().getPool(),
				new IndexStockSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public QueryRes getUnSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception {
		QueryRes res = new QueryRes();
		HashMap<String, String> map = serviceDao.getSelectedSecMap(
				c_Index_Code, d_Begin);
		List<BasePojo> list = new ArrayList<BasePojo>();
		SecBaseCache secCache = CacheManager.getInstance().getCache(
				CacheGroup.SECBASE);
		List<SecBase> templst = ((SecBaseCache) secCache)
				.getDataListBySecVars(new String[] { "GP" });

		if (map.isEmpty()) {
			for (SecBase secBase : templst) {
				list.add(secBase);
			}
		} else {
			for (SecBase secBase : templst) {
				if (!map.containsKey(secBase.getC_SEC_CODE())) {
					list.add(secBase);
				}
			}
		}

		res.setDataList(list);
		res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoActivator.class));
		setShowConvertAssemble(res);
		res.setMenuId("sv_sec");
		res.setOperRes(MvcConstant._Success);
		return res;
	}

	@Override
	public QueryRes getSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception {
		QueryRes res = new QueryRes();
		HashMap<String, String> map = serviceDao.getSelectedSecMap(
				c_Index_Code, d_Begin);
		List<BasePojo> list = new ArrayList<BasePojo>();
		SecBaseCache secCache = CacheManager.getInstance().getCache(
				CacheGroup.SECBASE);
		List<SecBase> templst = ((SecBaseCache) secCache)
				.getDataListBySecVars(new String[] { "GP" });

		if (!map.isEmpty()) {
			for (SecBase secBase : templst) {
				if (map.containsKey(secBase.getC_SEC_CODE())) {
					list.add(secBase);
				}
			}
		}
		
		res.setDataList(list);
//		res.setHeadKeyList(ServiceAssistance.getListHead("summarysecurity",SecInfoSupportActivator.class));
		res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoSupportActivator.class));
		setShowConvertAssemble(res);
		res.setMenuId("sv_sec");
		res.setOperRes(MvcConstant._Success);
		return res;
	}

	/**
	 * 根据id更新数据（批量）
	 * 
	 * @param pojoList
	 *            代更新数据列表
	 * @return 操作结果
	 */
	@DefaultDataCheck
	public String updateById(List<BasePojo> pojoList) {
		String retInfo = "";

		try {
			// 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时,将第一个POJO中放入审核信息 byleeyu20130718
			if (safeData != null && safeData.getN_CHECK() <= 0) {
				if (pojoList != null && pojoList.size() > 0
						&& pojoList.get(0) instanceof AuditableParamPojo) {
					AuditableParamPojo auditPojo = (AuditableParamPojo) pojoList
							.get(0);
					auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
					auditPojo.setOperator(ContextFactory.getContext()
							.getUserCode()); // 设置已审核的用户
					auditPojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
			}
			
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
			if(pojoList != null && pojoList.size() > 0){
				for (BasePojo pojo : pojoList) {
					if (pojo instanceof ParamPojo) {
						((ParamPojo) pojo).setModifyDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					}
				}
			}

			serviceDao.updateList(pojoList);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			String errorMess = "";
			if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE) && pojoList != null && pojoList.size() > 0) {
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(
						ex.getMessage(), dao, pojoList.get(0));
			} else if (ex.getMessage().contains(
					"Invalid Date Data While oper UpdateById ! ")) {
				errorMess = ReturnInfoGenerator.getDateErrStr();
			} else {
				errorMess = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeSaveErr, menuId);
			}
			throw new ErrorMessageException(ex, errorMess);
		}
		return retInfo;
	}
	
	@Override
	public QueryRes getLastSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception {
		QueryRes res = new QueryRes();
		HashMap<String, String> map = serviceDao.getLastSelectedSecMap(
				c_Index_Code, d_Begin);
		List<BasePojo> list = new ArrayList<BasePojo>();
		SecBaseCache secCache = CacheManager.getInstance().getCache(
				CacheGroup.SECBASE);
		List<SecBase> templst = ((SecBaseCache) secCache)
				.getDataListBySecVars(new String[] { "GP" });

		if (!map.isEmpty()) {
			for (SecBase secBase : templst) {
				if (map.containsKey(secBase.getC_SEC_CODE())) {
					list.add(secBase);
				}
			}
		}
		res.setDataList(list);
		res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoSupportActivator.class));
		setShowConvertAssemble(res);
		res.setMenuId("sv_sec");
		res.setOperRes(MvcConstant._Success);
		return res;
	}
	
	@Override
	public QueryRes getLastUnSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception {
		QueryRes res = new QueryRes();
		HashMap<String, String> map = serviceDao.getLastSelectedSecMap(
				c_Index_Code, d_Begin);
		List<BasePojo> list = new ArrayList<BasePojo>();
		SecBaseCache secCache = CacheManager.getInstance().getCache(
				CacheGroup.SECBASE);
		List<SecBase> templst = ((SecBaseCache) secCache)
				.getDataListBySecVars(new String[] { "GP" });

		if (map.isEmpty()) {
			for (SecBase secBase : templst) {
				list.add(secBase);
			}
		} else {
			for (SecBase secBase : templst) {
				if (!map.containsKey(secBase.getC_SEC_CODE())) {
					list.add(secBase);
				}
			}
		}

		res.setDataList(list);
		res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoSupportActivator.class));
		setShowConvertAssemble(res);
		res.setMenuId("sv_sec");
		res.setOperRes(MvcConstant._Success);
		return res;
	}
}
