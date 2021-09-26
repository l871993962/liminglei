package com.yss.ams.sec.information.support.modules.mp.secTransferPara.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * 证券代码转换选项服务接口
 * @author shepherd
 */
@RestfulSupported
public interface ISecTransferParaService extends IServiceBus{
	/**
	 * 修改功能选项参数
	 * @param pojoList
	 * @return
	 */
	public String updateConds(List<BasePojo> pojoList);
}
