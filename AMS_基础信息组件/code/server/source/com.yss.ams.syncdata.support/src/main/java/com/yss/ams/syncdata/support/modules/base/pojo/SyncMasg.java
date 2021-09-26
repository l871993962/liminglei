package com.yss.ams.syncdata.support.modules.base.pojo;

import java.util.List;

public class SyncMasg {

	//系统标识
	private String c_SYS_ID = "";
	
	//功能代码
	private String c_FUN_CODE = "";
	
	//数据状态：0-未同步；1-同步；2-已退回
	private int n_SYNC_STATE = 0 ;
	
	//数据id
	private List<String> idList ;

	public String getC_SYS_ID() {
		return c_SYS_ID;
	}

	public void setC_SYS_ID(String c_SYS_ID) {
		this.c_SYS_ID = c_SYS_ID;
	}

	public String getC_FUN_CODE() {
		return c_FUN_CODE;
	}

	public void setC_FUN_CODE(String c_FUN_CODE) {
		this.c_FUN_CODE = c_FUN_CODE;
	}

	public int getN_SYNC_STATE() {
		return n_SYNC_STATE;
	}

	public void setN_SYNC_STATE(int n_SYNC_STATE) {
		this.n_SYNC_STATE = n_SYNC_STATE;
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	
}
