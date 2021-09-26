package com.yss.ams.product.information.support.modules.aa.portcls.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetCashListByPortWDQVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetCacheByPortAndBuildDateVo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品分级信息>数据服务接口，主要进行跨应用数据获取
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
@RestfulSupported
@GenericPojo(pojo=PortCls.class)
public interface IClsPortCacheDataService {
	
	public String getKey(String cPortCode, String cPortCls) ;
	
	public PortCls getCacheByKey(String key);
	
	public PortCls getCacheByPortAndPortCls(String portCode,String portClsCode);
	
	public List<PortCls> getCashListByPort(String portCode);
	
	@LinkControllerMethod(value="getCashListByPortWDQ",arguTypes = GetCashListByPortWDQVo.class)
	public List<PortCls> getCashListByPortWDQ(@LinkControllerMethodArgu("portCode")String portCode,@LinkControllerMethodArgu("accDate")Date accDate);
	
	public List<PortCls> getCacheList();
	
	@LinkControllerMethod(value="getKeyConvertMap")
	public HashMap<String, String> getKeyConvertMap();
	
	@LinkControllerMethod(value="getKeyConvertMap",arguTypes = List.class)
	public HashMap<String, String> getKeyConvertMap(List<String> listKey);
	

	public void reloadData();
}
