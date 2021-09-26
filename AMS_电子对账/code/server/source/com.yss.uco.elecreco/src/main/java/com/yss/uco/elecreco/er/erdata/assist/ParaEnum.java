package com.yss.uco.elecreco.er.erdata.assist;

/**
 * 参数枚举类
 * 
 * @author liuxiang 2015年2月13日
 */
public enum ParaEnum {

	/**
	 * 日期
	 */
	D_DATE("P;D_DATE"),
	/**
	 * 组合
	 */
	C_PORT_CODE("P;C_PORT_CODE"),
	/**
	 * 科目级别
	 */
	N_KM_LEVEL("P;N_KM_LEVEL"),

	/**
	 * 报文序号
	 */
	C_SN("P;C_SN");

	private String value;

	private ParaEnum(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
