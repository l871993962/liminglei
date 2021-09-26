package com.yss.ifa.szt.tool.license;

import java.util.ArrayList;
import java.util.List;

public enum ResourceCodeEnum {
	/**
	 * 伺服器主备服务
	 */
	SFQ_MAIN_STANDBY_CODE("主备服务电子对账"),
	
	/**
	 * 电子对账基础框架
	 */
	DZ_BASE_FORWORD("电子对账基础框架"),
	
	/**
	 * 5家机构
	 */
	DZ_ORG_5("5家机构","5"),
	
	/**
	 * 10家机构
	 */
	DZ_ORG_10("10家机构","10"),
	
	/**
	 * 财务报表电子对账
	 */
	DZ_TYPE_MONTH("财务报表电子对账"),
	
	/**
	 * 增值税台账电子对账
	 */
	DZ_TYPE_ZZSTZ("增值税台账电子对账"),
	
	/**
	 * 净资产变动表电子对账
	 */
	DZ_TYPE_JZCBDB("净资产变动表电子对账"),
	
	/**
	 * 双估值表电子对账
	 */
	DZ_TYPE_DBLGZB("双估值表电子对账"),
	
	/**
	 * 银行间交易拆分
	 */
	DZ_TYPE_OTCTRANSSPLIT("电子对账交易数据"),
	
	/**
	 * STORY #107766 关于浦发托管年金组合月报电子对账模板匹配
	 */
	DZ_TYPE_MONTH_RSB("人社部财务报表电子对账"),
	
	NULL("");
	
	/**
	 * 许可码
	 */
	private String licCode = "";
	
	/**
	 * 映射值
	 */
	private String mapValue = "";
	
	private ResourceCodeEnum(String value)
	{
		this.licCode = value;
	}
	
	private ResourceCodeEnum(String value,String mapValue)
	{
		this.licCode = value;
		this.mapValue = mapValue;
	}
	
	/**
	 * 重写基类方法
	 */
	@Override
	public String toString() {
		return this.licCode.toString();
	}

	/**
	 * 获取词汇代码的映射值
	 * @return
	 */
	public String getMapValue() {
		return mapValue;
	}
	
	/**
	 * 获取许可码
	 * @return
	 */
	public String getLicCode() {
		return licCode;
	}

	/**
	 * 返回打包机构的许可码，顺序：打包数量由大到小
	 * @return
	 */
	public static List<ResourceCodeEnum> getOrgCountLic()
	{
		List<ResourceCodeEnum> list = new ArrayList<ResourceCodeEnum>();
		list.add(ResourceCodeEnum.DZ_ORG_10);
		list.add(ResourceCodeEnum.DZ_ORG_5);
		return list;
	}
	
}
