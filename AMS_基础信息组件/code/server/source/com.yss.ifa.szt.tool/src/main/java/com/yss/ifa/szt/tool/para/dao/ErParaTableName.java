package com.yss.ifa.szt.tool.para.dao;

/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: ErParaTableName 
 * @Description: 深圳通参数设置表
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
public enum ErParaTableName {
	table("T_D_ER_PARA"),
	recycle("R_D_ER_PARA_R");
	
	private String value;

	private ErParaTableName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
	

}
