package com.yss.uco.elecreco.support.dzdz.bus.erjzcbdb.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class ErJzcbdbElement {
	/**
	 * 指标代码
	 */
	@XmlElement(name = "F_CODE")
	private String c_INDEX_CODE = "";

	/**
	 * 期初值
	 * @return
	 */
	@XmlElement(name = "F_CUR_VALUE")
	private String n_CUR_VALUE = "0.000000";
	
	/**
	 * 期末值
	 * @return
	 */
	@XmlElement(name = "F_TOL_VALUE")
	private String n_TOL_VALUE = "0.000000";

	@XmlTransient
	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

	@XmlTransient
	public String getN_CUR_VALUE() {
		return n_CUR_VALUE;
	}

	public void setN_CUR_VALUE(String n_CUR_VALUE) {
		this.n_CUR_VALUE = n_CUR_VALUE;
	}

	@XmlTransient
	public String getN_TOL_VALUE() {
		return n_TOL_VALUE;
	}

	public void setN_TOL_VALUE(String n_TOL_VALUE) {
		this.n_TOL_VALUE = n_TOL_VALUE;
	}
}
