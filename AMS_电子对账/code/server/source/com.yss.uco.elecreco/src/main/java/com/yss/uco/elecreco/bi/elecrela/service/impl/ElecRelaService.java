package com.yss.uco.elecreco.bi.elecrela.service.impl;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaDao;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaSqlBuilder;
import com.yss.uco.elecreco.support.service.IElecRelaService;

public class ElecRelaService extends ServiceBus<ElecRelaService> implements
		IElecRelaService {

	private ElecRelaDao serviceDao = null;

	public ElecRelaService() throws Exception {
		serviceDao = new ElecRelaDao(DbPoolFactory.getInstance().getPool(),
				new ElecRelaSqlBuilder());
		dao = serviceDao;
	}
	
	@Override
	public List<BasePojo> getDataList() {
		return serviceDao.getDataList();
	}

	@Override
	public List<BasePojo> getDataListByName(List<String> paraList) {
		// TODO Auto-generated method stub
		return serviceDao.getDataListByName(paraList);
	}
	

	@Override
	public Map<String, String> getZbCodeByKeyCode(String paras) {
		return serviceDao.getZbCodeByKeyCode(paras);
	}
	
}
