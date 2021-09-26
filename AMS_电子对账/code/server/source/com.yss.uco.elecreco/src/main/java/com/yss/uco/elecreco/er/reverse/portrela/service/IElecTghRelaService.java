package com.yss.uco.elecreco.er.reverse.portrela.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IElecTghRelaService extends IServiceBus {
	List<BasePojo> queryOrganByPort(HashMap<String, Object> paraMap);
}
