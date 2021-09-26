package com.yss.ams.sec.information.support.modules.sv.secSoldBack.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.yss.framework.api.common.co.AuditableParamPojo;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecSoldBack extends AuditableParamPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2505636812300659421L;

	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";

	/**
	 * 证券上市代码
	 */
	private String c_SEC_MKT_CODE = "";

	/**
	 * 交易市场代码
	 */
	private String c_MKT_CODE = "";

	/**
	 * 回售价格
	 */
	private BigDecimal n_SOLDBACK_PRICE = BigDecimal.ZERO;

	/**
	 * 回售开始日期
	 */
	private String d_SOLDBACK_BEGIN = "1990-01-01";

	/**
	 * 回售结束日期
	 */
	private String d_SOLDBACK_END = "9998-12-31";
	
	/**
	 * 回售资金到账日期
	 */
	private String d_FINAL = "9998-12-31";

	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String c_SEC_CODE) {
		this.c_SEC_CODE = c_SEC_CODE;
	}

	public String getC_SEC_MKT_CODE() {
		return c_SEC_MKT_CODE;
	}

	public void setC_SEC_MKT_CODE(String c_SEC_MKT_CODE) {
		this.c_SEC_MKT_CODE = c_SEC_MKT_CODE;
	}

	public String getC_MKT_CODE() {
		return c_MKT_CODE;
	}

	public void setC_MKT_CODE(String c_MKT_CODE) {
		this.c_MKT_CODE = c_MKT_CODE;
	}

	public BigDecimal getN_SOLDBACK_PRICE() {
		return n_SOLDBACK_PRICE;
	}

	public void setN_SOLDBACK_PRICE(BigDecimal n_SOLDBACK_PRICE) {
		this.n_SOLDBACK_PRICE = n_SOLDBACK_PRICE;
	}

	public String getD_SOLDBACK_BEGIN() {
		return d_SOLDBACK_BEGIN;
	}

	public void setD_SOLDBACK_BEGIN(String d_SOLDBACK_BEGIN) {
		this.d_SOLDBACK_BEGIN = d_SOLDBACK_BEGIN;
	}

	public String getD_SOLDBACK_END() {
		return d_SOLDBACK_END;
	}

	public void setD_SOLDBACK_END(String d_SOLDBACK_END) {
		this.d_SOLDBACK_END = d_SOLDBACK_END;
	}

	public String getD_FINAL() {
		return d_FINAL;
	}

	public void setD_FINAL(String d_FINAL) {
		this.d_FINAL = d_FINAL;
	}

}
