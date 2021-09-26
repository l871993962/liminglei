package com.yss.ams.base.information.support.sys.mktsdi.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 交易市场字典普通服务接口，主要进行增删改查操作
 * 字典表：t_s_mkt_var
 *
 */
@RestfulSupported
public interface IMktsdiService extends IServiceBus{

	/**
	 * 重写根据主键更新对象方法
	 * 
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示 重写根据主键更新对象方法<br />
	 * modify 20160801 不覆盖updateById方法，添加方法updateByPk
	 */
	public String updateByPk(List<BasePojo> pojoList);

}
