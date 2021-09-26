package com.yss.ams.base.information.support.sys.voc.pojo;

import java.util.List;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
@ControllerMixArgu
public class UcoVocVo {

	private boolean isFistLoad;
	private List<Vocabulary> addList; 
	private List<Vocabulary> updList;
	public boolean isFistLoad() {
		return isFistLoad;
	}
	public void setFistLoad(boolean isFistLoad) {
		this.isFistLoad = isFistLoad;
	}
	public List<Vocabulary> getAddList() {
		return addList;
	}
	public void setAddList(List<Vocabulary> addList) {
		this.addList = addList;
	}
	public List<Vocabulary> getUpdList() {
		return updList;
	}
	public void setUpdList(List<Vocabulary> updList) {
		this.updList = updList;
	}
	
	
}
