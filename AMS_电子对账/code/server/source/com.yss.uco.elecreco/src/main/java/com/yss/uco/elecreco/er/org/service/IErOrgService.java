package com.yss.uco.elecreco.er.org.service;

import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.org.pojo.ErOrg;

/**
 * BUG230634电子对账界面卡顿严重
 * @author Lenovo
 *
 */
@RestfulSupported
public interface IErOrgService extends IServiceBus {
	/**
	 * 前台下拉框调用
	 * 获取托管机构
	 * @return
	 */
	public List<ErOrg> getTrusteeOrgs();
	/**
	 * 前台下拉框调用
	 * 获取管理机构
	 * @return
	 */
	public List<ErOrg> getManagerOrgs();
}
