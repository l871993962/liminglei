package com.yss.ams.base.information.support.bi.mkt.service;


import java.util.List;

import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 交易市场设置缓存服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
@GenericPojo(pojo=Mkt.class)
public interface IMktCacheDataService extends IMktDataService{
	
	public Mkt getCacheByKey(String key);
	
	public List<Mkt> getCacheList();
	
	public void reloadData();
}
