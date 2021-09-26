package com.yss.ams.sec.information.support.modules.sv.indexstock.pojo;

import java.util.Date;

import com.yss.framework.api.common.co.AuditableParamPojo;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * 指数成分券信息
 * @author liuxiang
 * @date 2014/8/11
 */
public class IndexStock extends AuditableParamPojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
    * 指数代码
    **/
    private String c_INDEX_CODE = "";

    /**
    * 成分券内码
    */
    private String c_SEC_CODE = "";

    /**
    * 成分券上市代码
    */
    private String c_SEC_MKT_CODE = "";

    /**
    * 启用日期
    */
    private Date d_BEGIN;

    /**
    * 描述信息
    */
    private String c_DESC = "";

	public String getC_INDEX_CODE() {
		return c_INDEX_CODE;
	}

	public void setC_INDEX_CODE(String c_INDEX_CODE) {
		this.c_INDEX_CODE = c_INDEX_CODE;
	}

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

	public Date getD_BEGIN() {
		return d_BEGIN;
	}

	public void setD_BEGIN(Date d_BEGIN) {
		this.d_BEGIN = d_BEGIN;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String c_DESC) {
		this.c_DESC = c_DESC;
	}
}
