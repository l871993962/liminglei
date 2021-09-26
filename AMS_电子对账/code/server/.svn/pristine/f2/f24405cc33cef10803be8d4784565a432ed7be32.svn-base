package com.yss.uco.elecreco.support.service;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.vo.AutoStateVo;

@RestfulSupported
public interface IAutoStateService extends IServiceBus {
	public void sendAutoMessage(String status, String fsn, String fileType, String cAssCode);
	public List<BEN_RECORD> getSendResult(Map<String, String> conditionMap);
	public List<BEN_RECORD> getDZResult(Map<String, String> conditionMap);
	
	/**
	 * 获取对账结果,通过特殊指标判断对账结果
	 * @param conditionMap
	 * @return
	 */
	public List<ErBbInfo> getDZResultInfo(Map<String, String> conditionMap);
	
	/**
	 * 获取不一致的数据
	 * @param erBbInfo
	 * @param bfCodes
	 * @return Map{key:电子对账指标代码，value:估值指标项代码}
	 * @throws Exception
	 */
	@LinkControllerMethod(value = "getDiffData", arguTypes = { AutoStateVo.class })
	public Map<String, String> getDiffData(@LinkControllerMethodArgu("erBbInfo")ErBbInfo erBbInfo,@LinkControllerMethodArgu("bfCodes")String bfCodes, @LinkControllerMethodArgu("checkCondition")String checkCondition) throws Exception;
}
