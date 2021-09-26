package com.yss.uco.elecreco.support.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.bean.ErGzb;
import com.yss.uco.elecreco.support.vo.ErGzbVo;

@RestfulSupported
public interface IErGzbService extends IServiceBus {
	/**
	 * 根据条件查询电子对账估值信息
	 * @param paraMap
	 * @return
	 */
	 public List<ErGzb> getGzData(HashMap<String, Object> paraMap);
	 
	 /**
	  * 获取电子对账关联指标代码
	  * add by qiantao STORY #83025 产品估值参数控制实收资本小数位 
	  * @param indexCode
	  * @return
	  */
	 public List<String> getRealIndexCode(String dzCode, String zbCode);
	 
	 /**
	 * 格式化实收资本数量
	 * add by qiantao STORY #83025 产品估值参数控制实收资本小数位 
	 * @param port
	 * @param d_trade
	 * @param value
	 * @return
	 */
	@LinkControllerMethod(value = "formatSSZBValue", arguTypes = { ErGzbVo.class })
	public String formatSSZBValue(@LinkControllerMethodArgu("port")String port,@LinkControllerMethodArgu("d_trade")String d_trade,@LinkControllerMethodArgu("value")BigDecimal value);
	
	/**
	 * 格式化实收资本数量
	 * @param ports
	 * @param formatData
	 * @return
	 */
	@LinkControllerMethod(value = "formatedData", arguTypes = { ErGzbVo.class })
	public HashMap<String, String> formatedData(@LinkControllerMethodArgu("ports")String ports,@LinkControllerMethodArgu("formatData")HashMap<String, String> formatData);
}
