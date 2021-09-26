package com.yss.ams.visaval.support.api.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;

/**
 * 封装前台参数和返回值对象
 * 
 * @author 马向峰
 * 
 */
public class DataAPI extends BasePojo{

	private static final long serialVersionUID = 1L;
	/**
	 * 参数集合
	 */
	private List<ParamAPI> paramAPIs = new ArrayList<ParamAPI>();
	/**
	 * 返回值
	 */
	private ReturnAPI returnAPI;

	public List<ParamAPI> getParamAPIs() {
		return paramAPIs;
	}

	public void setParamAPIs(List<ParamAPI> paramAPIs) {
		this.paramAPIs = paramAPIs;
	}

	public ReturnAPI getReturnAPI() {
		return returnAPI;
	}

	public void setReturnAPI(ReturnAPI returnAPI) {
		this.returnAPI = returnAPI;
	}

}
