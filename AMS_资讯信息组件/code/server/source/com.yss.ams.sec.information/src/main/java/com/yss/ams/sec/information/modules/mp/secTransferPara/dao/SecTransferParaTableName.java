package com.yss.ams.sec.information.modules.mp.secTransferPara.dao;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public enum SecTransferParaTableName {
	secTransferPara("T_D_MP_SEC_TRANSFERPARA");

	private String value;

	private SecTransferParaTableName(String value) {
		this.value = value;
	}

	/*
	 * 重写toString方法：获取枚举�?
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.value.toString();
	}
}
