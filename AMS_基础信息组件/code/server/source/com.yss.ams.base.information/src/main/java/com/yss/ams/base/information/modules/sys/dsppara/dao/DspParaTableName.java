package com.yss.ams.base.information.modules.sys.dsppara.dao;

/**
 * 综合参数字典T_S_DSP_PARA
 *
 */
public enum DspParaTableName {
	/**
	 * 核算参数字典
	 */
	userInfo("t_s_dsp_para");
	
	private String value;

	private DspParaTableName(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
