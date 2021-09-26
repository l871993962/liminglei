package com.yss.uco.elecreco.er.reverse.manager.info.service;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.er.reverse.manager.info.vo.ErReveInfoVo;

@RestfulSupported
public interface IErReveInfoService extends IServiceBus  {
	String unSdDzResult(List<ErReveInfo> list);
	String sdDzResult(List<ErReveInfo> list);
	
	@LinkControllerMethod(value = "editDzResult", arguTypes = { ErReveInfoVo.class })
	String editDzResult(@LinkControllerMethodArgu("list")List<ErReveInfo> list,@LinkControllerMethodArgu("dzResult")String dzResult,@LinkControllerMethodArgu("xgsm")String xgsm);
}