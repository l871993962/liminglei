package com.yss.ams.base.information.modules.sys.feeRelation.dao;

/**
 * @classDesc 费用关联表
 * @version 1.0 2012-9-22
 * @author yh
 */
/** 
 * @author yuankai 公共信息拆分 2017.5.31
 */
public enum FeeRelationExpandTableName {
	userInfo("T_S_DF_FEE_RELA");
	
	private String value ;
	
	private FeeRelationExpandTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举�?
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
