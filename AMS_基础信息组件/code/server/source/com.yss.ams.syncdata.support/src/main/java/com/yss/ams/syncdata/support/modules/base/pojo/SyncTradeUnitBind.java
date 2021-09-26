package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "SyncTradeUnitBind")
public class SyncTradeUnitBind {

	
	/*资产代码*/
	@XmlAttribute(name = "c_ASS_CODE")
	private String c_ASS_CODE = "";
	
	//交易单元号
	@XmlAttribute(name = "c_TD_CHAN_CODE")
	private String c_TD_CHAN_CODE = "";
	
	//券商（渠道名称）
	@XmlAttribute(name = "c_SECURY_TRADER_CODE")
	private String c_SECURY_TRADER_CODE = "";
	
	//交易市场
	@XmlAttribute(name = "c_MKT_CODE")
	private String c_MKT_CODE = "";
	
	//交易单元类型
	@XmlAttribute(name="c_DV_SEAT_TYPE")
	private String c_DV_SEAT_TYPE = "";

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
	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
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
	public String getC_DV_SEAT_TYPE() {
		return c_DV_SEAT_TYPE;
	}

	public void setC_DV_SEAT_TYPE(String c_DV_SEAT_TYPE) {
		this.c_DV_SEAT_TYPE = c_DV_SEAT_TYPE;
	}
	
}
