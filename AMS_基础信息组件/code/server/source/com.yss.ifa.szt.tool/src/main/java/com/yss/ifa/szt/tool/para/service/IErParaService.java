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
public interface IErParaService extends IServiceBus {
	/**
	 * @Description: 根据参数代码获取伺服器配置信息
	 * @param c_Para_Code 参数代码
	 * @return  返回主备机伺服器信息
	 * @author wulongxing 
	 * @date 2017年6月20日 下午4:12:59
	 */
	List<MrInfo> queryMrInfos(String c_Para_Code);
}
