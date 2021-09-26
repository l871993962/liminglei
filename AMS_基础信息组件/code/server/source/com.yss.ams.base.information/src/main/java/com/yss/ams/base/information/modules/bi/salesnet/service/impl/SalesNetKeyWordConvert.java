package com.yss.ams.base.information.modules.bi.salesnet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.salesnet.dao.SalesNetDao;
import com.yss.ams.base.information.modules.bi.salesnet.dao.SalesNetSqlBuilder;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.imp.api.CodeNameInfo;
import com.yss.framework.api.imp.api.IImpKeyWordConvert;

/**
 * 
 * @ClassName SalesNetKeyWordConvert
 * @Description 网点导入通配类  STORY #56105 支持按照销售网点代码导入数据
 * @author lixiang@ysstech.com
 * @CreateDate 2018年6月6日下午2:07:32
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SalesNetKeyWordConvert implements IImpKeyWordConvert{
	
	private SalesNetDao salesNetDao =null;

	@Override
	public List<CodeNameInfo> getCodeNameList() {
		List<CodeNameInfo> list = new ArrayList<CodeNameInfo>();
		salesNetDao = new SalesNetDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SalesNetSqlBuilder());
		list = salesNetDao.getSaleNetCodeNameInfo();
		return list;
	}

}
