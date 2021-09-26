package com.yss.uco.elecreco.er.reverse.map.kmrela.dao;
/**
 * 属于T_D_ER_REVE_KM_RELA的字段属性命名时不加任何后缀
 * 属于T_D_ER_REVE_KM_MAP的字段属性命名时以_KMMAP结尾
 * @author Lenovo
 *
 */
public enum KmRelaSingleReocrdColumnName {

	/**
	*
	*/
	id("C_IDEN"),
	
	/**
	*
	*/
	id_KMMAP("C_IDEN_KMMAP"),
	
	

	/**
	*映射关系ID
	*/
	c_IDEN_RELA_KMMAP("C_IDEN_RELA_KMMAP"),

	/**
	*产品组合
	*/
	c_PORT_CODE_KMMAP("C_PORT_CODE_KMMAP"),

	/**
	*科目代码
	*/
	c_KM_CODE_KMMAP("C_KM_CODE_KMMAP"),

	/**
	*科目名称
	*/
	c_KM_NAME_KMMAP("C_KM_NAME_KMMAP"),

	/**
	*托管机构
	*/
	c_TGH_CODE_KMMAP("C_TGH_CODE_KMMAP"),

	/**
	*科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)
	*/
	c_DV_KM_CLS_KMMAP("C_DV_KM_CLS_KMMAP"),

	/**
	*科目范围（REVE_KMFW_INNER:内部科目；REVE_KMFW_OUT：外部科目）
	*/
	c_DV_KM_SCOPE_KMMAP("C_DV_KM_SCOPE_KMMAP"),
	
	
	
	/**
	*
	*/
//	id_INNER("C_ID_INNER"),
	
	/**
	*
	*/
//	id_OUT("C_ID_OUT"),
	
	
//	/**
//	*映射关系ID
//	*/
//	c_IDEN_RELA_OUT("C_IDEN_RELA_OUT"),
//
//	/**
//	*产品组合
//	*/
//	c_PORT_CODE_OUT("C_PORT_CODE_OUT"),
//
//	/**
//	*科目代码
//	*/
//	c_KM_CODE_OUT("C_KM_CODE_OUT"),
//
//	/**
//	*科目名称
//	*/
//	c_KM_NAME_OUT("C_KM_NAME_OUT"),
//
//	/**
//	*托管机构
//	*/
//	c_TGH_CODE_OUT("C_TGH_CODE_OUT"),
//
//	/**
//	*科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)
//	*/
//	c_DV_KM_CLS_OUT("C_DV_KM_CLS_OUT"),
//
//	/**
//	*科目范围（REVE_KMFW_INNER:内部科目；REVE_KMFW_OUT：外部科目）
//	*/
//	c_DV_KM_SCOPE_OUT("C_DV_KM_SCOPE_OUT"),
	
	
	
//	/**
//	*映射关系ID
//	*/
//	c_IDEN_RELA_INNER("C_IDEN_RELA_INNER"),
//
//	/**
//	*产品组合
//	*/
//	c_PORT_CODE_INNER("C_PORT_CODE_INNER"),
//
//	/**
//	*科目代码
//	*/
//	c_KM_CODE_INNER("C_KM_CODE_INNER"),
//
//	/**
//	*科目名称
//	*/
//	c_KM_NAME_INNER("C_KM_NAME_INNER"),
//
//	/**
//	*托管机构
//	*/
//	c_TGH_CODE_INNER("C_TGH_CODE_INNER"),
//
//	/**
//	*科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)
//	*/
//	c_DV_KM_CLS_INNER("C_DV_KM_CLS_INNER"),
//
//	/**
//	*科目范围（REVE_KMFW_INNER:内部科目；REVE_KMFW_INNER：外部科目）
//	*/
//	c_DV_KM_SCOPE_INNER("C_DV_KM_SCOPE_INNER"),
	
	
	
	/**
	*产品组合
	*/
	c_PORT_CODE("C_PORT_CODE"),

	/**
	*托管机构
	*/
	c_TGH_CODE("C_TGH_CODE"),

	/**
	*映射范围（REVE_YSFW_GGYS:公共映射，REVE_YSFW_TGFYS:托管方映射,REVE_YSFW_CPYS:产品映射）
	*/
	c_DV_MAP_SCOPE("C_DV_MAP_SCOPE"),

	/**
	*
	*/
	c_DV_KM_CLS("C_DV_KM_CLS"),

	/**
	*
	*/
	auditState("N_CHECK_STATE"),

	/**
	*
	*/
	modifier("C_UPDATE_BY"),

	/**
	*
	*/
	modifyDate("C_UPDATE_TIME"),

	/**
	*
	*/
	operator("C_CHECK_BY"),

	/**
	*
	*/
	auditDate("C_CHECK_TIME"),

	endUseDate(""),

	startUseDate("");


	private String value;

	private KmRelaSingleReocrdColumnName(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value.toString();
	}
}