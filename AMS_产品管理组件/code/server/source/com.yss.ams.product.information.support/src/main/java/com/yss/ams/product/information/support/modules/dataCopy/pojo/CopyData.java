package com.yss.ams.product.information.support.modules.dataCopy.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class CopyData extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// / <summary>
	// / 数据项名称
	// / </summary>
	private String c_DATA_NAME;

	// / <summary>
	// / 数据项代码
	// / </summary>
	private String c_DATA_CODE;

	// / <summary>
	// / 数据项父级代码
	// / </summary>
	private String c_DATA_CODE_P;

	// / <summary>
	// / 启用状态
	// / </summary>
	private String c_DV_STATE;

	// / <summary>
	// / serviceId
	// / </summary>
	private String c_SERVICE_CODE;
	
	// / <summary>
	// / c_data_para
	// / </summary>
	private String c_DATE_PARA;
	
	private Number n_ORDER;

	public Number getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(Number n_ORDER) {
		this.n_ORDER = n_ORDER;
	}

	public String getC_DATA_PARA() {
		return c_DATE_PARA;
	}

	public void setC_DATA_PARA(String cDATEPARA) {
		c_DATE_PARA = cDATEPARA;
	}
	
	public String getC_DATA_NAME() {
		return c_DATA_NAME;
	}

	public void setC_DATA_NAME(String cDATANAME) {
		c_DATA_NAME = cDATANAME;
	}

	public String getC_DATA_CODE() {
		return c_DATA_CODE;
	}

	public void setC_DATA_CODE(String cDATACODE) {
		c_DATA_CODE = cDATACODE;
	}

	public String getC_DATA_CODE_P() {
		return c_DATA_CODE_P;
	}

	public void setC_DATA_CODE_P(String cDATACODEP) {
		c_DATA_CODE_P = cDATACODEP;
	}

	public String getC_DV_STATE() {
		return c_DV_STATE;
	}

	public void setC_DV_STATE(String cDVSTATE) {
		c_DV_STATE = cDVSTATE;
	}

	public String getC_SERVICE_CODE() {
		return c_SERVICE_CODE;
	}

	public void setC_SERVICE_CODE(String cSERVICECODE) {
		c_SERVICE_CODE = cSERVICECODE;
	}
}
