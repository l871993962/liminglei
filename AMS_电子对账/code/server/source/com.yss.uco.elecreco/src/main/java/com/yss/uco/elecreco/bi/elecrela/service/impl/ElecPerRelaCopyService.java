package com.yss.uco.elecreco.bi.elecrela.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.uco.elecreco.support.service.IElecPerRelaCopyService;

/**
 * STORY #95888 【招商基金】【0331】【公募】新基金成立自动复制''对账指标关联'指标
 * 
 * @author zhanghubin
 * @CreateDate 2021/03/09
 */
public class ElecPerRelaCopyService extends ServiceBus<IElecPerRelaCopyService>
		implements IElecPerRelaCopyService {


	@Override
	public List<String> copy(String portCode, String[] portCodes,
			String dataCode, String execProcCode, String checkState) {

		return null;
	}


	@Override
	public List<String> copy(String portCode, String[] portCodes,
			String dataCode, String execProcCode, String checkState,
			String isClear) {
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> copy(String portCode,
			HashMap<String, String> portParamsMap, String dataCode,
			String execProcCode, String checkState) {
		List<String> result = null;
		try {
			result= new ElecPerRelaService().copy(portCode, portParamsMap, dataCode,execProcCode,checkState);
		}
		catch (Exception e) {
//			e.printStackTrace();
			logger.log("对账指标关联模块：产品参数复制功能复制对账指标关联参数时出错", e);
		}
		return result;
	}

}
