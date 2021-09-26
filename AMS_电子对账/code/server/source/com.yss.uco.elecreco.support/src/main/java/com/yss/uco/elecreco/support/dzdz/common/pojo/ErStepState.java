package com.yss.uco.elecreco.support.dzdz.common.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class ErStepState extends AuditableParamPojo  {
	
	private static final long serialVersionUID = 4366187116066683856L;

	/**
	 * 报文序号
	 */
	private String c_SN = "";
	
	/**
	 * 基金代码
	 */
	private String c_ASS_CODE = "";
	
	/**
	 * 文件类型
	 */
	private String c_FILE_TYPE = "";
	
	/**
	 * 报表类型
	 */
	private String c_RPT_TYPE = "";
	
	/**
	 * 处理状态
	 */
	private String c_STATE = "";
	
	/**
	 * 当时状态日期
	 */
	private String c_STEP_DATE = "";

	/**
	 * 对账日期
	 */
	private String d_DATE = "";
	
	/**
	 * 错误信息
	 */
	private String errInfo = "";
	
	public String getErrInfo() {
		return errInfo;
	}


	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}


	public String getC_STEP_DATE() {
		return c_STEP_DATE;
	}


	public void setC_STEP_DATE(String c_STEP_DATE) {
		this.c_STEP_DATE = c_STEP_DATE;
	}
	
	public String getD_DATE() {
		return d_DATE;
	}


	public void setD_DATE(String dDATE) {
		d_DATE = dDATE;
	}


	public String getC_SN() {
		return c_SN;
	}


	public void setC_SN(String cSN) {
		c_SN = cSN;
	}

	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String cASSCODE) {
		c_ASS_CODE = cASSCODE;
	}


	public String getC_FILE_TYPE() {
		return c_FILE_TYPE;
	}


	public void setC_FILE_TYPE(String cFILETYPE) {
		c_FILE_TYPE = cFILETYPE;
	}


	public String getC_RPT_TYPE() {
		return c_RPT_TYPE;
	}


	public void setC_RPT_TYPE(String cRPTTYPE) {
		c_RPT_TYPE = cRPTTYPE;
	}


	public String getC_STATE() {
		return c_STATE;
	}


	public void setC_STATE(String cSTATE) {
		c_STATE = cSTATE;
	}
}
