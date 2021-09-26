package com.yss.ams.product.information.modules.ab.assetsTree_a_rule.dao;

/**
 * 树形结构分类规则
 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
 * add by yangru 20190717
 * @author lenovo
 *
 */
public enum AssetsTreeRuleTableName {
	userInfo("T_P_AB_ASS_TR_RULE"),
	recycle("R_P_AB_ASS_TR_RULE_R");
	
	private String value ;
	
	private AssetsTreeRuleTableName(String value){
		this.value = value;
	}
	
	/* 重写toString方法：获取枚举值
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return this.value.toString();
	}
}
