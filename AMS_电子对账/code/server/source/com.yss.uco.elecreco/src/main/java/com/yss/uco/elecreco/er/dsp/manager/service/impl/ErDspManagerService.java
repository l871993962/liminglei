package com.yss.uco.elecreco.er.dsp.manager.service.impl;

import java.util.List;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.uco.elecreco.er.dsp.manager.dao.ErDspManagerDao;
import com.yss.uco.elecreco.er.dsp.manager.dao.ErDspManagerSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErDspManager;
import com.yss.uco.elecreco.support.service.IErDspManagerService;

public class ErDspManagerService extends ServiceBus<ErDspManagerService> implements IErDspManagerService {
	
	private ErDspManagerDao serviceDao = null;
	
	public ErDspManagerService() throws Exception {
		serviceDao = new ErDspManagerDao(DbPoolFactory.getInstance().getPool(),
				new ErDspManagerSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public String upadteParam(List<ErDspManager> list)
	{
		String retInfo = "";
		try {
			for (ErDspManager pojo : list) {
				pojo.setModifyDate(DateUtil
						.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				// 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时,将第一个POJO中放入审核信息 byleeyu20130718
				if (safeData != null && safeData.getN_CHECK() <= 0) 
				{
					pojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
					pojo.setOperator(ContextFactory.getContext()
							.getUserCode()); // 设置已审核的用户
					pojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
			}
			serviceDao.upadteParam(list);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw new ServiceException(ex);
			}else{
				retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeDelErr, menuId);
				logger.error(ex.getMessage());
				throw new ErrorMessageException(ex,retInfo);
			}
		}
		return retInfo;
	}
}
