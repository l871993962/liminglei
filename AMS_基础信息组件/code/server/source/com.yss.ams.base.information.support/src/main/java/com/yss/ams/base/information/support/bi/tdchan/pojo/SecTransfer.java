package com.yss.ams.base.information.support.bi.tdchan.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * @ClassName SecTransfer
 * @Description 证券代码转换POJO
 * @author guohui
 * @CreateDate 2016年10月19日下午2:22:58
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SecTransfer extends AuditableParamPojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 证券代码
	 */
	private String c_SEC_CODE = "";

	/**
	 * 转换规则
	 */
	private String c_TRANSFER_CODE = "";
	
	/**
	 * 披露代码
	 */	
	private String c_PUB_CODE = "";

	/**
	  * 数据来源
	 */
	private String c_DATA_IDF = "";
	
	/**
	  * 类别
	 */
	private String c_TYPE = "";
	
	/**
	 * //描述
	 */
	private String c_DESC = "";
	
	/**
	 * 披露品种
	 * add by chenyoucai 20171223 STORY #48618 品种信息通过证券代码转换功能，实现按照实际的品种在增值税计税券中进行计税:增加披露品种 
	 */	
	private String c_TRANSFER_VAR_CODE = "";
	
	public String getC_SEC_CODE() {
		return c_SEC_CODE;
	}

	public void setC_SEC_CODE(String c_SEC_CODE) {
		this.c_SEC_CODE = c_SEC_CODE;
	}

	public String getC_TRANSFER_CODE() {
		return c_TRANSFER_CODE;
	}

	public void setC_TRANSFER_CODE(String c_TRANSFER_CODE) {
		this.c_TRANSFER_CODE = c_TRANSFER_CODE;
	}

	public String getC_PUB_CODE() {
		return c_PUB_CODE;
	}

	public void setC_PUB_CODE(String c_PUB_CODE) {
		this.c_PUB_CODE = c_PUB_CODE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String c_DESC) {
		this.c_DESC = c_DESC;
	}
	
	public String getC_DATA_IDF() {
		return c_DATA_IDF;
	}

	public void setC_DATA_IDF(String c_DATA_IDF) {
		this.c_DATA_IDF = c_DATA_IDF;
	}
	
	public String getC_TYPE() {
		return c_TYPE;
	}

	public void setC_TYPE(String c_TYPE) {
		this.c_TYPE = c_TYPE;
	}
	
	public String getC_TRANSFER_VAR_CODE() {
		return c_TRANSFER_VAR_CODE;
	}

	public void setC_TRANSFER_VAR_CODE(String c_TRANSFER_VAR_CODE) {
		this.c_TRANSFER_VAR_CODE = c_TRANSFER_VAR_CODE;
	}
}
