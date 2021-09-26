package com.yss.uco.elecreco.support.dzdz.common.pojo;

import com.yss.framework.api.common.co.BasePojo;


public class ElecGroupRela extends BasePojo {

	private static final long serialVersionUID = -579470288115268265L;
	
	/**
	 * 对账结果表中的对账类型标识
	 */
    private String c_RESULT_CODE = "";
    
	/**
	 * 核对项代码
	 */
    private String c_ELEC_CODE = "";

    /**
     * 核对项名称
     */
    private String c_ELEC_NAME = "";

    /**
     * 核对项父级代码
     */
    private String c_PARENT_CODE = "";

	public String getC_ELEC_CODE() {
		return c_ELEC_CODE;
	}

	public void setC_ELEC_CODE(String cELECCODE) {
		c_ELEC_CODE = cELECCODE;
	}

	public String getC_ELEC_NAME() {
		return c_ELEC_NAME;
	}

	public void setC_ELEC_NAME(String cELECNAME) {
		c_ELEC_NAME = cELECNAME;
	}

	public String getC_PARENT_CODE() {
		return c_PARENT_CODE;
	}

	public void setC_PARENT_CODE(String cPARENTCODE) {
		c_PARENT_CODE = cPARENTCODE;
	}

	public String getC_RESULT_CODE() {
		return c_RESULT_CODE;
	}

	public void setC_RESULT_CODE(String cRESULTCODE) {
		c_RESULT_CODE = cRESULTCODE;
	}

}
