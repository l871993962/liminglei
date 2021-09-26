package com.yss.uco.elecreco.er.reverse.manager.ignore.service;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IIgnoreItemService extends IServiceBus  {
	public List<IgnoreItem> getCompareIgnoreItem(String portCode,String tgh,String fileType);
}