package com.yss.uco.elecreco.support.dzdz.bus.result.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="OUT")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResult {
	@XmlElement(name = "FILE_TYPE")
	private String c_FILE_TYPE = "";
	@XmlElement(name = "FUND_ID")
	private String c_ASS_CODE = "";
	@XmlElement(name = "REPORT_TYPE")
	private String c_RPT_TYPE = "";
	@XmlElement(name = "BEGIN_DATE")
	private String d_START_DATE = "";
	@XmlElement(name = "END_DATE")
	private String d_END_DATE = "";
	@XmlElement(name = "DEPT_CODE")
	private String c_DEPT_CODE = "";
	@XmlElement(name = "CERT_ID")
	private String c_CERT_ID = "";
	@XmlElement(name = "SERIAL_NO")
	private String c_SN = "";

	@XmlElement(name = "RECORD")
	private List<ErResultElement> resultList;

	@XmlTransient
	public String getC_FILE_TYPE() {
		return c_FILE_TYPE;
	}

	public void setC_FILE_TYPE(String cFILETYPE) {
		c_FILE_TYPE = cFILETYPE;
	}

	@XmlTransient
	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String cASSCODE) {
		c_ASS_CODE = cASSCODE;
	}

	@XmlTransient
	public String getC_RPT_TYPE() {
		return c_RPT_TYPE;
	}

	public void setC_RPT_TYPE(String cRPTTYPE) {
		c_RPT_TYPE = cRPTTYPE;
	}

	@XmlTransient
	public String getD_START_DATE() {
		return d_START_DATE;
	}

	public void setD_START_DATE(String dSTARTDATE) {
		d_START_DATE = dSTARTDATE;
	}

	@XmlTransient
	public String getD_END_DATE() {
		return d_END_DATE;
	}

	public void setD_END_DATE(String dENDDATE) {
		d_END_DATE = dENDDATE;
	}

	@XmlTransient
	public String getC_DEPT_CODE() {
		return c_DEPT_CODE;
	}

	public void setC_DEPT_CODE(String cDEPTCODE) {
		c_DEPT_CODE = cDEPTCODE;
	}

	@XmlTransient
	public String getC_CERT_ID() {
		return c_CERT_ID;
	}

	public void setC_CERT_ID(String cCERTID) {
		c_CERT_ID = cCERTID;
	}

	@XmlTransient
	public String getC_SN() {
		return c_SN;
	}

	public void setC_SN(String cSN) {
		c_SN = cSN;
	}

	@XmlTransient
	public List<ErResultElement> getResultList() {
		return resultList;
	}

	public void setResultList(List<ErResultElement> resultList) {
		this.resultList = resultList;
	}

}
