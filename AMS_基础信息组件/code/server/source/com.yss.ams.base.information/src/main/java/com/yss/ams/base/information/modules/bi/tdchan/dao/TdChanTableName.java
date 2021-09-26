package com.yss.ams.base.information.modules.bi.tdchan.dao;

public enum TdChanTableName {
	userInfo("T_P_AB_TD_CHAN"),
	recycle("R_P_AB_TD_CHAN_R");
	
	private String value ;
	
	private TdChanTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举值
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
