package com.yss.ams.product.information.support.modules.dataCopy.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class CopyDataCheck extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/// <summary>
    /// 源组合
    /// </summary>
    private String c_SOURCE_CODE;

    /// <summary>
    /// 目标组合
    /// </summary>
    private String c_PORT_CODE;

    /// <summary>
    /// 数据项名称
    /// </summary>
    private String c_DATA_NAME;

    /// <summary>
    /// 数据项代码
    /// </summary>
    private String c_DATA_CODE;

    /// <summary>
    /// 复制状态
    /// </summary>
    private String c_COPY_STATE;

	public String getC_SOURCE_CODE() {
		return c_SOURCE_CODE;
	}

	public void setC_SOURCE_CODE(String c_SOURCE_CODE) {
		this.c_SOURCE_CODE = c_SOURCE_CODE;
	}

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	public String getC_DATA_NAME() {
		return c_DATA_NAME;
	}

	public void setC_DATA_NAME(String c_DATA_NAME) {
		this.c_DATA_NAME = c_DATA_NAME;
	}

	public String getC_DATA_CODE() {
		return c_DATA_CODE;
	}

	public void setC_DATA_CODE(String c_DATA_CODE) {
		this.c_DATA_CODE = c_DATA_CODE;
	}

	public String getC_COPY_STATE() {
		return c_COPY_STATE;
	}

	public void setC_COPY_STATE(String c_COPY_STATE) {
		this.c_COPY_STATE = c_COPY_STATE;
	}
}
