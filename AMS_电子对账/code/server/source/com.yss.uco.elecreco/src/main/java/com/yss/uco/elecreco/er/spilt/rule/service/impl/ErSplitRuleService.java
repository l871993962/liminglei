package com.yss.uco.elecreco.er.spilt.rule.service.impl;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rule.dao.ErSplitRuleDao;
import com.yss.uco.elecreco.er.spilt.rule.dao.ErSplitRuleSqlBuilder;
import com.yss.uco.elecreco.er.spilt.rule.pojo.ErSplitRule;
import com.yss.uco.elecreco.er.spilt.rule.service.IErSplitRuleService;

public class ErSplitRuleService extends ServiceBus<ErSplitRuleService> implements IErSplitRuleService {

	private ErSplitRuleDao serviceDao = null;
	public ErSplitRuleService() throws Exception {
		serviceDao = new ErSplitRuleDao(DbPoolFactory.getInstance().getPool(),new ErSplitRuleSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public String updateRelaDetailKmInfo(List<ErSplitRule> addRules,
			List<ErSplitRule> removeRules) {
		String result = "false";
		try {
			result = this.serviceDao.updateRelaDetailKmInfo(addRules, removeRules);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return result;
	}
	@Override
	public QueryRes showUnSplitDetailKmInfo(String portCode, String date) {
		QueryRes queryRes = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			
			dataList = this.serviceDao.showUnSplitDetailKmInfo(portCode, date);
			
			fillResultObject(queryRes, dataList, 0, null, "erSplitRuleKmShow");
			
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}
	@Override
	public QueryRes showRelaDetailKmInfo(String id) {
		QueryRes queryRes = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			
			dataList = this.serviceDao.showRelaDetailKmInfo(id);
			
			fillResultObject(queryRes, dataList, 0, null, "erSplitRuleKmShow");
			
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}

	@Override
	public List<ErSplitRule> getSplitRulesBySplitRela(ErSplitRela rela) {
		return this.serviceDao.getSplitRulesBySplitRela(rela);
	}
	
	public List<HeadKey> getTghKmTableHeadKeys()
	{
		ListHeadDtl listHeadInfo = null;
		boolean isOSGI = YssContextFactory.getInstance().getOSGI();
		if(isOSGI){
			Context context = YssContextFactory.getInstance().getAppContext(bundleContext.getAppCode());
			listHeadInfo = context.getListHeadMap("erSplitRuleKmShow");
		}else{
			listHeadInfo = AppContext.getInstance().getListHeadMap("erSplitRuleKmShow");
		}
		 return listHeadInfo.getHeadKeyList();
	}
	
	public List<HeadKey> getPortKmTableHeadKeys()
	{
		 return getTghKmTableHeadKeys();
	}
	
}