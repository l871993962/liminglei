package com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
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
}
