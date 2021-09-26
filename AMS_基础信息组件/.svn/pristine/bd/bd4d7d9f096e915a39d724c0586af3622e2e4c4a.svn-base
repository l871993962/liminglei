package com.yss.ams.base.information.support.sys.secvar.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 证券品种字典数据缓存服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DA_SEC_VAR
 *
 */
@RestfulSupported
@GenericPojo(pojo=SecVar.class)
public interface ISecVarCacheDataService  {
	
	public SecVar getCacheByKey(String key);
	
	public List<SecVar> getCacheList();
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	
	public void reloadData();
	
}
