package com.yss.ams.product.information.modules.ab.assetsTree_a.dao;

/**
 * <A区资产树型结构>列名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum AssetTreeATreeViewColumnName {
	/**
	* 结构代码 
	*/
	c_TR_CODE("C_TR_CODE"),

	/**
	* 结构名称 
	*/
	c_TR_NAME("C_TR_NAME"),

	/**
	* 上级结构 
	*/
	c_TR_CODE_P("C_TR_CODE_P"),

	/**
	* 分类规则 
	*/
	c_DV_TR("C_DV_TR"),

	/**
	* 描述 
	*/
	c_DESC("C_DESC"),

	/**
	 * 用户
	 */
	c_USER("C_USER"),
	
	/**
	* 顶级节点代码 
	*/
	c_TR_CODE_R("C_TR_CODE_R"),
	/**
	* add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
	* 显示未分配
	*/
	c_DV_UN_DIS("C_DV_UN_DIS"),
	/**
	 * 自动转入类型
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 */
	c_AUTO_ZR_TYPE("C_AUTO_ZR_TYPE"),
	
	/**
	* add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
	* 记录产品类型执行顺序
	*/
	n_ORDER("N_ORDER"),

	id("C_IDEN"),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE"),
	
	n_Level("N_LEVEL"),
	
	nodeCode("nodeCode"),
	
	parentCode("fParaentCode"),
	
	assetsTreeARule(""),
	
	startUseDate(""),

	endUseDate("");
	

	private String value ;

	private AssetTreeATreeViewColumnName(String value){
	this.value = value;
	}

	public String toString(){
	return this.value.toString();
	}

}
