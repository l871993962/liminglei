package com.yss.ams.base.information.support.sys.automaticSet.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/** 
 * 组合业务关联关系pojo类
 * @ClassName: AutomaticSetPojo
 * @date 2020年12月24日
 * @Stroy90952
 * @author yangze
 */
public class AutomaticSetPojo extends AuditableParamPojo {

	private static final long serialVersionUID = -3520008156412630800L;
	
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * 业务类型代码
	 */
	private String c_BUSINESS_TYPE_CODE = "";
	
	/**
	 * 明细类型代码
	 */
	private String c_BUSINESS_CODE = "";

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	public String getC_BUSINESS_TYPE_CODE() {
		return c_BUSINESS_TYPE_CODE;
	}

	public void setC_BUSINESS_TYPE_CODE(String c_BUSINESS_TYPE_CODE) {
		this.c_BUSINESS_TYPE_CODE = c_BUSINESS_TYPE_CODE;
	}

	public String getC_BUSINESS_CODE() {
		return c_BUSINESS_CODE;
	}

	public void setC_BUSINESS_CODE(String c_BUSINESS_CODE) {
		this.c_BUSINESS_CODE = c_BUSINESS_CODE;
	}
}
