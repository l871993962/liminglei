package com.yss.ams.sec.information.support.modules.mp.secTransfer.service;

import java.util.HashMap;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * @ClassName ISecTransferService
 * @Description 证券代码转换
 * @author guohui
 * @CreateDate 2016年10月24日下午2:11:59
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
@RestfulSupported
public interface ISecTransferService extends IServiceBus{	

	/**
	 * 根据组合代码及日期,后台查询参数“转换规则”,获取对应的证券代码转换表(导出当前页重写方法需要获取)
	 * @Title getSecTranMapByPort 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月24日下午5:18:33
	 * @param paramList 前台传来的参数列表
	 * @return
	 */
	public abstract HashMap<String, String> getSecTranMapByCondition(HashMap<String, String> paramList);
	
	/**
	 * 根据组合代码和查询日期及参数代码,获取对应的组合自定义参数
	 * @Title getParamValue 
	 * @Description STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @author guohui@ysstech.com
	 * @date 2017年2月20日下午1:35:01
	 * @param portCode 组合代码
	 * @param dateStr 日期
	 * @param dspCode 参数代码
	 * @return
	 */
	public abstract String getParamValue(String portCode, String dateStr, String dspCode);
}
