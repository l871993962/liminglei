package com.yss.uco.elecreco.er.erdblgz.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.erdblgz.controller.IErDblgzbServiceController;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.erdblgz.service.IErDblgzbService;
import com.yss.uco.elecreco.er.erdblgz.vo.ErDblgzbVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:15
 */
public class ErDblgzbServiceControllerImpl extends
		AbstractBaseServiceBusController<ErDblgzb, IErDblgzbService> implements
		IErDblgzbServiceController {

	@Override
	public List<ErDblgzb> getGzData(HashMap<String, Object> paraMap) {
		return getService().getGzData(paraMap);
	}

	@Override
	public void insertDatas(ErDblgzbVo vo) {
		getService().insertDatas(vo.getList(), vo.getConn());
	}

}