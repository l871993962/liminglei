package com.yss.ams.sec.information.support.modules.sv.base.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 理财基本信息接口
 * @author 马向峰
 *
 */
@RestfulSupported
public interface ISecBaseLcService extends IServiceBus {

	public String singleSecInitFi(String secCode) throws Exception;
	
	public String multipleSecInitFi(List<SecBase> secList) throws Exception;
	
	public List<BasePojo> getSecBasesByCondition(HashMap<String, Object> paraMap);
}
