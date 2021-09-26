package com.yss.ifa.szt.tool.pojo;

import java.util.List;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 电子对账参数设置pojo
 * @author liuxiang
 * @version betaV1.19.2
 * @date 2014-03-17
 */
//public class DzPara extends ParamPojo {
//STORY #27716 edit by xhb 2016-1-5 增加审核机制因而改变继承类
public class DzPara extends AuditableParamPojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
    * 基金代码
    */
    private String c_ASS_CODE = "";

    /**
    * 基金公司
    */
    private String c_DEPT_CODE = "";

    /**
    * 证书ID
    */
    private String c_CERT_ID = "";

    /**
    * 托管行代码
    */
    private String c_TGH_CODE = "";
    
    /**
     * 托管行名称
     */
    private String c_TGH_NAME = "";

    /**
    * 业务类型
    */
    private String c_BUS_TYPE = "";

    /**
    * 连接类型
    */
    private String c_COMM_TYPE = "";

    /**
    * 目标用户
    */
    private String c_TARGET_USER = "";

    /**
    * 目标用户标识
    */
    private String c_TARGET_APP_LOGO = "";
    
    /**
     * wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
     * 源用户
     */
    private String c_SRC_USER = "";

    /**
    * 源应用标识
    */
    private String c_SRC_APP_LOGO = "";

    /**
    * 包发送密码
    */
    private String c_PKG_SecretCode = "";
    
    /**
     * 对账模式
     */
    private String c_DZ_MODE = "";
     /**
     * 估值表时候发送 估值增值科目
     */
    private String c_GZB_MODE = "";
     /**
     * 深圳通参数代码
     */
    private String c_ErPara_Code = "";
    /**
	 * 伺服器连接配置信息
	 */
	private List<MrInfo> mrInfoList = null;
	/**
	 * 是否应用分机构
	 */
	private String c_Has_Branch = "";
	
	 /**
     * 科目名称长度 
     * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化
     * author:cls
     * @date:2018-08-14
     */
    private String c_KM_NAME_LENGTH="";
    
    /**
     *管理人机构
     *STORY55269【富国基金】支持电子对账参数设置支持多管理人
     */
     private String c_MANAGE_CODE = "";
     /**
      * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
      * 管理人秘钥
      */
     private String c_SECRETKEY = "";
     /**
      * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
      * 加密类型
      */
     private String c_DV_SECRETTYPE = "";
     /**
      * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
      * 报文编码
      */
     private String c_DV_CHARSET = "";
     
     /**
 	 * 机构许可信息
 	 */
     private String c_DV_LICORG = "";
     
     /**
      * @return	机构许可信息
      */
	public String getC_DV_LICORG() {
		return c_DV_LICORG;
	}

	/**
	 * @param c_DV_LICORG	机构许可信息
	 */
	public void setC_DV_LICORG(String c_DV_LICORG) {
		this.c_DV_LICORG = c_DV_LICORG;
	}

	public String getC_SECRETKEY() {
		return c_SECRETKEY;
	}

	public void setC_SECRETKEY(String c_SECRETKEY) {
		this.c_SECRETKEY = c_SECRETKEY;
	}

	public String getC_DV_SECRETTYPE() {
		return c_DV_SECRETTYPE;
	}

	public void setC_DV_SECRETTYPE(String c_DV_SECRETTYPE) {
		this.c_DV_SECRETTYPE = c_DV_SECRETTYPE;
	}

	public String getC_DV_CHARSET() {
		return c_DV_CHARSET;
	}

	public void setC_DV_CHARSET(String c_DV_CHARSET) {
		this.c_DV_CHARSET = c_DV_CHARSET;
	}

	public String getC_MANAGE_CODE() {
		return c_MANAGE_CODE;
	}

	public void setC_MANAGE_CODE(String c_MANAGE_CODE) {
		this.c_MANAGE_CODE = c_MANAGE_CODE;
	}

	public String getC_Has_Branch() {
		return c_Has_Branch;
	}
	public void setC_Has_Branch(String c_Has_Branch) {
		this.c_Has_Branch = c_Has_Branch;
	}
	public List<MrInfo> getMrInfoList() {
		return mrInfoList;
	}
	public void setMrInfoList(List<MrInfo> mrInfoList) {
		this.mrInfoList = mrInfoList;
	}
    
	public String getC_ErPara_Code() {
		return c_ErPara_Code;
	}

	public void setC_ErPara_Code(String c_ErPara_Code) {
		this.c_ErPara_Code = c_ErPara_Code;
	}

	public String getC_DZ_MODE() {
		return c_DZ_MODE;
	}

	public void setC_DZ_MODE(String c_dz_mode) {
		this.c_DZ_MODE = c_dz_mode;
	}
	 /**
     * 重发最高次数 STORY #35703 估值表自检以及自动生成发送电子对账
     */
    private String c_High_Time = "";
    
    /**
     * 每次间隔时间 STORY #35703 估值表自检以及自动生成发送电子对账
     */
    private String c_Interval = "";
    
    
	public String getC_High_Time() {
		return c_High_Time;
	}

	public void setC_High_Time(String c_High_Time) {
		this.c_High_Time = c_High_Time;
	}

	public String getC_Interval() {
		return c_Interval;
	}

	public void setC_Interval(String c_Interval) {
		this.c_Interval = c_Interval;
	}
		
	public String getC_GZB_MODE() {
		return c_GZB_MODE;
	}

	public void setC_GZB_MODE(String c_GZB_MODE) {
		this.c_GZB_MODE = c_GZB_MODE;
	}
	
	public String getC_ASS_CODE() {
		return this.c_ASS_CODE;
	}

	public void setC_ASS_CODE(String cASSCODE) {
		this.c_ASS_CODE = cASSCODE;
	}

	public String getC_DEPT_CODE() {
		return this.c_DEPT_CODE;
	}

	public void setC_DEPT_CODE(String cDEPTCODE) {
		this.c_DEPT_CODE = cDEPTCODE;
	}

	public String getC_CERT_ID() {
		return this.c_CERT_ID;
	}

	public void setC_CERT_ID(String cCERTID) {
		this.c_CERT_ID = cCERTID;
	}

	public String getC_TGH_CODE() {
		return this.c_TGH_CODE;
	}

	public void setC_TGH_CODE(String cTGHCODE) {
		this.c_TGH_CODE = cTGHCODE;
	}

	public String getC_TGH_NAME() {
		return this.c_TGH_NAME;
	}

	public void setC_TGH_NAME(String cTGHNAME) {
		this.c_TGH_NAME = cTGHNAME;
	}

	public String getC_BUS_TYPE() {
		return this.c_BUS_TYPE;
	}

	public void setC_BUS_TYPE(String cBUSTYPE) {
		this.c_BUS_TYPE = cBUSTYPE;
	}

	public String getC_COMM_TYPE() {
		return this.c_COMM_TYPE;
	}

	public void setC_COMM_TYPE(String cCOMMTYPE) {
		this.c_COMM_TYPE = cCOMMTYPE;
	}

	public String getC_TARGET_USER() {
		return this.c_TARGET_USER;
	}

	public void setC_TARGET_USER(String cTARGETUSER) {
		this.c_TARGET_USER = cTARGETUSER;
	}

	public String getC_TARGET_APP_LOGO() {
		return this.c_TARGET_APP_LOGO;
	}

	public void setC_TARGET_APP_LOGO(String C_TARGET_APP_LOGO) {
		this.c_TARGET_APP_LOGO = C_TARGET_APP_LOGO;
	}

	public String getC_SRC_APP_LOGO() {
		return this.c_SRC_APP_LOGO;
	}

	public void setC_SRC_APP_LOGO(String C_SRC_APP_LOGO) {
		this.c_SRC_APP_LOGO = C_SRC_APP_LOGO;
	}

	public String getC_PKG_PASSWORD() {
		return this.c_PKG_SecretCode;
	}

	public void setC_PKG_PASSWORD(String cPKGPASSWORD) {
		this.c_PKG_SecretCode = cPKGPASSWORD;
	}

	public String getC_SRC_USER() {
		return c_SRC_USER;
	}

	public void setC_SRC_USER(String c_SRC_USER) {
		this.c_SRC_USER = c_SRC_USER;
	}
	public String getC_KM_NAME_LENGTH() {
		return c_KM_NAME_LENGTH;
	}
	public void setC_KM_NAME_LENGTH(String c_KM_NAME_LENGTH) {
		this.c_KM_NAME_LENGTH = c_KM_NAME_LENGTH;
	}
    
	
}
