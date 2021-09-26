package com.yss.ams.base.information.support.sys.accele.service;

import com.yss.ams.base.information.support.sys.accele.pojo.AccEle;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

//import dataservice.comm.pojo.AccEle;

/**
 * 核算元素字典数据服务接口，主要进行跨应用数据获取
 *
 */
@RestfulSupported
@GenericPojo(pojo = AccEle.class)
public interface IDaeElemDataService extends IControlDataService,IKeyConvertDataService{

}
