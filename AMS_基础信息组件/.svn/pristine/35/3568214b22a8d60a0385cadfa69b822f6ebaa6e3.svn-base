package com.yss.ams.base.information.support.sys.dai.service;



import java.util.List;

import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

//import dataservice.comm.pojo.Dai;

/**
 * 核算项目字典普通服务接口，主要进行增删改查操作
 *
 */
@RestfulSupported
public interface IDaiDictService extends IServiceBus {
	
	/**
	 * 用于科目体系加载，从系统缓存中取数
	 * STORY #26934 byleeyu20151105
	 * @return 核算项目列表
	 */
	List<Dai> queryByCacheForKm();

}
