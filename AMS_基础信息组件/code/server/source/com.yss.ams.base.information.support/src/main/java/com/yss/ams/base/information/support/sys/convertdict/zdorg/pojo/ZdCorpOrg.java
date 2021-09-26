package com.yss.ams.base.information.support.sys.convertdict.zdorg.pojo;

import java.math.BigDecimal;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class ZdCorpOrg extends AuditableParamPojo{

	private static final long serialVersionUID = 1L;

	/**
	 * 组织代码
	 */
	private String c_GROUP_CODE = "";
	/**
	 * 组织名称
	 */
	private String c_GROUP_NAME = "";
	/**
	 * 上级组织代码
	 */
	private String c_GROUP_CODE_P = "";
	
	/**
	 * 应用场景
	 */
	private String c_DV_SCENE = "SCENE_CUSTOM";
	
	/**
	 * 序号
	 */
	private BigDecimal n_ORDER = BigDecimal.ZERO;
	
	/**
	 * 备注
	 */
	private String c_DESC = "";

	
	public String getC_DV_SCENE() {
		return c_DV_SCENE;
	}

	public void setC_DV_SCENE(String cDVSCENE) {
		c_DV_SCENE = cDVSCENE;
	}

	public String getC_GROUP_CODE() {
		return c_GROUP_CODE;
	}

	public void setC_GROUP_CODE(String cGROUPCODE) {
		c_GROUP_CODE = cGROUPCODE;
	}

	public String getC_GROUP_NAME() {
		return c_GROUP_NAME;
	}

	public void setC_GROUP_NAME(String cGROUPNAME) {
		c_GROUP_NAME = cGROUPNAME;
	}

	public String getC_GROUP_CODE_P() {
		return c_GROUP_CODE_P;
	}

	public void setC_GROUP_CODE_P(String cGROUPCODEP) {
		c_GROUP_CODE_P = cGROUPCODEP;
	}

	public BigDecimal getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(BigDecimal nORDER) {
		n_ORDER = nORDER;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	
	
	
}
