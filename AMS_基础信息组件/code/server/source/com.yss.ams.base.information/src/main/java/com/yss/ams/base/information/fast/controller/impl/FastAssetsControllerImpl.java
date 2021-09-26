package com.yss.ams.base.information.fast.controller.impl;

import com.yss.ams.base.information.support.fast.controller.IBaseFastAssetsController;
import com.yss.framework.api.commonInfo.pojo.FastAccType;
import com.yss.framework.api.commonInfo.service.IFastAssetsService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午9:42:10
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastAssetsControllerImpl extends AbstractBaseController<IFastAssetsService>  implements IBaseFastAssetsController{

	@Override
	public FastAccType getDataByCode(String dataCode) throws ServiceException {
		return getService().getDataByCode(dataCode);
	}
	


}
