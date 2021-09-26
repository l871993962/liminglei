package com.yss.platform.support.dataservice.service;


import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 
 * @author huyingzhao
 * STORY #59765 【南方基金】由于部分基金全称名字太长，需要将系统界面里展示全称的界面改成产品简称
 */
@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortSTDataService extends IControlDataService,
		IKeyConvertDataService {

}