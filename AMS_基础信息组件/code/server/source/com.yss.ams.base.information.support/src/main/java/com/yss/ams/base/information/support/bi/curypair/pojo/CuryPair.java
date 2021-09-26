package com.yss.ams.base.information.support.bi.curypair.pojo;

import java.math.BigDecimal;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 货币对 pojo
 * @author 马向峰  拆分  2017.0527
 *
 */
public class CuryPair extends AuditableParamPojo {

	/**
	* 货币对代码 
	*/
	  private String c_CURY_PAIR_CODE = "";

	/**
	* 货币对名称 
	*/
	  private String c_CURY_PAIR_NAME = "";

	/**
	* 基准货币 
	*/
	  private String c_DC_CODE_MARK = "";

	/**
	* 计价货币 
	*/
	  private String c_DC_CODE_PRICE = "";

	/**
	* 报价因子 
	*/
	  private BigDecimal n_QTE_FACTO = BigDecimal.ZERO;

	/**
	* 描述 
	*/
	  private String c_DESC = "";

	private static final long serialVersionUID = 1L ;

	public String getC_CURY_PAIR_CODE() {
		return c_CURY_PAIR_CODE;
	}

	public void setC_CURY_PAIR_CODE(String cCURYPAIRCODE) {
		c_CURY_PAIR_CODE = cCURYPAIRCODE;
	}

	public String getC_CURY_PAIR_NAME() {
		return c_CURY_PAIR_NAME;
	}

	public void setC_CURY_PAIR_NAME(String cCURYPAIRNAME) {
		c_CURY_PAIR_NAME = cCURYPAIRNAME;
	}

	public String getC_DC_CODE_MARK() {
		return c_DC_CODE_MARK;
	}

	public void setC_DC_CODE_MARK(String cDCCODEMARK) {
		c_DC_CODE_MARK = cDCCODEMARK;
	}

	public String getC_DC_CODE_PRICE() {
		return c_DC_CODE_PRICE;
	}

	public void setC_DC_CODE_PRICE(String cDCCODEPRICE) {
		c_DC_CODE_PRICE = cDCCODEPRICE;
	}

	public BigDecimal getN_QTE_FACTO() {
		return n_QTE_FACTO;
	}

	public void setN_QTE_FACTO(BigDecimal nQTEFACTO) {
		n_QTE_FACTO = nQTEFACTO;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
