package com.yss.uco.elecreco.er.spilt.rule.controller.impl;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rule.controller.IErSplitRuleServiceController;
import com.yss.uco.elecreco.er.spilt.rule.pojo.ErSplitRule;
import com.yss.uco.elecreco.er.spilt.rule.service.IErSplitRuleService;
import com.yss.uco.elecreco.er.spilt.rule.vo.ErSplitRuleVo;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ErSplitRuleServiceControllerImpl extends
		AbstractBaseServiceBusController<BasePojo, IErSplitRuleService>
		implements IErSplitRuleServiceController {

	@Override
	public String updateRelaDetailKmInfo(ErSplitRuleVo vo) {
		return getService().updateRelaDetailKmInfo(vo.getAddRules(),
				vo.getRemoveRules());
	}

	@Override
	public RestfulQueryResult<BasePojo> showUnSplitDetailKmInfo(
			String portCode, String date) {
		return queryResToT(getService().showUnSplitDetailKmInfo(portCode, date));
	}

	@Override
	public RestfulQueryResult<BasePojo> showRelaDetailKmInfo(String id) {
		return queryResToT(getService().showRelaDetailKmInfo(id));
	}

	@Override
	public List<ErSplitRule> getSplitRulesBySplitRela(ErSplitRela rela) {
		return getService().getSplitRulesBySplitRela(rela);
	}

	@Override
	public List<HeadKey> getTghKmTableHeadKeys() {
		return getService().getTghKmTableHeadKeys();
	}

	@Override
	public List<HeadKey> getPortKmTableHeadKeys() {
		return getService().getPortKmTableHeadKeys();
	}

}