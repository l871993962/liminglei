package com.yss.uco.elecreco.er.reverse.out.ergzb.dao;
public enum ErGzbOutColumnName {

	id("C_IDEN"),
	/**
	*
	*/
	c_TGH_NAME_OUT("C_TGH_NAME_OUT"),

	/**
	*
	*/
	c_TGH_CODE("C_TGH_CODE"),

	/**
	*
	*/
	c_ASS_CODE("C_ASS_CODE"),

	/**
	*
	*/
	c_ASS_NAME("C_ASS_NAME"),

	/**
	*
	*/
	d_GZ_DATE("D_GZ_DATE"),

	/**
	*
	*/
	c_ROW_INDEX("C_ROW_INDEX"),

	/**
	*
	*/
	c_KM_CODE("C_KM_CODE"),

	/**
	*
	*/
	c_KM_NAME("C_KM_NAME"),

	/**
	*
	*/
	c_CURR_TYPE("C_CURR_TYPE"),

	/**
	*
	*/
	c_EXRATE("C_EXRATE"),

	/**
	*
	*/
	n_AMOUNT("N_AMOUNT"),

	/**
	*
	*/
	n_ORGI_UNIT_COST("N_ORGI_UNIT_COST"),

	/**
	*
	*/
	n_PORT_UNIT_COST("N_PORT_UNIT_COST"),

	/**
	*
	*/
	n_ORGI_COST("N_ORGI_COST"),

	/**
	*
	*/
	n_PORT_COST("N_PORT_COST"),

	/**
	*
	*/
	n_CB_JZ_BL("N_CB_JZ_BL"),

	/**
	*
	*/
	n_ORGI_VA_PRICE("N_ORGI_VA_PRICE"),

	/**
	*
	*/
	n_PORT_VA_PRICE("N_PORT_VA_PRICE"),

	/**
	*
	*/
	n_ORGI_MV("N_ORGI_MV"),

	/**
	*
	*/
	n_PORT_MV("N_PORT_MV"),

	/**
	*
	*/
	n_SZ_JZ_BL("N_SZ_JZ_BL"),

	/**
	*
	*/
	n_ORGI_IV("N_ORGI_IV"),

	/**
	*
	*/
	n_PORT_IV("N_PORT_IV"),

	/**
	*
	*/
	c_TPXX("C_TPXX"),

	/**
	*
	*/
	c_HDAY_CODE("C_HDAY_CODE"),

	/**
	*
	*/
	n_ADJUST("N_ADJUST"),

	/**
	*
	*/
	c_CFG_CODE("C_CFG_CODE"),

	/**
	*
	*/
	c_USER("C_USER"),

	/**
	*
	*/
	c_PATH_TYPE("C_PATH_TYPE"),

	/**
	*
	*/
	c_PATH("C_PATH"),

	/**
	*
	*/
	d_DATE("D_DATE"),

	endUseDate(""),

	startUseDate("");

	private String value;

	private ErGzbOutColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}