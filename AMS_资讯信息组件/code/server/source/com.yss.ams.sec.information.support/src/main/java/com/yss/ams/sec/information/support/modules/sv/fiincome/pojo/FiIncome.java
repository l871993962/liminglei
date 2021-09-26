package com.yss.ams.sec.information.support.modules.sv.fiincome.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 债券每日利息pojo类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
public class FiIncome extends AuditableParamPojo {

	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";

	/**
	 * 计息日期
	 */
	private String d_INCOME = "";

	/**
	 * 报价方式
	 */
	private String c_DV_QUT_MOD = "";

	/**
	 * 计息天数
	 */
	private String n_INCOME_DAYS = "";

	/**
	 * 票面利率
	 */
	private String n_COUP_RATE = "";

	/**
	 * 剩余本金
	 */
	private String n_REM_COR = "";

	/**
	 * 税前计息利息
	 */
	private String n_INCOME_PT = "";

	/**
	 * 税后计息利息
	 */
	private String n_INCOME_AT = "";

	/**
	 * 税后付息利息
	 */
	private String n_INCOME_PT_DUE = "";

	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String cSECCODE) {
		c_SEC_CODE = cSECCODE;
	}

	public String getD_INCOME() {
		return d_INCOME;
	}

	public void setD_INCOME(String dINCOME) {
		d_INCOME = dINCOME;
	}

	public String getC_DV_QUT_MOD() {
		return c_DV_QUT_MOD;
	}

	public void setC_DV_QUT_MOD(String cDVQUTMOD) {
		c_DV_QUT_MOD = cDVQUTMOD;
	}

	public String getN_INCOME_DAYS() {
		return n_INCOME_DAYS;
	}

	public void setN_INCOME_DAYS(String nINCOMEDAYS) {
		n_INCOME_DAYS = nINCOMEDAYS;
	}

	public String getN_COUP_RATE() {
		return n_COUP_RATE;
	}

	public void setN_COUP_RATE(String nCOUPRATE) {
		n_COUP_RATE = nCOUPRATE;
	}

	public String getN_REM_COR() {
		return n_REM_COR;
	}

	public void setN_REM_COR(String nREMCOR) {
		n_REM_COR = nREMCOR;
	}

	public String getN_INCOME_PT() {
		return n_INCOME_PT;
	}

	public void setN_INCOME_PT(String nINCOMEPT) {
		n_INCOME_PT = nINCOMEPT;
	}

	public String getN_INCOME_AT() {
		return n_INCOME_AT;
	}

	public void setN_INCOME_AT(String nINCOMEAT) {
		n_INCOME_AT = nINCOMEAT;
	}

	public String getN_INCOME_PT_DUE() {
		return n_INCOME_PT_DUE;
	}

	public void setN_INCOME_PT_DUE(String nINCOMEPTDUE) {
		n_INCOME_PT_DUE = nINCOMEPTDUE;
	}

	public String getN_INCOME_AT_DUE() {
		return n_INCOME_AT_DUE;
	}

	public void setN_INCOME_AT_DUE(String nINCOMEATDUE) {
		n_INCOME_AT_DUE = nINCOMEATDUE;
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

	/**
	 * 税后付息利息
	 */
	private String n_INCOME_AT_DUE = "";
	/**
	 * 描述
	 */
	private String c_DESC = "";

	private static final long serialVersionUID = 1L;
}
