package com.yss.ams.product.information.support.modules.ab.port.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品基本信息>组合权限服务接口
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IRightPortService extends IServiceBus{
	
	/**
	 * 根据查询条件获取权限设置中的组合数据
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 * @throws Exception
	 */
	public List<Port> getRightManagePortList(HashMap<String, Object> paraMap)
			throws Exception;
	
	/**
	 * 获取权限设置中的组合数据
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 * @throws Exception
	 */
	public List<Port> getRightManagePortList()
			throws Exception;
	
	/**
	 * 根据查询条件获取组合信息列表
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 * @throws Exception
	 */
	public List<Port> getPortInfoList(HashMap<String, Object> paraMap)throws Exception;
	
	/**
	 * 根据查询条件获取权限设置中的组合数据（树形结构列表）
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 * @throws Exception
	 */
	public List<Port> getRightManagePortTree(
			HashMap<String, Object> paraMap) throws Exception;

	
	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) throws Exception;
}
