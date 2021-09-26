package com.yss.ams.product.information.support.modules.aa.portcustom.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * <用户自定义组合>实体类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortCustom extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9064752494974005358L;

	/***
	 * 组合代码
	 */
	private String c_PORT_CODE = "";

	/***
	 * 岗位代码
	 */
	private String c_POST_CODE = "";

	/***
	 * 用户代码
	 */
	private String c_USER_CODE = "";

	/***
	 * 功能代码
	 */
	private String c_FUN_CODE = "";

	/***
	 * 根节点代码
	 */
	private String c_TR_CODE_R = "";

	private String c_IDEN = "";

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String cPORTCODE) {
		c_PORT_CODE = cPORTCODE;
	}

	public String getC_POST_CODE() {
		return c_POST_CODE;
	}

	public void setC_POST_CODE(String cPOSTCODE) {
		c_POST_CODE = cPOSTCODE;
	}

	public String getC_USER_CODE() {
		return c_USER_CODE;
	}

	public void setC_USER_CODE(String cUSERCODE) {
		c_USER_CODE = cUSERCODE;
	}

	public String getC_FUN_CODE() {
		return c_FUN_CODE;
	}

	public void setC_FUN_CODE(String cFUNCODE) {
		c_FUN_CODE = cFUNCODE;
	}

	public String getC_TR_CODE_R() {
		return c_TR_CODE_R;
	}

	public void setC_TR_CODE_R(String cTRCODER) {
		c_TR_CODE_R = cTRCODER;
	}

	public String getC_IDEN() {
		return c_IDEN;
	}

	public void setC_IDEN(String cIDEN) {
		c_IDEN = cIDEN;
	}

	

}
