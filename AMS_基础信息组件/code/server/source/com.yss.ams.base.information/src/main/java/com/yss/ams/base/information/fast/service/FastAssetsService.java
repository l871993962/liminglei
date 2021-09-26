package com.yss.ams.base.information.fast.service;

import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeDataService;
import com.yss.framework.api.commonInfo.pojo.FastAccType;
import com.yss.framework.api.commonInfo.service.IFastAssetsService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午9:42:10
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastAssetsService implements IFastAssetsService {
	
	private FastAccType convert2FastAccType(AccType accType) {
		return JsonUtil.toBean(JsonUtil.toString(accType), FastAccType.class);
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午9:42:10
	 * @param @param dataCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public FastAccType getDataByCode(String dataCode) throws ServiceException {
		return convert2FastAccType((AccType) YssServiceFactory.getInstance()
				.createService(IAccTypeDataService.class)
				.getDataByCode(dataCode));
	}

}
