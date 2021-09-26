package com.yss.ams.product.information.fast.service;

import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.fast.dao.FastCommonInfoDao;
import com.yss.ams.product.information.fast.dao.FastCommonInfoSqlBuilder;
import com.yss.framework.api.commonInfo.service.IFastCommonInfoService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年4月4日 下午3:23:22
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastCommonInfoService implements IFastCommonInfoService {

	private FastCommonInfoDao serviceDao = null;
	public FastCommonInfoService() throws Exception{
		serviceDao = new FastCommonInfoDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new FastCommonInfoSqlBuilder());
	}
	
	@Override
	public List<String> getDictDataByKey(String key) throws ServiceException{
		return serviceDao.getDictDataByKey(key);
	}
}
