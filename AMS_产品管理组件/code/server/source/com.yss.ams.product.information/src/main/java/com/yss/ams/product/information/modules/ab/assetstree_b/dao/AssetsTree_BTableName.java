package com.yss.ams.product.information.modules.ab.assetstree_b.dao;

/**
 * <产品树型结构>表名定义类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public enum AssetsTree_BTableName {
	userInfo("T_P_AB_ASS_TR_SUB"),
	recycle("R_P_AB_ASS_TR_SUB_R");
	
	private String value ;
	
	private AssetsTree_BTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举值
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
