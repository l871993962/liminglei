package com.yss.uco.elecreco.support.dzdz.bus.syzqy.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yss.uco.elecreco.support.dzdz.common.RecordElement;

/***
 * 所有者权益（基金净值）变动表报文
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ErSyzqyElement extends RecordElement {

	/**
	 * 指标代码
	 */
	@XmlElement(name = "F_CODE")
	private String c_INDEX_CODE = "";

	/**
	 * 本期实收基金
	 * @return
	 */
	@XmlElement(name = "F_THIS_NAV")
	private String n_THIS_NAV = "0.000000";
	
	/**
	 * 本期未分配利润
	 * @return
	 */
	@XmlElement(name = "F_THIS_UNPROFIT")
	private String n_THIS_UNPROFIT = "0.000000";

	/**
	 * 本期所有者权益合计
	 * @return
	 */
	@XmlElement(name = "F_THIS_INTERESTS")
	private String n_THIS_INTERESTS = "0.000000";

	/**
	 * 上期实收基金
	 * @return
	 */
	@XmlElement(name = "F_LAST_NAV")
	private String n_LAST_NAV = "0.000000";

	/**
	 * 上期未分配利润
	 * @return
	 */
	@XmlElement(name = "F_LAST_UNPROFIT")
	private String n_LAST_UNPROFIT = "0.000000";

	/**
	 * 上期所有者权益
	 * @return
	 */
	@XmlElement(name = "F_LAST_INTERESTS")
	private String n_LAST_INTERESTS = "0.000000";

	@XmlTransient
	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

	@XmlTransient
	public String getN_THIS_NAV() {
		return n_THIS_NAV;
	}

	public void setN_THIS_NAV(String n_THIS_NAV) {
		this.n_THIS_NAV = n_THIS_NAV;
	}

	@XmlTransient
	public String getN_THIS_UNPROFIT() {
		return n_THIS_UNPROFIT;
	}

	public void setN_THIS_UNPROFIT(String n_THIS_UNPROFIT) {
		this.n_THIS_UNPROFIT = n_THIS_UNPROFIT;
	}

	@XmlTransient
	public String getN_THIS_INTERESTS() {
		return n_THIS_INTERESTS;
	}

	public void setN_THIS_INTERESTS(String n_THIS_INTERESTS) {
		this.n_THIS_INTERESTS = n_THIS_INTERESTS;
	}

	@XmlTransient
	public String getN_LAST_NAV() {
		return n_LAST_NAV;
	}

	public void setN_LAST_NAV(String n_LAST_NAV) {
		this.n_LAST_NAV = n_LAST_NAV;
	}

	@XmlTransient
	public String getN_LAST_UNPROFIT() {
		return n_LAST_UNPROFIT;
	}

	public void setN_LAST_UNPROFIT(String n_LAST_UNPROFIT) {
		this.n_LAST_UNPROFIT = n_LAST_UNPROFIT;
	}
	
	@XmlTransient
	public String getN_LAST_INTERESTS() {
		return n_LAST_INTERESTS;
	}

	public void setN_LAST_INTERESTS(String n_LAST_INTERESTS) {
		this.n_LAST_INTERESTS = n_LAST_INTERESTS;
	}
}
