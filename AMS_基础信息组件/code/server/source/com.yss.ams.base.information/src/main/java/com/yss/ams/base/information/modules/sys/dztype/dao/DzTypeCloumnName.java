package com.yss.ams.base.information.modules.sys.dztype.dao;

/**
 * 对账类型字典表 列名
 * @author yuankai 公共信息拆分 2017.5.31
 */
public enum DzTypeCloumnName {

	/**
	 * 对账类型父级节点代码
	 */
	c_DZ_CODE_P("C_DZ_CODE_P"),

	/**
	 * 对账类型代码
	 */
	c_DZ_CODE("C_DZ_CODE"),

	/**
	 * 对账类型名称
	 */
	c_DZ_NAME("C_DZ_NAME");

	private String value;

	private DzTypeCloumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
