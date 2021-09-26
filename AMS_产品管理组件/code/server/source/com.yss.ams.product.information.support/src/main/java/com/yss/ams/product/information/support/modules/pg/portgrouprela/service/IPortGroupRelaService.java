package com.yss.ams.product.information.support.modules.pg.portgrouprela.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * <产品群组管理>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortGroupRelaService extends IServiceBus{

	/**
	 * 根据群组代码查询所有组合
	 * @return
	 */
	List<Port> querySelectedPortList(String c_group_code);
	
	/**
	 * add method 根据群组代码获取组合
	 * 返回组件framework组合对象
	 * @param groupCode
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, com.yss.framework.api.common.co.Port> getFWPortByGroupCode(
			String groupCode) throws ServiceException;
	
	/**
	 * add method 根据群组代码获取组合
	 * 返回组件组合对象
	 * @param groupCode
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Port> getZJPortByGroupCode(
			String groupCode) throws ServiceException ;
	
	/**
	 * STORY #103420 产品组件提供批量查找群组的方法
	 * @param portCodeList
	 * @return
	 */
	public Map<String, List<String>> queryGroupCodeByPortCodeList(List<String> portCodeList) ;
	
	/**
	 *  STORY #103420 产品组件提供批量查找群组的方法
	 * @return
	 */
	public Map<String, List<String>> queryAllGroupCode() ;
	
}
