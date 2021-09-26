package com.yss.ams.sec.information.support.modules.sv.base.pojo;

import java.math.BigDecimal;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 期货结算债券转换信息 pojo类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class FutureFactor extends AuditableParamPojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 合约代码
	 */
	private String c_CONTRACT_CODE = "";
	
	/**
	 * 债券代码
	 */
	private String c_SEC_CODE = "";
	
	/**
	 * 交易市场
	 */
	private String c_MKT_CODE = "";
	
	/**
	 * 转换因子
	 */
	private BigDecimal n_CONVERT_FACTOR = BigDecimal.ONE;

	public String getC_CONTRACT_CODE() {
		return c_CONTRACT_CODE;
	}

	public void setC_CONTRACT_CODE(String c_CONTRACT_CODE) {
		this.c_CONTRACT_CODE = c_CONTRACT_CODE;
	}

	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String c_SEC_CODE) {
		this.c_SEC_CODE = c_SEC_CODE;
	}

	public BigDecimal getN_CONVERT_FACTOR() {
		return n_CONVERT_FACTOR;
	}

	public void setN_CONVERT_FACTOR(BigDecimal n_CONVERT_FACTOR) {
		this.n_CONVERT_FACTOR = n_CONVERT_FACTOR;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String c_MKT_CODE) {
		this.c_MKT_CODE = c_MKT_CODE;
	}
	
	
}
