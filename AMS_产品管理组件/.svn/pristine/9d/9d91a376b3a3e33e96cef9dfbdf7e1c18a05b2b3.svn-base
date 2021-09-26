package com.yss.ams.product.information.support.modules.pg.portgroup.service;

import java.util.HashMap;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * <群组管理>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortGroupService extends IServiceBus{

	/**
	 * 检查群组代码是否在组合中已经存在
	 * true:已经存在；false:不存在
	 * 
	 * @param groupCode 群组代码
	 * @return String 是否存在（true:已经存在；false:不存在）
	 */
	String checkGroupCode(String groupCode);
	
	/**
	 * 检查群组代码是否在组合中已经存在
	 * true:已经存在；false:不存在
	 * @param groupCode 群组代码
	 * @return String 是否存在（true:已经存在；false:不存在）
	 */
	String checkGroupCode(String groupCode, String ciden);

	/**
	 * 根据查询条件获取群组关联方案界面的 新增 群组
	 * By Jinghehe 2014-6-3 获取群组
	 *  
	 * @param paraMap 查询条件
	 * @return QueryRes 群组结果集
     * @throws Exception 
	 */
	QueryRes getPlanRelaPortGroupAdd(HashMap<String, Object> paraMap) throws Exception;

	/**
	 * 根据查询条件获取群组关联方案界面的 浏览 群组
	 * By Jinghehe 2014-6-3 获取群组
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 群组结果集
	 * @throws Exception 
	 */
    QueryRes getPlanRelaPortGroupBrow(HashMap<String, Object> paraMap) throws Exception;
    
    /**
	 * 查询群组A区数据
	 */
	QueryRes getPortGroupA(HashMap<String, Object> paraMap);

}
