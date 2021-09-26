package com.yss.ams.visaval.support.service;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.exception.YssException;

public interface IVisParameterDataService extends IDataService,
		IKeyConvertDataService {

	public <T extends BasePojo> T getDataByCodeName(String codeName) throws YssException;
}
