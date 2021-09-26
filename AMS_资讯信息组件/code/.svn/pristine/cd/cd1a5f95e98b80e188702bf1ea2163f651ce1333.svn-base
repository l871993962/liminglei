package com.yss.ams.sec.information.support.modules.mp.outmkt.service;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IOutMktService extends IServiceBus {

	/**
	 * 根据证券代码获取付息公式
	 * Parameters: secCode 证券代码
	 * Returns: 证券代码对应的付息公式
	 */
	String getSYLX(String secCode);

	/**
	 * BUG #318348 多行情同时存在  add by zmk 2020-06-15
	 * 检查数据库中是否已有证券内码、行情日期、行情分类、行情来源相同的数据
	 * @param secCode 证券内码
	 * @param d_mkt 行情日期
	 * @param mktCls 行情分类
	 * @param dvPlat 行情来源
	 * @return
	 * @throws Exception
	 */
	public String checkDuplicate(String secCode, String d_mkt, String mktCls, String dvPlat) throws Exception;
}
