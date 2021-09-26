package com.yss.ams.product.information.support.modules.pg.operport.service;

import java.util.Map;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品群组管理>组合相关操作服务接口
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IOperPortService extends IServiceBus{
	
	/**
	 * 根据群组代码获取组合信息
	 * 
	 * Author : ChenLong
	 * Date   : 2016-01-22
	 * Status : Add
	 * @param groupCode 群组代码
	 * @return Map<String,Port>
	 */
	public Map<String,Port> getPortByGroupCode(String groupCode);
	
	/**
	 * 获取所有组合群组信息
	 * 
     * Author : ChenLong
     * Date   : 2016-01-22
     * Status : Add 
     * @return
     */
    public Map<String,String> getAllPortGroup();
}
