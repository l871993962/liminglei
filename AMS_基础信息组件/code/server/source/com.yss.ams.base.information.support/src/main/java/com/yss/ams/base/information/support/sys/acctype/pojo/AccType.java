package com.yss.ams.base.information.support.sys.acctype.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class AccType extends BasePojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 资产类型代码
	 */
	private String c_DAT_CODE = "";
	
	/**
	 * 资产类型名称
	 */
	private String c_DAT_NAME = "";
	
	/**
	 * 编号
	 */
	private int n_ORDER = 0;
	
	/**
	 * 资产类型代码父节点
	 */
	private String c_DAT_CODE_P = "";
	
	/**
	 * 资产类型（分类）
	 */
	private String c_DAT_TYPE = "";

	public String getC_DAT_CODE() {
		return c_DAT_CODE;
	}

	public void setC_DAT_CODE(String cDATCODE) {
		c_DAT_CODE = cDATCODE;
	}

	public String getC_DAT_NAME() {
		return c_DAT_NAME;
	}

	public void setC_DAT_NAME(String cDATNAME) {
		c_DAT_NAME = cDATNAME;
	}

	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}

	public String getC_DAT_CODE_P() {
		return c_DAT_CODE_P;
	}

	public void setC_DAT_CODE_P(String cDATCODEP) {
		c_DAT_CODE_P = cDATCODEP;
	}

	public String getC_DAT_TYPE() {
		return c_DAT_TYPE;
	}

	public void setC_DAT_TYPE(String cDATTYPE) {
		c_DAT_TYPE = cDATTYPE;
	}
}
