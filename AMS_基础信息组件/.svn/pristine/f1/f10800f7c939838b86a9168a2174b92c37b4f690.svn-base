package com.yss.ifa.szt.tool.license;

import com.yss.framework.api.license.pojo.AuthenticateResult;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.StringUtil;
import com.yss.ifa.szt.tool.pojo.DzPara;
/**
 * 电子对账对接机构license验证
 * @author lwz
 *
 */
public class ErOrgAuthUtil {
	
	private static Logger logger = LogManager.getLogger(ErOrgAuthUtil.class);
	
	/**
	 * lic验证电子对账参数,是否允许新增
	 * 认证逻辑：1.先认证是否具有电子对账基础框架的许可
	 * 2.再认证是否有该机构的许可
	 * 3.最后认证是否超过设置的最大打包数量的许可
	 * @param para
	 * @param useCount
	 * @param operType 操作类型：1.新增，2.修改
	 * @return
	 */
	public static AuthenticateResult authDzPara(DzPara para,int useCount,int operType)
	{
		//先认证基础功能
		AuthenticateResult result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_BASE.toString(), ResourceCodeEnum.DZ_BASE_FORWORD.toString());
		if(!result.isAuthorized())
		{
			logger.error("电子对帐托管机构验证失败：" + result.getFaildMsg());
			result.setFaildMsg("电子对账基础框架未申请许可，请联系供应方！");
			return result;
		}
		//继续认证是否有这个机构的许可
		String orgName = para.getC_DV_LICORG();
		if(StringUtil.IsNullOrEmptyT(orgName))
		{
			result.setAuthorized(false);
			result.setFaildMsg("电子对帐托管机构验证失败：许可信息不可为空！");
			return result;
		}else
		{
			result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_ORG.toString(), orgName);
			if(result.isAuthorized())
			{
				return result;
			}
		}
		
		//机构未认证通过，继续认证数量
		int setCount = getLicOrgCount();
		logger.debug("电子对账打包机构数：" + setCount);
		boolean countCheck = 1 == operType ? useCount < setCount : useCount <= setCount;
		if(countCheck)
		{
			result.setAuthorized(true);
			result.setFaildMsg("该托管机构的许可认证成功！");
		}else
		{
			result.setAuthorized(false);
			result.setFaildMsg("电子对账未申请对接该托管机构的许可且已经达到最大数量，如需新增对接新机构，请联系软件供应方！");
		}
		return result;
	}
	
	/**
	 * 获取许可信息中的打包数量
	 * @param useCount
	 * @return
	 */
	private static int getLicOrgCount()
	{
		for (ResourceCodeEnum licCode : ResourceCodeEnum.getOrgCountLic()) {
			AuthenticateResult result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_ORG.toString(), licCode.toString());
			if(result.isAuthorized())
			{
				return Integer.parseInt(licCode.getMapValue());
			}
		}
		return 0;
	}
	
}
