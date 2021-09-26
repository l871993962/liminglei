package com.yss.uco.elecreco.er.reverse.compare.ye.dao;

public enum ErYebItemColumnName {

	REVE_YE_QCYB("n_ORIG_STARTBAL"),//期初余额（原币）

	REVE_YE_QCBB("n_PORT_STARTBAL"),//期初余额（本位币）

	REVE_YE_QCSL("n_AMOUNT_STARTBAL"),//期初余额（数量）
	
	REVE_YE_DEYB("n_ORIG_DEBIT"),//本期借方发生额（原币）
	
	REVE_YE_DEBB("n_PORT_DEBIT"),//本期借方发生额（本位币）

	REVE_YE_DESL("n_AMOUNT_DEBIT"),//本期借方发生额（数量）
	
	REVE_YE_CRYB("n_ORIG_CREDIT"),//本期贷方发生额（原币）
	
	REVE_YE_CRBB("n_PORT_CREDIT"),//本期贷方发生额（本位币）
	
	REVE_YE_CRSL("n_AMOUNT_CREDIT"),//本期贷方发生额（数量）
	
	REVE_YE_QMYB("n_ORIG_ENDBAL"),//期末余额（原币）
	
	REVE_YE_QMBB("n_PORT_ENDBAL"),//期末余额（本位币）
	
	REVE_YE_QMSL("n_AMOUNT_ENDBAL"),//期末余额（数量）

	REVE_YE_DETOLBB("n_J_TOLTAL_AMOUNT"),//借方累计发生额（本位币）

	REVE_YE_CRTOLBB("n_D_TOLTAL_AMOUNT");//贷方累计发生额（本位币）

	private String value ;

	private ErYebItemColumnName(String value){
		this.value = value;
	}

	public String toString(){
		return this.value.toString();
	}
}
