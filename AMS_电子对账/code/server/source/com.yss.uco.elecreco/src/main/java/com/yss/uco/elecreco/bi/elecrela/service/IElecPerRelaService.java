package com.yss.uco.elecreco.bi.elecrela.service;

import java.util.HashMap;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;

@RestfulSupported
public interface IElecPerRelaService extends IServiceBus {
	
	/**
	 * 使用逻辑，指标有无个性设置，用公共设置，有，先取设置组合的个性设置，然后选【投资组合】 空的个性设置
	 * 
	 * @param c_ZB_CODE 指标代码
	 * @param c_ZB_Name 指标名称
	 * @param c_DZ_CODE 对账类型
	 * @return
	 */
	public BasePojo getPerRelaByCodeAndName(String c_ZB_CODE,String c_ZB_Name,String c_DZ_CODE) ;
	
	/**
	 * 使用逻辑，指标有无个性设置，用公共设置，有，先取设置组合的个性设置，然后选【投资组合】 空的个性设置
	 * 
	 * @param c_ZB_CODE
	 * @return
	 */
	public BasePojo getPerRelaByCode(String c_ZB_CODE) ;
	/**
	 * 供生成的时候调用，按优先级排序，组合不为空的数据排在后面
	 * @param c_PORT_CODE
	 * @param c_DZ_CODE
	 * @return
	 */
	public HashMap<String, ElecPerRela> getPerRelaByPortAndDZCode(String c_PORT_CODE,String c_DZ_CODE);
}
