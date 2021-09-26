package com.yss.uco.elecreco.er.erinfostate.dao;

public enum ErStepStateTableName {

	bbinfo("T_D_ER_STEP_STATE"),
	recycle("R_D_ER_STEP_STATE_R");
	
	private String value ;
	
	private ErStepStateTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}

}
