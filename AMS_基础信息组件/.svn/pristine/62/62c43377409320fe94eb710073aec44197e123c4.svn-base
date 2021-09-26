package com.yss.ams.base.information.fast.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.fast.controller.IBaseFastOrgController;
import com.yss.framework.api.commonInfo.pojo.FastOrg;
import com.yss.framework.api.commonInfo.service.IFastOrgService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午10:14:47
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastOrgControllerImpl extends AbstractBaseController<IFastOrgService>  implements IBaseFastOrgController {

	@Override
	public List<FastOrg> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public String getOneManagerFrom(String portCodes) throws Exception {
		return getService().getOneManagerFrom(portCodes);
	}

	@Override
	public List<Map<String, String>> getCacodes() throws Exception {
		return getService().getCacodes();
	}

	

}
