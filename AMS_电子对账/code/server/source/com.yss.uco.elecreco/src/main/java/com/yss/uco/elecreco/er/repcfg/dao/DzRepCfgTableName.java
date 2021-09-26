package com.yss.uco.elecreco.er.repcfg.dao;

public enum DzRepCfgTableName {
	////STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
	//添加回收表
	recycle("R_P_ER_REPCFG_R"),
	
	userInfo("T_P_ER_REPCFG");
	
	private String value;

	private DzRepCfgTableName(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
