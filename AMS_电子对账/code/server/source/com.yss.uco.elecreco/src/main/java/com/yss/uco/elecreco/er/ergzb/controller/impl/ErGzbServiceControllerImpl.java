package com.yss.uco.elecreco.er.ergzb.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.ergzb.controller.IErGzbServiceController;
import com.yss.uco.elecreco.support.bean.ErGzb;
import com.yss.uco.elecreco.support.service.IErGzbService;
import com.yss.uco.elecreco.support.vo.ErGzbVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErGzbServiceControllerImpl extends
		AbstractBaseServiceBusController<ErGzb, IErGzbService> implements
		IErGzbServiceController {

	@Override
	public List<ErGzb> getGzData(HashMap<String, Object> paraMap) {
		return getService().getGzData(paraMap);
	}

	@Override
	public List<String> getRealIndexCode(String dzCode, String zbCode) {
		return getService().getRealIndexCode(dzCode, zbCode);
	}

	@Override
	public String formatSSZBValue(ErGzbVo vo) {
		return getService().formatSSZBValue(vo.getPort(), vo.getD_trade(), vo.getValue());
	}

	@Override
	public HashMap<String, String> formatedData(ErGzbVo vo) {
		return getService().formatedData(vo.getPorts(), vo.getFormatData());
	}

}