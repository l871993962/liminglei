package com.yss.ams.visaval.support.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class AdvAlgo extends AuditableParamPojo {
	/**
	 * 新增序列 修改人：李海智 修改时间：20130620
	 */
	private static final long serialVersionUID = -5558104943534072970L;

	/**
	 * 算法代码
	 */
	private String c_ALGO_CODE = "";

	/**
	 * 算法名称
	 */
	private String c_ALGO_NAME = "";

	/**
	 * 算法公式
	 */
	private String c_ALGO_FORMULA = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 算法类型
	 */
	private String c_DV_ALGO_TYPE = "";

	/**
	 * 算法表达式
	 */
	private String c_ALGO_FORMULA_TRANSFORM = "";
	
	/**
	 * 算法公式对应的中文
	 */
	private String c_ALGO_FORMULA_ZH = "";
	
	private String c_ALGO_FUN_PARAMS = "";
	
	/**
	 * 对应多个下拉框单数
	 */
	private String c_ALGO_FUN_MORE = "";
	/**
	 * 对应单个下拉框单数
	 */
	private String c_ALGO_FUN_ONE = "";
	
	private String c_ALGO_IS_NEW = "";
	
	/**
	 * 更新目标：是set界面更新还是list界面更新
	 */
	private String c_ALGO_UPDATE_TARGET = "";
	

	
	public String getC_ALGO_UPDATE_TARGET() {
		return c_ALGO_UPDATE_TARGET;
	}

	public void setC_ALGO_UPDATE_TARGET(String c_ALGO_UPDATE_TARGET) {
		this.c_ALGO_UPDATE_TARGET = c_ALGO_UPDATE_TARGET;
	}

	public String getC_ALGO_IS_NEW() {
		return c_ALGO_IS_NEW;
	}

	public void setC_ALGO_IS_NEW(String c_ALGO_IS_NEW) {
		this.c_ALGO_IS_NEW = c_ALGO_IS_NEW;
	}

	public String getC_ALGO_FUN_PARAMS() {
		return c_ALGO_FUN_PARAMS;
	}

	public void setC_ALGO_FUN_PARAMS(String c_ALGO_FUN_PARAMS) {
		this.c_ALGO_FUN_PARAMS = c_ALGO_FUN_PARAMS;
	}

	public String getC_ALGO_FUN_MORE() {
		return c_ALGO_FUN_MORE;
	}

	public void setC_ALGO_FUN_MORE(String c_ALGO_FUN_MORE) {
		this.c_ALGO_FUN_MORE = c_ALGO_FUN_MORE;
	}

	public String getC_ALGO_FUN_ONE() {
		return c_ALGO_FUN_ONE;
	}

	public void setC_ALGO_FUN_ONE(String c_ALGO_FUN_ONE) {
		this.c_ALGO_FUN_ONE = c_ALGO_FUN_ONE;
	}

	public String getC_ALGO_CODE() {
		return c_ALGO_CODE;
	}

	public void setC_ALGO_CODE(String cALGOCODE) {
		c_ALGO_CODE = cALGOCODE;
	}

	public String getC_ALGO_NAME() {
		return c_ALGO_NAME;
	}

	public void setC_ALGO_NAME(String cALGONAME) {
		c_ALGO_NAME = cALGONAME;
	}

	public String getC_ALGO_FORMULA() {
		return c_ALGO_FORMULA;
	}

	public void setC_ALGO_FORMULA(String cALGOFORMULA) {
		c_ALGO_FORMULA = cALGOFORMULA;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_DV_ALGO_TYPE() {
		return c_DV_ALGO_TYPE;
	}

	public void setC_DV_ALGO_TYPE(String cDVALGOTYPE) {
		c_DV_ALGO_TYPE = cDVALGOTYPE;
	}

	public String getC_ALGO_FORMULA_TRANSFORM() {
		return c_ALGO_FORMULA_TRANSFORM;
	}

	public void setC_ALGO_FORMULA_TRANSFORM(String cALGOFORMULATRANSFORM) {
		c_ALGO_FORMULA_TRANSFORM = cALGOFORMULATRANSFORM;
	}

	public String getC_ALGO_FORMULA_ZH() {
		return c_ALGO_FORMULA_ZH;
	}

	public void setC_ALGO_FORMULA_ZH(String c_ALGO_FORMULA_ZH) {
		this.c_ALGO_FORMULA_ZH = c_ALGO_FORMULA_ZH;
	}

}
