package com.yss.ams.sec.information.support.modules.sv.fiincome.service;

import java.util.HashMap;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 债券每日利息普通服务接口，主要进行增删改查操作
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
@RestfulSupported
public interface IFiIncomeService extends IServiceBus {
	
	/**
	 * 根据每百元利息界面点击计算税前利息按钮计算方法
	 * @param paraMap
	 * @return string
	 */
	public String calcBeforeTaxAndDue(HashMap<String, Object> paraMap) throws Exception;
}
