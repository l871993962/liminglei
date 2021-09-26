package com.yss.ams.product.information.support.modules.ab.port.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.port.pojo.Port_A;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * <产品基本信息>A区组合数据服务接口，主要进行跨应用数据获取
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
@GenericPojo(pojo = Port_A.class)
public interface IPortADataService extends IDataService{

	/**
	 * 根据参数过滤，并获取组合信息
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @param dvPortCode 组合级别
	 * @param trCode 组合树结构代码
	 * @return QueryRes 组合信息
	 */
	@LinkControllerMethod(value = "doPortFilterRes", arguTypes = {String.class,String.class,String.class,String.class})
	QueryRes doPortFilterRes(String isDataRight, String datClass, String dvPortCode, String trCode);
	
	HashMap<String,Port_A> getAssList();
	
	/**
	 * 保存为常用产品
	 * 
	 * STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * @param basePojoList
	 * @return
	 */
	String saveToOftenUsePort(List<BasePojo> basePojoList);
	
	/**
	 * 获取常用产品list
	 * 
	 * STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * @return
	 */
	List<String> getOftenUsePortList();
	
	/**
	 * 删除常用产品list
	 * 
	 * STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * @param basePojoList
	 * @return
	 */
	String deleteOftenUsePort(List<BasePojo> basePojoList);
	
	/**
	 * 根据参数过滤，并获取组合信息
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @param dvPortCode 组合级别
	 * @param trCode 组合树代码
	 * @param menuId 功能代码
	 * @return QueryRes 组合信息
	 */
	@LinkControllerMethod(value = "doPortFilterRes", arguTypes = {String.class,String.class,String.class,String.class,String.class})
	QueryRes doPortFilterRes(String isDataRight, String datClass,String dvPortCode, String trCode,String menuId);
}
