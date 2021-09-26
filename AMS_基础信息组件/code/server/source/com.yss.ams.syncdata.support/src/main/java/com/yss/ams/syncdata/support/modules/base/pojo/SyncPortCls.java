package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 产品分级实体类
 * @description 
 * @author zhangyongzhao
 * @version 1.0, 2018年6月13日
 */
public class SyncPortCls {
	
	/*产品全称*/
	@XmlAttribute(name = "c_PORT_CODE")
	private String c_PORT_CODE = "";
	
	/*分级名称*/
	@XmlAttribute(name = "c_PORT_CLS_NAME")
	private String c_PORT_CLS_NAME = "";
	
	/*分级代码*/
	@XmlAttribute(name = "c_PORT_CLS_CODE")
	private String c_PORT_CLS_CODE = "";
	
	/*级别类型*/
	@XmlAttribute(name = "c_DV_PORT_CLS")
	private String c_DV_PORT_CLS = "";
	
//	/*成立日期*/
	private Date d_TO_LIST ;
	
	/*到期日期*/
	private Date d_OFF_LIST ;
	
	/*清算日期*/
	private Date d_LIQUID_DATE ;

	@XmlTransient
	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String c_PORT_CODE) {
		this.c_PORT_CODE = c_PORT_CODE;
	}

	@XmlTransient
	public String getC_PORT_CLS_CODE() {
		return c_PORT_CLS_CODE;
	}

	public void setC_PORT_CLS_CODE(String c_PORT_CLS_CODE) {
		this.c_PORT_CLS_CODE = c_PORT_CLS_CODE;
	}

	@XmlTransient
	public String getC_DV_PORT_CLS() {
		return c_DV_PORT_CLS;
	}

	public void setC_DV_PORT_CLS(String c_DV_PORT_CLS) {
		this.c_DV_PORT_CLS = c_DV_PORT_CLS;
	}

	@XmlTransient
	public Date getD_TO_LIST() {
		return d_TO_LIST;
	}

	public void setD_TO_LIST(Date d_TO_LIST) {
		this.d_TO_LIST = d_TO_LIST;
	}

	@XmlTransient
	public Date getD_OFF_LIST() {
		return d_OFF_LIST;
	}

	public void setD_OFF_LIST(Date d_OFF_LIST) {
		this.d_OFF_LIST = d_OFF_LIST;
	}

	@XmlTransient
	public Date getD_LIQUID_DATE() {
		return d_LIQUID_DATE;
	}

	public void setD_LIQUID_DATE(Date d_LIQUID_DATE) {
		this.d_LIQUID_DATE = d_LIQUID_DATE;
	}

	@XmlTransient
	public String getC_PORT_CLS_NAME() {
		return c_PORT_CLS_NAME;
	}

	public void setC_PORT_CLS_NAME(String c_PORT_CLS_NAME) {
		this.c_PORT_CLS_NAME = c_PORT_CLS_NAME;
	}

}
