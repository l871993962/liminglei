package com.yss.ams.sec.information.support.modules.sv.base.service;

import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.vo.TransSecToPlCodeVo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 债券基本信息接口
 * @author 马向峰
 *
 */
@RestfulSupported
public interface ISecBaseZqService extends IServiceBus {

	public String singleSecInitFi(String secCode) throws Exception;
	
	public String multipleSecInitFi(List<SecBase> secList) throws Exception;
	
	@LinkControllerMethod(value="transSecToPlCode",arguTypes = TransSecToPlCodeVo.class)
	public String transSecToPlCode(@LinkControllerMethodArgu("secList")List<SecBase> secList, @LinkControllerMethodArgu("zhgz")String zhgz) throws Exception;

	public String ruleIsOpen(String zhgz, String type) throws Exception;
}
