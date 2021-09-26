package com.yss.uco.elecreco.support.service;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IElecRelaService extends IServiceBus {
	
	/**
	 * 获取所有数据
	 * 
	 * @return
	 */
	public List<BasePojo> getDataList();
	
	/**
	 * 根据指标名称获取数据
	 * 
	 * @param paraList
	 * @return
	 */
	public List<BasePojo> getDataListByName(List<String> paraList);
	

	/**
	 * 根据估值指标代码获取电子对账指标代码
	 * 
	 * @param paras
	 * @return Map{key:电子对账指标代码，value:估值指标项代码}
	 */
	public Map<String,String> getZbCodeByKeyCode(String paras);
}
