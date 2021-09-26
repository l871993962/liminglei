package com.yss.uco.elecreco.er.reverse.map.kmrela.service;

import java.util.HashMap;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IKmRelaRecordService extends IServiceBus  {
	public QueryRes queryInnerKm(HashMap<String, Object> paraMap);
	public QueryRes queryOutKm(HashMap<String, Object> paraMap);
	public List<KmRelaRecord> queryIsMappingKm(HashMap<String, Object> paraMap);
	public List<KmRelaRecord> getCompareKmMap(String portCode,String tgh);
	public List<KmRelaRecord> getPortAndCommKmMap(String portCode);
}