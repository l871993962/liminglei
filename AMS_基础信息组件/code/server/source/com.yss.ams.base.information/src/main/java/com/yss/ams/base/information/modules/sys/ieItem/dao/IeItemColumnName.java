package com.yss.ams.base.information.modules.sys.ieItem.dao;

/**
 * 收支项目字典表T_S_IE_ITEM 列名
 *
 */
public enum IeItemColumnName {

	/**
	 * 收支项目代码
	 */
	c_IE_CODE("C_IE_CODE"), 
	
	/**
	 * 收支项目名称
	 */
	c_IE_NAME("C_IE_NAME"), 
	
	/**
	 * 应收项目
	 */
	c_DAI_CODE_REC("C_DAI_CODE_REC"),
	
	/**
	 * 应付项目
	 */
	c_DAI_CODE_COP("C_DAI_CODE_COP"),
	
	/**
	 * 收入项目
	 */
	c_DAI_CODE_INC("C_DAI_CODE_INC"), 
	
	/**
	 * 支出项目
	 */
	c_DAI_CODE_FEE("C_DAI_CODE_FEE"), 
	
	/**
	 * 状态
	 */
	n_STATE("N_STATE"), 
	
	/**
	 * 序号
	 */
	n_ORDER("N_ORDER"),
	/**
	 * 收支项目分类
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化（替代手工凭证的数据录入界面功能优化）
	 */
	c_IE_TYPE("C_IE_TYPE"),
	/**
	 * 收支类型
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化（替代手工凭证的数据录入界面功能优化）
	 */
	c_SZ_TYPE("C_SZ_TYPE"),
	id("");
	
	private String value;

	private IeItemColumnName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
