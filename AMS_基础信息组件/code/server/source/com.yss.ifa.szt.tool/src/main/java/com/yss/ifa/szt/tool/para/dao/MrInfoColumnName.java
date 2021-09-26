package com.yss.ifa.szt.tool.para.dao;

/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: ErParaColumnName 
 * @Description: 深圳通参数设置
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
public enum MrInfoColumnName {

	id("C_IDEN"),

	/**
	 * 序号
	 */
	n_Order("n_Order"),
	/**
	 * 连接状态
	 */
	n_Link_Status("n_Link_Status"),
	/**
	 * 参数代码
	 */
	c_Para_Code("c_Para_Code"),

	/**
	 * 主备标识
	 */
	c_Dv_Switch_Mark("c_Dv_Switch_Mark"),

	/**
	 * 伺服器IP
	 */
	c_Mr_Ip("c_Mr_Ip"),

	/**
	 * 伺服器端口
	 */
	c_Mr_Port("c_Mr_Port");

	private String value;

	private MrInfoColumnName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
