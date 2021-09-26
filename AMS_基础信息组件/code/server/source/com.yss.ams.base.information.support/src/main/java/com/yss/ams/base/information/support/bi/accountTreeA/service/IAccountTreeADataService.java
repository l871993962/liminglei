package com.yss.ams.base.information.support.bi.accountTreeA.service;

import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IAccountTreeADataService extends IDataService,
IControlDataService, IKeyConvertDataService {

}
