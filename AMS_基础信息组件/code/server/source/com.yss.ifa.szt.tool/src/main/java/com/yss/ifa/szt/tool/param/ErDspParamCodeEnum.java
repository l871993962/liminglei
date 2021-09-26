package com.yss.ifa.szt.tool.param;

public enum ErDspParamCodeEnum {
	
	/**
	*电子对账分级产品合计项指标代码是否加上分级组合-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_FJHJXJSFJZH("SV_BB_DZDZ_FJHJXJSFJZH"),
	/**
	*电子对账估值表按托管行拆分生成发送-默认值：0(1:是;0：否)
	*/
	DZ_BB_DZDZ_GZBCFFS("DZ_BB_DZDZ_GZBCFFS"),
	/**
	*电子对账估值持有到期类债券投资1111开头的第三级科目的市值取成本-默认值：0(1:是;0：否)
	*/
	DZ_BB_DZDZ_MVCBLEVEL3("DZ_BB_DZDZ_MVCBLEVEL3"),
	/**
	*电子对账估值指标0016基金资产单位净值是否需要发送估值增值列数据-默认值：0(1:是;0：否)
	*/
	DZ_BB_DZDZ_DWJZIV("DZ_BB_DZDZ_DWJZIV"),
	/**
	*电子对账合计小计是否需要数量-默认值：1(1:是;0：否)
	*/
	SV_BB_DZDZ_HJXJ("SV_BB_DZDZ_HJXJ"),
	/**
	*电子对账科目代码1111.是否转换为1103.-默认值：0(1:是;0：否)
	*/
	DZ_BB_DZDZ_TRANS1111("DZ_BB_DZDZ_TRANS1111"),
	/**
	*电子对账实收资本是否需要数量-默认值：1(1:是;0：否)
	*/
	SV_BB_DZDZ_SSZBZSSL("SV_BB_DZDZ_SSZBZSSL"),
	/**
	*电子对账是否发送现金类占净值比指标数据-默认值：1(1:是;0：否)
	*/
	DZ_BB_DZDZ_XJZBFS("DZ_BB_DZDZ_XJZBFS"),
	/**
	*电子对账是否将第四级科目补齐为六位-默认值：1(1:是;0：否)
	*/
	SV_BB_DZDZ_KMBW("SV_BB_DZDZ_KMBW"),
	/**
	*电子对账数据1102.99,1103.99科目市值列为0-默认值：1(1:是;0：否)
	*/
	SV_BB_DZDZ_GZETFSZ("SV_BB_DZDZ_GZETFSZ"),
	/**
	*电子对账数据成本列加上溢折价-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_CBJSYZJ("SV_BB_DZDZ_CBJSYZJ"),
	/**
	*电子对账数据持有到期类成本科目行情取面值-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_CYDALHQ("SV_BB_DZDZ_CYDALHQ"),
	/**
	*电子对账数据估增3级汇总科目市值列为0-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_GZSJKMSZ("SV_BB_DZDZ_GZSJKMSZ"),
	/**
	*电子对账数据估增4级明细科目市值列为0-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_GZKMSZ("SV_BB_DZDZ_GZKMSZ"),
	/**
	*电子对账数据估值是否最明细科目默认为1-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_GZSFZMXKM("SV_BB_DZDZ_GZSFZMXKM"),
	/**
	*电子对账数据净值增长率是否取本期净值增值率-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_JZZZL("SV_BB_DZDZ_JZZZL"),
	/**
	*电子对账数据使用本币发送-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_BBFS("SV_BB_DZDZ_BBFS"),
	/**
	*电子对账数据无行情时取单位成本-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_WHQQDWCB("SV_BB_DZDZ_WHQQDWCB"),
	/**
	*电子对账数据溢折价明细科目是否发送-默认值：1(1:是;0：否)
	*/
	SV_BB_DZDZ_YZJMXKM("SV_BB_DZDZ_YZJMXKM"),
	/**
	*电子对账数据溢折价市值列取估值表成本值-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_YZJSZQZ("SV_BB_DZDZ_YZJSZQZ"),
	/**
	*电子对账数据指标项市值占比列为0-默认值：0(1:是;0：否)
	*/
	SV_BB_DZDZ_ZBXSSZB("SV_BB_DZDZ_ZBXSSZB"),
	/**
	*电子对账余额表非明细科目是否发送数量-默认值：1(1:是;0：否)
	*/
	DZ_BB_ELEC_YE_AMOUNT("DZ_BB_ELEC_YE_AMOUNT"),
	/**
	*电子对账余额表实收资本是否需要数量-默认值：1(1:是;0：否)
	*/
	DZ_BB_DZDZ_YEBSSZBZSSL("DZ_BB_DZDZ_YEBSSZBZSSL"),
	/**
	*反向电子对账证券内码是否转换为ISIN码-默认值：0(1:是;0：否)
	*/
	DZ_BB_DZDZ_FXZHISIN("DZ_BB_DZDZ_FXZHISIN"),
	/**
	*估值表非明细科目是否生成数量-默认值：1(1:是;0：否)
	*/
	SV_BB_ELCE_GZ_AMOUNT("SV_BB_ELCE_GZ_AMOUNT"),
	/**
	*可分配收益发送时是否发送净值占市值比例-默认值：1(1:是;0：否)
	*/
	SV_BB_DZDZ_KFPSY("SV_BB_DZDZ_KFPSY");
	
	private String value = "";
	
	private ErDspParamCodeEnum(String value)
	{
		this.value = value;
	}
	@Override
	public String toString() {
		return this.value.toString();
	}

}
