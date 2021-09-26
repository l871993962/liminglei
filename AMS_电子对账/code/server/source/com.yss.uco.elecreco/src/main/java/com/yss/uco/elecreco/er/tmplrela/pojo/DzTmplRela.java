package com.yss.uco.elecreco.er.tmplrela.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 电子对账模板关联
 * 
 * @author liuxiang 2015年2月13日
 */
public class DzTmplRela extends AuditableParamPojo {
	private static final long serialVersionUID = 1L;

	/**
	 * 模板代码
	 */
	private String c_TMPL_CODE;

	/**
	 * 组合代码
	 */
	private String c_PORT_CODE;

	/**
	 * 模板类型
	 */
	private String c_TMPL_TYPE;

	/**
	 * 描述信息
	 */
	private String c_DESC;

	public String getC_TMPL_CODE() {
		return c_TMPL_CODE;
	}

	public void setC_TMPL_CODE(String c_TMPL_CODE) {
		this.c_TMPL_CODE = c_TMPL_CODE;
	}

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	public String getC_TMPL_TYPE() {
		return c_TMPL_TYPE;
	}

	public void setC_TMPL_TYPE(String c_TMPL_TYPE) {
		this.c_TMPL_TYPE = c_TMPL_TYPE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String c_DESC) {
		this.c_DESC = c_DESC;
	}
}
