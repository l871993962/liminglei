package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "FundTradeAcc")
public class FundTradeAcc extends SyncBasePojo{
	
	//网点名称
	@XmlAttribute(name = "c_INT_NAME")
	private String c_INT_NAME = "";
	
	//销售商代码
	@XmlAttribute(name = "c_VENDOR_CODE")
	private String c_VENDOR_CODE = "";
	
	//交易账户
	@XmlAttribute(name = "c_ACC_NUM_TRADE")
	private String c_ACC_NUM_TRADE ="";
	
	//交易/资金密码
	@XmlAttribute(name = "c_PSW")
	private String c_PSW ="";
	
	@XmlTransient
	public String getC_INT_NAME() {
		return c_INT_NAME;
	}
	public void setC_INT_NAME(String c_INT_NAME) {
		this.c_INT_NAME = c_INT_NAME;
	}
	
	@XmlTransient
	public String getC_VENDOR_CODE() {
		return c_VENDOR_CODE;
	}
	public void setC_VENDOR_CODE(String c_VENDOR_CODE) {
		this.c_VENDOR_CODE = c_VENDOR_CODE;
	}
	
	@XmlTransient
	public String getC_ACC_NUM_TRADE() {
		return c_ACC_NUM_TRADE;
	}
	public void setC_ACC_NUM_TRADE(String c_ACC_NUM_TRADE) {
		this.c_ACC_NUM_TRADE = c_ACC_NUM_TRADE;
	}
	
	@XmlTransient
	public String getC_PSW() {
		return c_PSW;
	}
	public void setC_PSW(String c_PSW) {
		this.c_PSW = c_PSW;
	}
	
	

}
