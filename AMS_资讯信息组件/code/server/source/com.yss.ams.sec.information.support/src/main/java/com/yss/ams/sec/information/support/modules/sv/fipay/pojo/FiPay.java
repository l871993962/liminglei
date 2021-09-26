package com.yss.ams.sec.information.support.modules.sv.fipay.pojo;


import java.math.BigDecimal;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 债券付息信息实体类，解析和拼接数据
 * 
 * @author yh 2010.12.31
 * @version V4.5.0.1 债券付息信息实体类，实现具体的解析和拼接数据 lyh 2011.1.6 V4.5.0.2 ---修改记录---
 *          修改人：wuwenlan 20110223 当前版本：v4.5.03 修改描述： 类的属性命名修改
 *          修改解析方法，拼接方法和获取数据库字段值
 * 
 *          4.5.0.4 修改日期 2011-03-11 修改简介：添加//周期新利率 字段
 * @author chenyoulong
 */

/**
 * 债券付息信息pojo类
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
public class FiPay extends AuditableParamPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3167945825423304062L;
	/**
	* 债券代码 
	*/
	  private String c_SEC_CODE = "";

	/**
	* 调息日期 
	*/
	  private String d_ADJ = "";

	/**
	* 票面利率 
	*/
	  private BigDecimal n_COUP_RATE = BigDecimal.ZERO;

	/**
	* 剩余本金 
	*/
	  private BigDecimal n_REM_COR = BigDecimal.ZERO;
	  
	/**
	* 偿还数量
	*/
	  private BigDecimal n_REPAY_AMOUNT = BigDecimal.ZERO;

	/**
	* 本次起息日 
	*/
	  private String d_BEGIN = "";

	/**
	* 本次截息日 
	*/
	  private String d_END = "";

	/**
	* 描述 
	*/
	  private String c_DESC = "";

	/**
	* 周期新利率 
	*/
	  private String c_DV_BOOL_TYPE = "";
	  
   /**
    * 申购期间收益
    */
	  private String c_SGQJSY = "";

	/**
	* 交易市场 
	*/
	  private String c_MKT_CODE = "";
	//  edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
	 /**
	  * 数据来源
	 */
	  private String c_DATA_IDF = "";
	  
	/**
	 * 是否期内还本
	 */
	private int n_QNHB = 0 ;
	  
	/**
	 * 期内还本日期
	 */
	private String d_QNHB = "";
	  
	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String cSECCODE) {
		c_SEC_CODE = cSECCODE;
	}

	public String getD_ADJ() {
		return d_ADJ;
	}

	public void setD_ADJ(String dADJ) {
		d_ADJ = dADJ;
	}

	public BigDecimal getN_COUP_RATE() {
		return n_COUP_RATE;
	}

	public void setN_COUP_RATE(BigDecimal nCOUPRATE) {
		n_COUP_RATE = nCOUPRATE;
	}

	public BigDecimal getN_REM_COR() {
		return n_REM_COR;
	}

	public void setN_REM_COR(BigDecimal nREMCOR) {
		n_REM_COR = nREMCOR;
	}

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

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_DV_BOOL_TYPE() {
		return c_DV_BOOL_TYPE;
	}

	public void setC_DV_BOOL_TYPE(String cDVBOOLTYPE) {
		c_DV_BOOL_TYPE = cDVBOOLTYPE;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getN_REPAY_AMOUNT() {
		return n_REPAY_AMOUNT;
	}

	public void setN_REPAY_AMOUNT(BigDecimal n_REPAY_AMOUNT) {
		this.n_REPAY_AMOUNT = n_REPAY_AMOUNT;
	}

//  edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
	public String getC_DATA_IDF() {
		return c_DATA_IDF;
	}

	public void setC_DATA_IDF(String c_DATA_IDF) {
		this.c_DATA_IDF = c_DATA_IDF;
	}

	public String getC_SGQJSY() {
		return c_SGQJSY;
	}

	public void setC_SGQJSY(String cSGQJSY) {
		this.c_SGQJSY = cSGQJSY;
	}

	public int getN_QNHB() {
		return n_QNHB;
	}

	public void setN_QNHB(int n_QNHB) {
		this.n_QNHB = n_QNHB;
	}

	public String getD_QNHB() {
		return d_QNHB;
	}

	public void setD_QNHB(String d_QNHB) {
		this.d_QNHB = d_QNHB;
	}



}
