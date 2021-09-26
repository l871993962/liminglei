package com.yss.uco.elecreco.er.erzcfzb.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class ErZcfzb extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 报文序号
	 */
	private String c_SN = "";

	/**
	 * 资金代码
	 */
	private String c_ASS_CODE = "";

	/**
	 * 文件类型
	 */
	private String c_FILE_TYPE ="";

	/**
	 * 报表类型
	 */
	private String c_RPT_TYPE ="";

		/**
	 * 开始日期
	 */
	private String d_START_DATE = "";

	/**
	 * 结束日期
	 */
	private String d_END_DATE = "";

	/**
	 * 公司代码
	 */
	private String c_DEPT_CODE = "";
	
	/**
	 * 证书代码
	 */
	private String c_CERT_ID = "";

	/**
	 * 指标代码
	 */
	private String c_INDEX_CODE = "";
	
	/**
	 * 指标名称
	 */
	private String c_INDEX_NAME = "";
	
	/**
	 * 期初值
	 */
	private String n_BEGIN_VALUE = "";
	
	/**
	 * 期末值
	 */
	private String n_END_VALUE = "";

	public String getC_SN() {
		return c_SN;
	}

	public void setC_SN(String c_SN) {
		this.c_SN = c_SN;
	}

	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
	}

	public String getC_FILE_TYPE() {
		return c_FILE_TYPE;
	}

	public void setC_FILE_TYPE(String c_FILE_TYPE) {
		this.c_FILE_TYPE = c_FILE_TYPE;
	}

	public String getC_RPT_TYPE() {
		return c_RPT_TYPE;
	}

	public void setC_RPT_TYPE(String c_RPT_TYPE) {
		this.c_RPT_TYPE = c_RPT_TYPE;
	}

	public String getD_START_DATE() {
		return d_START_DATE;
	}

	public void setD_START_DATE(String d_START_DATE) {
		this.d_START_DATE = d_START_DATE;
	}

	public String getD_END_DATE() {
		return d_END_DATE;
	}

	public void setD_END_DATE(String d_END_DATE) {
		this.d_END_DATE = d_END_DATE;
	}

	public String getC_DEPT_CODE() {
		return c_DEPT_CODE;
	}

	public void setC_DEPT_CODE(String c_DEPT_CODE) {
		this.c_DEPT_CODE = c_DEPT_CODE;
	}

	public String getC_CERT_ID() {
		return c_CERT_ID;
	}

	public void setC_CERT_ID(String c_CERT_ID) {
		this.c_CERT_ID = c_CERT_ID;
	}

	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

	public String getN_BEGIN_VALUE() {
		return n_BEGIN_VALUE;
	}

	public void setN_BEGIN_VALUE(String n_BEGIN_VALUE) {
		this.n_BEGIN_VALUE = n_BEGIN_VALUE;
	}

	public String getN_END_VALUE() {
		return n_END_VALUE;
	}

	public void setN_END_VALUE(String n_END_VALUE) {
		this.n_END_VALUE = n_END_VALUE;
	}

	public String getC_INDEX_NAME() {
		return c_INDEX_NAME;
	}

	public void setC_INDEX_NAME(String c_INDEX_NAME) {
		this.c_INDEX_NAME = c_INDEX_NAME;
	}

}
