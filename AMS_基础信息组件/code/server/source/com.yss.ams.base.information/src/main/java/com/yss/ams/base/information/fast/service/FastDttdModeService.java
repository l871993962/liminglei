package com.yss.ams.base.information.fast.service;

import java.util.List;

import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeDataService;
import com.yss.framework.api.commonInfo.pojo.FastDttdMode;
import com.yss.framework.api.commonInfo.service.IFastDttdModeService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午10:16:24
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastDttdModeService implements IFastDttdModeService {

	private List<FastDttdMode> convert2FastDttdMode(List<DttdMode> dttdModeList) {
		if(null != dttdModeList && dttdModeList.size() > 0){
			return JsonUtil.toList(JsonUtil.toString(dttdModeList), FastDttdMode.class);
		}else{
			return null;
		}
	}
	
	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:16:24
	 * @param @param funCode
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public List<FastDttdMode> getDataListByFun(String funCode)
			throws ServiceException {
		return convert2FastDttdMode(YssServiceFactory.getInstance()
				.createService(IDtTdModeDataService.class)
				.getDataListByFun(funCode));
	}

}
