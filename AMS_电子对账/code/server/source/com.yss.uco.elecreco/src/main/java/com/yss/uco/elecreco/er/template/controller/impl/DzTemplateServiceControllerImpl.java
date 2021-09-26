package com.yss.uco.elecreco.er.template.controller.impl;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.er.template.controller.IDzTemplateServiceController;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;
import com.yss.uco.elecreco.er.template.pojo.FileStreamParam;
import com.yss.uco.elecreco.er.template.service.IDzTemplateService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class DzTemplateServiceControllerImpl extends
		AbstractBaseServiceBusController<DzTemplate, IDzTemplateService>
		implements IDzTemplateServiceController {

	@Override
	public DzTemplate getTemplateByTypeCodeAndPortCode(String typeCode,
			String portCode) {
		return getService()
				.getTemplateByTypeCodeAndPortCode(typeCode, portCode);
	}

	@Override
	public String updateStatus(List<BasePojo> basePojoList) throws Exception {
		return getService().updateStatus(basePojoList);
	}

	@Override
	public String deploy(String zipFiles) throws Exception {
		return getService().deploy(zipFiles);
	}

	@Override
	public String unDeploy(List<BasePojo> templateList) throws Exception {
		return getService().unDeploy(templateList);
	}

	@Override
	public String downLoad(BasePojo basePojo) throws Exception {
		return getService().downLoad(basePojo);
	}

	@Override
	public List<String> getDeployTemplate() throws Exception {
		return getService().getDeployTemplate();
	}

	@Override
	public String upload(FileStreamParam fileStreamParam) throws Exception {
		return getService().upload(fileStreamParam);
	}

}