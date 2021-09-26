package com.yss.uco.elecreco.er.reverse.out.erkmb.dao;
public enum ErKmbOutColumnName {

	id("C_IDEN"),
	/**
	*
	*/
	c_ROW_INDEX("C_ROW_INDEX"),

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
	c_KM_CODE("C_KM_CODE"),

	/**
	*
	*/
	c_KM_NAME("C_KM_NAME"),

	/**
	*
	*/
	c_KM_LEVEL("C_KM_LEVEL"),

	/**
	*
	*/
	c_KM_CODE_P("C_KM_CODE_P"),

	/**
	*
	*/
	c_DETAIL("C_DETAIL"),

	/**
	*
	*/
	c_DV_KM_CLS("C_DV_KM_CLS"),

	/**
	*
	*/
	c_DV_JD_WAY("C_DV_JD_WAY"),

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

	private ErKmbOutColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}