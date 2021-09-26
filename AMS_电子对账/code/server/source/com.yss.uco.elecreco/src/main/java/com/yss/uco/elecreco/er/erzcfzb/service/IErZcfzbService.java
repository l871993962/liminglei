package com.yss.uco.elecreco.er.erzcfzb.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.erzcfzb.pojo.ErZcfzb;

@RestfulSupported
public interface IErZcfzbService extends IServiceBus {

	/**
	 * 根据条件查询电子对账资产负债信息
	 * @param paraMap
	 * @return
	 */
	 public List<ErZcfzb> getZcfzData(HashMap<String, Object> paraMap);
}
