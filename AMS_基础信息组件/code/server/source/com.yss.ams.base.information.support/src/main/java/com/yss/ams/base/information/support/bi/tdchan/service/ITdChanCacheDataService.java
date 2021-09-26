package com.yss.ams.base.information.support.bi.tdchan.service;


import java.util.List;

import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
@GenericPojo(pojo=TdChan.class)
public interface ITdChanCacheDataService extends ISecSeatDataService {
	
	public TdChan getCacheByKey(String key);
	
	public List<TdChan> getCacheList();
	
	public String getTimestamp();
}
