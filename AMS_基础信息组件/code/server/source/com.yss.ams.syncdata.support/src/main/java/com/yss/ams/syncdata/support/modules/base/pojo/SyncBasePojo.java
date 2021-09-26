package com.yss.ams.syncdata.support.modules.base.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class SyncBasePojo {
	

	//唯一主键
	@XmlTransient
	private String id = "";
	
	//同步人
	@XmlTransient
	private String c_SYNC_BY = "";

	//同步人名称
	@XmlTransient
	private String c_SYNC_BY_NAME = "";
	
	//同步时间
	@XmlTransient
	private String c_SYNC_TIME = "";
	
	//系统ID
	@XmlTransient
	private String c_SYS_ID = "";
	
	//关联ID
	@XmlTransient
	private String c_RELA_ID = "";
	
	//funcode
	@XmlTransient
	private String c_FUN_CODE = "";
	
	//数据操作
	@XmlTransient
	private String c_SYNC_OPERATE ;
	
	//本系统同步状态
	@XmlTransient
	private String n_SYNC_STATE = "" ;
	
	//本系统同步状态名称
	@XmlTransient
	private String n_SYNC_STATE_NAME = "" ;
	
	//系统同步状态
	@XmlTransient
	private int n_SYS_STATE ;
	
	//同步数据
	@XmlTransient
	private String c_SYNC_DATA = "";
	
	//数据详情
	@XmlTransient
	private String c_SYNC_DETAIL = "";

	//数据状态
	@XmlAttribute(name = "n_DATA_STATE")
	private int n_DATA_STATE ;
	
	@XmlTransient
	public String getC_SYNC_BY() {
		return c_SYNC_BY;
	}

	public void setC_SYNC_BY(String c_SYNC_BY) {
		this.c_SYNC_BY = c_SYNC_BY;
	}

	@XmlTransient
	public String getC_SYNC_BY_NAME() {
		return c_SYNC_BY_NAME;
	}

	public void setC_SYNC_BY_NAME(String c_SYNC_BY_NAME) {
		this.c_SYNC_BY_NAME = c_SYNC_BY_NAME;
	}

	@XmlTransient
	public String getC_SYNC_TIME() {
		return c_SYNC_TIME;
	}

	public void setC_SYNC_TIME(String c_SYNC_TIME) {
		this.c_SYNC_TIME = c_SYNC_TIME;
	}

	@XmlTransient
	public String getC_SYS_ID() {
		return c_SYS_ID;
	}

	public void setC_SYS_ID(String c_SYS_ID) {
		this.c_SYS_ID = c_SYS_ID;
	}

	@XmlTransient
	public String getC_RELA_ID() {
		return c_RELA_ID;
	}

	public void setC_RELA_ID(String c_RELA_ID) {
		this.c_RELA_ID = c_RELA_ID;
	}

	@XmlTransient
	public String getC_FUN_CODE() {
		return c_FUN_CODE;
	}

	public void setC_FUN_CODE(String c_FUN_CODE) {
		this.c_FUN_CODE = c_FUN_CODE;
	}

	@XmlTransient
	public int getN_SYS_STATE() {
		return n_SYS_STATE;
	}

	public void setN_SYS_STATE(int n_SYS_STATE) {
		this.n_SYS_STATE = n_SYS_STATE;
	}

	@XmlTransient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@XmlTransient
	public String getC_SYNC_OPERATE() {
		return c_SYNC_OPERATE;
	}

	public void setC_SYNC_OPERATE(String c_SYNC_OPERATE) {
		this.c_SYNC_OPERATE = c_SYNC_OPERATE;
	}

	@XmlTransient
	public String getC_SYNC_DATA() {
		return c_SYNC_DATA;
	}

	public void setC_SYNC_DATA(String c_SYNC_DATA) {
		this.c_SYNC_DATA = c_SYNC_DATA;
	}
	@XmlTransient
	public String getC_SYNC_DETAIL() {
		return c_SYNC_DETAIL;
	}

	public void setC_SYNC_DETAIL(String c_SYNC_DETAIL) {
		this.c_SYNC_DETAIL = c_SYNC_DETAIL;
	}

	@XmlTransient
	public String getN_SYNC_STATE_NAME() {
		return n_SYNC_STATE_NAME;
	}

	public void setN_SYNC_STATE_NAME(String n_SYNC_STATE_NAME) {
		this.n_SYNC_STATE_NAME = n_SYNC_STATE_NAME;
	}

	@XmlTransient
	public String getN_SYNC_STATE() {
		return n_SYNC_STATE;
	}

	public void setN_SYNC_STATE(String n_SYNC_STATE) {
		this.n_SYNC_STATE = n_SYNC_STATE;
	}

	@XmlTransient
	public int getN_DATA_STATE() {
		return n_DATA_STATE;
	}

	public void setN_DATA_STATE(int n_DATA_STATE) {
		this.n_DATA_STATE = n_DATA_STATE;
	}

}
