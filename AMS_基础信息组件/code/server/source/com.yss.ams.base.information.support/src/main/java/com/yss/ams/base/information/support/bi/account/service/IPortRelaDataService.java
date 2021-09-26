package com.yss.ams.base.information.support.bi.account.service;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * STORY #76292 【4.5同步】同步“机构名称”、“账户明细类型”、“关联组合”至“现金账户”
 * @author lenovo
 *
 */
@RestfulSupported
public interface IPortRelaDataService extends IServiceBus {

	/**
	 * 查询现金账户信息关联组合
	 * @param relaCode
	 * @return
	 * @throws Exception
	 */
	public String getCashPortByInfo(String relaCode) throws Exception;
	
	/**
	 * 批量删除账号组合关联关系
	 * @param relaCode
	 * @param portCodes
	 * @throws Exception
	 */
	public void deletePortRela(String relaCode, String portCodes) throws Exception;
	
	/**
	 * 批量更新账号组合关联关系
	 * @param relaCode
	 * @param portCodes
	 * @throws Exception
	 */
	public void updatePortRela(String relaCode, String portCodes) throws Exception;
	
	/**
	 * 批量保存账号组合关联关系
	 * @param relaCode
	 * @param portCodes
	 * @throws Exception
	 */
	public void insertPortRela(String relaCode, String portCodes) throws Exception;
}
