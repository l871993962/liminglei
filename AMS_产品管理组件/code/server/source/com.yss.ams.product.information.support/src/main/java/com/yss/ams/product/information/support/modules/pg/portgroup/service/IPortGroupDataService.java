package com.yss.ams.product.information.support.modules.pg.portgroup.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 功能：群组DataService
 * 时间：20140603
 * 作者：陈文海
 * @author ysstech
 *
 */
/**
 * <群组管理>数据服务接口，主要进行跨应用数据获取
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortGroupDataService extends IDataService,IControlDataService,
IKeyConvertDataService{
	
	/**
	 * 根据查询条件获取群组A区显示数据
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 群组数据结果集
	 */
	QueryRes getPortGroupA(HashMap<String, Object> paraMap);
	
	
	/**
	 * 根据群组代码查询所有组合代码 返回对象QueryRes
	 * 
	 * @param c_group_code 群组代码
	 * @return QueryRes 组合结果集
	 */
    QueryRes querySelectedPort(String c_group_code);
    
	/**
	 * 根据群组代码查询所有组合代码 返回对象QueryRes，无用户和岗位权限
	 * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
	 * 
	 * @param c_group_code 群组代码
	 * @return QueryRes 组合结果集
	 */
    QueryRes querySelectedPortWithoutRight(String c_group_code);
	
	/**
	 * 获取群组数据下挂组合树形结构，无用户和岗位权限
	 * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
	 * 
	 * @return QueryRes 组合结果集
	 */
	QueryRes getGroupDataTreeWithoutRight();
	
    /**
	 * 获取群组数据下挂组合树形结构
	 * STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * 
	 * @return QueryRes 组合结果集
	 */
	QueryRes getGroupDataTree();
	
	/**
	 * 使用前台缓存的用户组合权限查询群组数据，减少set页面打开时间
	 * @param portList
	 * @return
	 */
	QueryRes getGroupDataTree(List<String> portList);
	
	/**
	 * 检查组合代码是否在群组中已经存在
	 * true:已经存在；false:不存在
	 * 
	 * @param groupCode 群组代码
	 * @return String 是否存在（true:已经存在；false:不存在）
	 */
	String checkPortCode(String groupCode);
	
	/**
	 * 根据组合代码删除群组中的组合
	 * 
	 * @param portCodes 组合代码数组
	 * @return String 执行结果
	 */
	String deleteByPortCodes(String[] portCodes);
	
	/**
	 * 获取当前登录用户下有权限的群组
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	<K extends BasePojo> List<K> getAuthorityGroup();
	
	/**
	 * 根据群组代码查询所有组合代码
	 * @return
	 */
	public List<String> querySelectedPortCode(String c_group_code);
}
