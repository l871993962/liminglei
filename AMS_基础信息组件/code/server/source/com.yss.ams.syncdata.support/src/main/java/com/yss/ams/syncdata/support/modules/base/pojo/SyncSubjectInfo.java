package com.yss.ams.syncdata.support.modules.base.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "SyncSubjectInfo")
public class SyncSubjectInfo extends SyncBasePojo{
	
	//主体名称
	@XmlAttribute(name = "c_ORG_NAME")
	private String c_ORG_NAME = "";
	
	//主体简称
	@XmlAttribute(name = "c_ORG_NAME_ST")
	private String c_ORG_NAME_ST = "";
	
	//主体代码
	@XmlAttribute(name = "c_ORG_CODE")
	private String c_ORG_CODE = "";
	
	//主体类型
	@XmlAttribute(name = "c_DV_ORG_TYPE")
	private String c_DV_ORG_TYPE = "";
	
	//上级主体
	@XmlAttribute(name = "c_ORG_CODE_P")
	private String c_ORG_CODE_P = "";
	
	//主体资质
	@XmlAttribute(name = "c_DV_SUM")
	private String c_DV_SUM = "";
	
	//法人代表
	@XmlAttribute(name = "c_CORP_REP")
	private String c_CORP_REP = "";
	
	//法人代表证件类型
	@XmlAttribute(name = "c_DV_REPCARD_TYPE")
	private String c_DV_REPCARD_TYPE = "";
	
	//法人代表证件代码
	@XmlAttribute(name = "c_REP_CARD_CODE")
	private String c_REP_CARD_CODE = "";
	
	//注册资本
	@XmlAttribute(name = "n_REG_CAP")
	private BigDecimal n_REG_CAP = BigDecimal.ZERO;
	
	//资本币种
	@XmlAttribute(name = "c_DC_CODE")
	private String c_DC_CODE = "";
	
	//成立日期
	@XmlAttribute(name = "d_FOUND_TIME")
	private Date d_FOUND_TIME = null;
	
	//联系人
	@XmlAttribute(name = "c_LINK_MAN")
	private String c_LINK_MAN = "";
	
	//电话
	@XmlAttribute(name = "c_LINK_TEL")
	private String c_LINK_TEL = "";
	
	//手机
	@XmlAttribute(name = "c_MO_TEL")
	private String c_MO_TEL = "";
	
	//邮箱
	@XmlAttribute(name = "c_EMAIL")
	private String c_EMAIL = "";
	
	//传真
	@XmlAttribute(name = "c_FAX_TEL")
	private String c_FAX_TEL ="";
	
	//注册地址
	@XmlAttribute(name = "c_REG_ADDR")
	private String c_REG_ADDR ="";
	
	//注册邮编
	@XmlAttribute(name = "c_REG_POST")
	private String c_REG_POST = "";
	
	//办公地址
	@XmlAttribute(name = "c_OFFIC_ADDR")
	private String c_OFFIC_ADDR ="";
	
	//办公邮编
	@XmlAttribute(name = "c_OFFIC_POST")
	private String c_OFFIC_POST = "";
	
	//公司网址
	@XmlAttribute(name = "c_WWW_ADDR")
	private String c_WWW_ADDR = "";

	@XmlTransient
	public String getC_ORG_NAME() {
		return c_ORG_NAME;
	}

	public void setC_ORG_NAME(String c_ORG_NAME) {
		this.c_ORG_NAME = c_ORG_NAME;
	}

	@XmlTransient
	public String getC_ORG_NAME_ST() {
		return c_ORG_NAME_ST;
	}

	public void setC_ORG_NAME_ST(String c_ORG_NAME_ST) {
		this.c_ORG_NAME_ST = c_ORG_NAME_ST;
	}

	@XmlTransient
	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}

	@XmlTransient
	public String getC_DV_ORG_TYPE() {
		return c_DV_ORG_TYPE;
	}

	public void setC_DV_ORG_TYPE(String c_DV_ORG_TYPE) {
		this.c_DV_ORG_TYPE = c_DV_ORG_TYPE;
	}

	@XmlTransient
	public String getC_ORG_CODE_P() {
		return c_ORG_CODE_P;
	}

	public void setC_ORG_CODE_P(String c_ORG_CODE_P) {
		this.c_ORG_CODE_P = c_ORG_CODE_P;
	}

	@XmlTransient
	public String getC_DV_SUM() {
		return c_DV_SUM;
	}

	public void setC_DV_SUM(String c_DV_SUM) {
		this.c_DV_SUM = c_DV_SUM;
	}

	@XmlTransient
	public String getC_CORP_REP() {
		return c_CORP_REP;
	}

	public void setC_CORP_REP(String c_CORP_REP) {
		this.c_CORP_REP = c_CORP_REP;
	}

	@XmlTransient
	public String getC_DV_REPCARD_TYPE() {
		return c_DV_REPCARD_TYPE;
	}

	public void setC_DV_REPCARD_TYPE(String c_DV_REPCARD_TYPE) {
		this.c_DV_REPCARD_TYPE = c_DV_REPCARD_TYPE;
	}

	@XmlTransient
	public String getC_REP_CARD_CODE() {
		return c_REP_CARD_CODE;
	}

	public void setC_REP_CARD_CODE(String c_REP_CARD_CODE) {
		this.c_REP_CARD_CODE = c_REP_CARD_CODE;
	}

	@XmlTransient
	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String c_DC_CODE) {
		this.c_DC_CODE = c_DC_CODE;
	}

	@XmlTransient
	public Date getD_FOUND_TIME() {
		return d_FOUND_TIME;
	}

	public void setD_FOUND_TIME(Date d_FOUND_TIME) {
		this.d_FOUND_TIME = d_FOUND_TIME;
	}

	@XmlTransient
	public String getC_LINK_MAN() {
		return c_LINK_MAN;
	}

	public void setC_LINK_MAN(String c_LINK_MAN) {
		this.c_LINK_MAN = c_LINK_MAN;
	}

	@XmlTransient
	public String getC_LINK_TEL() {
		return c_LINK_TEL;
	}

	public void setC_LINK_TEL(String c_LINK_TEL) {
		this.c_LINK_TEL = c_LINK_TEL;
	}

	@XmlTransient
	public String getC_MO_TEL() {
		return c_MO_TEL;
	}

	public void setC_MO_TEL(String c_MO_TEL) {
		this.c_MO_TEL = c_MO_TEL;
	}

	@XmlTransient
	public String getC_EMAIL() {
		return c_EMAIL;
	}

	public void setC_EMAIL(String c_EMAIL) {
		this.c_EMAIL = c_EMAIL;
	}

	@XmlTransient
	public String getC_FAX_TEL() {
		return c_FAX_TEL;
	}

	public void setC_FAX_TEL(String c_FAX_TEL) {
		this.c_FAX_TEL = c_FAX_TEL;
	}

	@XmlTransient
	public String getC_REG_ADDR() {
		return c_REG_ADDR;
	}

	public void setC_REG_ADDR(String c_REG_ADDR) {
		this.c_REG_ADDR = c_REG_ADDR;
	}

	@XmlTransient
	public String getC_REG_POST() {
		return c_REG_POST;
	}

	public void setC_REG_POST(String c_REG_POST) {
		this.c_REG_POST = c_REG_POST;
	}

	@XmlTransient
	public String getC_OFFIC_ADDR() {
		return c_OFFIC_ADDR;
	}

	public void setC_OFFIC_ADDR(String c_OFFIC_ADDR) {
		this.c_OFFIC_ADDR = c_OFFIC_ADDR;
	}

	@XmlTransient
	public String getC_OFFIC_POST() {
		return c_OFFIC_POST;
	}

	public void setC_OFFIC_POST(String c_OFFIC_POST) {
		this.c_OFFIC_POST = c_OFFIC_POST;
	}

	@XmlTransient
	public String getC_WWW_ADDR() {
		return c_WWW_ADDR;
	}

	public void setC_WWW_ADDR(String c_WWW_ADDR) {
		this.c_WWW_ADDR = c_WWW_ADDR;
	}

	@XmlTransient
	public BigDecimal getN_REG_CAP() {
		return n_REG_CAP;
	}

	public void setN_REG_CAP(BigDecimal n_REG_CAP) {
		this.n_REG_CAP = n_REG_CAP;
	}


}
