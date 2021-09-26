package com.yss.uco.elecreco.er.reverse.compare.service;

import com.yss.uco.elecreco.er.reverse.compare.gz.dao.GzDataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.GzDataCompareSqlBuilder;
import com.yss.uco.elecreco.er.reverse.compare.gz.service.GzDataCompareService;
import com.yss.uco.elecreco.er.reverse.compare.ye.dao.YeDataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.ye.dao.YeDataCompareSqlBuilder;
import com.yss.uco.elecreco.er.reverse.compare.ye.service.YeDataCompareService;
import com.yss.framework.db.DbPoolFactory;

public class DataCompareServiceFactory {
	public static final String GZB = "1011";
	public static final String YEB = "1001";
	public static IDataCompareService createService(String fileType)
	{
		if(GZB.equalsIgnoreCase(fileType))
		{
			return new GzDataCompareService(new GzDataCompareDao(DbPoolFactory.getInstance().getPool(), new GzDataCompareSqlBuilder()));
		}else if(YEB.equalsIgnoreCase(fileType))
		{
			return new YeDataCompareService(new YeDataCompareDao(DbPoolFactory.getInstance().getPool(), new YeDataCompareSqlBuilder()));
		}
		return null;
	}
}
