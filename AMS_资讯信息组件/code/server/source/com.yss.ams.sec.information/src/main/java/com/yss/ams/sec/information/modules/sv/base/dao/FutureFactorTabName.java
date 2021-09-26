package com.yss.ams.sec.information.modules.sv.base.dao;

/**
 * 期货结算债券转换信息 数据库表名
 * @author gongyue
 * 资讯信息拆分    STORY #42948 资讯信息管理组件化拆分
 */
public enum FutureFactorTabName {
	futurefactor("T_P_SV_TF_CF"),
	recycle("R_P_SV_TF_CF_R");
	
	private String value ;
	
	private FutureFactorTabName(String value){
		this.value = value;
	}

	public String toString(){
		return this.value.toString();
	}
	
}
