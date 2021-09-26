package com.yss.uco.elecreco.bi.elecrela.service;

import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 对账指标关联指标项名称服务接口
 * @author shepherd
 *
 */
@RestfulSupported
public interface IElecIndexDataService extends IControlDataService,
		IKeyConvertDataService {

}
