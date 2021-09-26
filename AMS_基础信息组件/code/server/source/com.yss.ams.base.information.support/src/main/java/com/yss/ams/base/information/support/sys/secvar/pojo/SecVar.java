package com.yss.ams.base.information.support.sys.secvar.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 证券品种字典T_S_DA_SEC_VAR  pojo
 *
 */
public class SecVar extends AuditableParamPojo {

	/**
	 * 证券品种代码
	 */
	private String c_SEC_VAR_CODE = "";

	/**
	 * 证券品种名称
	 */
	private String c_SEC_VAR_NAME = "";

	/**
	 * 证券属性代码
	 */
	private String c_DA_CODE = "";

	/**
	 * 品种属性的父级代码
	 */
	private String c_DA_CODE_P = "";

	/**
	 * 证券属性名称
	 */
	private String c_DA_NAME = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 显示状态
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示
	 */
	private String c_DV_STATE = "";

	private static final long serialVersionUID = 1L;

	public String getC_SEC_VAR_CODE() {
		return c_SEC_VAR_CODE;
	}

	public void setC_SEC_VAR_CODE(String cSECVARCODE) {
		c_SEC_VAR_CODE = cSECVARCODE;
	}

	public String getC_SEC_VAR_NAME() {
		return c_SEC_VAR_NAME;
	}

	public void setC_SEC_VAR_NAME(String cSECVARNAME) {
		c_SEC_VAR_NAME = cSECVARNAME;
	}

	public String getC_DA_CODE() {
		return c_DA_CODE;
	}

	public void setC_DA_CODE(String cDACODE) {
		c_DA_CODE = cDACODE;
	}

	public String getC_DA_CODE_P() {
		return c_DA_CODE_P;
	}

	public void setC_DA_CODE_P(String cDACODEP) {
		c_DA_CODE_P = cDACODEP;
	}

	public String getC_DA_NAME() {
		return c_DA_NAME;
	}

	public void setC_DA_NAME(String cDANAME) {
		c_DA_NAME = cDANAME;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_DV_STATE() {
		return c_DV_STATE;
	}

	public void setC_DV_STATE(String c_DV_STATE) {
		this.c_DV_STATE = c_DV_STATE;
	}

}
