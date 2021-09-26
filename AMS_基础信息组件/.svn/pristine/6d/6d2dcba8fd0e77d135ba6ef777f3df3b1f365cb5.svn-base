package com.yss.ams.base.information.support.bi.account.service;

import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品账户信息>数据缓存服务接口，主要进行跨应用数据获取
 * 
 * Added by neil
 * */
@RestfulSupported
@GenericPojo(pojo = FundAcc.class)
public interface IFundAccCacheDataService {
	
	public List<FundAcc> getCacheList();
	
	public List<String> bindServiceNames();
	
	public FundAcc getCacheByKey(String key);
	
	
}
