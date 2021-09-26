package com.yss.uco.elecreco.er.repcfg.controller.impl;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.reportdesign.template.pojo.ReportTemplateTreeView;
import com.yss.uco.elecreco.support.bean.DzRepCfgInsert;
import com.yss.uco.elecreco.support.controller.IDzRepCfgServiceController;
import com.yss.uco.elecreco.support.service.IDzRepCfgService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class DzRepCfgServiceControllerImpl extends
		AbstractBaseServiceBusController<DzRepCfgInsert, IDzRepCfgService> implements
		IDzRepCfgServiceController {

	@Override
	public RestfulQueryResult<ReportTemplateTreeView> getReportTemplateTreeView(String ports) {
		return queryResToT(getService().getReportTemplateTreeView(ports), ReportTemplateTreeView.class);
	}

}