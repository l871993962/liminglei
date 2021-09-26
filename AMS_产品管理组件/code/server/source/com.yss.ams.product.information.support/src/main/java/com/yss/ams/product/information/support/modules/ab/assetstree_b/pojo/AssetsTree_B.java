package com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * <产品树型结构>实体类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_B extends AuditableParamPojo {
	/**
	 * 结构代码
	 */
	private String c_TR_CODE = "";

//	/**
//	 * 结构名称
//	 */
//	private String c_TR_NAME = "";

//	/**
//	 * 上级结构
//	 */
//	private String c_TR_CODE_P = "";

//	/**
//	 * 分类规则
//	 */
//	private String c_DV_TR = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 顶级节点代码
	 */
	private String c_TR_CODE_R = "";

	private String c_PORT_CODE = "";
	
	/**
	 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * 只用于判断当前数据是否为父级节点。不报存至数据库
	 */
	private int isParent;
	
	private static final long serialVersionUID = 1L;

	public String getC_TR_CODE() {
		return c_TR_CODE;
	}

	public void setC_TR_CODE(String cTRCODE) {
		c_TR_CODE = cTRCODE;
	}

//	public String getC_TR_NAME() {
//		return c_TR_NAME;
//	}
//
//	public void setC_TR_NAME(String cTRNAME) {
//		c_TR_NAME = cTRNAME;
//	}

//	public String getC_TR_CODE_P() {
//		return c_TR_CODE_P;
//	}
//
//	public void setC_TR_CODE_P(String cTRCODEP) {
//		c_TR_CODE_P = cTRCODEP;
//	}

//	public String getC_DV_TR() {
//		return c_DV_TR;
//	}
//
//	public void setC_DV_TR(String cDVTR) {
//		c_DV_TR = cDVTR;
//	}

	public String getC_DESC() {
		return c_DESC;
	}

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String cPORTCODE) {
		c_PORT_CODE = cPORTCODE;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_TR_CODE_R() {
		return c_TR_CODE_R;
	}

	public void setC_TR_CODE_R(String cTRCODER) {
		c_TR_CODE_R = cTRCODER;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}
}
