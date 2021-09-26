package com.yss.ams.sec.information.support.modules.sv.suspendedcond.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo.SuspendedCond;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * 停牌股票信息服务接口
 * @author shepherd
 */
@RestfulSupported
public interface ISuspendedCondService extends IServiceBus{
	/**
	 * 修改功能选项参数
	 * @param pojoList
	 * @return
	 */
	public String updateConds(List<BasePojo> pojoList);
	
	/**
	 * 根据组合获取参数
	 * @return
	 */
	public List<SuspendedCond> getCondList(String port);
}
