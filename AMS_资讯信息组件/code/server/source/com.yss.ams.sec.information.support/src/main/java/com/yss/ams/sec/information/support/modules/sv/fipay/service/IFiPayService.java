package com.yss.ams.sec.information.support.modules.sv.fipay.service;


import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 债券历史付息信息普通服务接口，主要进行增删改查操作
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
@RestfulSupported
public interface IFiPayService extends IServiceBus {
	
	/**
	 * 校验当前选中的需要删除的历史付息数据是否符合删除要求
	 * @param pojoList
	 * @return
	 */
	public String checkDeleteData(List<BasePojo> pojoList);
	
	public String singleSecFiPayInit(FiPay fiPay) throws Exception;
	
	public String multipleFiPayInit(List<FiPay> fiPayList) throws Exception;
}
