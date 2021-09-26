package com.yss.ams.product.information.modules.ab.assetstree_b.dao;

/**
 * <产品树型结构>列名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum AssetsTree_BColumnName {
	/**
	* 结构代码 
	*/
	c_TR_CODE("C_TR_CODE"),

	/**
	* 组合代码 
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	* 顶级节点代码 
	*/
	c_TR_CODE_R("C_TR_CODE_R"),
	
//	c_TR_CODE_P("C_TR_CODE_P"),
	
//	c_DV_TR("C_DV_TR"),
	
	c_DESC("C_DESC"),

	id("C_IDEN"),
	
	/**
	 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * 只用于判断当前数据是否为父级节点。不报存至数据库
	 */
	isParent(""),
	
	startUseDate(""),

	endUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");

	private String value ;

	private AssetsTree_BColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
