package com.yss.uco.elecreco.er.erdztype.dao;
public enum ErDzTypeColumnName {

	/**
	*
	*/
	id("C_IDEN"),

	/**
	*对账类型代码
	*/
	c_DZ_CODE("C_DZ_CODE"),

	/**
	*对账类型名称
	*/
	c_DZ_NAME("C_DZ_NAME"),
	
	/**
	*对账类型
	*/
	c_CHECK_FLAG("C_CHECK_FLAG"),

	/**
	*对账类型父级代码
	*/
	c_DZ_CODE_P("C_DZ_CODE_P");

	private String value;

	private ErDzTypeColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}