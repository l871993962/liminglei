package com.yss.uco.elecreco.er.reverse.map.zbmap.service;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.support.bean.ElecRela;

@RestfulSupported
public interface IZbMapService extends IServiceBus  {
	public Map<String,ElecRela> getZbItems(String fileType);
	public List<ZbMap> getCompareZbItems(String portCode,String tgh,String fileType);
}