package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "SyncTradeUnitManage")
public class SyncTradeUnitManage extends SyncBasePojo{
	
	//渠道类型（默认：席位）
	@XmlAttribute(name = "c_DV_CHAN_TYPE")
	private String c_DV_CHAN_TYPE = "";
	
	//交易单元号
	@XmlAttribute(name = "c_TD_CHAN_CODE")
	private String c_TD_CHAN_CODE = "";
	
	//券商（渠道名称）
	@XmlAttribute(name = "c_SECURY_TRADER_CODE")
	private String c_SECURY_TRADER_CODE = "";
	
	//券商（机构名称）
	@XmlAttribute(name = "c_SECURY_MECh_CODE")
	private String c_SECURY_MECh_CODE = "";
	
	//交易市场
	@XmlAttribute(name = "c_MKT_CODE")
	private String c_MKT_CODE = "";

	@XmlTransient
	public String getC_DV_CHAN_TYPE() {
		return c_DV_CHAN_TYPE;
	}

	public void setC_DV_CHAN_TYPE(String c_DV_CHAN_TYPE) {
		this.c_DV_CHAN_TYPE = c_DV_CHAN_TYPE;
	}

	@XmlTransient
	public String getC_TD_CHAN_CODE() {
		return c_TD_CHAN_CODE;
	}

	public void setC_TD_CHAN_CODE(String c_TD_CHAN_CODE) {
		this.c_TD_CHAN_CODE = c_TD_CHAN_CODE;
	}

	@XmlTransient
	public String getC_SECURY_TRADER_CODE() {
		return c_SECURY_TRADER_CODE;
	}

	public void setC_SECURY_TRADER_CODE(String c_SECURY_TRADER_CODE) {
		this.c_SECURY_TRADER_CODE = c_SECURY_TRADER_CODE;
	}

	@XmlTransient
	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String c_MKT_CODE) {
		this.c_MKT_CODE = c_MKT_CODE;
	}

	@XmlTransient
	public String getC_SECURY_MECh_CODE() {
		return c_SECURY_MECh_CODE;
	}

	public void setC_SECURY_MECh_CODE(String c_SECURY_MECh_CODE) {
		this.c_SECURY_MECh_CODE = c_SECURY_MECh_CODE;
	}


}
