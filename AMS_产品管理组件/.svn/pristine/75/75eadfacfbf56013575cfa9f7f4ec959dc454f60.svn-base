package com.yss.ams.product.information.support.modules.ab.assetsTree_report.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 提供公司内部报表使用
 * 访问资产结构 
 * STORY #74751 需要提供两个web访问的接口，依次是返回组合树类型、根据组合树类型返回组合树 
 * @author neil
 * @date 2019-06-19
 *
 */
@RestfulSupported
public interface IAssetsTreeReportService {

	public <T extends BasePojo> List<T> portFilter(boolean isDataRight,
			String datClass, String dvPortCode, String trCode,String userCode) throws Exception;
	
}
