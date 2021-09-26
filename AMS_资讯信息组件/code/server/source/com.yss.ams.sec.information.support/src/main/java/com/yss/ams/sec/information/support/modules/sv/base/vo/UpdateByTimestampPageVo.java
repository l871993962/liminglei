package com.yss.ams.sec.information.support.modules.sv.base.vo;

import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class UpdateByTimestampPageVo {
	private String timestamp;
	private PageInation page;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public PageInation getPage() {
		return page;
	}
	public void setPage(PageInation page) {
		this.page = page;
	}
	
}
