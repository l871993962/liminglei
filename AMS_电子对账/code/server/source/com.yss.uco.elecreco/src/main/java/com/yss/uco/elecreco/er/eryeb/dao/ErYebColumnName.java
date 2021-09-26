package com.yss.uco.elecreco.er.eryeb.dao;

public enum ErYebColumnName {

	id("C_IDEN"),
	
	c_SN("C_SN"),

	c_ASS_CODE("C_ASS_CODE"),

	c_FILE_TYPE("C_FILE_TYPE"),

	c_RPT_TYPE("C_RPT_TYPE"),

	d_START_DATE("D_START_DATE"),

	d_END_DATE("D_END_DATE"),

	c_KM_CODE("C_KM_CODE"),
	//STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。
	c_KM_NAME("C_KM_NAME"),
	
	c_KM_CODE_P("C_KM_CODE_P"),

	c_DC_CODE("C_DC_CODE"),
	
	n_ORIG_STARTBAL("N_ORIG_STARTBAL"),

	n_ORIG_DEBIT("N_ORIG_DEBIT"),

	n_ORIG_CREDIT("N_ORIG_CREDIT"),

	n_ORIG_ENDBAL("N_ORIG_ENDBAL"),

	n_PORT_STARTBAL("N_PORT_STARTBAL"),
	
	n_PORT_DEBIT("N_PORT_DEBIT"),

	n_PORT_CREDIT("N_PORT_CREDIT"),

	n_PORT_ENDBAL("N_PORT_ENDBAL"),

	n_AMOUNT_STARTBAL("N_AMOUNT_STARTBAL"),

	n_AMOUNT_DEBIT("N_AMOUNT_DEBIT"),
	
	n_AMOUNT_CREDIT("N_AMOUNT_CREDIT"),

	n_AMOUNT_ENDBAL("N_AMOUNT_ENDBAL"),

	n_DETAIL("N_DETAIL"),

	c_DV_ER_WAY("C_DV_ER_WAY"),
	
	n_J_TOLTAL_AMOUNT("N_J_TOLTAL_AMOUNT"),

	n_D_TOLTAL_AMOUNT("N_D_TOLTAL_AMOUNT");
	
	private String value ;

	private ErYebColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
