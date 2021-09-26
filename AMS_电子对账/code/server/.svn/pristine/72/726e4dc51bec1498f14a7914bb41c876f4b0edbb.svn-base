package com.yss.uco.elecreco.support.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;

@RestfulSupported
public interface IErStepStateService extends IServiceBus {

	/**
	 * 插入方法
	 * @param erStatePojo
	 * @return
	 */
	public String insertPojo(ErStepState erStatePojo);

	/**
	 * 构造pojo
	 * 
	 * @param xml
	 * @return
	 */
	public ErStepState buildPojo(String assCode, String fsn,
			String c_FILE_TYPE, String c_RPT_TYPE, String c_STATE, String d_DATE,String errInfo);

	public List<BasePojo> queryListByTypes(HashMap<String, String> paraMap);
}