package com.yss.ams.base.information.support.bi.account.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class CashAcc extends AuditableParamPojo {
	/**
	 * 现金账户代码
	 */
	private String c_CA_CODE = "";

	/**
	 * 现金账户名称
	 */
	private String c_CA_NAME = "";

	/**
	 * 账户类型
	 */
	private String c_DV_ACC_TYPE = "";

	/**
	 * 投资组合
	 */
	private String c_PORT_CODE = "";
	
	/**
	 * 币种代码
	 */
	private String c_DC_CODE = "";
	
	/**
	 * 账户性质
	 */
	private String c_DV_ACC_NATURE = "";
	
	/**
	 * 账户性质
	 * liuxiang 2016年3月21日 STORY #28610 华泰证券：估值4.5对接托管清算的账户同步和资金划拨指令（4.5系统）
	 */
	private String c_DV_DETAIL_TYPE = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";
	
	/**
	 * 机构名称
	 * add by Yuntao Lau 2015.11.17 STORY #27225
	 */
	private String c_ORG_CODE = "";
	
	/**
	 * 开户账号
	 */
	private String c_OPEN_ACC_NO = "";

	private static final long serialVersionUID = 1L;

	public String getC_CA_CODE() {
		return c_CA_CODE;
	}

	public void setC_CA_CODE(String cCACODE) {
		c_CA_CODE = cCACODE;
	}

	public String getC_CA_NAME() {
		return c_CA_NAME;
	}

	public void setC_CA_NAME(String cCANAME) {
		c_CA_NAME = cCANAME;
	}

	public String getC_DV_ACC_TYPE() {
		return c_DV_ACC_TYPE;
	}

	public void setC_DV_ACC_TYPE(String cDVACCTYPE) {
		c_DV_ACC_TYPE = cDVACCTYPE;
	}

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
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

	public String getC_DV_ACC_NATURE() {
		return c_DV_ACC_NATURE;
	}

	public void setC_DV_ACC_NATURE(String cDVACCNATURE) {
		c_DV_ACC_NATURE = cDVACCNATURE;
	}

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String cPORTCODE) {
		c_PORT_CODE = cPORTCODE;
	}

	public String getC_DV_DETAIL_TYPE() {
		return c_DV_DETAIL_TYPE;
	}


	public void setC_DV_DETAIL_TYPE(String c_DV_DETAIL_TYPE) {
		this.c_DV_DETAIL_TYPE = c_DV_DETAIL_TYPE;
	}
	
	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}	
	
	public String getC_OPEN_ACC_NO() {
		return c_OPEN_ACC_NO;
	}

	public void setC_OPEN_ACC_NO(String c_OPEN_ACC_NO) {
		this.c_OPEN_ACC_NO = c_OPEN_ACC_NO;
	}
}
