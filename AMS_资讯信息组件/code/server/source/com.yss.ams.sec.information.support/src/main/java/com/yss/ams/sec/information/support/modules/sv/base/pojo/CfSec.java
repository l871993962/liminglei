package com.yss.ams.sec.information.support.modules.sv.base.pojo;

import java.math.BigDecimal;

import com.yss.framework.api.common.co.AuditableParamPojo;
/**
 * 存放品种set界面交易证券
 */
public class CfSec extends AuditableParamPojo {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 机构代码父节点
	 */
	private String c_ORG_CODE_P = "";
	
	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";
	
	/**
	 * 证券名称
	 */
	private String c_SEC_NAME = "";
	
	/**
	 * 证券品种代码
	 */
	private String c_SEC_VAR_CODE = "";
	
	/**
	 * 币种代码
	 */
	private String c_DC_CODE = "";
	
	/**
	 * 机构代码
	 */
	private String c_ORG_CODE = "";
	
	/**
	 * 出票人
	 */
	private String c_ORG_NAME = "";
	
	/**
	 * 发行类型 
	 */
	private String c_DV_ISSUE = "";
	
	/**
	 * 发行价格
	 */
	private BigDecimal n_PRICE_ISSUE = BigDecimal.ZERO;
	
	
	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String c_SEC_CODE) {
		this.c_SEC_CODE = c_SEC_CODE;
	}

	public String getC_SEC_NAME() {
		return c_SEC_NAME;
	}

	public void setC_SEC_NAME(String c_SEC_NAME) {
		this.c_SEC_NAME = c_SEC_NAME;
	}

	public String getC_SEC_VAR_CODE() {
		return c_SEC_VAR_CODE;
	}

	public void setC_SEC_VAR_CODE(String c_SEC_VAR_CODE) {
		this.c_SEC_VAR_CODE = c_SEC_VAR_CODE;
	}

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String c_DC_CODE) {
		this.c_DC_CODE = c_DC_CODE;
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}

	public String getC_ORG_NAME() {
		return c_ORG_NAME;
	}

	public void setC_ORG_NAME(String c_ORG_NAME) {
		this.c_ORG_NAME = c_ORG_NAME;
	}

	public String getC_DV_ISSUE() {
		return c_DV_ISSUE;
	}

	public void setC_DV_ISSUE(String c_DV_ISSUE) {
		this.c_DV_ISSUE = c_DV_ISSUE;
	}

	public BigDecimal getN_PRICE_ISSUE() {
		return n_PRICE_ISSUE;
	}

	public void setN_PRICE_ISSUE(BigDecimal n_PRICE_ISSUE) {
		this.n_PRICE_ISSUE = n_PRICE_ISSUE;
	}

	public String getC_ORG_CODE_P() {
		return c_ORG_CODE_P;
	}

	public void setC_ORG_CODE_P(String c_ORG_CODE_P) {
		this.c_ORG_CODE_P = c_ORG_CODE_P;
	}
}
