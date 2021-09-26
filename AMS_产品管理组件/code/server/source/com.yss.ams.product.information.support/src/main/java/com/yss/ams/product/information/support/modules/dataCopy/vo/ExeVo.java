package com.yss.ams.product.information.support.modules.dataCopy.vo;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyData;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class ExeVo {

	private HashMap<String,Object> map;
	private List<CopyData> list;
	public HashMap<String, Object> getMap() {
		return map;
	}
	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}
	public List<CopyData> getList() {
		return list;
	}
	public void setList(List<CopyData> list) {
		this.list = list;
	}
	
	
}
