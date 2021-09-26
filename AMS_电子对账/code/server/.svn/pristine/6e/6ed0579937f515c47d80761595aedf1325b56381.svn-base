package com.yss.uco.elecreco.er.erresult.controller.impl;

import java.util.HashMap;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.uco.elecreco.er.erresult.controller.IErResultServiceController;
import com.yss.uco.elecreco.er.erresult.service.IErResultService;
import com.yss.uco.elecreco.er.erresult.vo.ErResultVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErResultServiceControllerImpl extends
		AbstractBaseServiceBusController<BasePojo, IErResultService> implements
		IErResultServiceController {

	@Override
	public RestfulQueryResult<BasePojo> queryOrigDataByCondition(
			HashMap<String, Object> paraMap) {
		return queryResToT(getService().queryOrigDataByCondition(paraMap));
	}

	@Override
	public boolean isExistAssetCode(String assetCode) {
		return getService().isExistAssetCode(assetCode);
	}

	@Override
	public boolean isSameData(ErResultVo vo) {
		return getService().isSameData(vo.getCsn(), vo.getRowFlag());
	}

	@Override
	public int queryUnAcceptCount(String csn) {
		return getService().queryUnAcceptCount(csn);
	}

}