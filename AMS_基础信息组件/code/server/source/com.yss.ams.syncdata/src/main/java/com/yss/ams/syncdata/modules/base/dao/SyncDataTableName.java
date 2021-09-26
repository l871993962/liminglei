package com.yss.ams.syncdata.modules.base.dao;


/**
 * 数据同步 数据库表名
 * @author chenyoucai
 */
public enum SyncDataTableName {
	tableName("T_D_OD_SYNC_MQ"),
	recycle("R_D_OD_SYNC_MQ_R");
	
	private String value ;
	
	private SyncDataTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
