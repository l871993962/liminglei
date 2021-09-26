package com.yss.ams.product.information.modules.ab.portrela.dao;

public enum PortRelaTradeSeatColumnName {
	/**
	* 交易席位 
	*/
	c_TD_CHAN_CODE("C_TD_CHAN_CODE"),
	c_TD_CHAN_NAME("C_TD_CHAN_NAME"),
	c_DV_CHAN_TYPE("C_DV_CHAN_TYPE");
	
	private String value ;

	private PortRelaTradeSeatColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
