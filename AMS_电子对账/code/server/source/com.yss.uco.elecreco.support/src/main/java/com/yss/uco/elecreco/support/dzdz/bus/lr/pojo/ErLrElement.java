package com.yss.uco.elecreco.support.dzdz.bus.lr.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/***
 * 利润表报文
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ErLrElement {

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
	private String n_cur_value = "0.000000";
	
	/**
	 * 期末值
	 * @return
	 */
	@XmlElement(name = "F_TOL_VALUE")
	private String n_tol_value = "0.000000";

	@XmlTransient
	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

	@XmlTransient
	public String getN_cur_value() {
		return n_cur_value;
	}

	public void setN_cur_value(String n_cur_value) {
		this.n_cur_value = n_cur_value;
	}

	@XmlTransient
	public String getN_tol_value() {
		return n_tol_value;
	}

	public void setN_tol_value(String n_tol_value) {
		this.n_tol_value = n_tol_value;
	}
	
}
