package com.yss.ams.base.information.support.sys.ieItem.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * @classDesc  收支项目pojo
 * @version 1.0 2012-11-29
 * @author yh
 */
public class IeItem extends BasePojo {
	/**  */
	private static final long serialVersionUID = -2375542313812508033L;

	/**
	 * 收支项目代码
	 */
	private String c_IE_CODE = "";
	
	/**
	 * 收支项目名称
	 */
	private String c_IE_NAME = "";
	
	/**
	 * 应收项目
	 */
	private String c_DAI_CODE_REC = "";
	
	/**
	 * 应付项目
	 */
	private String c_DAI_CODE_COP = "";
	
	/**
	 * 收入项目
	 */
	private String c_DAI_CODE_INC = "";
	
	/**
	 * 支出项目
	 */
	private String c_DAI_CODE_FEE = "";
	
	/**
	 * 状态
	 */
	private int n_STATE = 0;
	
	/**
	 * 序号
	 */
	private int n_ORDER = 0;

	/**
	 * 收支项目分类
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化（替代手工凭证的数据录入界面功能优化）
	 */
	private String c_IE_TYPE = "";
	
	/**
	 * 收支类型
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化（替代手工凭证的数据录入界面功能优化）
	 */
	private String c_SZ_TYPE = "";
	
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
	 * @return the c_IE_NAME
	 */
	public String getC_IE_NAME() {
		return c_IE_NAME;
	}

	/**
	 * @param cIENAME the c_IE_NAME to set
	 */
	public void setC_IE_NAME(String cIENAME) {
		c_IE_NAME = cIENAME;
	}

	/**
	 * @return the c_DAI_CODE_REC
	 */
	public String getC_DAI_CODE_REC() {
		return c_DAI_CODE_REC;
	}

	/**
	 * @param cDAICODEREC the c_DAI_CODE_REC to set
	 */
	public void setC_DAI_CODE_REC(String cDAICODEREC) {
		c_DAI_CODE_REC = cDAICODEREC;
	}

	/**
	 * @return the c_DAI_CODE_COP
	 */
	public String getC_DAI_CODE_COP() {
		return c_DAI_CODE_COP;
	}

	/**
	 * @param cDAICODECOP the c_DAI_CODE_COP to set
	 */
	public void setC_DAI_CODE_COP(String cDAICODECOP) {
		c_DAI_CODE_COP = cDAICODECOP;
	}

	/**
	 * @return the c_DAI_CODE_INC
	 */
	public String getC_DAI_CODE_INC() {
		return c_DAI_CODE_INC;
	}

	/**
	 * @param cDAICODEINC the c_DAI_CODE_INC to set
	 */
	public void setC_DAI_CODE_INC(String cDAICODEINC) {
		c_DAI_CODE_INC = cDAICODEINC;
	}

	/**
	 * @return the c_DAI_CODE_FEE
	 */
	public String getC_DAI_CODE_FEE() {
		return c_DAI_CODE_FEE;
	}

	/**
	 * @param cDAICODEFEE the c_DAI_CODE_FEE to set
	 */
	public void setC_DAI_CODE_FEE(String cDAICODEFEE) {
		c_DAI_CODE_FEE = cDAICODEFEE;
	}

	/**
	 * @return the n_STATE
	 */
	public int getN_STATE() {
		return n_STATE;
	}

	/**
	 * @param nSTATE the n_STATE to set
	 */
	public void setN_STATE(int nSTATE) {
		n_STATE = nSTATE;
	}

	/**
	 * @return the n_ORDER
	 */
	public int getN_ORDER() {
		return n_ORDER;
	}

	/**
	 * @param nORDER the n_ORDER to set
	 */
	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}

	public String getC_IE_TYPE() {
		return c_IE_TYPE;
	}

	public void setC_IE_TYPE(String c_IE_TYPE) {
		this.c_IE_TYPE = c_IE_TYPE;
	}

	public String getC_SZ_TYPE() {
		return c_SZ_TYPE;
	}

	public void setC_SZ_TYPE(String c_SZ_TYPE) {
		this.c_SZ_TYPE = c_SZ_TYPE;
	}
	
}
