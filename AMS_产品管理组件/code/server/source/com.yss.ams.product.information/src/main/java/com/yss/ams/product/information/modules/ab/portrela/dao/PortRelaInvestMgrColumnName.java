package com.yss.ams.product.information.modules.ab.portrela.dao;

public enum PortRelaInvestMgrColumnName {
	/**
	 * 投资经理
	 */
	c_USER_CODE("C_USER_CODE"),
	c_USER_NAME_CN("C_USER_NAME_CN");
	
	private String value ;

	private PortRelaInvestMgrColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
