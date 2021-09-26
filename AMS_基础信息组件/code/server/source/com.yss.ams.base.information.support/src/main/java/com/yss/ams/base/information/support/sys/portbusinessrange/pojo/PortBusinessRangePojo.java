/**
 *
 * @Title: BusinessType.java 
 * @Package com.yss.ams.base.information.support.sys.businesstype.pojo 
 * @date 2019年5月13日 下午3:30:19 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.support.sys.portbusinessrange.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/** 
 * 组合业务关联关系pojo类
 * @ClassName: BusinessType 
 * @date 2019年5月13日 下午3:30:19
 * @Stroy72335/Bug
 * @author xiadeqi 
 */
public class PortBusinessRangePojo extends AuditableParamPojo{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -3520008156412630800L;
	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";
	/**
	 * 业务类型代码
	 */
	private String c_BUSINESS_CODE = "";
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_BUSINESS_CODE() {
		return c_BUSINESS_CODE;
	}
	public void setC_BUSINESS_CODE(String c_BUSINESS_CODE) {
		this.c_BUSINESS_CODE = c_BUSINESS_CODE;
	}
	
}
