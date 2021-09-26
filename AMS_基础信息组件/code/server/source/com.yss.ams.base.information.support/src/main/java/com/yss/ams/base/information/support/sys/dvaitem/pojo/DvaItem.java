package com.yss.ams.base.information.support.sys.dvaitem.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class DvaItem extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 核算项目代码
	 */
	private String c_DVA_ITEM_CODE = "";
	
	/**
	 * 核算项目名称
	 */
	private String c_DVA_ITEM_NAME = "";
	
	/**
	 * 父级代码
	 */
	private String c_DVA_ITEM_CODE_P = "";
	
	/**
	 * 序号
	 */
	private int n_ORDER = 0;
	
	/**
	 * 是否明细项 1:明细项 0:非明细项
	 */
	private int n_DETAIL = 0;

	public String getC_DVA_ITEM_CODE() {
		return c_DVA_ITEM_CODE;
	}

	public void setC_DVA_ITEM_CODE(String cDVAITEMCODE) {
		c_DVA_ITEM_CODE = cDVAITEMCODE;
	}

	public String getC_DVA_ITEM_NAME() {
		return c_DVA_ITEM_NAME;
	}

	public void setC_DVA_ITEM_NAME(String cDVAITEMNAME) {
		c_DVA_ITEM_NAME = cDVAITEMNAME;
	}

	public String getC_DVA_ITEM_CODE_P() {
		return c_DVA_ITEM_CODE_P;
	}

	public void setC_DVA_ITEM_CODE_P(String cDVAITEMCODEP) {
		c_DVA_ITEM_CODE_P = cDVAITEMCODEP;
	}

	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}

	public int getN_DETAIL() {
		return n_DETAIL;
	}

	public void setN_DETAIL(int nDETAIL) {
		n_DETAIL = nDETAIL;
	}
}
