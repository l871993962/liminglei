package com.yss.uco.elecreco.er.reverse.manager.result.dao;
/**
 * 副表数据以_RELAFB结尾
 * @author Lenovo
 *
 */
public enum FBResRelaColumnName {

	/**
	*
	*/
	c_IGNORE_FLAG("C_IGNORE_FLAG_RELAFB"),
	/**
	*
	*/
	id("C_IDEN_RELAFB"),

	/**
	*
	*/
	c_RESULT_RELA("C_RESULT_RELA_RELAFB"),

	/**
	*
	*/
	c_KM_CODE("C_KM_CODE_RELAFB"),

	/**
	*
	*/
	c_KM_NAME("C_KM_NAME_RELAFB"),

	/**
	*
	*/
	c_TGH_CODE("C_TGH_CODE_RELAFB"),

	/**
	*
	*/
	c_DV_KM_SCOPE("C_DV_KM_SCOPE_RELAFB"),
	
	/**
	*
	*/
	n_JE14("N_JE14_RELAFB"),
	
	/**
	*
	*/
	n_JE13("N_JE13_RELAFB"),
	
	/**
	*
	*/
	n_JE12("N_JE12_RELAFB"),
	
	/**
	*
	*/
	n_JE11("N_JE11_RELAFB"),
	
	/**
	*
	*/
	n_JE10("N_JE10_RELAFB"),
	
	/**
	*
	*/
	n_JE9("N_JE9_RELAFB"),
	
	/**
	*
	*/
	n_JE8("N_JE8_RELAFB"),
	
	/**
	*
	*/
	n_JE7("N_JE7_RELAFB"),

	/**
	*
	*/
	n_JE6("N_JE6_RELAFB"),


	/**
	*
	*/
	n_SL1("N_SL1_RELAFB"),


	/**
	*
	*/
	n_JE3("N_JE3_RELAFB"),


	/**
	*
	*/
	n_JE4("N_JE4_RELAFB"),


	/**
	*
	*/
	n_JE5("N_JE5_RELAFB"),


	/**
	*
	*/
	c_BY1("C_BY1_RELAFB"),


	/**
	*
	*/
	n_JE2("N_JE2_RELAFB"),


	/**
	*
	*/
	n_JE1("N_JE1_RELAFB"),

	endUseDate(""),

	startUseDate("");

	private String value;

	private FBResRelaColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}