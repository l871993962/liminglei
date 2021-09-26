package com.yss.uco.elecreco.support.dzdz.bus.gz.pojo;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.support.dzdz.common.RecordElement;

/***
 * 估值表报文
 * 
 * @author weijj
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ErGzbElement extends RecordElement{

	/**
	 * 
	 * 科目编码
	 */
	@XmlElement(name = "FKMBM")
	private String c_KM_CODE = "";

	/**
	 * 科目名称
	 */
	@XmlElement(name = "FKMMC")
	private String c_KM_NAME = "";

	/**
	 * 行情价格
	 */
	@XmlElement(name = "FHQJG")
	private String n_VA_PRICE = "0.000000";

	/**
	 * 行情标志
	 */
	@XmlElement(name = "FHQBZ")
	private int n_QUOT_LOGO = 1;

	/**
	 * 证券数量
	 */
	@XmlElement(name = "FZQSL")
	private String n_AMOUNT = "0.000000";

	/**
	 * 证券成本
	 */
	@XmlElement(name = "FZQCB")
	private String n_PORT_COST = "0.000000";
	/**
	 * 证券市值
	 */
	@XmlElement(name = "FZQSZ")
	private String n_PORT_MV = "0.000000";

	/**
	 * 估值增值
	 */
	@XmlElement(name = "FGZ_ZZ")
	private String n_PORT_IV = "0.000000";

	/**
	 * 成本占净值比
	 */
	@XmlElement(name = "FCB_JZ_BL")
	private String n_CB_JZ_BL = "0.000000";

	/**
	 * 市值占净值比
	 */
	@XmlElement(name = "FSZ_JZ_BL")
	private String n_SZ_JZ_BL = "0.000000";
	/**
	 * 是否明细数据
	 */
	@XmlElement(name = "FISDETAIL")
	private int n_DETAIL = 0;
	
	/**
	 * 分级组合代码
	 */
	private String c_PORT_CLS_CODE = "";
	
	 /**
	  * asscode
	  */
	 
	 private String c_ASS_CODE = "";
	
	/**  
	 * 报文序号
	 */
	private String c_SN = "";

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
	public String getN_VA_PRICE() {
		return n_VA_PRICE;
	}

	public void setN_VA_PRICE(String nVAPRICE) {
		n_VA_PRICE = getDoubleValue(nVAPRICE);
	}

	@XmlTransient
	public int getN_QUOT_LOGO() {
		return n_QUOT_LOGO;
	}

	public void setN_QUOT_LOGO(int nQUOTLOGO) {
		n_QUOT_LOGO = nQUOTLOGO;
	}

	@XmlTransient
	public String getN_AMOUNT() {
		return n_AMOUNT;
	}

	public void setN_AMOUNT(String nAMOUNT) {
		n_AMOUNT = getDoubleValue(nAMOUNT);
	}

	@XmlTransient
	public String getN_PORT_COST() {
		return getDoubleValue(n_PORT_COST);
	}

	public void setN_PORT_COST(String nPORTCOST) {
		n_PORT_COST = getDoubleValue(nPORTCOST);
	}

	@XmlTransient
	public String getN_PORT_MV() {
		return n_PORT_MV;
	}

	public void setN_PORT_MV(String nPORTMV) {
		n_PORT_MV = getDoubleValue(nPORTMV);
	}

	@XmlTransient
	public String getN_PORT_IV() {
		return getDoubleValue(n_PORT_IV);
	}

	public void setN_PORT_IV(String nPORTIV) {
		n_PORT_IV = getDoubleValue(nPORTIV);
	}

	public String getN_CB_JZ_BL() {
		return n_CB_JZ_BL;
	}

	public void setN_CB_JZ_BL(String nCBJZBL) {
		n_CB_JZ_BL = getDoubleValue(nCBJZBL);
	}

	public String getN_SZ_JZ_BL() {
		return n_SZ_JZ_BL;
	}

	public void setN_SZ_JZ_BL(String nSZJZBL) {
		n_SZ_JZ_BL = getDoubleValue(nSZJZBL);
	}

	@XmlTransient
	public int getN_DETAIL() {
		return n_DETAIL;
	}

	public void setN_DETAIL(int nDETAIL) {
		n_DETAIL = nDETAIL;
	}

	public String getC_PORT_CLS_CODE() {
		return c_PORT_CLS_CODE;
	}

	public void setC_PORT_CLS_CODE(String c_PORT_CLS_CODE) {
		this.c_PORT_CLS_CODE = c_PORT_CLS_CODE;
	}
	
	
	public String getC_ASS_CODE() {
		return c_ASS_CODE;
	}

	public void setC_ASS_CODE(String c_ASS_CODE) {
		this.c_ASS_CODE = c_ASS_CODE;
	}

	public String getC_SN() {
		return c_SN;
	}

	public void setC_SN(String c_SN) {
		this.c_SN = c_SN;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	protected String getDoubleValue(String val) {
		// 20181015 wlx STORY61881【招商基金】光大银行电子对账需要支持8位小数
		int digits = 6;
		String key = "DZ_BB_DZDZ_DWJZ_001_" + this.getC_ASS_CODE();
		if (!StringUtil.IsNullOrEmptyT(this.getC_PORT_CLS_CODE())) {
			key = key + this.getC_PORT_CLS_CODE();
		}
		// 20190220 BUG243659产品估值参数“单位净值保留位数”修改时参数值会变为小数，导致发送电子对账明细数据报错
		if (paraMap != null && paraMap.get(key) != null && Double.parseDouble(paraMap.get(key)) > 6) {
			digits = (int) Double.parseDouble(paraMap.get(key));
		}
		DecimalFormat nFormat = new DecimalFormat("#####.##");
		nFormat.setMaximumFractionDigits(digits);// 设置小数点后面位数为
		nFormat.setMinimumFractionDigits(digits);
		return nFormat.format(new Double(val));
	}
}
