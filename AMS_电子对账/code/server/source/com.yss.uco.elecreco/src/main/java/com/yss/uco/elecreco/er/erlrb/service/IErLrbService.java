package com.yss.uco.elecreco.er.erlrb.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.erlrb.pojo.ErLrb;

@RestfulSupported
public interface IErLrbService extends IServiceBus {

	/**
	 * 根据条件查询电子对账利润信息
	 * @param paraMap
	 * @return
	 */
	 public List<ErLrb> getLrData(HashMap<String, Object> paraMap);
}
