package com.yss.uco.elecreco.support.dzdz.common.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *  文件回执
 * @author weijj
 *
 *
 */
@XmlRootElement(name = "OUT")
public class FileReceipt {
	@XmlElement(name="FILE_TYPE")
	private String c_FILE_TYPE = "";
	
	@XmlElement(name="FUND_ID")
	private String c_ASS_CODE = "";
	
	@XmlElement(name="REPORT_TYPE")
	private String c_RPT_TYPE = "";
	
	@XmlElement(name="BEGIN_DATE")
	private String d_START_DATE = "";
	
	@XmlElement(name="END_DATE")
	private String d_END_DATE = "";
	
	@XmlElement(name="DEPT_CODE")
	private String c_DEPT_CODE = "";
	
	@XmlElement(name="SERIAL_NO")
	private String c_SN = "";
	
	@XmlElement(name="RETCODE")
	private String c_RET_CODE = "";
	
	@XmlElement(name="RETMSG")
	private String c_RET_MSG = "";
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
	public String getC_SN() {
		return c_SN;
	}

	public void setC_SN(String cSN) {
		c_SN = cSN;
	}
	@XmlTransient
	public String getC_RET_CODE() {
		return c_RET_CODE;
	}

	public void setC_RET_CODE(String cRETCODE) {
		c_RET_CODE = cRETCODE;
	}
	@XmlTransient
	public String getC_RET_MSG() {
		return c_RET_MSG;
	}

	public void setC_RET_MSG(String cRETMSG) {
		c_RET_MSG = cRETMSG;
	}
	
}
