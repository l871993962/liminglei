package com.yss.uco.elecreco.er.template.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;
import com.yss.uco.elecreco.er.template.pojo.FileStreamParam;

/**
 * @author liuxiang 2015年2月13日
 */
@RestfulSupported
public interface IDzTemplateService extends IServiceBus {

	/**
	 * 根据模板类型和组合代码查询可用模板
	 * 
	 * @param typeCode
	 *            模板类型代码
	 * @param portCode
	 *            组合代码
	 * @return
	 */
	public DzTemplate getTemplateByTypeCodeAndPortCode(String typeCode, String portCode);

	public String updateStatus(List<BasePojo> basePojoList) throws Exception;

	public String deploy(String zipFiles) throws Exception;

	public String unDeploy(List<BasePojo> templateList) throws Exception;

	public String downLoad(BasePojo basePojo) throws Exception;

	public List<String> getDeployTemplate() throws Exception;

	public String upload(FileStreamParam fileStreamParam) throws Exception;

}
