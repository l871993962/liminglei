package com.yss.ams.base.information.support.bi.orgmgr.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 机构结算会员设置    pojo
 * @author 马向峰  拆分  20170531
 *
 */
public class OrgMgr extends AuditableParamPojo {

	/**
	* 会员号 
	*/
	  private String c_MBR_CODE = "";

	/**
	* 机构代码 
	*/
	  private String c_ORG_CODE = "";

	/**
	* 资金账号 
	*/
	  private String c_ACC_CODE = "";

	/**
	* 开户行名称 
	*/
	  private String c_ORG_NAME = "";

	/**
	* 账户代码 
	*/
	  private String c_CA_CODE = "";

	/**
	* 账户名称 
	*/
	  private String c_CA_NAME = "";

	/**
	 * 开始日期
	 */
	private String d_BEGIN ;

	/**
	 * 结束日期
	 */
	private String d_END ;

	/**
	* 描述 
	*/
	private String c_DESC = "";
	
	/**
	* TASK190987:新增“券商代码”,AddBy-Bohua.Gu-2016/02/26
	*/
	private String c_BROKER_CODE = "";

	private static final long serialVersionUID = 1L ;

	
	public String getD_BEGIN() {
		return d_BEGIN;
	}

	public void setD_BEGIN(String dBEGIN) {
		d_BEGIN = dBEGIN;
	}

	public String getD_END() {
		return d_END;
	}

	public void setD_END(String dEND) {
		d_END = dEND;
	}

	public String getC_MBR_CODE() {
		return c_MBR_CODE;
	}

	public void setC_MBR_CODE(String cMBRCODE) {
		c_MBR_CODE = cMBRCODE;
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String cORGCODE) {
		c_ORG_CODE = cORGCODE;
	}

	public String getC_ACC_CODE() {
		return c_ACC_CODE;
	}

	public void setC_ACC_CODE(String cACCCODE) {
		c_ACC_CODE = cACCCODE;
	}

	public String getC_ORG_NAME() {
		return c_ORG_NAME;
	}

	public void setC_ORG_NAME(String cORGNAME) {
		c_ORG_NAME = cORGNAME;
	}

	public String getC_CA_CODE() {
		return c_CA_CODE;
	}

	public void setC_CA_CODE(String cCACODE) {
		c_CA_CODE = cCACODE;
	}

	public String getC_CA_NAME() {
		return c_CA_NAME;
	}

	public void setC_CA_NAME(String cCANAME) {
		c_CA_NAME = cCANAME;
	}

	
	
	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_BROKER_CODE() {
		return c_BROKER_CODE;
	}

	public void setC_BROKER_CODE(String c_BROKER_CODE) {
		this.c_BROKER_CODE = c_BROKER_CODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String bizPrimeKeyNames() {
		// TODO Auto-generated method stub
		return "c_MBR_CODE";
	}
}
