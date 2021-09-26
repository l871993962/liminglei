package com.yss.uco.elecreco.er.reverse.map.assmap.service;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IAssMapService extends IServiceBus  {
	String getDzMode(String portCode,String fileType);
	List<AssMap> getCommonAssMapByPortCode(String portCode);
	List<AssMap> getAssMapByPortCodeAndFileType(String portCode,String fileType);
	List<String> getTghCodesByPortCode(String portCode);
}