package com.yss.ams.sec.information.support.modules.sv.base.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.yss.framework.api.common.co.BasePojo;

public class SecBaseLcfxzq extends BasePojo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 销售数据的ID   add by gh 20161021 BUG #140945 
	 */
	private String c_IDEN_RELA = "";

	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";
	
	/**
	 * 起息日期
	 */
	private Date d_AI_BEGIN;
	
	/**
	 * 截息日期
	 */
	private Date d_AI_END;
	
	/**
	 * 应计利息
	 */
	private BigDecimal n_FV_IR = BigDecimal.ZERO;
	
	

	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String c_SEC_CODE) {
		this.c_SEC_CODE = c_SEC_CODE;
	}

	public Date getD_AI_BEGIN() {
		return d_AI_BEGIN;
	}

	public void setD_AI_BEGIN(Date d_AI_BEGIN) {
		this.d_AI_BEGIN = d_AI_BEGIN;
	}

	public Date getD_AI_END() {
		return d_AI_END;
	}

	public void setD_AI_END(Date d_AI_END) {
		this.d_AI_END = d_AI_END;
	}

	public BigDecimal getN_FV_IR() {
		return n_FV_IR;
	}

	public void setN_FV_IR(BigDecimal n_FV_IR) {
		this.n_FV_IR = n_FV_IR;
	}

	public String getC_IDEN_RELA() {
		return c_IDEN_RELA;
	}

	public void setC_IDEN_RELA(String c_IDEN_RELA) {
		this.c_IDEN_RELA = c_IDEN_RELA;
	}


	
}
