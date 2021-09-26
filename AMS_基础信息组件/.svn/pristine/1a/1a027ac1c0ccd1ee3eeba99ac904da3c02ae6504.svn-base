package com.yss.ifa.szt.tool.para.service.impl;

import java.util.List;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.para.dao.MrInfoDao;
import com.yss.ifa.szt.tool.para.dao.MrInfoSqlBuilder;
import com.yss.ifa.szt.tool.para.service.IMrInfoService;
import com.yss.ifa.szt.tool.pojo.MrInfo;

public class MrInfoService extends ServiceBus<MrInfoService> implements IMrInfoService{

	private MrInfoDao mrInfoDao = null;
	
	public MrInfoService(){
		mrInfoDao = new MrInfoDao(DbPoolFactory.getInstance().getPool(), new MrInfoSqlBuilder());
		dao = mrInfoDao;
	}

	@Override
	public List<MrInfo> queryAllMrInfos() {
		return mrInfoDao.queryAllMrInfos();
	}

	@Override
	public void updateCheckState(MrInfo info) {
		mrInfoDao.updateCheckState(info);
	}

}
