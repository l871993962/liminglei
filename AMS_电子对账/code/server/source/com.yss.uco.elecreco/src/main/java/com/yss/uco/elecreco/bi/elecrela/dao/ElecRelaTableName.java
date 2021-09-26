package com.yss.uco.elecreco.bi.elecrela.dao;

public enum ElecRelaTableName {
	elecRela("T_Z_BI_RELA"),
	recycle("R_Z_BI_RELA_R");
	
	private String value ;
	
	private ElecRelaTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
