package com.yss.uco.elecreco.er.org.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 
 * @author Lenovo
 *
 */
public class ErOrg extends AuditableParamPojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 机构代码
	 */
	private String c_ORG_CODE = "";

	/**
	 * 机构名称
	 */
	private String c_ORG_NAME = "";

	/**
	 * 中文名称
	 */
	private String c_ORG_NAME_CN = "";

	/**
	 * 机构简称
	 */
	private String c_ORG_NAME_ST = "";
	/**
	 * 母公司
	 */
	private String c_ORG_CODE_P = "";
	/**
	 * 机构类型
	 */
	private String c_DV_ORG_TYPE = "";

	/**
	 * 公司代码
	 */
	private String c_CORP_CODE = "";
	/**
	 ** 管理人
	 */
	private String c_DV_MANAGER = "";

	/**
	 ** 托管人
	 */
	private String c_DV_TRUSTEE = "";

	/**
	 ** 次托管人
	 */
	private String c_DV_TRUSTEE_SEC = "";

	/**
	 ** 主托管人
	 */
	private String c_DV_TRUSTEE_MA = "";

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

	public String getC_ORG_NAME_CN() {
		return c_ORG_NAME_CN;
	}

	public void setC_ORG_NAME_CN(String c_ORG_NAME_CN) {
		this.c_ORG_NAME_CN = c_ORG_NAME_CN;
	}

	public String getC_ORG_NAME_ST() {
		return c_ORG_NAME_ST;
	}

	public void setC_ORG_NAME_ST(String c_ORG_NAME_ST) {
		this.c_ORG_NAME_ST = c_ORG_NAME_ST;
	}

	public String getC_ORG_CODE_P() {
		return c_ORG_CODE_P;
	}

	public void setC_ORG_CODE_P(String c_ORG_CODE_P) {
		this.c_ORG_CODE_P = c_ORG_CODE_P;
	}

	public String getC_DV_ORG_TYPE() {
		return c_DV_ORG_TYPE;
	}

	public void setC_DV_ORG_TYPE(String c_DV_ORG_TYPE) {
		this.c_DV_ORG_TYPE = c_DV_ORG_TYPE;
	}

	public String getC_CORP_CODE() {
		return c_CORP_CODE;
	}

	public void setC_CORP_CODE(String c_CORP_CODE) {
		this.c_CORP_CODE = c_CORP_CODE;
	}

	public String getC_DV_MANAGER() {
		return c_DV_MANAGER;
	}

	public void setC_DV_MANAGER(String c_DV_MANAGER) {
		this.c_DV_MANAGER = c_DV_MANAGER;
	}

	public String getC_DV_TRUSTEE() {
		return c_DV_TRUSTEE;
	}

	public void setC_DV_TRUSTEE(String c_DV_TRUSTEE) {
		this.c_DV_TRUSTEE = c_DV_TRUSTEE;
	}

	public String getC_DV_TRUSTEE_SEC() {
		return c_DV_TRUSTEE_SEC;
	}

	public void setC_DV_TRUSTEE_SEC(String c_DV_TRUSTEE_SEC) {
		this.c_DV_TRUSTEE_SEC = c_DV_TRUSTEE_SEC;
	}

	public String getC_DV_TRUSTEE_MA() {
		return c_DV_TRUSTEE_MA;
	}

	public void setC_DV_TRUSTEE_MA(String c_DV_TRUSTEE_MA) {
		this.c_DV_TRUSTEE_MA = c_DV_TRUSTEE_MA;
	}
}
