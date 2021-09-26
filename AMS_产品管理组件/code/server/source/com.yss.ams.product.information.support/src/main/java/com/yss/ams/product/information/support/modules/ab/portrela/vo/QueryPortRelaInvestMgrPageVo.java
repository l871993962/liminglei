package com.yss.ams.product.information.support.modules.ab.portrela.vo;

import java.util.HashMap;

import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class QueryPortRelaInvestMgrPageVo {

	private HashMap<String, Object> paraMap;
	private PageInation page;
	public HashMap<String, Object> getParaMap() {
		return paraMap;
	}
	public void setParaMap(HashMap<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
	public PageInation getPage() {
		return page;
	}
	public void setPage(PageInation page) {
		this.page = page;
	}
}
