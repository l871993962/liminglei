package com.yss.ams.sec.information.support.modules.mp.hggthq.service;

import java.util.Date;

import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRateVo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 回购收益行情 普通服务接口，主要进行增删改查操作
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
@RestfulSupported
public interface ICounterRateService extends IServiceBus {

	/**
	 * 根据公共回购收益行情变更对应证券利率
	 * @param mktDate 行情日期
	 * @param duration 回购期限
	 * @param rate 变更后利率
	 */
	@LinkControllerMethod(value="updateSecRate",arguTypes = CounterRateVo.class)
	public int updateSecRate(@LinkControllerMethodArgu("mktDate")Date mktDate, @LinkControllerMethodArgu("duration")int duration, @LinkControllerMethodArgu("rate")double rate);
	
//	/**
//	 * 根据主数据同步操作证券回购收益行情
//	 * @param mktDate 行情日期
//	 * @param duration 回购期限
//	 * @param operType 操作类型
//	 * @return
//	 */
//	public int syncSecRate(Date mktDate, int duration, String operType);
}
