package com.yss.ams.base.information.modules.sys.automaticSet.dao;

/**
 * 自动化业务设置
 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
 * @ClassName: AutomaticSetPathColumnName
 * @date 2021年06月01日
 * @Stroy106083
 * @author zhuziqing
 */
public enum AutomaticSetPathTableName {
	userInfo("T_P_AUTOMATIC_SET_PATH"), 
	recycle("R_P_AUTOMATIC_SET_PATH_R");

	private String value;

	private AutomaticSetPathTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
