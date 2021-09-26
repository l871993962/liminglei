package com.yss.uco.elecreco.er.erdztype.util;

import java.util.LinkedHashSet;
import java.util.Set;

import com.yss.framework.api.license.pojo.AuthenticateResult;
import com.yss.ifa.szt.tool.license.ErLicenseUtil;
import com.yss.ifa.szt.tool.license.ResourceCodeEnum;
import com.yss.ifa.szt.tool.license.ResourceTypeEnum;

public class LicDzTypeUtil {
	/**
	 * 对账类型-月报
	 */
	private Set<String> monthDzTypes = new LinkedHashSet<String>();
	
	/**
	 * 对账类型-日报
	 */
	private Set<String> dayDzTypes = new LinkedHashSet<String>();
	
	/**
	 * 对账类型-交易数据
	 */
	private Set<String> transTypes = new LinkedHashSet<String>();
	
	
	private LicDzTypeUtil()
	{
		AuthenticateResult result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_BASE.toString(), ResourceCodeEnum.DZ_BASE_FORWORD.toString());
		if(result.isAuthorized())
		{
			dayDzTypes.add("1011");//估值表
			dayDzTypes.add("1001");//余额表
			dayDzTypes.add("1031");//科目表
			result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_TYPE.toString(), ResourceCodeEnum.DZ_TYPE_MONTH.toString());
			if(result.isAuthorized())
			{
				monthDzTypes.add("1701");//资产负债表
				monthDzTypes.add("1801");//利润表
				monthDzTypes.add("1711");//资产负债表（年金工行）
				monthDzTypes.add("1811");//经营业绩表（年金工行）
				monthDzTypes.add("1901");//所有者权益（基金净值）变动表
			}
			result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_TYPE.toString(), ResourceCodeEnum.DZ_TYPE_JZCBDB.toString());
			if(result.isAuthorized())
			{
				monthDzTypes.add("1903");//净资产变动表
			}
			result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_TYPE.toString(), ResourceCodeEnum.DZ_TYPE_DBLGZB.toString());
			if(result.isAuthorized())
			{
				dayDzTypes.add("1013");//双估值表
			}
			
			result = ErLicenseUtil.doResourceAuthenticate(ResourceTypeEnum.DZ_TYPE.toString(), ResourceCodeEnum.DZ_TYPE_OTCTRANSSPLIT.toString());
			if(result.isAuthorized())
			{
				transTypes.add("A001");//银行间交易拆分
			}
		}
	}
	
	private static class SingleHolder
	{
		public static LicDzTypeUtil obj = new LicDzTypeUtil();
	}
	
	public static LicDzTypeUtil getInstance()
	{
		return SingleHolder.obj;
	}

	/**
	 * 获取所有许可的对账类型（包括上级节点，日报01，月报03）
	 * @return
	 */
	public Set<String> getAllDzTypes() {
		Set<String> set = new LinkedHashSet<String>();
		if(dayDzTypes.size()>0)
		{
			set.add("01");
			set.addAll(dayDzTypes);
		}
		if(monthDzTypes.size()>0)
		{
			set.add("03");
			set.addAll(monthDzTypes);
		}
		if(transTypes.size()>0)
		{
			set.add("A01");
			set.addAll(transTypes);
		}
		return set;
	}

	/**
	 * 获取许可的月报类型（不包含上级节点01）
	 * @return
	 */
	public Set<String> getMonthDzTypes() {
		return monthDzTypes;
	}

	/**
	 * 获取许可的日报类型（不包含上级节点03）
	 * @return
	 */
	public Set<String> getDayDzTypes() {
		return dayDzTypes;
	}

	/**
	 * 获取许可的交易类型
	 * @return
	 */
	public Set<String> getTransTypes() {
		return transTypes;
	}
	
}
