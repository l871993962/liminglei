package com.yss.ifa.szt.tool.license;

import com.yss.fast.systemmanager.support.license.service.ILicenseService;
import com.yss.framework.api.license.pojo.AuthenticateResult;
import com.yss.framework.api.service.YssServiceFactory;

public class ErLicenseUtil {
	
	/**
	 * 根据资源Code鉴定该资源是否被许可授权，明确指定授权客户名称 <br/>
	 * 
	 * 继续是否授权鉴定时：
	 * <ul>
	 * <li>
	 * 传递的资源名称在许可中有对应的名称存在，则鉴权成功</li>
	 * </ul>
	 * 
	 * @param resourceType 资源所属类型
	 * @param resourceCode 资源唯一标识 （必选参数）
	 * 
	 * @return AuthenticateResult 返回鉴定结果
	 * 
	 * */
	public static AuthenticateResult doResourceAuthenticate(String resourceType, String resourceCode)
	{
		ILicenseService licenseService = YssServiceFactory.getInstance().createService(ILicenseService.class);
		//兼容旧版本，如果没有基础的许可，认为包含电子对账所有的许可
		if(!licenseService.doResourceAuthenticate(ResourceTypeEnum.DZ_BASE.toString(), ResourceCodeEnum.DZ_BASE_FORWORD.toString()).isAuthorized())
		{
			AuthenticateResult result = new AuthenticateResult();
			result.setAuthorized(true);
			return result;
		}
		return licenseService.doResourceAuthenticate(resourceType, resourceCode);
	}
}
