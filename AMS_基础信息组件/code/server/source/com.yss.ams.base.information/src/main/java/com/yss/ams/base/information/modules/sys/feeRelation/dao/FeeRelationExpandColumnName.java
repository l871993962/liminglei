package com.yss.ams.base.information.modules.sys.feeRelation.dao;

/**
 * @classDesc 费用关联表字段和表对应关系
 * @author chenchangyou
 * 20150105
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public enum FeeRelationExpandColumnName {
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
	 * 费用收支方
	 */
	c_FEE_PARTY("C_FEE_PARTY"),
	
	/**
	 * 序号
	 */
	n_ORDER("N_ORDER"),
	
	id("C_IDEN");
	
	private String value ;

	private FeeRelationExpandColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}
}
