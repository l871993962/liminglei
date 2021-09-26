package com.yss.uco.elecreco.er.spilt.rela.service;
import java.util.List;

import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IErSplitRelaService extends IServiceBus  {
	/**
	 * 通过组合代码获取其配置的拆分映射关系
	 * @param port
	 * @return
	 */
	public List<ErSplitRela> getErSplitRelasByPortCode(String port);
}