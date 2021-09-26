package com.yss.uco.elecreco.er.tmplrela.dao;

/**
 * @author liuxiang
 * 2015年2月13日
 */
public enum DzTmplRelaTableName {
	templateRela("T_D_ER_TMPL_RELA");
	
	private String value;

	private DzTmplRelaTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}
}
