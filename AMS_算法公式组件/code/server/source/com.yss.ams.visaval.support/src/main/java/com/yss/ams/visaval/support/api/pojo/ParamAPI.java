package com.yss.ams.visaval.support.api.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * 封装前台参数对象
 * 
 * @author 马向峰
 * 
 */
public class ParamAPI extends BasePojo{

	private static final long serialVersionUID = 1L;

	/**
	 * 对应参数的code（数据库对应code）
	 */
	private String code;
	/**
	 * 参数名称
	 */
	private String name;

	/**
	 * 参数值
	 */
	private String paramValue;
	
	/**
	 * 默认是否显示
	 */
	private boolean isdefault;
	
	/**
	 * 对应参数的keyword（数据库对应code）
	 */
	private String keyWord;
	
	/**
	 * 对应参数source 
	 */
	private String source;
	
	/**
	 * 是否有明细
	 */
	private boolean hasdetails;
	
	private boolean isFromFile;
	
	


	public boolean isFromFile() {
		return isFromFile;
	}

	public void setFromFile(boolean isFromFile) {
		this.isFromFile = isFromFile;
	}

	public boolean isHasdetails() {
		return hasdetails;
	}

	public void setHasdetails(boolean hasdetails) {
		this.hasdetails = hasdetails;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public boolean isIsdefault() {
		return isdefault;
	}

	public void setIsdefault(boolean isdefault) {
		this.isdefault = isdefault;
	}

}
