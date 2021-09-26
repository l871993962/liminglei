package com.yss.ams.base.information.support.bi.ieLink.pojo;


import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * @classDesc 收支连接
 * @version 1.0 2012-11-29
 * @author yh
 */

/**
 * 收支连接设置实体类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeLink extends AuditableParamPojo {

	/**  */
	private static final long serialVersionUID = 8201729347446425977L;

	/**
	 * 收支代码
	 */
	private String c_FEE_CODE_P = null;
	
	/**
	 * 收支代码
	 */
	private String c_FEE_CODE = null;
	
	/**
	 * 收支项目
	 */
	private String c_IE_CODE = null;
	
	/**
	 * 来源标识
	 */
	private String c_SRC_MARK = null;
	
	/**
	 * 描述
	 */
	private String c_DESC = null;
	
	/**
	 * 收支名称
	 */
	private String c_FEE_NAME = null;
	
	public String getC_FEE_NAME() {
		return c_FEE_NAME;
	}

	public void setC_FEE_NAME(String c_FEE_NAME) {
		this.c_FEE_NAME = c_FEE_NAME;
	}

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
	 * @return the c_IE_CODE
	 */
	public String getC_IE_CODE() {
		return c_IE_CODE;
	}

	/**
	 * @param cIECODE the c_IE_CODE to set
	 */
	public void setC_IE_CODE(String cIECODE) {
		c_IE_CODE = cIECODE;
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

	public String getC_FEE_CODE_P() {
		return c_FEE_CODE_P;
	}

	public void setC_FEE_CODE_P(String c_FEE_CODE_P) {
		this.c_FEE_CODE_P = c_FEE_CODE_P;
	}
	
	

}
