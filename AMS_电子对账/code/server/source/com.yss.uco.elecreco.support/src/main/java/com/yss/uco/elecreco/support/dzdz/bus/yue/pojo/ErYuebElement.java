package com.yss.uco.elecreco.support.dzdz.bus.yue.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yss.uco.elecreco.support.dzdz.common.RecordElement;

public class ErYuebElement extends RecordElement{
	/**
	 * 科目代码
	 */
	@XmlElement(name = "FACCTCODE")
	private String c_KM_CODE = "";

	/**
	 * 币种代码
	 */
	@XmlElement(name = "FCURCODE")
	private String c_DC_CODE = "";
	
	/**
	 * 期初余额(原币)
	 */
	@XmlElement(name = "FSTARTBAL")
	private String n_ORIG_STARTBAL = "0.000000";

	/**
	 * 本期借方发生额(原币)
	 */
	@XmlElement(name = "FDEBIT")
	private String n_ORIG_DEBIT = "0.000000";

	/**
	 * 本期贷方发生额(原币)
	 */
	@XmlElement(name = "FCREDIT")
	private String n_ORIG_CREDIT = "0.000000";

	/**
	 * 期末余额(原币)
	 */
	@XmlElement(name = "FENDBAL")
	private String n_ORIG_ENDBAL = "0.000000";

	/**
	 * 期初余额(本位币)
	 */
	@XmlElement(name = "FBSTARTBAL")
	private String n_PORT_STARTBAL = "0.000000";

	/**
	 * 本期借方发生额(本位币)
	 */
	@XmlElement(name = "FBDEBIT")
	private String n_PORT_DEBIT = "0.000000";

	/**
	 * 本期贷方发生额(本位币)
	 */
	@XmlElement(name = "FBCREDIT")
	private String n_PORT_CREDIT = "0.000000";

	/**
	 * 期末余额(本位币)
	 */
	@XmlElement(name = "FBENDBAL")
	private String n_PORT_ENDBAL = "0.000000";

	/**
	 * 
	 * 期初余额(数量)
	 */
	@XmlElement(name = "FASTARTBAL")
	private String n_AMOUNT_STARTBAL = "0.000000";

	/**
	 * 本期借方发生额(数量)
	 */
	@XmlElement(name = "FADEBIT")
	private String n_AMOUNT_DEBIT = "0.000000";

	/**
	 * 本期贷方发生额(数量)
	 */
	@XmlElement(name = "FACREDIT")
	private String n_AMOUNT_CREDIT = "0.000000";
	
	/**
	 * 期末余额(数量)
	 */
	@XmlElement(name = "FAENDBAL")
	private String n_AMOUNT_ENDBAL = "0.000000";


	/**
	 * 借方累计发生额
	 */
	@XmlElement(name = "F_J_TOLTAL_AMOUNT")
	private String n_J_TOLTAL_AMOUNT = "0.000000";
	
	/**
	 * 贷方累计发生额
	 */
	@XmlElement(name = "F_D_TOLTAL_AMOUNT")
	private String n_D_TOLTAL_AMOUNT = "0.000000";
	
	/**
	 * 是否最明细科目
	 */
	@XmlElement(name = "FISDETAIL")
	private int n_DETAIL = 0;
	
	@XmlTransient
	public String getC_KM_CODE() {
		return c_KM_CODE;
	}
	public void setC_KM_CODE(String cKMCODE) throws Exception {
		c_KM_CODE = getKmCode(cKMCODE);
	}
	@XmlTransient
	public String getC_DC_CODE() {
		return c_DC_CODE;
	}
	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
	}
	@XmlTransient
	public String getN_ORIG_STARTBAL() {
		return n_ORIG_STARTBAL;
	}
	public void setN_ORIG_STARTBAL(String nORIGSTARTBAL) {
		n_ORIG_STARTBAL = abs(getDoubleValue(nORIGSTARTBAL));
	}
	@XmlTransient
	public String getN_ORIG_DEBIT() {
		return n_ORIG_DEBIT;
	}
	public void setN_ORIG_DEBIT(String nORIGDEBIT) {
		n_ORIG_DEBIT = abs(getDoubleValue(nORIGDEBIT));
	}
	@XmlTransient
	public String getN_ORIG_CREDIT() {
		return n_ORIG_CREDIT;
	}
	public void setN_ORIG_CREDIT(String nORIGCREDIT) {
		n_ORIG_CREDIT = abs(getDoubleValue(nORIGCREDIT));
	}
	@XmlTransient
	public String getN_ORIG_ENDBAL() {
		return n_ORIG_ENDBAL;
	}
	public void setN_ORIG_ENDBAL(String nORIGENDBAL) {
		n_ORIG_ENDBAL = abs(getDoubleValue(nORIGENDBAL));
	}
	@XmlTransient
	public String getN_PORT_STARTBAL() {
		return n_PORT_STARTBAL;
	}
	public void setN_PORT_STARTBAL(String nPORTSTARTBAL) {
		n_PORT_STARTBAL = abs(getDoubleValue(nPORTSTARTBAL));
	}
	@XmlTransient
	public String getN_PORT_DEBIT() {
		return n_PORT_DEBIT;
	}
	public void setN_PORT_DEBIT(String nPORTDEBIT) {
		n_PORT_DEBIT = abs(getDoubleValue(nPORTDEBIT));
	}
	@XmlTransient
	public String getN_PORT_CREDIT() {
		return n_PORT_CREDIT;
	}
	public void setN_PORT_CREDIT(String nPORTCREDIT) {
		n_PORT_CREDIT = abs(getDoubleValue(nPORTCREDIT));
	}
	@XmlTransient
	public String getN_PORT_ENDBAL() {
		return n_PORT_ENDBAL;
	}
	public void setN_PORT_ENDBAL(String nPORTENDBAL) {
		n_PORT_ENDBAL = abs(getDoubleValue(nPORTENDBAL));

	}
	@XmlTransient
	public String getN_AMOUNT_STARTBAL() {
		return n_AMOUNT_STARTBAL;
	}
	public void setN_AMOUNT_STARTBAL(String nAMOUNTSTARTBAL) {
		n_AMOUNT_STARTBAL = abs(getDoubleValue(nAMOUNTSTARTBAL));
	}
	@XmlTransient
	public String getN_AMOUNT_DEBIT() {
		return n_AMOUNT_DEBIT;
	}
	public void setN_AMOUNT_DEBIT(String nAMOUNTDEBIT) {
		n_AMOUNT_DEBIT = abs(getDoubleValue(nAMOUNTDEBIT));
	}
	@XmlTransient
	public String getN_AMOUNT_CREDIT() {
		return n_AMOUNT_CREDIT;
	}
	public void setN_AMOUNT_CREDIT(String nAMOUNTCREDIT) {
		n_AMOUNT_CREDIT = abs(getDoubleValue(nAMOUNTCREDIT));
	}
	@XmlTransient
	public String getN_AMOUNT_ENDBAL() {
		return n_AMOUNT_ENDBAL;
	}
	public void setN_AMOUNT_ENDBAL(String nAMOUNTENDBAL) {
		n_AMOUNT_ENDBAL = abs(getDoubleValue(nAMOUNTENDBAL));
	}

	@XmlTransient
	public String getN_J_TOLTAL_AMOUNT() {
		return n_J_TOLTAL_AMOUNT;
	}
	public void setN_J_TOLTAL_AMOUNT(String nJTOLTALAMOUNT) {
		n_J_TOLTAL_AMOUNT = abs(getDoubleValue(nJTOLTALAMOUNT));
	}
	
	@XmlTransient
	public String getN_D_TOLTAL_AMOUNT() {
		return n_D_TOLTAL_AMOUNT;
	}
	public void setN_D_TOLTAL_AMOUNT(String nDTOLTALAMOUNT) {
		n_D_TOLTAL_AMOUNT = abs(getDoubleValue(nDTOLTALAMOUNT));
	}
	
	@XmlTransient
	public int getN_DETAIL() {
		return n_DETAIL;
	}
	public void setN_DETAIL(int nDETAIL) {
		n_DETAIL = nDETAIL;
	}
	
	private String abs(String val){
		String value = val;
	///修改  3003 6403  等 科目要求 带符号 	广发证券 托管行为工行  
	//	if(val.startsWith("-")){
	//		value = val.replaceFirst("-", "");
	//	}
		return value;
	}
}
