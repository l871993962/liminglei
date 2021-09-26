package com.yss.ams.base.information.support.sys.indexdi.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * 合规指标类型字典T_S_INDEX pojo
 *
 */
public class Indexdi extends BasePojo{
	
	private static final long serialVersionUID = -6675703938820914139L;

	private String c_INDEX_CODE = "";
	
	private String c_INDEX_NAME = "";
	
	private String c_DATA_SOURCE = "";
	
	private String c_DATA_TYPE = "";
	
	private int n_STATE = 0;
	
	private int n_ORDER = 0;
	
	private String c_NAV_TYPE = "";
	
	private int n_DETAIL = 0;
	
	private String c_KEY_CODE = "";
	
	private String c_KEY_NAME = "";
	
	private String c_IS_SYS = "";
	
	private String c_TRU = "";
	
	private String c_MODE = "";
	
	private String c_RET = "";
	
	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String cINDEXCODE) {
		c_INDEX_CODE = cINDEXCODE;
	}

	public String getC_INDEX_NAME() {
		return c_INDEX_NAME;
	}

	public void setC_INDEX_NAME(String cINDEXNAME) {
		c_INDEX_NAME = cINDEXNAME;
	}

	public String getC_DATA_SOURCE() {
		return c_DATA_SOURCE;
	}

	public void setC_DATA_SOURCE(String cDATASOURCE) {
		c_DATA_SOURCE = cDATASOURCE;
	}

	public String getC_DATA_TYPE() {
		return c_DATA_TYPE;
	}

	public void setC_DATA_TYPE(String cDATATYPE) {
		c_DATA_TYPE = cDATATYPE;
	}

	public int getN_STATE() {
		return n_STATE;
	}

	public void setN_STATE(int nSTATE) {
		n_STATE = nSTATE;
	}

	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
	}

	public String getc_NAV_TYPE() {
		return c_NAV_TYPE;
	}

	public void setc_NAV_TYPE(String cNAVTYPE) {
		c_NAV_TYPE = cNAVTYPE;
	}

	public int getN_DETAIL() {
		return n_DETAIL;
	}

	public void setN_DETAIL(int nDETAIL) {
		n_DETAIL = nDETAIL;
	}

	public String getC_KEY_CODE() {
		return c_KEY_CODE;
	}

	public void setC_KEY_CODE(String cKEYCODE) {
		c_KEY_CODE = cKEYCODE;
	}

	public String getC_KEY_NAME() {
		return c_KEY_NAME;
	}

	public void setC_KEY_NAME(String cKEYNAME) {
		c_KEY_NAME = cKEYNAME;
	}

	public String getC_IS_SYS() {
		return c_IS_SYS;
	}

	public void setC_IS_SYS(String cISSYS) {
		c_IS_SYS = cISSYS;
	}

	public String getC_TRU() {
		return c_TRU;
	}

	public void setC_TRU(String cTRU) {
		c_TRU = cTRU;
	}

	public String getC_MODE() {
		return c_MODE;
	}

	public void setC_MODE(String cMODE) {
		c_MODE = cMODE;
	}

	public String getC_RET() {
		return c_RET;
	}

	public void setC_RET(String cRET) {
		c_RET = cRET;
	}
}
