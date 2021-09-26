package com.yss.ams.sec.information.support.modules.sv.base.service;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 票据基本信息 2013-03-14
 * @author Tangshifeng
 *
 */
@RestfulSupported
public interface ISecBasePjService extends IServiceBus {

	public BasePojo queryPjSecBase(String secCode);
}
