package com.yss.ams.base.information.support.sys.secvar.pojo;



/**
 * 证券品种字典T_S_DA_SEC_VAR 扩展pojo
 *
 */
public class SecVarExtend extends SecVar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6820690966603701360L;

	/**
	* C_DA_CODE_P 
	*/
	private String c_DA_CODE_P = "";
	
	/**
	* C_DA_NAME 
	*/
	private String c_DA_NAME = "";
	
	/**
	 * 顺序
	 * add by Yuntao Lau STORY #26999
	 */
	private int n_ORDER = 0;

	public String getC_DA_CODE_P() {
		return c_DA_CODE_P;
	}

	public void setC_DA_CODE_P(String cDACODEP) {
		c_DA_CODE_P = cDACODEP;
	}

	public String getC_DA_NAME() {
		return c_DA_NAME;
	}

	public void setC_DA_NAME(String cDANAME) {
		c_DA_NAME = cDANAME;
	}
	
	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int n_ORDER) {
		this.n_ORDER = n_ORDER;
	}
}
