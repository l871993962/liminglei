package com.yss.ams.sec.information.support.modules.sv.base.pojo;

import com.yss.framework.api.common.co.ShortPojo;

/**
 * 基本证券信息
 * 
 * @author Orlando
 * 
 */
public class SecShortPojo extends ShortPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4284212980882167641L;

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
	 * 交易市场代码
	 */
	private String c_MKT_CODE = "";

	/**
	 * 证券品种代码
	 */
	private String c_SEC_VAR_CODE = "";

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

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public String getC_SEC_VAR_CODE() {
		return c_SEC_VAR_CODE;
	}

	public void setC_SEC_VAR_CODE(String cSECVARCODE) {
		c_SEC_VAR_CODE = cSECVARCODE;
	}
}