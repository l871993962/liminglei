package com.yss.uco.elecreco.support.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.vo.AutomaticParamVo;

@RestfulSupported
public interface IDzdzAutomaticParamService {

	/**
	 * STORY #90284 【富国基金】ETF重新跑账后对应的联接基金自动重新跑
	 * 通过ETF基金获取联接基金
	 * @param portList
	 * @param date
	 * @return
	 */
	@LinkControllerMethod(value = "getLinkPortbyEtfPort", arguTypes = { AutomaticParamVo.class })
	public Map<String, List<String>> getLinkPortbyEtfPort(@LinkControllerMethodArgu("portList")List<String> portList, @LinkControllerMethodArgu("date")Date date);
	
}
