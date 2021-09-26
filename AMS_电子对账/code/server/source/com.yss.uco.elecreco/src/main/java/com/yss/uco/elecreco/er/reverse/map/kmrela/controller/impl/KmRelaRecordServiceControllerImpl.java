package com.yss.uco.elecreco.er.reverse.map.kmrela.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.reverse.map.kmrela.controller.IKmRelaRecordServiceController;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaRecordService;
import com.yss.uco.elecreco.er.reverse.out.erkmb.pojo.ErKmbOut;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class KmRelaRecordServiceControllerImpl extends
		AbstractBaseServiceBusController<KmRelaRecord, IKmRelaRecordService>
		implements IKmRelaRecordServiceController {

	@Override
	public RestfulQueryResult<ErKmb> queryInnerKm(
			HashMap<String, Object> paraMap) {
		return queryResToT(getService().queryInnerKm(paraMap), ErKmb.class);
	}

	@Override
	public RestfulQueryResult<ErKmbOut> queryOutKm(
			HashMap<String, Object> paraMap) {
		return queryResToT(getService().queryOutKm(paraMap), ErKmbOut.class);
	}

	@Override
	public List<KmRelaRecord> queryIsMappingKm(HashMap<String, Object> paraMap) {
		return getService().queryIsMappingKm(paraMap);
	}

	@Override
	public List<KmRelaRecord> getCompareKmMap(String portCode, String tgh) {
		return getService().getCompareKmMap(portCode, tgh);
	}

	@Override
	public List<KmRelaRecord> getPortAndCommKmMap(String portCode) {
		return getService().getPortAndCommKmMap(portCode);
	}

}