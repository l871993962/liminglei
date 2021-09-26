package com.yss.uco.elecreco.er.autostate.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.AutoState;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.controller.IAutoStateServiceController;
import com.yss.uco.elecreco.support.service.IAutoStateService;
import com.yss.uco.elecreco.support.vo.AutoStateVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class AutoStateServiceControllerImpl extends
		AbstractBaseServiceBusController<AutoState, IAutoStateService>
		implements IAutoStateServiceController {

	@Override
	public void sendAutoMessage(String status, String fsn, String fileType,
			String cAssCode) {
		getService().sendAutoMessage(status, fsn, fileType, cAssCode);
	}

	@Override
	public List<BEN_RECORD> getSendResult(Map<String, String> conditionMap) {
		return getService().getSendResult(conditionMap);
	}

	@Override
	public List<BEN_RECORD> getDZResult(Map<String, String> conditionMap) {
		return getService().getDZResult(conditionMap);
	}

	@Override
	public List<ErBbInfo> getDZResultInfo(Map<String, String> conditionMap) {
		return getService().getDZResultInfo(conditionMap);
	}

	@Override
	public Map<String, String> getDiffData(AutoStateVo vo) throws Exception {
		return getService().getDiffData(vo.getErBbInfo(), vo.getBfCodes(), vo.getCheckCondition());
	}

}