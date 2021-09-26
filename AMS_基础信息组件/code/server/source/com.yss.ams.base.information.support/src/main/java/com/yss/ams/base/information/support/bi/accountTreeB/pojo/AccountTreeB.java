package com.yss.ams.base.information.support.bi.accountTreeB.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class AccountTreeB  extends AuditableParamPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 节点代码
	 */
	private String c_NODE_CODE = "";
	
	/**
	 * 父级节点
	 */
	private String c_NODE_CODE_P = "";

	/**
	 * 关联账户id
	 */
	private String c_IDEN_RELA = "";
	
	/**
	 * 关联账户信息账户名称（不保存入数据库）
	 */
	private String c_OPEN_ACC_NAME = "";
	
	/**
	 * 关联账户信息账户账号（不保存入数据库）
	 */
	private String c_OPEN_ACC_NO = "";
	
	/**
	 * 关联账户信息账户开户地址（不保存入数据库）
	 */
	private String c_OPEN_ADDR = "";
	
	/**
	 * 关联账户信息账户类型（不保存入数据库）
	 */
	private String c_ACCOUNT_TYPE = "";
	
	/**
	 * 关联账户信息账户币种（不保存入数据库）
	 */
	private String c_DC_CODE = "";
	
	/**
	 * 关联账户信息账户开户机构（不保存入数据库）
	 */
	private String c_ORG_CODE = "";
	
	public String getC_NODE_CODE() {
		return c_NODE_CODE;
	}
	public void setC_NODE_CODE(String c_NODE_CODE) {
		this.c_NODE_CODE = c_NODE_CODE;
	}
	public String getC_NODE_CODE_P() {
		return c_NODE_CODE_P;
	}
	public void setC_NODE_CODE_P(String c_NODE_CODE_P) {
		this.c_NODE_CODE_P = c_NODE_CODE_P;
	}
	public String getC_IDEN_RELA() {
		return c_IDEN_RELA;
	}
	public void setC_IDEN_RELA(String c_IDEN_RELA) {
		this.c_IDEN_RELA = c_IDEN_RELA;
	}
	public String getC_OPEN_ACC_NAME() {
		return c_OPEN_ACC_NAME;
	}
	public void setC_OPEN_ACC_NAME(String c_OPEN_ACC_NAME) {
		this.c_OPEN_ACC_NAME = c_OPEN_ACC_NAME;
	}
	public String getC_OPEN_ACC_NO() {
		return c_OPEN_ACC_NO;
	}
	public void setC_OPEN_ACC_NO(String c_OPEN_ACC_NO) {
		this.c_OPEN_ACC_NO = c_OPEN_ACC_NO;
	}
	public String getC_OPEN_ADDR() {
		return c_OPEN_ADDR;
	}
	public void setC_OPEN_ADDR(String c_OPEN_ADDR) {
		this.c_OPEN_ADDR = c_OPEN_ADDR;
	}
	public String getC_ACCOUNT_TYPE() {
		return c_ACCOUNT_TYPE;
	}
	public void setC_ACCOUNT_TYPE(String c_ACCOUNT_TYPE) {
		this.c_ACCOUNT_TYPE = c_ACCOUNT_TYPE;
	}
	public String getC_DC_CODE() {
		return c_DC_CODE;
	}
	public void setC_DC_CODE(String c_DC_CODE) {
		this.c_DC_CODE = c_DC_CODE;
	}
	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}
	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
