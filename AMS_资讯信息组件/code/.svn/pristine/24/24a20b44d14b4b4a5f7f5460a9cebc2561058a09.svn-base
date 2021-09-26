
package com.yss.ams.sec.information.support.modules.pub.pojo;

import java.util.Date;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc 计算债券每百元利息的关键因子
 * @version 1.0 2011-12-9
 * @author yh
 */
public class FiHundIntFactorBean {
	
	/**
	 * 债券代码
	 */
	private String secCode = null;
	
	/**
	 * 市场
	 */
	private String market = null;
	
	/**
	 * 债券起息日
	 */
	private Date bondBeginDate = null;
	
	/**
	 * 债券截息日
	 */
	private Date bondEndDate = null;
	
	/**
	 * 当前付息期间起息日
	 */
	private Date curPeriodBeginDate = null;
	
	/**
	 * 当期付息期间截息日
	 */
	private Date curPeriodEndDate = null;
	
	/**
	 * 是否启用新利率
	 */
	private boolean isApplyNewRate = false;
	
	/**
	 * 调息日
	 */
	private Date adjustDate = null;
	
	/**
	 * 剩余本金
	 */
	private double remainMoney = 0;
	
	/**
	 * 发行面值
	 */
	private double issueFaceValue = 0;
	
	/**
	 * 发行价格
	 */
	private double issuePrice = 0;
	
	/**
	 * 票面利率
	 */
	private double couponRate = 0;
	
	/**
	 * 报价方式
	 */
	private String quoteMode = null;
	
	/**
	 * 付息频率
	 */
	private String payFrequency = null;
	
	/**
	 * 计息方式
	 */
	private String accrualMode = null;
	
	/**
	 * 计息公式
	 */
	private String accrualFormula = null;
	
	/**
	 * 付息方式
	 */
	private String payMode = null;
	
	/**
	 * 税率
	 */
	private double taxRate = 0;

	/**
	 * 倒置算法 add by liuxiang 2013/12/26 STORY #14457 理财品种信息增加一种正推的计息模式
	 */
	private String backWard = null;
	
	/**
	 * add by wangtangyao 2016/04/05 STORY #28884理财产品信息计息公式：A/A-F，请增加一项计息天数
	 */
	private double intyeardays = 0;
	
	/**
	 * 是否期内还本
	 */
	private int isQnhb = 0;
	/**
	 * 期内还本日期
	 */
	private Date qnhbDate =null;
	
	public int getIsQnhb() {
		return isQnhb;
	}

	public void setIsQnhb(int isQnhb) {
		this.isQnhb = isQnhb;
	}

	public Date getQnhbDate() {
		return qnhbDate;
	}

	public void setQnhbDate(Date qnhbDate) {
		this.qnhbDate = qnhbDate;
	}

	public double getIntyeardays() {
		return intyeardays;
	}

	public void setIntyeardays(double intyeardays) {
		this.intyeardays = intyeardays;
	}

	/**
	 * @return the secCode
	 */
	public String getSecCode() {
		return secCode;
	}

	/**
	 * @param secCode the secCode to set
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	

	/**
	 * @return the adjustDate
	 */
	public Date getAdjustDate() {
		return adjustDate;
	}

	/**
	 * @param adjustDate the adjustDate to set
	 */
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	/**
	 * @return the remainMoney
	 */
	public double getRemainMoney() {
		return remainMoney;
	}

	/**
	 * @param remainMoney the remainMoney to set
	 */
	public void setRemainMoney(double remainMoney) {
		this.remainMoney = remainMoney;
	}

	/**
	 * @return the couponRate
	 */
	public double getCouponRate() {
		return couponRate;
	}

	/**
	 * @param couponRate the couponRate to set
	 */
	public void setCouponRate(double couponRate) {
		this.couponRate = couponRate;
	}

	/**
	 * @return the payFrequency
	 */
	public String getPayFrequency() {
		return payFrequency;
	}

	/**
	 * @param payFrequency the payFrequency to set
	 */
	public void setPayFrequency(String payFrequency) {
		this.payFrequency = payFrequency;
	}

	/**
	 * @return the accrualFormula
	 */
	public String getAccrualFormula() {
		return accrualFormula;
	}

	/**
	 * @param accrualFormula the accrualFormula to set
	 */
	public void setAccrualFormula(String accrualFormula) {
		this.accrualFormula = accrualFormula;
	}

	
	/**
	 * @return the taxRate
	 */
	public double getTaxRate() {
		return taxRate;
	}

	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * @return the bondBeginDate
	 */
	public Date getBondBeginDate() {
		return bondBeginDate;
	}

	/**
	 * @param bondBeginDate the bondBeginDate to set
	 */
	public void setBondBeginDate(Date bondBeginDate) {
		this.bondBeginDate = bondBeginDate;
	}

	/**
	 * @return the bondEndDate
	 */
	public Date getBondEndDate() {
		return bondEndDate;
	}

	/**
	 * @param bondEndDate the bondEndDate to set
	 */
	public void setBondEndDate(Date bondEndDate) {
		this.bondEndDate = bondEndDate;
	}

	/**
	 * @return the curPeriodBeginDate
	 */
	public Date getCurPeriodBeginDate() {
		return curPeriodBeginDate;
	}

	/**
	 * @param curPeriodBeginDate the curPeriodBeginDate to set
	 */
	public void setCurPeriodBeginDate(Date curPeriodBeginDate) {
		this.curPeriodBeginDate = curPeriodBeginDate;
	}

	/**
	 * @return the curPeriodEndDate
	 */
	public Date getCurPeriodEndDate() {
		return curPeriodEndDate;
	}

	/**
	 * @param curPeriodEndDate the curPeriodEndDate to set
	 */
	public void setCurPeriodEndDate(Date curPeriodEndDate) {
		this.curPeriodEndDate = curPeriodEndDate;
	}

	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * @param market the market to set
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * @return the isApplyNewRate
	 */
	public boolean isApplyNewRate() {
		return isApplyNewRate;
	}

	/**
	 * @param isApplyNewRate the isApplyNewRate to set
	 */
	public void setApplyNewRate(boolean isApplyNewRate) {
		this.isApplyNewRate = isApplyNewRate;
	}

	/**
	 * @return the quoteMode
	 */
	public String getQuoteMode() {
		return quoteMode;
	}

	/**
	 * @param quoteMode the quoteMode to set
	 */
	public void setQuoteMode(String quoteMode) {
		this.quoteMode = quoteMode;
	}

	/**
	 * @return the faceIssueValue
	 */
	public double getIssueFaceValue() {
		return issueFaceValue;
	}

	/**
	 * @param issueFaceValue the faceIssueValue to set
	 */
	public void setIssueFaceValue(double issueFaceValue) {
		this.issueFaceValue = issueFaceValue;
	}

	/**
	 * @return the payMode
	 */
	public String getPayMode() {
		return payMode;
	}

	/**
	 * @param payMode the payMode to set
	 */
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	/**
	 * @return the accrualMode
	 */
	public String getAccrualMode() {
		return accrualMode;
	}

	/**
	 * @param accrualMode the accrualMode to set
	 */
	public void setAccrualMode(String accrualMode) {
		this.accrualMode = accrualMode;
	}

	/**
	 * @return the issuePrice
	 */
	public double getIssuePrice() {
		return issuePrice;
	}

	/**
	 * @param issuePrice the issuePrice to set
	 */
	public void setIssuePrice(double issuePrice) {
		this.issuePrice = issuePrice;
	}

	/**
	 * @return backWare
	 */
	public String getBackWard() {
		return backWard;
	}

	/**
	 * @param backWare
	 */
	public void setBackWard(String backWard) {
		this.backWard = backWard;
	}
	
	
}

