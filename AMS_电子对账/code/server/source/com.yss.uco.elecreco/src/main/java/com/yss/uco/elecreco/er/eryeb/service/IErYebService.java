package com.yss.uco.elecreco.er.eryeb.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.eryeb.pojo.ErYeb;

@RestfulSupported
public interface IErYebService extends IServiceBus {
	/**
	 * 根据条件查询电子对账余额信息
	 * @param paraMap
	 * @return
	 */
	 public List<ErYeb> getYeData(HashMap<String, Object> paraMap);
}
