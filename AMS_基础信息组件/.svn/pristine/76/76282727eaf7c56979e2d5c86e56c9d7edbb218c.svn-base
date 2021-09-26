package com.yss.ifa.szt.tool.para.service;

import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.ifa.szt.tool.pojo.MrInfo;
/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: IErParaService 
 * @Description: 深圳通参数设置服务类
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
@RestfulSupported
public interface IMrInfoService extends IServiceBus {
	/**
	 * STORY #57549嘉实基金-支付平台-深证通链接检测
	 * STORY #63787优化需求57549深证通链接检测中的任务配置功能	伺服器去重处理
	 * @return
	 */
	public List<MrInfo> queryAllMrInfos();
	/**
	 * 根据ip和端口更新检测状态
	 * @param mrInfo
	 */
	public void updateCheckState(MrInfo mrInfo);
}
