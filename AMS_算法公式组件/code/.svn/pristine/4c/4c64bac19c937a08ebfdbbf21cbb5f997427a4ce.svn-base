package com.yss.ams.visaval.support.util.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.YssCons;

/***
 * 参数pojo
 * @author wuwenlan
 *
 */
public class Parameter extends BasePojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7650008540852691992L;

	/***
	 * 参数代码
	 */
	private String c_PARA_CODE = null; 
	
	/***
	 *  参数名称
	 */
	private String c_PARA_NAME = null;
	
	/***
	 * 参数类型
	 */
	private String c_DV_PARA_TYPE = null;
	
	/***
	 * 参数描述
	 */
	private String c_DESC = null;

	/**
	 * 控件类型（TXT:文本;COMBOX:下拉)	
	 */
	private String c_DV_CTL_TYPE = null;
	
	/**
	 * 值类型
	 */
	private String c_DV_VALUE_TYPE = null;
	
	/**
	 * 控件属性（属性1=值1<回车>属性2=值2)
	 */
	private String c_CTL_ATTR = null;
	
	/**
	 * 数据来源配置(功能代码、方法名、参数)	
	 */
	private String c_DS_ATTR = null;
	
	/**
	 * 扩展显示条件(VALUE=值条件,SHOWMODE=SET/LIST,SHOWFORM=通用窗体,FUN=功能代码)
	 */
	private String c_EXPAND_COND = null;

	/***
	 * 参数代码
	 * @return 返回参数代码
	 */
	public String getC_PARA_CODE() {
		return c_PARA_CODE;
	}

	/***
	 * 参数代码 
	 * @param cPARACODE 参数代码
	 */
	public void setC_PARA_CODE(String cPARACODE) {
		c_PARA_CODE = cPARACODE;
	}

	/***
	 * 参数名称
	 * @return 参数名称
	 */
	public String getC_PARA_NAME() {
		return c_PARA_NAME;
	}

	/***
	 * 参数名称
	 * @param cPARANAME 参数名称
	 */
	public void setC_PARA_NAME(String cPARANAME) {
		c_PARA_NAME = cPARANAME;
	}

	/***
	 * 参数类型
	 * @return 参数类型
	 */
	public String getC_DV_PARA_TYPE() {
		return c_DV_PARA_TYPE;
	}

	/***
	 * 参数类型
	 * @param cDVPARATYPE 参数类型
	 */
	public void setC_DV_PARA_TYPE(String cDVPARATYPE) {
		c_DV_PARA_TYPE = cDVPARATYPE;
	}

	/****
	 * 参数描述
	 */
	public String getC_DESC() {
		return c_DESC;
	}

	/***
	 * 参数描述
	 */
	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}		
	
	/**
	 * @return 控件属性（属性1=值1<回车>属性2=值2)
	 */
	public String getC_DV_CTL_TYPE() {
		return c_DV_CTL_TYPE;
	}

	/**
	 * @return 值类型

	 */
	public String getC_DV_VALUE_TYPE() {
		return c_DV_VALUE_TYPE;
	}

	/**
	 * @return the c_CTL_ATTR
	 */
	public String getC_CTL_ATTR() {
		return c_CTL_ATTR;
	}

	/**
	 * @return the c_DS_ATTR
	 */
	public String getC_DS_ATTR() {
		return c_DS_ATTR;
	}

	/**
	 * @return the c_EXPAND_COND
	 */
	public String getC_EXPAND_COND() {
		return c_EXPAND_COND;
	}

	/**
	 * @param c_DV_CTL_TYPE the c_DV_CTL_TYPE to set
	 */
	public void setC_DV_CTL_TYPE(String c_DV_CTL_TYPE) {
		this.c_DV_CTL_TYPE = c_DV_CTL_TYPE;
	}

	/**
	 * @param c_DV_VALUE_TYPE the c_DV_VALUE_TYPE to set
	 */
	public void setC_DV_VALUE_TYPE(String c_DV_VALUE_TYPE) {
		this.c_DV_VALUE_TYPE = c_DV_VALUE_TYPE;
	}

	/**
	 * @param c_CTL_ATTR the c_CTL_ATTR to set
	 */
	public void setC_CTL_ATTR(String c_CTL_ATTR) {
		this.c_CTL_ATTR = c_CTL_ATTR;
	}

	/**
	 * @param c_DS_ATTR the c_DS_ATTR to set
	 */
	public void setC_DS_ATTR(String c_DS_ATTR) {
		this.c_DS_ATTR = c_DS_ATTR;
	}

	/**
	 * @param c_EXPAND_COND the c_EXPAND_COND to set
	 */
	public void setC_EXPAND_COND(String c_EXPAND_COND) {
		this.c_EXPAND_COND = c_EXPAND_COND;
	}

	/**
	 * 设置实体Bean值
	 * 
	 * @param rs
	 * @throws SQLException
	 * @throws YssException
	 */
	public void parseRsToAttr(ResultSet rs) throws SQLException, YssException {
		c_PARA_CODE = rs.getString("c_PARA_CODE");
		c_PARA_NAME = rs.getString("c_PARA_NAME");
		c_DV_PARA_TYPE = rs.getString("c_DV_PARA_TYPE");
		c_DESC = rs.getString("c_DESC"); // 描述
	}
	
	/**
	 * buildRowStr 按一定格式拼接数据
	 * 
	 * @return String
	 */
	public String buildAttrToStr() {
		StringBuffer buf = new StringBuffer();
		buf.append(c_PARA_CODE).append(YssCons.YSS_REPORTSHOWDETAIL);
		buf.append(c_PARA_NAME).append(YssCons.YSS_REPORTSHOWDETAIL);
		buf.append(c_DV_PARA_TYPE).append(YssCons.YSS_REPORTSHOWDETAIL);
		buf.append(c_DESC).append(YssCons.YSS_REPORTSHOWDETAIL); // 描述
		return buf.toString();
	}
}
