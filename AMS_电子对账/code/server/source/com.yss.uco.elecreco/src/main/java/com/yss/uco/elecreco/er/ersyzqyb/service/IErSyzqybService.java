package com.yss.uco.elecreco.er.ersyzqyb.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.ersyzqyb.pojo.ErSyzqyb;

@RestfulSupported
public interface IErSyzqybService extends IServiceBus {

	/**
	 * 根据条件查询电子对账所有者权益（基金净值）变动信息
	 * @param paraMap
	 * @return
	 */
	 public List<ErSyzqyb> getSyzqyData(HashMap<String, Object> paraMap);
}
