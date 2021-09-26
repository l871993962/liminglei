package com.yss.uco.elecreco.support.dzdz.bus.zcfz.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/***
 * 资产负债表报文
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ErZcfzElement {

	/**
	 * 指标代码
	 */
	@XmlElement(name = "F_CODE")
	private String c_INDEX_CODE = "";

	/**
	 * 期初值
	 * @return
	 */
	@XmlElement(name = "F_BEGIN_VALUE")
	private String n_BEGIN_VALUE = "0.000000";
	
	/**
	 * 期末值
	 * @return
	 */
	@XmlElement(name = "F_END_VALUE")
	private String n_END_VALUE = "0.000000";

	@XmlTransient
	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

	@XmlTransient
	public String getN_BEGIN_VALUE() {
		return n_BEGIN_VALUE;
	}

	public void setN_BEGIN_VALUE(String n_BEGIN_VALUE) {
		this.n_BEGIN_VALUE = n_BEGIN_VALUE;
	}

	@XmlTransient
	public String getN_END_VALUE() {
		return n_END_VALUE;
	}

	public void setN_END_VALUE(String n_END_VALUE) {
		this.n_END_VALUE = n_END_VALUE;
	}
	
}
