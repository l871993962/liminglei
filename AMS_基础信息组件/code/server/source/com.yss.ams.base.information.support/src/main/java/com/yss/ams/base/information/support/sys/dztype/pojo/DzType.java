package com.yss.ams.base.information.support.sys.dztype.pojo;


import com.yss.framework.api.common.co.BasePojo;

/**
 * 对账类型字典表实体类
 * @author yuankai 公共组件拆分 2017.5.31
 *
 */
public class DzType extends BasePojo {
	
	private static final long serialVersionUID = 1095788588950793961L;

	/**
	 * 对账类型父级节点代码
	 */
	private String C_DZ_CODE_P = "";

	/**
	 * 对账类型代码
	 */
	private String C_DZ_CODE = "";

	/**
	 * 对账类型名称
	 */
	private String C_DZ_NAME = "";

	public String getC_DZ_CODE_P() {
		return C_DZ_CODE_P;
	}

	public void setC_DZ_CODE_P(String cDZCODEP) {
		C_DZ_CODE_P = cDZCODEP;
	}

	public String getC_DZ_CODE() {
		return C_DZ_CODE;
	}

	public void setC_DZ_CODE(String cDZCODE) {
		C_DZ_CODE = cDZCODE;
	}

	public String getC_DZ_NAME() {
		return C_DZ_NAME;
	}

	public void setC_DZ_NAME(String cDZNAME) {
		C_DZ_NAME = cDZNAME;
	}

}
