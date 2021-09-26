package com.yss.uco.elecreco.support.dzdz.bus.km.pojo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yss.uco.elecreco.support.dzdz.common.RecordElement;

public class ErKmElement extends RecordElement{
	/**
	 * 科目代码
	 */
	@XmlElement(name = "FACCTCODE")
	private String c_KM_CODE = "";
	
	/**
	 * 科目名称
	 */
	@XmlElement(name = "FACCTNAME")
	private String c_KM_NAME = "";
	
	/**
	 * 科目级别
	 */
	@XmlElement(name = "FACCTLEVEL")
	private String c_KM_LEVEL = "";

	/**
	 * 上级科目代码
	 */
	@XmlElement(name = "FACCTPARENT")
	private String c_KM_CODE_P = "";

	/**
	 * 是否明细科目
	 */
	@XmlElement(name = "FACCTDETAIL")
	private int n_DETAIL = 0;
	
	/**
	 * 科目类别
	 */
	@XmlElement(name = "FACCTCLASS")
	private String c_DV_KM_CLS = "";
	
	/**
	 * 余额方向
	 */
	@XmlElement(name = "FBALDC")
	private String c_DV_JD_WAY = "";
	

	@XmlTransient
	public String getC_KM_CODE() {
		return c_KM_CODE;
	}

	public void setC_KM_CODE(String cKMCODE) throws Exception {
		c_KM_CODE = getKmCode(cKMCODE);
	}
	
	@XmlTransient
	public String getC_KM_NAME() {
		return c_KM_NAME;
	}

	public void setC_KM_NAME(String cKMNAME) {
		c_KM_NAME = cKMNAME;
	}
	@XmlTransient
	public String getC_KM_LEVEL() {
		return c_KM_LEVEL;
	}

	public void setC_KM_LEVEL(String cKMLEVEL) {
		c_KM_LEVEL = cKMLEVEL;
	}
	@XmlTransient
	public String getC_KM_CODE_P() {
		return c_KM_CODE_P;
	}

	public void setC_KM_CODE_P(String cKMCODEP) throws Exception {
		c_KM_CODE_P = getKmCode(cKMCODEP);
	}
	@XmlTransient
	public int getN_DETAIL() {
		return n_DETAIL;
	}

	public void setN_DETAIL(int nDETAIL) {
		n_DETAIL = nDETAIL;
	}
	@XmlTransient
	public String getC_DV_KM_CLS() {
		return c_DV_KM_CLS;
	}

	public void setC_DV_KM_CLS(String cDVKMCLS) {
		c_DV_KM_CLS = cDVKMCLS;
	}
	@XmlTransient
	public String getC_DV_JD_WAY() {
		return c_DV_JD_WAY;
	}

	public void setC_DV_JD_WAY(String cDVJDWAY) {
		c_DV_JD_WAY = cDVJDWAY;
	}
}