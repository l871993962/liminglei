package com.yss.ams.sec.information.support.modules.sv.secbasejf.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 计费证券信息pojo类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
public class SecBaseJf extends AuditableParamPojo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";
	
	/**
	 * 证券名称
	 */
	private String c_SEC_NAME = "";
	
	/**
	 * 证券上市代码
	 */
	private String c_SEC_MKT_CODE = "";
	
	/**
	 * 证券品种代码
	 */
	private String c_SEC_VAR_CODE = "";
	
	/**
	 * 计费证券信息
	 */
	private String c_SFJT = "";
	
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	
	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String cSECCODE) {
		c_SEC_CODE = cSECCODE;
	}
	
	public String getC_SEC_NAME() {
		return c_SEC_NAME;
	}

	public void setC_SEC_NAME(String cSECNAME) {
		c_SEC_NAME = cSECNAME;
	}
	
	public String getC_SEC_MKT_CODE() {
		return c_SEC_MKT_CODE;
	}

	public void setC_SEC_MKT_CODE(String cSECMKTCODE) {
		c_SEC_MKT_CODE = cSECMKTCODE;
	}
	
	public String getC_SEC_VAR_CODE() {
		return c_SEC_VAR_CODE;
	}

	public void setC_SEC_VAR_CODE(String cSECVARCODE) {
		c_SEC_VAR_CODE = cSECVARCODE;
	}
	
	public String getC_SFJT() {
		return c_SFJT;
	}

	public void setC_SFJT(String cSFJT) {
		c_SFJT = cSFJT;
	}
	
	public String getC_PORT_CODE() {
		return this.c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	
}
