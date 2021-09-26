package com.yss.uco.elecreco.er.reverse.compare.gz.dao;

public enum ErGzbItemColumnName {
	
	REVE_GZ_SL("n_AMOUNT"),//数量

	REVE_GZ_HQ("n_VA_PRICE"),//行情

	REVE_GZ_BBCB("n_PORT_COST"),//本币成本

	REVE_GZ_SZ("n_PORT_MV"),//本币市值
	
	REVE_GZ_BBGZ("n_PORT_IV"),//本币估增
	
	REVE_GZ_YBCB("n_ORIG_COST"),//原币成本

	REVE_GZ_YBSZ("n_ORIG_MV"),//原币市值
	
	REVE_GZ_YBGZ("n_ORIG_IV");//原币估增


	private String value ;

	private ErGzbItemColumnName(String value){
		this.value = value;
	}

	public String toString(){
		return this.value.toString();
	}

}
