package com.yss.ams.base.information.support.bi.account.service;

import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 
 * @ClassName IAreaCityService
 * @Description 区域信息服务接口，主要用于区域信息的下拉内容加载
 * @author liangyilin@ysstech.com
 * @date 2017年6月22日下午7:23:08
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
@RestfulSupported
@GenericPojo(pojo = AreaCity.class)
public interface IAreaCityDataMapService extends IControlDataService,
IKeyConvertDataService {

}
