package com.yss.platform.support.dataservice.service;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 组合缓存查询服务，从缓存中查询
 * @author leeyu
 *
 */
@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortCacheDataService extends IPortDataService {

}
