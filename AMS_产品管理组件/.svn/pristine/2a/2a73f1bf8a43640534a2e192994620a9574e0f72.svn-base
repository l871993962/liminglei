package com.yss.ams.product.information.support.modules.ab.portPdAttribute.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo.PortPdAttribute;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品属性分类>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortPdAttributeService extends IServiceBus {

	
	/**
	 * 获取所有词汇字典信息
	 * 
	 * @return HashMap<String, String> 词汇字典信息
	 */
	public HashMap<String, String> getVocabularyDict();
	
	/**
	 * 获取所有资产类型字典信息
	 * 
	 * @return HashMap<String, String> 资产类型字典信息
	 */
	public HashMap<String, String> getAssetDict();
	
	/**
	 * 根据查询条件获取产品基本信息实体列表（组合代码 + 资产类型）
	 * 
	 * @param paraMap 查询条件
	 * @return HashMap<String, String> 产品基本信息实体列表（组合代码 + 资产类型）
	 */
	public HashMap<String, String> getPortPojoList (HashMap<String, String> paraMap);
	
	/**
	 * 根据查询条件获取产品基本信息实体列表（组合代码 + 组合名称）
	 * 
	 * @param paraMap 查询条件
	 * @return HashMap<String, String> 产品基本信息实体列表（组合代码 + 组合名称）
	 */
	public HashMap<String, String> getPortNameDict(HashMap<String, String> paraMap);
	
	/**
	 * 根据组合类别查询组合code
	 * @param portType 组合类别
	 */
	public List<String> queryPortCodesByType(String portType);
	
	/**
	 * 获取含有短编码的数据
	 * BUG #325127 
	 */
	public List<PortPdAttribute> getShortNumPort();
	
	/**
	 * 
	 * 获取组合 T+N 估值属性，天数相同的为一组，进行日期的转换
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, List<String>> getPortCodeForNDay(String ports) throws Exception;
	
}
