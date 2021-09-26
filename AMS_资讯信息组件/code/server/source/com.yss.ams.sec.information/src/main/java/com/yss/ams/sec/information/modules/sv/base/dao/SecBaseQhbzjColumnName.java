package com.yss.ams.sec.information.modules.sv.base.dao;

/**
 * 期货&期权保证金 数据库表对应字段
 * @author shiliang
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public enum SecBaseQhbzjColumnName {
	
	id("C_IDEN"),
	
	/**
	 * 证券代码
	 */
	c_SEC_CODE("C_SEC_CODE"),
	
	/**
	 * 保证金比例
	 */
	n_RATE("N_RATE"),
	
	/**
	 * 固定保证金
	 */
	n_PRICE_ISSUE("N_PRICE_ISSUE"),
	
	/**
	 * 启用日期
	 */
	d_START("D_START"),
	
	/**
	 *  By Jinghehe 2014-7-30
	 *  期权品种信息用到，期货的默认为 0 
	 *  保证金比例1
	 */
	n_RATIO("N_RATIO");
	
	private String value ;
	
	private SecBaseQhbzjColumnName(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}

}
