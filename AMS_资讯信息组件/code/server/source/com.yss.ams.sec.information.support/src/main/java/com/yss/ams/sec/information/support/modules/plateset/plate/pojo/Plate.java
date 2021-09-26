package com.yss.ams.sec.information.support.modules.plateset.plate.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.IEffectivable;

/**
 * 板块信息pojo
 * @author 马向峰 拆分
 * @Date 20170531
 */
public class Plate extends AuditableParamPojo  implements IEffectivable{

	/**
	* 板块分类代码 
	*/
	  private String c_PLATE_CODE = "";

	/**
	* 板块分类名称 
	*/
	  private String c_PLATE_NAME = "";

	/**
	* 上级板块代码 
	*/
	  private String c_PLATE_CODE_P = "";

	/**
	 * 板块分类标准
	 */
	private String c_PLATE_TYPE = "";

	/**
	 * 行业指数代码
	 */
	private String c_INDEX_CODE = "";
	
	/**
	* 描述 
	*/
	  private String c_DESC = "";

	private static final long serialVersionUID = 1L ;
	
	public String getC_PLATE_TYPE() {
		return c_PLATE_TYPE;
	}

	public void setC_PLATE_TYPE(String c_PLATE_TYPE) {
		this.c_PLATE_TYPE = c_PLATE_TYPE;
	}

	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

	public String getC_PLATE_CODE() {
		return c_PLATE_CODE;
	}

	public void setC_PLATE_CODE(String cPLATECODE) {
		c_PLATE_CODE = cPLATECODE;
	}

	public String getC_PLATE_NAME() {
		return c_PLATE_NAME;
	}

	public void setC_PLATE_NAME(String cPLATENAME) {
		c_PLATE_NAME = cPLATENAME;
	}

	public String getC_PLATE_CODE_P() {
		return c_PLATE_CODE_P;
	}

	public void setC_PLATE_CODE_P(String cPLATECODEP) {
		c_PLATE_CODE_P = cPLATECODEP;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String bizPrimeKeyNames() {
		// TODO Auto-generated method stub
		return "c_PLATE_CODE";
	}
}
