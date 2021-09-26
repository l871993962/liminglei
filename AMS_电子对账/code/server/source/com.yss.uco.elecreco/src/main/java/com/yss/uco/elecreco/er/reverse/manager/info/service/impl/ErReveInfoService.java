package com.yss.uco.elecreco.er.reverse.manager.info.service.impl;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.manager.info.dao.ErReveInfoDao;
import com.yss.uco.elecreco.er.reverse.manager.info.dao.ErReveInfoSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.er.reverse.manager.info.service.IErReveInfoService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

public class ErReveInfoService extends ServiceBus<ErReveInfoService> implements IErReveInfoService {

	private ErReveInfoDao serviceDao = null;
	public ErReveInfoService() throws Exception {
		serviceDao = new ErReveInfoDao(DbPoolFactory.getInstance().getPool(),new ErReveInfoSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public String unSdDzResult(List<ErReveInfo> list) {
		//String result = "fail";
		return serviceDao.unSdDzResult(list);
	}
	@Override
	public String sdDzResult(List<ErReveInfo> list) {
		return serviceDao.sdDzResult(list);
	}
	@Override
	public String editDzResult(List<ErReveInfo> list,String dzResult,String xgsm) {
		return serviceDao.editDzResult(list,dzResult,xgsm);
	}

	
}