package com.yss.ams.sec.information.support.modules.mp.preStockInterest.service;


import java.util.List;

import com.yss.ams.sec.information.support.modules.mp.preStockInterest.pojo.PreStockInterest;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 优先股计息信息普通服务接口，主要进行增删改查操作
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
@RestfulSupported
public interface IPreStockInterestService  extends IServiceBus{
	/**
	 * 优先股计息信息生成历史计息信息
	 * @param pre
	 * @return
	 * @throws Exception
	 */
	public String singleSecInitFi(PreStockInterest pre) throws Exception;
	
	/**
	 * 生成历史计息信息
	 * @param preList
	 * @return
	 * @throws Exception
	 */
	public String multiplePreInitFi(List<PreStockInterest> preList) throws Exception;
}
