package com.yss.uco.elecreco.support.dzdz.common.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yss.uco.elecreco.support.dzdz.bus.dblgz.pojo.ErDblgzbElement;
import com.yss.uco.elecreco.support.dzdz.bus.erjzcbdb.pojo.ErJzcbdbElement;
import com.yss.uco.elecreco.support.dzdz.bus.gz.pojo.ErGzbElement;
import com.yss.uco.elecreco.support.dzdz.bus.km.pojo.ErKmElement;
import com.yss.uco.elecreco.support.dzdz.bus.lr.pojo.ErLrElement;
import com.yss.uco.elecreco.support.dzdz.bus.result.pojo.ErResultElement;
import com.yss.uco.elecreco.support.dzdz.bus.syzqy.pojo.ErSyzqyElement;
import com.yss.uco.elecreco.support.dzdz.bus.yue.pojo.ErYuebElement;
import com.yss.uco.elecreco.support.dzdz.bus.zcfz.pojo.ErZcfzElement;

/**
 * xml文件输出 增加额外的信息表述
 * 
 * @author weijj
 * 
 */
@XmlRootElement(name = "OUT")
public class XmlFile {
	////添加描述信息
	@XmlElement(name = "失败原因")
	protected String c_ERR_INFO;
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
	@XmlElement(name="CERT_ID")
	private String c_CERT_ID = "";
	@XmlElement(name="SERIAL_NO")
	private String c_SN = "";

	@XmlElement(name = "RECORD")
	private List<ErGzbElement> erGzbList;
	
	@XmlElement(name = "RECORD")
	private List<ErDblgzbElement> erDblgzbList;
	
	@XmlElement(name = "RECORD")
	private List<ErYuebElement> erYuebList;
	
	@XmlElement(name = "RECORD")
	private List<ErKmElement> erKmList;
	
	@XmlElement(name = "RECORD")
	private List<ErSyzqyElement> erSyzqyList;

	@XmlElement(name = "RECORD")
	private List<ErLrElement> erLrList;
	
	@XmlElement(name = "RECORD")
	private List<ErZcfzElement> erZcfzList;
	
	@XmlElement(name = "RECORD")
	private List<ErResultElement> resultList;
	
	@XmlElement(name = "RECORD")
	private List<ErJzcbdbElement> erJzcbdbList;
	
	public void setC_ERR_INFO(String cERRINFO) {
		c_ERR_INFO = cERRINFO;
	}

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
	public List<ErKmElement> getErKmList() {
		return erKmList;
	}

	public void setErKmList(List<ErKmElement> erKmList) {
		this.erKmList = erKmList;
	}

	@XmlTransient
	public List<ErYuebElement> getErYuebList() {
		return erYuebList;
	}

	public void setErYuebList(List<ErYuebElement> erYuebList) {
		this.erYuebList = erYuebList;
	}
	
	@XmlTransient
	public List<ErGzbElement> getErGzbList() {
		return erGzbList;
	}

	public void setErGzbList(List<ErGzbElement> erGzbList) {
		this.erGzbList = erGzbList;
	}
	
	@XmlTransient
	public List<ErResultElement> getResultList() {
		return resultList;
	}

	public void setResultList(List<ErResultElement> resultList) {
		this.resultList = resultList;
	}
	
	@XmlTransient
	public String getC_ERR_INFO() {
		return c_ERR_INFO;
	}

	@XmlTransient
	public List<ErSyzqyElement> getErSyzqyList() {
		return erSyzqyList;
	}

	public void setErSyzqyList(List<ErSyzqyElement> erSyzqyList) {
		this.erSyzqyList = erSyzqyList;
	}

	@XmlTransient
	public List<ErLrElement> getErLrList() {
		return erLrList;
	}

	public void setErLrList(List<ErLrElement> erLrList) {
		this.erLrList = erLrList;
	}

	@XmlTransient
	public List<ErZcfzElement> getErZcfzList() {
		return erZcfzList;
	}

	public void setErZcfzList(List<ErZcfzElement> erZcfzList) {
		this.erZcfzList = erZcfzList;
	}

	@XmlTransient
	public List<ErDblgzbElement> getErDblgzbList() {
		return erDblgzbList;
	}

	public void setErDblgzbList(List<ErDblgzbElement> erDblgzbList) {
		this.erDblgzbList = erDblgzbList;
	}
	
	@XmlTransient
	public List<ErJzcbdbElement> getErJzcbdbList() {
		return erJzcbdbList;
	}

	public void setErJzcbdbList(List<ErJzcbdbElement> erJzcbdbList) {
		this.erJzcbdbList = erJzcbdbList;
	}
	
}
