package com.yss.uco.elecreco.er.erdata.service;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;

@RestfulSupported
public interface IErDataService extends IServiceBus{

	/**
	 * 保存xml文件
	 * @param fsn
	 * 返回文件内容
	 */
	public XmlFile getXmlFileRoot(String fsn,String fileType,String cAssCode);
}
