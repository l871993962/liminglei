package com.yss.ams.base.information.support.bi.ie.pojo;


import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 收支代码设置实体类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class Ie extends AuditableParamPojo {

	/**  */
	private static final long serialVersionUID = 130810881731508996L;
	
	/**
	 * 收支代码
	 */
	private String c_FEE_CODE = null;
	
	/**
	 * 收支名称
	 */
	private String c_FEE_NAME = null;
	
	/**
	 * 来源标识
	 */
	private String c_SRC_MARK = null;
	
	/**
	 * 描述
	 */
	private String c_DESC = null;

	/**
	 * @return the c_FEE_CODE
	 */
	public String getC_FEE_CODE() {
		return c_FEE_CODE;
	}

	/**
	 * @param cFEECODE the c_FEE_CODE to set
	 */
	public void setC_FEE_CODE(String cFEECODE) {
		c_FEE_CODE = cFEECODE;
	}

	/**
	 * @return the c_FEE_NAME
	 */
	public String getC_FEE_NAME() {
		return c_FEE_NAME;
	}

	/**
	 * @param cFEENAME the c_FEE_NAME to set
	 */
	public void setC_FEE_NAME(String cFEENAME) {
		c_FEE_NAME = cFEENAME;
	}

	/**
	 * @return the c_SRC_MARK
	 */
	public String getC_SRC_MARK() {
		return c_SRC_MARK;
	}

	/**
	 * @param cSRCMARK the c_SRC_MARK to set
	 */
	public void setC_SRC_MARK(String cSRCMARK) {
		c_SRC_MARK = cSRCMARK;
	}

	/**
	 * @return the c_DESC
	 */
	public String getC_DESC() {
		return c_DESC;
	}

	/**
	 * @param cDESC the c_DESC to set
	 */
	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
