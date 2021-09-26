package com.yss.uco.elecreco.er.erbbinfo.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.controller.IErBbInfoServiceController;
import com.yss.uco.elecreco.support.service.IErBbInfoService;
import com.yss.uco.elecreco.support.vo.ErBbInfoVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ErBbInfoServiceControllerImpl extends
		AbstractBaseServiceBusController<ErBbInfo, IErBbInfoService> implements
		IErBbInfoServiceController {

	@Override
	public String updateBbInfo(ErBbInfoVo vo) {
		return getService().updateBbInfo(vo.getBbInfoList(), vo.getStatus());
	}

	@Override
	public String deleteBbInfo(List<ErBbInfo> bbInfoList) {
		return getService().deleteBbInfo(bbInfoList);
	}

	@Override
	public String getXmlFile(ErBbInfo erBbInfo) {
		return getService().getXmlFile(erBbInfo);
	}

	@Override
	public String sendBbInfo(List<ErBbInfo> bbInfoList) {
		return getService().sendBbInfo(bbInfoList);
	}

	@Override
	public ErBbInfo getBbInfoById(String id) {
		return getService().getBbInfoById(id);
	}

	@Override
	public String reStartDzMgr() {
		return getService().reStartDzMgr();
	}

	@Override
	public String acceptBbInfo(List<ErBbInfo> pojoList) {
		return getService().acceptBbInfo(pojoList);
	}

	@Override
	public String acceptBbInfoForQTDZ(List<ErBbInfo> pojoList) {
		return getService().acceptBbInfoForQTDZ(pojoList);
	}

	@Override
	public String UnPortOper(ErBbInfoVo vo) {
		return getService().UnPortOper(vo.getBbInfoList(), vo.getOperType());
	}

	@Override
	public String lockEconfirm(ErBbInfoVo vo) {
		return getService().lockEconfirm(vo.getBbInfoList(), vo.getExecOperCode(), vo.getIsCheckExe(),vo.getExecuteType());
	}

	@Override
	public Map<String, ErBbInfo> getDzResultInfo(ErBbInfoVo vo) {
		return getService().getDzResultInfo(vo.getdDate(), vo.getAssCodes());
	}

	@Override
	public boolean isManualAccept(String csn) {
		return getService().isManualAccept(csn);
	}

	@Override
	public String unAcceptClick(List<ErBbInfo> pojoList) {
		return getService().unAcceptClick(pojoList);
	}

	@Override
	public String queryNumberOfRows(ErBbInfo erBbInfo) {
		return getService().queryNumberOfRows(erBbInfo);
	}

}