package com.yss.ams.base.information.fast.controller.impl;

import java.util.List;

import com.yss.ams.base.information.support.fast.controller.IBaseFastMktDataController;
import com.yss.framework.api.commonInfo.pojo.FastMkt;
import com.yss.framework.api.commonInfo.service.IFastMktDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午10:16:24
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastMktDataControllerImpl extends AbstractBaseController<IFastMktDataService> implements IBaseFastMktDataController {

	@Override
	public List<FastMkt> getDataList() throws ServiceException {
		return getService().getDataList();
	}



}
