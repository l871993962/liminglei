package com.yss.ams.base.information.support.bi.orgmgr.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 机构结算会员设置普通服务接口，主要进行增删改查操作
 * @author 马向峰  拆分  
 * @Date 20170531
 */
@RestfulSupported
public interface IOrgMgrService extends IServiceBus {
	/**
	 * Author : ChenLong
	 * Date   : 2016-05-10
	 * Status : Add
	 * Comment: 获取所有结算会员代码
	 */
	public List<String> getAllMBRCodes();
	
	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 * 根据查询条件查询组合关联结算会员
	 * 
	 * @param paraMap
	 *            查询条件
	 * @return 查询结果
	 */
	public QueryRes getPortRelaMember(HashMap<String, Object> paraMap);
}
