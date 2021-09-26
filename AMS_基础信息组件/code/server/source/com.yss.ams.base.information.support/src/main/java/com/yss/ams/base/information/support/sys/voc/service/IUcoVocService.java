package com.yss.ams.base.information.support.sys.voc.service;

import java.util.List;

import com.yss.ams.base.information.support.sys.voc.pojo.UcoVocVo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/**
 * //STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志  edit by sunyanlin 20191202
 * @author lenovo
 *
 */
@RestfulSupported
public interface IUcoVocService   extends IServiceBus {
	
	@LinkControllerMethod(value="addAndUpdUcoVoc",arguTypes = UcoVocVo.class)
	public void addAndUpdUcoVoc(@LinkControllerMethodArgu("isFistLoad")boolean isFistLoad, @LinkControllerMethodArgu("addList")List<Vocabulary> addList, @LinkControllerMethodArgu("updList")List<Vocabulary> updList)  throws Exception;

}
