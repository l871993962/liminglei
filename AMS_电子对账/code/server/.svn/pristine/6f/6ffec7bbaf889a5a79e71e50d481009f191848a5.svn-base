package com.yss.uco.elecreco.er.erbbinfo.controller.impl;

import java.util.HashMap;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.uco.elecreco.er.erbbinfo.controller.IElecGeneServiceController;
import com.yss.uco.elecreco.er.erbbinfo.service.IElecGeneService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ElecGeneServiceControllerImpl extends
		AbstractBaseServiceBusController<BasePojo, IElecGeneService>
		implements IElecGeneServiceController {

	@Override
	public RestfulQueryResult<BasePojo> queryByCondition(HashMap<String, Object> paraMap){
		return queryResToT(getService().queryByCondition(paraMap));
	}
}