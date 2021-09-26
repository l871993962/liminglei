package com.yss.ams.sec.information.support.modules.sv.indexstock.service;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
@RestfulSupported
public interface IIndexStockService extends IServiceBus {

	/**
	 * 获取未选中的股票信息列表
	 * 
	 * @param c_Index_Code
	 *            指数代码
	 * @param d_Begin
	 *            启用日期
	 * @return List<SecBase>
	 */
	public QueryRes getUnSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception;

	/**
	 * 获取已选中的股票信息列表
	 * 
	 * @param c_Index_Code
	 *            指数代码
	 * @param d_Begin
	 *            启用日期
	 * @return List<SecBase>
	 */
	public QueryRes getSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception;

	/**
	 * 获取最近已选中的股票信息列表
	 * 
	 * @param c_Index_Code
	 *            指数代码
	 * @param d_Begin
	 *            启用日期
	 * @return List<SecBase>
	 */
	public QueryRes getLastSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception;

	/**
	 * 获取最近未选中的股票信息列表
	 * 
	 * @param c_Index_Code
	 *            指数代码
	 * @param d_Begin
	 *            启用日期
	 * @return List<SecBase>
	 */
	public QueryRes getLastUnSelectedSecBase(String c_Index_Code, String d_Begin)
			throws Exception;
}
