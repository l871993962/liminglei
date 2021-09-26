package com.yss.uco.elecreco.support.bean;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class DzRepCfgInsert extends AuditableParamPojo{

	private static final long serialVersionUID = 7957165054657126664L;

	/**
	 * 机构代码
	 */
	private String c_ORG_CODE;

	/**
	 * 电子对账报表
	 */
	private String c_DZ_CODE;

	/**
	 * 财务报表
	 */
	private String c_REPORT_CODE;

	/**
	 * 组合代码
	 */
	private String c_PORT_CODE;
	
	/**
	 * STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
	 * 资产类型
	 */
	private String c_DAT_CODE;
	
	/**
	 * STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
     * 报表类型
	 */
    private String c_RPT_TYPE;
	
	public DzRepCfgInsert() { }

	public DzRepCfgInsert(DzRepCfgInsert dzRepCfg) {
		c_ORG_CODE = dzRepCfg.getC_ORG_CODE();
		c_DZ_CODE = dzRepCfg.getC_DZ_CODE();
		c_REPORT_CODE = dzRepCfg.getC_REPORT_CODE();
		c_PORT_CODE = dzRepCfg.getC_PORT_CODE();
		c_DAT_CODE = dzRepCfg.getC_DAT_CODE();
		c_RPT_TYPE = dzRepCfg.getC_RPT_TYPE();
		this.setOperator(dzRepCfg.getOperator());
		this.setAuditDate(dzRepCfg.getAuditDate());
		this.setAuditState(dzRepCfg.getAuditState());
		this.setModifier(dzRepCfg.getModifier());
		this.setModifyDate(dzRepCfg.getModifyDate());
		this.setStartUseDate(dzRepCfg.getStartUseDate());
		this.setEndUseDate(dzRepCfg.getEndUseDate());
		this.setId(dzRepCfg.getId());		
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}

	public String getC_DZ_CODE() {
		return c_DZ_CODE;
	}

	public void setC_DZ_CODE(String c_DZ_CODE) {
		this.c_DZ_CODE = c_DZ_CODE;
	}

	public String getC_REPORT_CODE() {
		return c_REPORT_CODE;
	}

	public void setC_REPORT_CODE(String c_REPORT_CODE) {
		this.c_REPORT_CODE = c_REPORT_CODE;
	}

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	public String getC_DAT_CODE() {
		return c_DAT_CODE;
	}

	public void setC_DAT_CODE(String c_DAT_CODE) {
		this.c_DAT_CODE = c_DAT_CODE;
	}

	public String getC_RPT_TYPE() {
		return c_RPT_TYPE;
	}

	public void setC_RPT_TYPE(String c_RPT_TYPE) {
		this.c_RPT_TYPE = c_RPT_TYPE;
	}

}
