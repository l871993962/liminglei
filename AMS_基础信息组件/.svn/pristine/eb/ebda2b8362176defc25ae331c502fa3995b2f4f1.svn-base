package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 交易账户
 * @author chenyoucai
 *
 */
public class SyncTradeAcc {
	
	/**
	 * 银行间资金账户类型
	 */
	@XmlAttribute(name = "c_TYPE")
	private String c_TYPE = "";
	
	/**
	 * 银行间资金账号
	 */
	@XmlAttribute(name = "c_SH_ACC_CODE")
	private String c_SH_ACC_CODE = "";
	
	/**
	 * 银行间债券账户名称
	 */
	@XmlAttribute(name = "c_SH_ACC_NAME")
	private String c_SH_ACC_NAME = "";
	
	/**
	 * 交易市场
	 */
	@XmlAttribute(name = "c_MKT_CODE")
	private String c_MKT_CODE = "";
	
	/**
	 * 开户日期
	 */
	@XmlAttribute(name = "d_BEGIN")
	private Date d_BEGIN = null;
	
	/**
	 * 产品名称
	 */
	@XmlAttribute(name = "c_PORT_NAME")
	private String c_PORT_NAME = "";
	
	/**
	 * 资产代码
	 */
	@XmlAttribute(name = "c_ASS_CODE")
	private String c_ASS_CODE = "";
	
	/**
	 * 基金公司
	 */
	@XmlAttribute(name = "c_FUND_CORP")
	private String c_FUND_CORP = "";
	
	/**
	 * 基金交易账户信息
	 */
	@XmlElement(name = "FundTradeAcc")
	private List<SynFundTradeAcc> fundTradeAccList ;
	
	/**组合代码*/
	@XmlAttribute(name = "c_PORT_CODE")
	private String c_PORT_CODE = "";
	
	@XmlTransient
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	@XmlTransient
	public String getC_TYPE() {
		return c_TYPE;
	}

	public void setC_TYPE(String c_ACCOUNT_TYPE) {
		this.c_TYPE = c_ACCOUNT_TYPE;
	}

	@XmlTransient
	public String getC_SH_ACC_CODE() {
		return c_SH_ACC_CODE;
	}

	public void setC_SH_ACC_CODE(String c_SH_ACC_CODE) {
		this.c_SH_ACC_CODE = c_SH_ACC_CODE;
	}

	@XmlTransient
	public String getC_SH_ACC_NAME() {
		return c_SH_ACC_NAME;
	}

	public void setC_SH_ACC_NAME(String c_SH_ACC_NAME) {
		this.c_SH_ACC_NAME = c_SH_ACC_NAME;
	}

	@XmlTransient
	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String c_MKT_CODE) {
		this.c_MKT_CODE = c_MKT_CODE;
	}

	@XmlTransient
	public Date getD_BEGIN() {
		return d_BEGIN;
	}

	public void setD_BEGIN(Date d_BEGIN) {
		this.d_BEGIN = d_BEGIN;
	}

	@XmlTransient
	public String getC_PORT_NAME() {
		return c_PORT_NAME;
	}

	public void setC_PORT_NAME(String c_PORT_NAME) {
		this.c_PORT_NAME = c_PORT_NAME;
	}
	
	@XmlTransient
	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
	}
	
	@XmlTransient
	public String getC_FUND_CORP() {
		return c_FUND_CORP;
	}

	public void setC_FUND_CORP(String c_FUND_CORP) {
		this.c_FUND_CORP = c_FUND_CORP;
	}

	@XmlTransient
	public List<SynFundTradeAcc> getFundTradeAccList() {
		return fundTradeAccList;
	}

	public void setFundTradeAccList(List<SynFundTradeAcc> fundTradeAccList) {
		this.fundTradeAccList = fundTradeAccList;
	}
}
