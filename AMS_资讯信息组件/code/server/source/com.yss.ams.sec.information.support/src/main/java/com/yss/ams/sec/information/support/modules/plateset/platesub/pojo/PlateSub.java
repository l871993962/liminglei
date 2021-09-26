package com.yss.ams.sec.information.support.modules.plateset.platesub.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.IEffectivable;
/**
 * STORY #33682 彭博证券信息接口_重新设计
 * xiaozhilong 20161122
 */
public class PlateSub extends AuditableParamPojo implements IEffectivable{

	/**
	 * 板块代码
	 */
	private String c_PLATE_CODE = "";

	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 交易市场代码
	 */
	private String c_MKT_CODE = "";

	/**
	 * 总股本
	 */
	private String c_Capital = "";

	/**
	 * 流通股本
	 */
	private String c_Cir_Capital = "";

//	/**
//	 * 开始日期
//	 */
//	private String d_BEGIN = "";
//
//	/**
//	 * 结束日期
//	 */
//	private String d_END = "";

	private static final long serialVersionUID = 1L;

	public String getC_PLATE_CODE() {
		return c_PLATE_CODE;
	}

	public void setC_PLATE_CODE(String cPLATECODE) {
		c_PLATE_CODE = cPLATECODE;
	}

	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String cSECCODE) {
		c_SEC_CODE = cSECCODE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String cMKTCODE) {
		c_MKT_CODE = cMKTCODE;
	}

	public String getC_Capital() {
		return c_Capital;
	}

	public void setC_Capital(String cCapital) {
		c_Capital = cCapital;
	}

	public String getC_Cir_Capital() {
		return c_Cir_Capital;
	}

	public void setC_Cir_Capital(String cCirCapital) {
		c_Cir_Capital = cCirCapital;
	}

//	public String getD_BEGIN() {
//		return d_BEGIN;
//	}
//
//	public void setD_BEGIN(String dBEGIN) {
//		d_BEGIN = dBEGIN;
//	}
//
//	public String getD_END() {
//		return d_END;
//	}
//
//	public void setD_END(String dEND) {
//		d_END = dEND;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String bizPrimeKeyNames() {
		// TODO Auto-generated method stub
		return "c_PLATE_CODE,c_SEC_CODE";
	}
}
