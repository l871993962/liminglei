package com.yss.ams.base.information.support.sys.acctype.service;

import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

//import dataservice.comm.pojo.AccType;

/**
 * 资产类型字典数据查询服务接口，主要进行跨应用数据获取
 *
 */
@RestfulSupported
@GenericPojo(pojo = AccType.class)
public interface IAccTypeDataService extends IControlDataService,IKeyConvertDataService {

}
