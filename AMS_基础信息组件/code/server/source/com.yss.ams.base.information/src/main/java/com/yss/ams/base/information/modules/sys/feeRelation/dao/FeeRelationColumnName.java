package com.yss.ams.base.information.modules.sys.feeRelation.dao;

/**
 * @classDesc 费用关联表字段和表对应关系
 * @version 1.0 2012-9-22
 * @author yh
 */

/**
 * 费用关联表字段和表对应关系
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum FeeRelationColumnName {
	/**
	 * 费用代码
	 */
	c_FEE_CODE("C_FEE_CODE"),
	
	/**
	 * 交易类型
	 */
	c_TD_TYPE("C_TD_TYPE"),
	
	/**
	 * 费用类型
	 */
	c_IE_CODE("C_IE_CODE"),
	
	/**
	 * 序号
	 */
	n_ORDER("N_ORDER"),
	
	id("C_IDEN");
	
	private String value ;

	private FeeRelationColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
