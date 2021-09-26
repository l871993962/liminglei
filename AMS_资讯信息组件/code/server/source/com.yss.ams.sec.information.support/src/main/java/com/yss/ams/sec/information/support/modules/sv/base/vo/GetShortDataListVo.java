package com.yss.ams.sec.information.support.modules.sv.base.vo;

import java.util.List;

import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;

@ControllerMixArgu
public class GetShortDataListVo {
	private List<String> types;
	private String like;
	private PageInation page;
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public PageInation getPage() {
		return page;
	}
	public void setPage(PageInation page) {
		this.page = page;
	}
	
}
