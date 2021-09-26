package com.yss.ams.sec.information.support.modules.mp.hlmkt.service;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IHlMktService extends IServiceBus {
    /////// By Jinghehe 2017-8-4 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范 代码调整 
    /////// 挪到 IBaseValService 父类 接口当中 以便供其他界面调用 而不仅仅是凭证界面使用 核算管理数据 在YssData 项目中
    /////// IHlMktService 服务中的 queryErByCondition 方法废弃掉  
	/**
	 * 用于核算综合管理跟据组合，日期，和交易币种生成汇率表达式<br>
	 * Added By xzl 需求3705核算综合管理新增界面自动加载汇率
	 * @param port 组合
	 * @param accDate 核算日期
	 * @return
	 * @throws Exception
	 */
//	public String queryErByCondition(String port, String accDate, String tdCury)
//			throws Exception;
}
